import repository.AddressModel.AddressRepo
import repository.CarePlanModel.CarePlanRepo
import repository.ContactPersonModel.ContactPersonRepo
import repository.DailyReportModel.DailyReportRepo
import repository.DemographicModel.DemographicRepo
import repository.DiagnosisModel.DiagnosisRepo
import repository.PatientModel.PatientRepo
import services.impl._
import services._
//import services.impl.{AddressServiceImpl, AddressImpl, PatientServiceImpl}
import scala.slick.driver.MySQLDriver.simple._
/**
 * Created by phakama on 2014/09/23.
 */
val obj : DemographicService = new DemographicServiceImpl
Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>
 // var list = CarePlanRepo
var  list = obj.getPersonDemo(9)
  //println("Diagnosis: " + list)
}