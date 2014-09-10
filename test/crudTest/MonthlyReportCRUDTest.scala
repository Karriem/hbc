package crudTest

import domain.MonthlyReport
import org.scalatest.{FeatureSpec, GivenWhenThen}
import repository.MonthlyReportModel.MonthlyReportRepo

import scala.slick.driver.MySQLDriver.simple._

/**
 * Created by karriem on 9/10/14.
 */
class MonthlyReportCRUDTest extends FeatureSpec with GivenWhenThen {
  feature("Save Monthly Report") {
    info("As a Coodinator")
    info("I want to Set up Tables")
    info("So that I can add their info into the database")

    scenario("Create tables in the database") {
      Given("Given a Connection to the Database through a repository")

      val monthlyReport = TableQuery[MonthlyReportRepo]

      Database.forURL("jdbc:mysql://localhost:3306/mysql", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

        info("Creating a Monthly Report")
        val monthlyRecord = MonthlyReport(12L, 5)
        val mReportID = monthlyReport.returning(monthlyReport.map(_.monthlyReportId)).insert(monthlyRecord)

        def Read(visits: Int, id: Long) = {
          monthlyReport foreach { case (report: MonthlyReport) =>
            if (report.monthlyReportId == id) {
              assert(report.monthlyReportId == id)
            }
          }
        }
        def Update(visits:Int, id:Long) = {
          monthlyReport.filter(_.monthlyReportId === id).map(_.visits).update(visits)
          Read(visits, id)
        }

        def Delete(id:Long) = {

          monthlyReport.filter(_.monthlyReportId === id).delete
        }

        info("Reading Monthly Report")
        Read(12, mReportID)
        info("Updating Monthly Report")
        Update(17, mReportID)
        info("Deleting Monthly Report")
        Delete(mReportID)
      }
    }
  }
}
