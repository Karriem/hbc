package services

/**
 * Created by phakama on 2014/09/18.
 */
import domain.Address
trait AddressService {

    def createAddress(address : Address)
    def getAddressById(id: Long) : Address
    def getAllAddresses(addresses: Address)
}
