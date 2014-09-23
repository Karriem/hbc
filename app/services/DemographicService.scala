package services

import repository.DemographicModel.DemographicRepo

/**
 * Created by phakama on 2014/09/23.
 */
trait DemographicService {
  def getPersonDemo(id : Long) : List[DemographicRepo#TableElementType]
  def getAllDemos() : List[DemographicRepo#TableElementType]

}
