package crudTest

/**
 * Created by tonata on 9/10/14.
 */

import domain.{Role, User, Caregiver}
import org.scalatest.{FeatureSpec, GivenWhenThen}
import repository.CaregiverModel.CaregiverRepo
import repository.RoleModel.RoleRepo
import repository.UserModel.UserRepo

import scala.slick.driver.MySQLDriver.simple._

class roleCRUDTest extends FeatureSpec with GivenWhenThen {

  feature("Save Role") {
    info("I want to Set up Tables")
    info("So that I can Add Data into the MYSQL")

    scenario(" Create Tables in the Database ") {
      Given("Given a Connection to the Database Through a Repository")

      val roleRepo = TableQuery[RoleRepo]
      val userRepo = TableQuery[UserRepo]
      val caregiverRepo = TableQuery[CaregiverRepo]

      Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>

       //(roleRepo.ddl).create
       //(userRepo.ddl).create
       //(caregiverRepo.ddl).create
        info("Creating Role")

        val caregiverRecord = Caregiver(1, "Max", "Crews")
        val careId = caregiverRepo.returning(caregiverRepo.map(_.caregiverId)).insert(caregiverRecord)

        val userRecord = User(1, "root", "pass", Some(careId), Some(0))
        val userID = userRepo.returning(userRepo.map(_.userId)).insert(userRecord)

        val roleRecord = Role(1, "HealthCare Professional", Some(userID))
        val roleId = roleRepo.returning(roleRepo.map(_.roleId)).insert(roleRecord)

        def Read(desc: String, uName: String, id: Long) = {
          roleRepo foreach { case (r: Role) =>
            if (r.roleId == id) {
              assert(r.description == desc)

              userRepo foreach { case (u: User) =>
                if (Option(u.userId) == r.userId) {
                  assert(u.username == uName)
                }
              }
            }
          }
        }

        def Update(newDesc: String, newUsername: String, id: Long) = {
          roleRepo.filter(_.roleId === id).map(_.description).update(newDesc)
          roleRepo foreach { case (r: Role) =>

            if (r.roleId == id) {

              userRepo foreach { case (u: User) =>
                if (Option(u.userId) == r.userId) {
                  val usId = u.userId
                  userRepo.filter(_.userId === usId).map(_.username).update(newUsername)
                }
              }
            }
          }

          Read(newDesc, newUsername, id)
        }

        /*def searchDelete(id: Long) = {
          pat foreach { case (patient: Patient) =>
            println("/////" + id)
            assertResult(true) {

              if (patient.patientId == id) {
                true
              }
              else
                false
            }
          }
        }*/

        def Delete(cId:Long, uId: Long, rId: Long) = {
          caregiverRepo.filter(_.caregiverId === cId ).delete
          userRepo.filter(_.userId === uId).delete
          roleRepo.filter(_.roleId === cId).delete
          //searchDelete(id)
        }

        info("Reading Role")
        Read("HealthCare Professional", "root", roleId)

        info("Updating Role")
        Update("Administration", "admin", roleId)

        info("Deleting Role")
        Delete(careId, userID, roleId)
      }
    }
  }
}
