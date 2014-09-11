package crudTest

/**
 * Created by tonata on 9/10/14.
 */

import domain.{User, Caregiver}
import org.scalatest.{FeatureSpec, GivenWhenThen}
import repository.CaregiverModel.CaregiverRepo
import repository.UserModel.UserRepo

import scala.slick.driver.MySQLDriver.simple._

class UserCRUDTest extends FeatureSpec with GivenWhenThen {

  feature("Save User") {
    info("As Administrator")
    info("I want to Set up Tables")
    info("So that I can Add Data into the MYSQL")

    scenario(" Create Tables in the Database ") {
      Given("Given a Connection to the Database Through a Repository")

      val userRepo = TableQuery[UserRepo]
      val caregiverRepo = TableQuery[CaregiverRepo]

      Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

        //(roleRepo.ddl).create
        // (userRepo.ddl).create

        val caregiverRecord = Caregiver(1, "Max", "Crews")
        val careId = caregiverRepo.returning(caregiverRepo.map(_.caregiverId)).insert(caregiverRecord)

        val userRecord = User(1, "root", "pass", Some(careId), Some(0))
        val userID = userRepo.returning(userRepo.map(_.userId)).insert(userRecord)

        def Read(desc: String, uName: String, id: Long) = {
          userRepo foreach { case (u: User) =>
            if (u.userId == id) {
              assert(u.username == desc)

              caregiverRepo foreach { case (c: Caregiver) =>
                if (Option(c.caregiverId) == u.caregiverId) {
                  assert(c.firstName == uName)
                }
              }
            }
          }
        }

        def Update(newDesc: String, newFirstName: String, id: Long) = {
          userRepo.filter(_.userId === id).map(_.username).update(newDesc)
          userRepo foreach { case (u: User) =>

            if (u.userId == id) {

              caregiverRepo foreach { case (c: Caregiver) =>
                if (Option(c.caregiverId) == u.caregiverId) {
                  val cId = c.caregiverId
                  caregiverRepo.filter(_.caregiverId === cId).map(_.firstName).update(newFirstName)
                }
              }
            }
          }

          Read(newDesc, newFirstName, id)
        }

        def searchDelete(id: Long) : Int = {
          userRepo foreach { case (cr: User) =>
            assertResult(true) {
              userRepo.filter(_.userId === id).exists.run
            }
          }

          return 0;
        }


        def Delete(cId:Long, uId: Long) = {
          caregiverRepo.filter(_.caregiverId === cId ).delete
          userRepo.filter(_.userId === uId).delete
          searchDelete(uId)
        }

        info("Reading User")
        Read("root", "Max", userID)

        info("Updating User")
        Update("normal user", "Tomas", userID)

        info("Deleting User")
        Delete(careId, userID)
      }
    }
  }
}

