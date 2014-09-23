import domain.Diagnosis
import org.joda.time.DateTime
import repository.CarePlanModel.CarePlanRepo
//import repository.DailyReportModel.DailyReportRepo
//import repository.DiagnosisModel.DiagnosisRepo
//import repository.DiseaseModel.DiseaseRepo
//import repository.PatientModel.PatientRepo
//import services.{CoordinatorService, CarePlanService}
//import services.impl.{CoordinatorServiceImpl, CarePlanServiceImpl}

import scala.collection.mutable.ArrayBuffer
import scala.slick.driver.MySQLDriver.simple._
import scala.slick.lifted.TableQuery

//val l = List(1,2,3)
//def sum(list: List[Int]): Int = list.foldLeft(0)((r,c) => r+c)
//val ans = sum(l)

/*val careRepo = TableQuery[CarePlanRepo]
val patRepo = TableQuery[PatientRepo]
val diagnosisRepo = TableQuery[DiagnosisRepo]
val diseaseRepo = TableQuery[DiseaseRepo]
val dailyReportRepo = TableQuery[DailyReportRepo]*/


Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin").withSession { implicit session =>
  //val ob : CarePlanService = new CarePlanServiceImpl
  //val co : CoordinatorService = new CoordinatorServiceImpl
  //val care = CarePlan(1, "Caring for elder", "5/05/2014", "5/05/2014", 1, 1)
  //ob.createPlan(care)
  //ob.getPatient(12)
  //ob.getPlanIssued(12)
  //ob.getVisit(1)
  //val d = Diagnosis(1L, "da" ,"" ,"" ,1L)
  //val d2 = Diagnosis(1L, "db" ,"" ,"" ,1L)
  //val aList = new ArrayBuffer[Diagnosis]
//  aList += d
 // aList += d2

 // println(aList)

  val d = new DateTime().toDate()

  println(d)


 // co.getInstitution(10)
}
