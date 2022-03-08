package za.co.addressing.customeraddressing.service;

import java.util.*;
import za.co.addressing.customeraddressing.model.*;

public interface AddressService
{
	List<Address> getAddresses();

	Address getAddressById(Long Id);

	Address createAddress(Address address, String countryCode, String provinceCode);

	List<Address> getAddressByProvince( Province province);

	List<Address> getAddressByCountry(Country country);

	Map<String,Boolean> deleteAddress(Long id);

	Address updateAddress(Address address, Long id, String countryCode, String provinceCode);

}
