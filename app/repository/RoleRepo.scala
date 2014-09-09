package repository

import domain.Role
import repository.UserModel.UserRepo

import scala.slick.driver.MySQLDriver.simple._

/**
 * Created by karriem on 9/9/14.
 */
object RoleModel {

  class RoleRepo(tag:Tag) extends Table[Role](tag, "ROLE"){

      def roleId = column[Long]("ROLE_ID", O.PrimaryKey, O.AutoInc)
      def description = column[String]("DESCRIPTION")
      def userId = column[Option[Long]]("USER_ID")
      def * = (roleId, description, userId) <> (Role.tupled, Role.unapply)

      def user = foreignKey("USER_FK", userId, TableQuery[UserRepo])(_.userId)
  }

}
