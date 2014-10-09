package services

import domain.Address
import repository.AddressModel.AddressRepo
import repository.PatientModel.PatientRepo

/**
 * Created by phakama on 2014/09/23.
 */
trait AddressService {
  def getCaregiverAddress(id: Long) : Address
  def getCoordinatorAddress(id: Long) : Address
  def getContactPersonAddress(id: Long) : Address
  def getPatientAddress(id: Long) : Address
  def getAllAddresses() : List[AddressRepo#TableElementType]
}
