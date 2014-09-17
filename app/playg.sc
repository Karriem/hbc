import domain.CarePlan
import repository.CarePlanModel.CarePlanRepo
import repository.PatientModel.PatientRepo
import services.CarePlanService
import services.impl.CarePlanServiceImpl
import scala.slick.driver.MySQLDriver.simple._
import scala.slick.lifted.TableQuery

//val l = List(1,2,3)
//def sum(list: List[Int]): Int = list.foldLeft(0)((r,c) => r+c)
//val ans = sum(l)

val careRepo = TableQuery[CarePlanRepo]
val patRepo = TableQuery[PatientRepo]

Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>
  println("1")
  var list : List[String] = List()
  val ob : CarePlanService = new CarePlanServiceImpl
  println("2")
  val care = CarePlan(1, "Caring for elder", "5/05/2014", "5/05/2014", 1, 1)
  println("3")
  ob.createPlan(care)
 var name = ob.getPatient(1)
  println(name)
}
