package crudTest

import domain.{Institution, Referral}
import org.scalatest.{GivenWhenThen, FeatureSpec}
import repository.InstituteModel.InstitutionRepo
import repository.ReferralModel.ReferralRepo

import scala.slick.driver.MySQLDriver.simple._
import scala.slick.lifted.TableQuery

/**
 * Created by tonata on 2014/09/11.
 */
class referrelCRUDTest extends FeatureSpec with GivenWhenThen{
  feature("Save Referral") {
    info("As a Coodinator")
    info("I want to Set up Tables")
    info("So that I can add their info into the database")

    scenario("Create tables in the database") {
      Given("Given a Connection to the Database through a repository")

      val instituteRepo  = TableQuery[InstitutionRepo]
      val referalRepo = TableQuery[ReferralRepo]

      Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>
        //(instituteRepo.ddl).create
        //(referalRepo.ddl).create

        info("Creating a Referral")

        val refRecord = Referral(1, "2014/05/23", Some(0))
        val refID = referalRepo.returning(referalRepo.map(_.referralId)).insert(refRecord)

        val instituteRecord = Institution (1, "Hospital", "Grabouw Hospital", Some(0), refID)
        val institueId = instituteRepo.returning (instituteRepo.map (_.instituteId) ).insert (instituteRecord)

        def Read(referrelDate: String, id: Long) = {
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

        def Update(referrelDate: String, id:Long) = {
          referalRepo.filter(_.referralId === id).map(_.referralDate).update(referrelDate)
          Read(referrelDate, id)
        }

        def Delete(id:Long) = {
          referalRepo.filter(_.referralId === id).delete
        }

        info("Reading Referral")
        Read("2014/05/23", refID)
        info("Updating Referral")
        Update("2014/07/23", refID)
        info("Deleting Referral")
        Delete(refID)

      }
    }
  }

}
