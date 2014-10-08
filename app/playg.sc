import domain.{Adherence, CarePlan, Patient, Diagnosis}
import services.PatientService
import services.impl.PatientServiceImpl
import scala.slick.driver.MySQLDriver.simple._
val obj : PatientService = new PatientServiceImpl
Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>
  val adherence = Adherence("Laxatives", "take 1, 3 times a day", 110)
  val test = obj.createAdherence(adherence)
}

