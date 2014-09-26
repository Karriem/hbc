package crudTest

import java.util.Date

import domain.{Coordinator, Institution, Referral}
import org.joda.time.DateTime
import org.scalatest.{GivenWhenThen, FeatureSpec}
import repository.CoordinatorModel.CoordinatorRepo
import repository.InstituteModel.InstitutionRepo
import repository.ReferralModel.ReferralRepo

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

      Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>
        //(instituteRepo.ddl).create
        //(referalRepo.ddl).create

        info("Creating a Referral")
        val refDate = DateTime.parse("2014-05-23")
        val updatedRefDate = DateTime.parse("2014-07-23")

        val coordinatorRecord = Coordinator(1, "Phakama", "Ntwsehula")
        val coId = coordinatorRepo.returning (coordinatorRepo.map (_.coId) ).insert (coordinatorRecord)

        val refRecord = Referral(1, refDate.toDate, Some(0))
        val refID = referalRepo.returning(referalRepo.map(_.referralId)).insert(refRecord)

        val instituteRecord = Institution (1, "Hospital", "Grabouw Hospital", Some(coId), refID)
        val institueId = instituteRepo.returning (instituteRepo.map (_.instituteId) ).insert (instituteRecord)

        def Read(referrelDate: Date, id: Long) = {
          referalRepo foreach { case (r: Referral) =>
            if (r.referralId == id) {
              assert(r.referralDate == referrelDate )

              instituteRepo foreach  { case (institute: Institution) =>
                if(institute.referralId == id) {
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
        Read(refDate.toDate, refID)
        info("Updating Referral")
        Update(updatedRefDate.toDate, refID)
        info("Deleting Referral")
        Delete(refID)

      }
    }
  }

}
