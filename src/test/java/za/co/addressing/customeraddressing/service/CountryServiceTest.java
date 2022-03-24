package za.co.addressing.customeraddressing.service;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import za.co.addressing.customeraddressing.model.*;
import za.co.addressing.customeraddressing.repository.CountryRepository;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CountryServiceTest
{
	@Autowired
	CountryService countryService;
	@MockBean
	CountryRepository countryRepository;

	@Test
	public void whenCodeIsProvided_returnCorrectCountry(){
		Country country = new Country("ZA","South Africa");
		doReturn( country).when( countryRepository).findCountryByCode( "ZA" );
		Optional<Country> country1 = countryService.findByCountryCode( "ZA" );
		assertEquals( "South Africa" ,country1.get().getName());
	}

	@Test
	public void testCreatCountry_usingMock(){
		Country country = new Country("ZA","South Africa");
		countryRepository.save( country );
		verify(countryRepository, times( 1)).save( country );
	}


}
