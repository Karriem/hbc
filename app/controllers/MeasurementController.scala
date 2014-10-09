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

  /*def createMeasurement(measurement: String,
                         patientID: Long,
                         caregiverID: Long) = Action.async(parse.json){
    request =>

      val input = request.body
      val measure = Json.fromJson[MeasurementModel](input).get
      val measurementDom = measure.getDomain()
      val mObj = measurementDom.copy(measurementID = measurement.toLong)

      val results : Future[Long] = Future{measurementServ.createMeasurement(mObj, patientID, caregiverID)}


      results.map(result => Ok(Json.toJson(mObj)))
  }*/

 def getMeasurements(id: Long) = Action{
    val measurementsList = measurementServ.getMeasurements(id)
    val json = Json.toJson(measurementsList)
    Ok(json)
  }
}
