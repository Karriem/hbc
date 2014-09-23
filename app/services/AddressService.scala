package services

import domain.Address
import repository.AddressModel.AddressRepo
import repository.PatientModel.PatientRepo

/**
 * Created by phakama on 2014/09/23.
 */
trait AddressService {
  def getAddressById(id: Long) : List[AddressRepo#TableElementType]// : List[AddressRepo#TableElementType]
  def getAllAddresses() : List[AddressRepo#TableElementType]
}
