package za.co.addressing.customeraddressing.controller;

import java.util.*;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.commons.logging.Logger;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.*;
import org.springframework.http.*;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import za.co.addressing.customeraddressing.controller.CountryController;
import za.co.addressing.customeraddressing.model.Country;
import za.co.addressing.customeraddressing.repository.CountryRepository;
import za.co.addressing.customeraddressing.service.*;
import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith( MockitoExtension.class )
public class CountryControllerTest
{
	@InjectMocks
	private CountryService countryService;

	@Mock
	private CountryRepository countryRepository;

/*	@Configuration
	static class Config {
		@Bean
		public CountryService countryService(){
			return new CountryServiceImpl();
		}
	}*/

	Country mockCountry = new Country("ZA","South Africa");

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

		when(countryRepository.findAll()).thenReturn( list );

		List<Country> countryList = countryService.getCountries();
		assertEquals( 4,countryList.size() );
		verify( countryService, times( 1 ) ).getCountries();
		var found = countryList.stream().filter(ctr -> "ZA".equals( ctr.getCode()) && "South Africa".equals(ctr.getName())).count() > 0;
		assertThat(found).isTrue();
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




