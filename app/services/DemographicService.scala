package services

import domain.Demographic
import repository.DemographicModel.DemographicRepo

/**
 * Created by phakama on 2014/09/23.
 */
trait DemographicService {
  def getPersonDemo(id : Long) : Demographic//List[DemographicRepo#TableElementType]
  def getAllDemos() : List[DemographicRepo#TableElementType]

}
