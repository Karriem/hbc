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

    Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

      //(monthlyReport.ddl).create
      //(dailyReport.ddl).create
      //(caregiver.ddl).create
      //(patient.ddl).create

      val monthlyRecord = MonthlyReport(1, 10)
      val mReportID = monthlyReport.returning(monthlyReport.map(_.monthlyReportId)).insert(monthlyRecord)

      val caregiverRecord = Caregiver(1, "Nobu", "Tyokozo")
      val careID = caregiver.returning(caregiver.map(_.caregiverId)).insert(caregiverRecord)

      val patientRecord = Patient(1, "25/12/2014", "25/12/2014", "Phakama", "Ntshewula")
      val patID = patient.returning(patient.map(_.patientId)).insert(patientRecord)

      info("Creating Daily Report")
      val dreportRecord = DailyReport(1, "Cleaned the patient", mReportID, careID, patID)
      val dReportID = dailyReport.returning(dailyReport.map(_.dailyReportId)).insert(dreportRecord)

      def Read(patientName:String, id:Long) = {
        dailyReport foreach { case (report: DailyReport) =>
          if (report.dailyReportId == id) {
            patient foreach { case (pat: Patient) =>
              if (pat.patientId == report.patientId) {
                assert(pat.firstName == patientName)
              }
            }

            monthlyReport foreach { case (monthly: MonthlyReport) =>
              if(monthly.monthlyReportId == report.monthlyReportId)
                assert(monthly.visits == 10)
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

      def Delete(id:Long) = {
        dailyReport.filter(_.dailyReportId === id).delete
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