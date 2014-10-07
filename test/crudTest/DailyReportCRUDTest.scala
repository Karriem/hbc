package crudTest

import domain._
import org.joda.time.DateTime
import org.scalatest.{GivenWhenThen, FeatureSpec}
import repository.CaregiverModel.CaregiverRepo
import repository.DailyReportModel.DailyReportRepo
import repository.MonthlyReportModel.MonthlyReportRepo
import repository.PatientModel.PatientRepo
import repository.WeeklyReportModel.WeeklyReportRepo
import scala.slick.driver.MySQLDriver.simple._
import scala.slick.lifted.TableQuery

/**
 * Created by karriem on 9/10/14.
 */
class DailyReportCRUDTest extends FeatureSpec with GivenWhenThen {

feature("Save Daily Report") {
  info("As a Caregiver")
  info("I want to Set up Tables")
  info("So that I can Add Data into the MYSQL")

  scenario("Create Tables in the Database ") {
    Given("Given a Connection to the Database Through a Repository")

    val dailyReport = TableQuery[DailyReportRepo]
    val weeklyReport = TableQuery[WeeklyReportRepo]
    val caregiver = TableQuery[CaregiverRepo]
    val patient = TableQuery[PatientRepo]

    Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      //(weeklyReport.ddl).create
      //(dailyReport.ddl).create
      //(caregiver.ddl).create
     // (patient.ddl).create

      val wSDate = new DateTime(2014, 3, 12, 0, 0)
      val wEDate = new DateTime(2014, 3, 19, 0, 0)

      val weeklyRecord = WeeklyReport(1L, wSDate.toDate, wEDate.toDate, "No transfer", 3, Some(1L))
      val wReportID = weeklyReport.returning(weeklyReport.map(_.weeklyReportId)).insert(weeklyRecord)

      val caregiverRecord = Caregiver(1, "Nobu", "Tyokozo")
      val careID = caregiver.returning(caregiver.map(_.caregiverId)).insert(caregiverRecord)

      val patientRecord = Patient(1, DateTime.parse("2014-12-25").toDate, DateTime.parse("2014-12-25").toDate, "Phakama", "Ntshewula")
      val patID = patient.returning(patient.map(_.patientId)).insert(patientRecord)

      info("Creating Daily Report")
      val dreportRecord = DailyReport(1, "Cleaned the patient", Option(wReportID), careID, patID)
      val dReportID = dailyReport.returning(dailyReport.map(_.dailyReportId)).insert(dreportRecord)

      def Read(patientName:String, id:Long) = {
        dailyReport foreach { case (report: DailyReport) =>
          if (report.dailyReportId == id) {
            patient foreach { case (pat: Patient) =>
              if (pat.patientId == report.patientId) {
                assert(pat.firstName == patientName)
              }
            }

            weeklyReport foreach { case (weekly: WeeklyReport) =>
              if(Option(weekly.weeklyReportId) == report.weeklyReportId)
                assert(weekly.visits == 3)
            }
          }
        }

      }
      def Update(services:String, id:Long) = {
        dailyReport.filter(_.dailyReportId === id).map(_.servicesRendered).update(services)
        dailyReport foreach { case (report: DailyReport) =>
            if(report.dailyReportId == id){
              assert(report.servicesRendered == services)
            }
        }

      }

      def searchDelete(id: Long) : Int = {
        dailyReport foreach { case (cr: DailyReport) =>
          assertResult(false) {
            dailyReport.filter(_.dailyReportId === id).exists.run
          }
        }

        return 0;
      }

      def Delete(id:Long) = {
        dailyReport.filter(_.dailyReportId === id).delete
        searchDelete(id)
      }
      info("Reading Daily Report")
      Read("Phakama", dReportID)

      info("Updating Daily Report")
      Update("Cooking for the patient", dReportID)

      info("Deleting Daily Report")
      Delete(dReportID)
    }
  }
  }
}