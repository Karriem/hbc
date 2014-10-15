package crudTest


import domain._
import org.joda.time.DateTime
import org.scalatest.{FeatureSpec, GivenWhenThen}
import repository.AddressModel.AddressRepo
import repository.ContactModel.ContactRepo
import repository.ContactPersonModel.ContactPersonRepo
import repository.CoordinatorModel.CoordinatorRepo
import repository.InstituteModel.InstitutionRepo
import repository.ReferralModel.ReferralRepo

import scala.slick.driver.MySQLDriver.simple._
import scala.slick.lifted.TableQuery

/**
 * Created by tonata on 9/10/14.
 */
class InstitutionCRUDTest extends FeatureSpec with GivenWhenThen {

  feature("Save Institution") {
    info("I want to Set up Tables")
    info("So that I can Add Data into the MYSQL")

    scenario(" Create Tables in the Database ") {
      Given("Given a Connection to the Database Through a Repository")

      val instituteRepo = TableQuery[InstitutionRepo]
      val coordinatorRepo = TableQuery[CoordinatorRepo]
      val referrallRepo = TableQuery[ReferralRepo]
      val contRepo = TableQuery[ContactRepo]
      val addressRepo = TableQuery[AddressRepo]

      Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>
        info("Creating Institution")

        //(instituteRepo.ddl).create
        //(coordinatorRepo.ddl).create
        //(referrallRepo.ddl).create

        val refDate = DateTime.parse("2013-12-30")

        val coordinatorRecord = Coordinator(1, "Phakama", "Ntwsehula")
        val coId = coordinatorRepo.returning (coordinatorRepo.map (_.coId) ).insert (coordinatorRecord)

        val referralRecord = Referral(1, refDate.toDate , None)
        val referalId = referrallRepo.returning (referrallRepo.map (_.referralId) ).insert (referralRecord)

        val instituteRecord = Institution (1, "Hospital", "Grabouw Hospital", Some(coId), referalId)
        val institueId = instituteRepo.returning (instituteRepo.map (_.instituteId) ).insert (instituteRecord)

        val addressRecord = Address("78 Grabouw", "78 Grabouw", "8000", None, Some(institueId), None, None, None, None)
        addressRepo.insert(addressRecord)

        val contact = Contact(Some("0214578"), "08245789", "kp@grabowhosp.com", None, Some(institueId), None, None, None, None)
        contRepo.insert(contact)

        def Read(instName: String, coName: String,  id : Long) =
          instituteRepo foreach { case (ins : Institution) =>
            if (ins.instituteId == id){
              assert(ins.instituteName == instName)

              coordinatorRepo foreach {case (co: Coordinator) =>
                  if(Option(co.coId) == ins.coordinatorId){
                     assert(co.firstName == coName)
                  }
              }
            }
          }

        def Update(newInstName:String, newCoName: String,  id:Long) = {
          instituteRepo.filter(_.instituteId === id).map(_.instituteName ).update(newInstName)
          instituteRepo foreach {case (ins: Institution) =>

              if(ins.instituteId == id){

                coordinatorRepo foreach { case (co: Coordinator) =>
                  if (Option(co.coId) == ins.coordinatorId) {
                    val coid = co.coId
                    coordinatorRepo.filter(_.coId === coid).map(_.firstName ).update(newCoName)
                  }
                }
              }
          }

          Read(newInstName, newCoName, id )
        }

        def searchDelete(id: Long) : Int = {
          instituteRepo foreach { case (cr: Institution) =>
            assertResult(false) {
              instituteRepo.filter(_.instituteId === id).exists.run
            }
          }

          return 0;
        }


        def Delete(id:Long, coId: Long, refId: Long) = {
          referrallRepo.filter(_.referralId === refId).delete
          instituteRepo.filter(_.instituteId === id).delete
          coordinatorRepo.filter(_.coId=== coId).delete
          searchDelete(id)
        }

        info("Reading Institution")
        Read("Grabouw Hospital", "Phakama", institueId)

        info("Updating Institution")
        Update("Parow Hospital", "Maggie", institueId )

        info("Deleting Institution")
        Delete(institueId, coId, referalId)
      }
    }
  }
}
