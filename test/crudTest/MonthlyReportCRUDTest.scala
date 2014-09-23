package crudTest

import domain.{Referral, MonthlyReport}
import org.scalatest.{FeatureSpec, GivenWhenThen}
import repository.MonthlyReportModel.MonthlyReportRepo
import repository.ReferralModel.ReferralRepo

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
      val referalRepo = TableQuery[ReferralRepo]

      Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>


        //(monthlyReport.ddl).create
        //(referalRepo.ddl).create

        info("Creating a Monthly Report")
        val monthlyRecord = MonthlyReport(1,"2014/03/1", 5)
        val mReportID = monthlyReport.returning(monthlyReport.map(_.monthlyReportId)).insert(monthlyRecord)

        val refRecord = Referral(1, "2014/05/23", Some(mReportID))
        val refID = referalRepo.returning(referalRepo.map(_.referralId)).insert(refRecord)

        def Read(visits: Int, id: Long) = {
          monthlyReport foreach { case (report: MonthlyReport) =>
            if (report.monthlyReportId == id) {
              assert(report.visits == visits)

              referalRepo foreach{case (ref: Referral) =>
                  if(ref.monthlyReportId == Option(id)){
                    assert(ref.referralDate == "2014/05/23")
                  }
              }
            }
          }
        }

        def Update(visits:Int, id:Long) = {
          monthlyReport.filter(_.monthlyReportId === id).map(_.visits).update(visits)
          Read(visits, id)
        }

        def searchDelete(id: Long) : Int = {
          monthlyReport foreach { case (cr: MonthlyReport) =>
            assertResult(false) {
              monthlyReport.filter(_.monthlyReportId === id).exists.run
            }
          }

          return 0;
        }


        def Delete(id:Long) = {
          monthlyReport.filter(_.monthlyReportId === id).delete
          searchDelete(id)
        }

        info("Reading Monthly Report")
        Read(5, mReportID)
        info("Updating Monthly Report")
        Update(17, mReportID)
        info("Deleting Monthly Report")
        Delete(mReportID)
      }
    }
  }
}
