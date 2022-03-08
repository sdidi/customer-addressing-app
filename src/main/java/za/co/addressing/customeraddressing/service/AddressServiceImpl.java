package za.co.addressing.customeraddressing.service;


import java.util.*;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.addressing.customeraddressing.model.*;
import za.co.addressing.customeraddressing.repository.*;

@Service
public class AddressServiceImpl implements   AddressService
{

	private final static Logger log = Logger.getLogger(Address.class.getName());
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private CountryRepository countryRepository;
	@Autowired
	private  ProvinceRepository provinceRepository;

	@Override
	public List<Address> getAddresses()
	{
		List<Address> addressList = addressRepository.findAll();

		for(Address address : addressList){
			String countryCode = address.getProvince().getCountryCode();
			log.info( "Country code at the top is : "+countryCode );
			Country country = countryRepository.findCountryByCode( countryCode);
			log.info( "Country is : "+country );
			address.setCountry(country );

		}
		//address.findAll().forEach(add -> add.setCountry(countryRepository.findByCountryCode(add.getCountryCode))
		return addressList;
	}

	@Override
	public Address getAddressById( Long id )
	{
		return addressRepository.findById( id ).orElseThrow(RuntimeException::new);
	}

	@Override
	public Address createAddress( Address address, String countryCode, String provinceCode )
	{
		/*Province province2 = provinceRepository.getById( new ProvinceId(countryCode, provinceCode) );
		log.info("Province data "+province2);*/
		Province province = provinceRepository.findByCountryCodeAndProvinceCode(countryCode, provinceCode );
		Country country = countryRepository.findCountryByCode( countryCode );
		log.info("Province data "+province);
		address.setProvince( province);
		//address.setCountry( country );
		return addressRepository.save( address );
	}


	@Override
	public Map<String,Boolean> deleteAddress(Long id )
	{
		Address address = addressRepository.findById( id )
				.orElseThrow(() -> new NoSuchElementException("Address does not exist with id: "+id  ));
		addressRepository.delete( address );
		//addressRepository.deleteById( id );
		Map<String, Boolean> response = new HashMap<>();
		response.put( "delete",Boolean.TRUE );
		return response;
	}

	@Override
	public Address updateAddress( Address tempAddress, Long id, String countryCode, String provinceCode )
	{
		Province province = provinceRepository.findByCountryCodeAndProvinceCode(countryCode, provinceCode );
		Country country = countryRepository.findCountryByCode( countryCode );
		tempAddress.setProvince( province );
		tempAddress.setCountry( country );
		Address currentAddress = addressRepository.findById( id )
				.orElseThrow(() -> new NoSuchElementException("Address does not exist with id: "+id  ));
		currentAddress.setLine1( tempAddress.getLine1() );
		currentAddress.setLine2( tempAddress.getLine2() );
		currentAddress.setSuburb( tempAddress.getSuburb() );
		currentAddress.setPostalCode( tempAddress.getPostalCode() );
		currentAddress.setCity( tempAddress.getCity() );
		currentAddress.setProvince( tempAddress.getProvince() );
		currentAddress.setCountry( tempAddress.getCountry() );
		return addressRepository.save( currentAddress);
	}

	@Override
	public List<Address> getAddressByProvince( Province province )
	{
		return null;
	}

	@Override
	public List<Address> getAddressByCountry( Country country )
	{
		return null;
	}

}
