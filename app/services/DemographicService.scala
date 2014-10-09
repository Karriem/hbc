package services

import domain.Demographic
import repository.DemographicModel.DemographicRepo

/**
 * Created by phakama on 2014/09/23.
 */
trait DemographicService {
  def getPersonDemo(id : Long) : Demographic//List[DemographicRepo#TableElementType]
  def getPatientDemo(id : Long) : Demographic
  def getCaregiverDemo(id : Long) : Demographic
  def getCoordinatorDemo(id : Long) : Demographic
  def getAllDemos() : List[DemographicRepo#TableElementType]

}
