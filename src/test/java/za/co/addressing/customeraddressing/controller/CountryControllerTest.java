package za.co.addressing.customeraddressing.controller;

import java.util.*;
import org.junit.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.*;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import za.co.addressing.customeraddressing.controller.CountryController;
import za.co.addressing.customeraddressing.model.Country;
import za.co.addressing.customeraddressing.service.CountryService;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith( SpringRunner.class )
@WebMvcTest(value= CountryController.class )
public class CountryControllerTest
{
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CountryService countryService;

	Country mockCountry = new Country("ZA","South Africa");
	String resultCountryJson = "{\"code\":\"ZA\",\"name\":\"South Africa\"}";

	//passed
	@Test
	public void getAllCountriesTest(){
		List<Country> list = new ArrayList<>();
		Country c1 = new Country("ZA","South Africa");
		Country c2 = new Country("AU","Australia");
		Country c3 = new Country("BW","Botswana");
		Country c4 = new Country("ZW","Zimbabwe");

		list.add( c1 );
		list.add( c2 );
		list.add( c3 );
		list.add( c4 );

		when(countryService.getCountries()).thenReturn( list );

		List<Country> countryList = countryService.getCountries();
		assertEquals( 4,countryList.size() );
		verify( countryService, times( 1 ) ).getCountries();
	}

	//passed
	@Test
	public void getCountrybyCodeTest(){
		when(countryService.findByCountryCode( "ZA" )).thenReturn( Optional.of(mockCountry) );
		Country country = Optional.of(countryService.findByCountryCode( "ZA" )).orElseThrow().get();
		assertEquals( "South Africa",country.getName() );
		assertEquals( "ZA",country.getCode() );

	}

	@Test
	public void createCountryTest(){
		Country country = new Country("ZA","South Africa");
		countryService.createCountry( country );
		verify(countryService, times( 1)).createCountry( country );
	}

}




