package repository

import domain.User
import repository.CaregiverModel.CaregiverRepo
import repository.CoordinatorModel.CoordinatorRepo

import scala.slick.driver.MySQLDriver.simple._

/**
 * Created by karriem on 9/9/14.
 */
object UserModel {

  class UserRepo(tag:Tag) extends Table[User](tag, "USER"){

      def userId = column[Long]("USER_ID", O.PrimaryKey, O.AutoInc)
      def username = column[String]("USER_NAME")
      def password = column[String]("PASSWORD")
      def caregiverId = column[Option[Long]]("CAREGIVER_ID")
      def coordinatorId = column[Option[Long]]("COORDINATOR_ID")
      def * = (userId, username, password, caregiverId, coordinatorId) <> (User.tupled, User.unapply)

      val caregiver = foreignKey("CAREGIVER_FK", caregiverId, TableQuery[CaregiverRepo])(_.caregiverId)
      val coordinator = foreignKey("COORDINATOR_FK", coordinatorId, TableQuery[CoordinatorRepo])(_.coId)
  }

}
