package controllers

import domain.MedicalSummary
import model.MeasurementModel
import play.api.libs.json._
import play.api.mvc.{Action, Controller}
import services.MedicalSummaryService
import services.impl.MedicalSummaryServiceImpl

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
 * Created by tonata on 10/8/14.
 */
object MedicalSummaryController extends Controller{

  val measurementServ: MedicalSummaryService = new MedicalSummaryServiceImpl()

  implicit val measurementWrites = Json.writes[MedicalSummary]

  def createMeasurement(measurement: String) = Action.async(parse.json){
    request =>

      val input = request.body
      val o = (input \ "object").as[String]
      val pid = (input \ "patid").as[Long]
      val cid = (input \ "careid").as[Long]
      val json = Json.parse(o)

      val measure = Json.fromJson[MeasurementModel](json).get
      val measurementDom = measure.getDomain()

      val mObj = MedicalSummary(measurementDom.measurementID, measurementDom.dateTaken,
        measurementDom.weight, measurementDom.bloodPressure, measurementDom.temperature, pid, cid)

      val results : Future[Long] = Future{measurementServ.createMeasurement(mObj)}

      results.map(res =>
        Ok(Json.toJson(res)))
  }

 def getMeasurements(id: Long) = Action{
    val measurementsList = measurementServ.getMeasurements(id)
    val json = Json.toJson(measurementsList)
    Ok(json)
  }
}
