import domain.{CarePlan, User, Patient}
import repository.CarePlanModel.CarePlanRepo
import repository.PatientModel.PatientRepo
import services.{CaregiverService, CoordinatorService, CarePlanService}
import services.impl.{CaregiverServiceImpl, CoordinatorServiceImpl, CarePlanServiceImpl}

import scala.slick.driver.MySQLDriver.simple._
import scala.slick.lifted.TableQuery

//val l = List(1,2,3)
//def sum(list: List[Int]): Int = list.foldLeft(0)((r,c) => r+c)
//val ans = sum(l)

val careRepo = TableQuery[CarePlanRepo]
val patRepo = TableQuery[PatientRepo]

Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>
  val ob : CarePlanService = new CarePlanServiceImpl
  val co : CoordinatorService = new CoordinatorServiceImpl
  val gi : CaregiverService = new CaregiverServiceImpl
  val care = CarePlan(1, "Providing healthcare", "5/05/2014", "5/05/2014", 10, 10)
  //ob.createPlan(care)
  //ob.getPatient(12)
  //ob.getPlanIssued(12)
  //ob.getVisit(1)
  val user = User(1, "Sasuke", "Uchiha", None, Some(13))
  //co.createUser(user)
  //co.getInstitution(10)
  //co.getUser(12)
  //co.viewPatients(5)
  //co.viewAllPatient()
  //co.createCarePlan(care)
  val pat = Patient(1, "2014/5/5", "2014/5/6", "Inoue", "Orihime")
  var list : List[CarePlanRepo#TableElementType] = List()
  var listP : List[PatientRepo#TableElementType] = List()
  list = gi.getCareplan(10)
  println("Patient Description: " +list.head.description)
  listP = gi.getPatientDetails(5)
  println("Patient Name: " +listP.head.firstName)
  //gi.addPatient(pat)
}
