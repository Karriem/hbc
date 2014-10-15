package controllers

import domain.Measurement
import model.MeasurementModel
import play.api.libs.json._
import play.api.mvc.{Action, Controller}
import services.{MeasurementService, QuestionAnswerService}
import services.impl.{MeasurementServiceImpl, QuestionAnswerServiceImpl}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
 * Created by tonata on 10/8/14.
 */
object MeasurementController extends Controller{

  val measurementServ: MeasurementService = new MeasurementServiceImpl()

  implicit val measurementWrites = Json.writes[Measurement]

  def createMeasuremen(measurement: String) = Action.async(parse.json){
    request =>

      //,
      //patientID: String,
      //caregiverID: String

      /*val measurement = param{0}
      val patientID = param{1}
      val caregiverID = param{2}*/

      val input = request.body
      //println("Body", input)
      val measure = Json.fromJson[MeasurementModel](input).get
      val measurementDom = measure.getDomain()
      //val mObj = measurementDom.copy(measurement = measurement)
      val results : Future[Long] = Future{measurementServ.createMeasurement(measurementDom)}

      results.map(res =>
        Ok(Json.toJson(res)))
  }

 def getMeasurements(id: Long) = Action{
    val measurementsList = measurementServ.getMeasurements(id)
    val json = Json.toJson(measurementsList)
    Ok(json)
  }
}
