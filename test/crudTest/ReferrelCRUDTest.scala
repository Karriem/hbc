package crudTest

import java.util.Date

import domain._
import org.joda.time.DateTime
import org.scalatest.{GivenWhenThen, FeatureSpec}
import repository.CaregiverModel.CaregiverRepo
import repository.CoordinatorModel.CoordinatorRepo
import repository.InstituteModel.InstitutionRepo
import repository.MedicalSummaryModel.MedicalSummaryRepo
import repository.PatientModel.PatientRepo
import repository.ReferralModel.ReferralRepo
import repository.WeeklyReportModel.WeeklyReportRepo

import scala.slick.driver.MySQLDriver.simple._
import scala.slick.lifted.TableQuery

/**
 * Created by tonata on 2014/09/11.
 */
class ReferrelCRUDTest extends FeatureSpec with GivenWhenThen{
  feature("Save Referral") {
    info("As a Coodinator")
    info("I want to Set up Tables")
    info("So that I can add their info into the database")

    scenario("Create tables in the database") {
      Given("Given a Connection to the Database through a repository")

      val instituteRepo  = TableQuery[InstitutionRepo]
      val referalRepo = TableQuery[ReferralRepo]
      val coordinatorRepo = TableQuery[CoordinatorRepo]
      val weeklyReport = TableQuery[WeeklyReportRepo]
      val pat = TableQuery[PatientRepo]
      val care = TableQuery[CaregiverRepo]
      val measureRepo = TableQuery[MedicalSummaryRepo]

      Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>
        //(instituteRepo.ddl).create
        //(referalRepo.ddl).create

        info("Creating a Referral")

        val patRecord = Patient(7, DateTime.parse("2014-05-20").toDate, DateTime.parse("2014-08-02").toDate, "Chris", "Johnson" ,
          "Tom", "0784559100" , "Christian", "English", "CPR")
        val patID = pat.returning (pat.map (_.patientId) ).insert(patRecord)

        val caregiverRecord = Caregiver(1,  "Nikki", "Shiyagaya")
        val careID = care.returning(care.map(_.caregiverId)).insert(caregiverRecord)

        val refDate = DateTime.parse("2014-05-23")
        val updatedRefDate = DateTime.parse("2014-07-23")
        val wSDate = new DateTime(2014, 3, 12, 0, 0)
        val wEDate = new DateTime(2014, 3, 19, 0, 0)

        val weeklyRecord = WeeklyReport(1L, wSDate.toDate, wEDate.toDate, "No transfer", 3, None)
        val wReportID = weeklyReport.returning(weeklyReport.map(_.weeklyReportId)).insert(weeklyRecord)

        val coordinatorRecord = Coordinator(1, "Phakama", "Ntwsehula")
        val coId = coordinatorRepo.returning (coordinatorRepo.map (_.coId) ).insert (coordinatorRecord)

        val instituteRecord = Institution (1, "Hospital", "Grabouw Hospital", Some(coId))
        val institueId = instituteRepo.returning (instituteRepo.map (_.instituteId) ).insert (instituteRecord)

        val summaryRecord = MedicalSummary(1L, DateTime.parse("2014-02-12").toDate, 65, 12, 25,patID, careID, "Dust Allergy",
          "Final Diag", false, "")
        val mID = measureRepo.returning(measureRepo.map(_.medicalSummaryID)).insert(summaryRecord)

        val refRecord = Referral(1, refDate.toDate, Some(wReportID), patID , mID, "req", coId, institueId)
        val refID = referalRepo.returning(referalRepo.map(_.referralId)).insert(refRecord)

        def Read(referrelDate: Date, id: Long) = {
          referalRepo foreach { case (r: Referral) =>
            if (r.referralId == id) {
              assert(r.referralDate == referrelDate )
              instituteRepo foreach  { case (institute: Institution) =>
                if(institute.instituteId == r.institueID) {
                  assert(institute.instituteName == "Grabouw Hospital")
                }
              }
            }
          }
        }

        def Update(referrelDate: Date, id:Long) = {
          referalRepo.filter(_.referralId === id).map(_.referralDate).update(referrelDate)
          Read(referrelDate, id)
        }

        def searchDelete(id: Long) : Int = {
          referalRepo foreach { case (cr: Referral) =>
            assertResult(false) {
              referalRepo.filter(_.referralId === id).exists.run
            }
          }

          return 0;
        }


        def Delete(id:Long) = {
          referalRepo.filter(_.referralId === id).delete
          searchDelete(id)
        }

        info("Reading Referral")
        //Read(refDate.toDate, refID)
        info("Updating Referral")
        //Update(updatedRefDate.toDate, refID)
        info("Deleting Referral")
        //Delete(refID)

      }
    }
  }

}
