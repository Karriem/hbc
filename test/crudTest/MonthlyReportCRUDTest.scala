package crudTest

import domain.MonthlyReport
import org.joda.time.DateTime
import org.scalatest.{FeatureSpec, GivenWhenThen}
import repository.MonthlyReportModel.MonthlyReportRepo

import scala.slick.driver.MySQLDriver.simple._

/**
 * Created by karriem on 9/10/14.
 */
class MonthlyReportCRUDTest extends FeatureSpec with GivenWhenThen {
  feature("Save Monthly Report") {
    info("As a Coordinator")
    info("I want to Set up Tables")
    info("So that I can add their info into the database")

    scenario("Create tables in the database") {
      Given("Given a Connection to the Database through a repository")

      val monthlyReport = TableQuery[MonthlyReportRepo]

      Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>
        //(monthlyReport.ddl).create

        info("Creating a Monthly Report")
        val refDate = DateTime.parse("2014-05-23")
        val mDate = new DateTime(2014, 12, 1, 0, 0)
        val monthlyRecord = MonthlyReport(4, mDate.toDate, 2, 6, 10, 4, 3)

        val mReportID = monthlyReport.returning(monthlyReport.map(_.monthlyReportId)).insert(monthlyRecord)

        def Read(carers: Int, id: Long) = {
          monthlyReport foreach { case (report: MonthlyReport) =>
            if (report.monthlyReportId == id) {
              assert(report.noOfCarers == carers)

            }
          }
        }


      def Update(carers: Int, id: Long) = {
        monthlyReport.filter(_.monthlyReportId === id).map(_.noOfCarers).update(carers)
        Read(carers, id)
      }

          def searchDelete(id: Long): Int = {
              monthlyReport foreach { case (cr: MonthlyReport) =>
              assertResult(false) {
              monthlyReport.filter(_.monthlyReportId === id).exists.run
            }
          }

          return 0
        }


          def Delete(id: Long) = {
            monthlyReport.filter(_.monthlyReportId === id).delete
            searchDelete(id)
          }

          info("Reading Monthly Report")
          Read(6, mReportID)
          info("Updating Monthly Report")
          Update(10, mReportID)
          info("Deleting Monthly Report")
          Delete(mReportID)
        }
      }
    }
  }

