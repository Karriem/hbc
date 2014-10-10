package controllers

import play.api.mvc._
import repository.AddressModel.AddressRepo
import repository.AdherenceModel.AdherenceRepo
import repository.CarePlanModel.CarePlanRepo
import repository.CaregiverModel.CaregiverRepo
import repository.CategoryModel.CategoryRepo
import repository.ContactModel.ContactRepo
import repository.ContactPersonModel.ContactPersonRepo
import repository.CoordinatorModel.CoordinatorRepo
import repository.DailyReportModel.DailyReportRepo
import repository.DemographicModel.DemographicRepo
import repository.DiagnosisModel.DiagnosisRepo
import repository.DiseaseModel.DiseaseRepo
import repository.InstituteModel.InstitutionRepo
import repository.MeasurementModel.MeasurementRepo
import repository.MonthlyReportModel.MonthlyReportRepo
import repository.PatientModel.PatientRepo
import repository.QuestionAnswerModel.QuestionAnswerRepo
import repository.ReferralModel.ReferralRepo
import repository.RoleModel.RoleRepo
import repository.ScheduleModel.ScheduleRepo
import repository.TimeSheetModel.TimeSheetRepo
import repository.UnplannedVisitModel.UnplannedVisitRepo
import repository.UserModel.UserRepo
import repository.VisitModel.VisitRepo
import repository.WeeklyReportModel.WeeklyReportRepo
import scala.slick.driver.MySQLDriver.simple._

object Application extends Controller {

  val weekRepo = TableQuery[WeeklyReportRepo]
  val visitRepo = TableQuery[VisitRepo]
  val unplannedRepo = TableQuery[UnplannedVisitRepo]
  val timeRepo = TableQuery[TimeSheetRepo]
  val scheRepo = TableQuery[ScheduleRepo]
  val roleRepo = TableQuery[RoleRepo]
  val refRepo = TableQuery[ReferralRepo]
  val qaRepo = TableQuery[QuestionAnswerRepo]
  val monthlyRepo = TableQuery[MonthlyReportRepo]
  val measRepo = TableQuery[MeasurementRepo]
  val disRepo = TableQuery[DiseaseRepo]
  val diagRepo = TableQuery[DiagnosisRepo]
  val dailyRepo = TableQuery[DailyReportRepo]
  val coorRepo = TableQuery[CoordinatorRepo]
  val insRepo = TableQuery[InstitutionRepo]
  val userRepo = TableQuery[UserRepo]
  val patRepo = TableQuery[PatientRepo]
  val givRepo = TableQuery[CaregiverRepo]
  val careRepo = TableQuery[CarePlanRepo]
  val catRepo = TableQuery[CategoryRepo]
  val conRepo = TableQuery[ContactPersonRepo]
  val demoRepo = TableQuery[DemographicRepo]
  val contactRepo = TableQuery[ContactRepo]
  val addressRepo = TableQuery[AddressRepo]
  val adRepo = TableQuery[AdherenceRepo]

  def index = Action {

    Ok(views.html.index("Your new application is ready."))

  }

  val dataCon = Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver", user = "root", password = "admin")

  def dbSetup = Action {

    dataCon.withSession { implicit session =>

      (weekRepo.ddl ++ visitRepo.ddl ++ unplannedRepo.ddl ++ timeRepo.ddl ++ scheRepo.ddl ++ roleRepo.ddl ++ refRepo.ddl ++
        qaRepo.ddl ++ monthlyRepo.ddl ++ measRepo.ddl ++ disRepo.ddl ++ diagRepo.ddl ++ dailyRepo.ddl ++ coorRepo.ddl ++
        insRepo.ddl ++ userRepo.ddl ++ patRepo.ddl ++ givRepo.ddl ++ careRepo.ddl ++ catRepo.ddl ++ conRepo.ddl ++
        demoRepo.ddl ++ contactRepo.ddl ++ addressRepo.ddl ++ adRepo.ddl).create
    }

    Ok("Tables Created")
  }
}