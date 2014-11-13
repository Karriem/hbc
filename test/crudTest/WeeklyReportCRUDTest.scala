package crudTest

import domain._
import org.joda.time.DateTime
import org.scalatest.{GivenWhenThen, FeatureSpec}
import repository.CaregiverModel.CaregiverRepo
import repository.CategoryModel.CategoryRepo
import repository.CoordinatorModel.CoordinatorRepo
import repository.InstituteModel.InstitutionRepo
import repository.MedicalSummaryModel.MedicalSummaryRepo
import repository.MonthlyReportModel.MonthlyReportRepo
import repository.PatientModel.PatientRepo
import repository.ReferralModel.ReferralRepo
import repository.WeeklyReportModel.WeeklyReportRepo
import scala.slick.driver.MySQLDriver.simple._
import scala.slick.lifted.TableQuery

/**
 * Created by tonata on 10/7/14.
 */
class WeeklyReportCRUDTest extends FeatureSpec with GivenWhenThen {
  feature("Save Weekly Report") {
    info("As a Coordinator")
    info("I want to Set up Tables")
    info("So that I can add their info into the database")

    scenario("Create tables in the database") {
      Given("Given a Connection to the Database through a repository")

      val referalRepo = TableQuery[ReferralRepo]
      val catRepo = TableQuery[CategoryRepo]
      val weeklyReport = TableQuery[WeeklyReportRepo]
      val monthlyReportRepo = TableQuery[MonthlyReportRepo]
      val pat = TableQuery[PatientRepo]
      val measureRepo = TableQuery[MedicalSummaryRepo]
      val care = TableQuery[CaregiverRepo]
      val coordinatorRepo= TableQuery[CoordinatorRepo]
      val instituteRepo = TableQuery[InstitutionRepo]

      Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

        //(weeklyReport.ddl).create
        //(referalRepo.ddl).create
        //(catRepo.ddl).create
        //(monthlyReportRepo.ddl).create

        info("Creating a Weekly Report")
        val refDate = DateTime.parse("2014-05-23")
        val wSDate = new DateTime(2014, 3, 12, 0, 0)
        val wEDate = new DateTime(2014, 3, 19, 0, 0)

        val mDate = new DateTime(2014, 12, 1, 0 ,0)
        val monthlyReport = MonthlyReport(4 ,mDate.toDate,  2, 6, 10, 4, 3)
        val mReportID = monthlyReportRepo.returning(monthlyReportRepo.map(_.monthlyReportId)).insert(monthlyReport)

        val patRecord = Patient(7, DateTime.parse("2014-05-20").toDate, DateTime.parse("2014-08-02").toDate, "Chris", "Johnson" ,
          "Tom", "0784559100" , "Christian", "English", "CPR")
        val patID = pat.returning (pat.map (_.patientId) ).insert(patRecord)

        val caregiverRecord = Caregiver(1,  "Nikki", "Shiyagaya")
        val careID = care.returning(care.map(_.caregiverId)).insert(caregiverRecord)

        val coordinatorRecord = Coordinator(1,  "PK", "Shiya")
        val coID = coordinatorRepo.returning(coordinatorRepo.map(_.coId)).insert(coordinatorRecord)

        val summaryRecord = MedicalSummary(1L, DateTime.parse("2014-02-12").toDate, 65, 12, 25,patID, careID, "Dust Allergy",
          "Final Diag", false, "")
        val mID = measureRepo.returning(measureRepo.map(_.medicalSummaryID)).insert(summaryRecord)

        val instituteRecord = Institution (1, "Hospital", "Grabouw Hospital", Some(coID))
        val institueId = instituteRepo.returning (instituteRepo.map (_.instituteId) ).insert (instituteRecord)

        val weeklyRecord = WeeklyReport(1L, wSDate.toDate, wEDate.toDate, "No transfer", 3, Some(mReportID))
        val wReportID = weeklyReport.returning(weeklyReport.map(_.weeklyReportId)).insert(weeklyRecord)

        val refRecord = Referral(1, refDate.toDate, Some(wReportID), patID , mID, "req", coID, institueId)
        val refID = referalRepo.returning(referalRepo.map(_.referralId)).insert(refRecord)

        def Read(visits: Int, id: Long) = {
          weeklyReport foreach { case (report: WeeklyReport) =>
            if (report.weeklyReportId == id) {
              assert(report.visits == visits)

              referalRepo foreach{case (ref: Referral) =>
                if(ref.weeklyReportId == Option(id)){
                  assert(ref.referralDate == refDate.toDate)
                }
              }
            }
          }
        }

        def Update(visits:Int, id:Long) = {
          weeklyReport.filter(_.weeklyReportId === id).map(_.visits).update(visits)
          Read(visits, id)
        }

        def searchDelete(id: Long) : Int = {
          weeklyReport foreach { case (cr: WeeklyReport) =>
            assertResult(false) {
              weeklyReport.filter(_.weeklyReportId === id).exists.run
            }
          }

          return 0;
        }


        def Delete(id:Long) = {
          referalRepo.filter(_.weeklyReportId=== id).delete
          weeklyReport.filter(_.weeklyReportId === id).delete
          searchDelete(id)
        }

        info("Reading Weekly Report")
        Read(3, wReportID)
        info("Updating Weekly Report")
        Update(17, wReportID)
        info("Deleting Weekly Report")
        Delete(wReportID)
      }
    }
  }

}
