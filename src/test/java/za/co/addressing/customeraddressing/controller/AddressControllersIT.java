package za.co.addressing.customeraddressing.controller;

import java.util.Map;
import java.util.logging.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import za.co.addressing.customeraddressing.CustomerAddressingApplication;
import za.co.addressing.customeraddressing.model.*;
import za.co.addressing.customeraddressing.service.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = CustomerAddressingApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AddressControllersIT
{
	private final static Logger log = Logger.getLogger(AddressControllersIT.class.getName() );
	private final HttpHeaders httpHeaders = new HttpHeaders();

	@Autowired
	private TestRestTemplate testRestTemplate;
	@Autowired
	AddressService addressService;
	@Autowired
	ProvinceService provinceService;
	@Autowired
	CountryService countryService;

	@LocalServerPort
	private int port;

	@Test
	public void givenCorrectAddressIdCheckIfCorrectDetailsAreRetrieved(){
		int id = 3;
		HttpEntity<Address> entity = new HttpEntity<>(null, httpHeaders);
		ResponseEntity<Address> addressResponseEntity;
		addressResponseEntity = testRestTemplate.exchange(createURLWithPort("/addresses/"+id),
				HttpMethod.GET,
				entity,
				Address.class);
		String expectedSuburb = "Gaborone West";
		assertNotNull(addressResponseEntity);
		assertEquals(expectedSuburb,addressResponseEntity.getBody().getSuburb());
	}

	@Test
	public void givenInCorrectAddressIdCheckIfCorrectDetailsAreRetrieved(){
		int id = 4;
		HttpEntity<String> entity = new HttpEntity<>(null, httpHeaders);
		ResponseEntity<Address> addressResponseEntity;
		addressResponseEntity = testRestTemplate.exchange(createURLWithPort("/addresses/"+id),
				HttpMethod.GET,
				entity,
				Address.class);
		String expectedSurburb = "Gaborone West";
		assertNotEquals(expectedSurburb,addressResponseEntity.getBody().getSuburb());
	}

	@Test
	public void givenCorrectAddressDeatilsCheckIfCorrectDetailsAreCreated(){
		Address address = new Address();
		address.setLine1( "Unit 15" );
		address.setLine2( "Mews Street" );
		address.setSuburb( "Mowbray" );
		address.setCity( "Cape Town " );
		Country country = countryService.findByCountryCode( "ZA" ).get();
		Province province = provinceService.findByCountryCodeAndProvinceCode( "ZA","WC" );
		address.setProvince( province );
		address.setCountry( country );
		HttpEntity<Address> entity = new HttpEntity<>(address, httpHeaders);
		ResponseEntity<String> addressResponseEntity;
		addressResponseEntity = testRestTemplate.exchange(createURLWithPort("/createaddresses"),
				HttpMethod.POST,
				entity,
				String.class);
		String expectedSurburb = "Mowbray";
		assertNotNull(addressResponseEntity);
		assertNotNull(addressResponseEntity.getBody());
		//assertEquals(expectedSurburb,addressResponseEntity.getBody());
	}


	private String createURLWithPort(String uri){
		return "http://localhost:" + port + "/addressapi" + uri ;
	}
}
