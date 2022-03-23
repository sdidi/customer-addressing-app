package za.co.addressing.customeraddressing.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import za.co.addressing.customeraddressing.model.Country;
import za.co.addressing.customeraddressing.repository.CountryRepository;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CountryServiceTest
{
	@MockBean
	CountryRepository countryRepository;

	@Test
	public void testGetCountryByCode_usingMock(){
		//CountryRepository countryRepository = mock(CountryRepository.class);
		when(countryRepository.findCountryByCode( "ZA" )).thenReturn( new Country("ZA","South Africa") );
		Country country = countryRepository.findCountryByCode( "ZA" );
		assertEquals("South Africa",country.getName());
		assertEquals( "ZA",country.getCode() );
	}

	@Test
	public void testCreatCountry_usingMock(){
		Country country = new Country("ZA","South Africa");
		countryRepository.save( country );
		verify(countryRepository, times( 1)).save( country );
	}


}
