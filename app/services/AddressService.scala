package services

import domain.Address

/**
 * Created by phakama on 2014/09/23.
 */
trait AddressService {
  def getAddressById(id: Long)
  def getAllAddresses(addresses: Address)
}
