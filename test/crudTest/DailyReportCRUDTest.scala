package crudTest

import domain.{Patient, Caregiver, MonthlyReport, DailyReport}
import org.scalatest.{GivenWhenThen, FeatureSpec}
import repository.CaregiverModel.CaregiverRepo
import repository.DailyReportModel.DailyReportRepo
import repository.MonthlyReportModel.MonthlyReportRepo
import repository.PatientModel.PatientRepo
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
    val monthlyReport = TableQuery[MonthlyReportRepo]
    val caregiver = TableQuery[CaregiverRepo]
    val patient = TableQuery[PatientRepo]

    Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      info("Creating Daily Report")

      val dreportRecord = DailyReport(1, "Cleaned the patient", 14, 95, 21)
      val dReportID = dailyReport.returning(dailyReport.map(_.dailyReportId)).insert(dreportRecord)

      val monthlyRecord = MonthlyReport(12L, 5)
      val mReportID = monthlyReport.returning(monthlyReport.map(_.monthlyReportId)).insert(monthlyRecord)
      //monthlyReport.insert(monthly)

      val caregiverRecord = Caregiver(147, "Nobu", "Tyokozo")
    //  val careID = caregiver.returning(caregiver.map(_.caregiverId)).insert(caregiverRecord)

      val patientRecord = Patient(74, "25/12/2014", "25/12/2014", "Phakama", "Ntshewula")
      val patID = patient.returning(patient.map(_.patientId)).insert(patientRecord)

      def Read(services:String, id:Long) = {
        dailyReport foreach { case (report: DailyReport) =>
          if (report.dailyReportId == id) {
            patient foreach { case (pat: Patient) =>
              if (pat.patientId == id) {
                assert(pat.patientId == id)
              }
            }
          }
        }
         monthlyReport foreach { case (monthly: MonthlyReport) =>
          if(monthly.monthlyReportId == mReportID)
            assert(monthly.visits == 5)
        }
      }
      def Update(services:String, id:Long) = {
        dailyReport.filter(_.dailyReportId === id).map(_.servicesRendered).update(services)
        Read(services, id)
      }

      def Delete(id:Long) = {

        monthlyReport.filter(_.monthlyReportId === dReportID).delete
        patient.filter(_.patientId === patID).delete
      }
      info("Reading Daily Report")
      Read("Cleaned the patient", dReportID)
      info("Updating Patient")
      Update("Cooking for the patient", dReportID)
      info("Deleting Patient")
      //Delete(patID)
      Delete(dReportID)
    }
  }
  }
}