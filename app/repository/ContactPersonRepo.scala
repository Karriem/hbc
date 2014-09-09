package repository

import domain.ContactPerson

import scala.slick.driver.MySQLDriver.simple._

/**
 * Created by karriem on 9/8/14.
 */

object ContactPersonModel {

  class ContactPersonRepo(tag:Tag) extends Table[ContactPerson](tag, "CONTACTPERSON"){

      def personId = column[Long]("PERSON_ID", O.PrimaryKey, O.AutoInc)
      def firstName = column[String]("FIRST_NAME")
      def lastName = column[String]("LAST_NAME")
      def * = (personId, firstName, lastName) <> (ContactPerson.tupled, ContactPerson.unapply)
  }

}