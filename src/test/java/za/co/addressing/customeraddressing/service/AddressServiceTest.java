package za.co.addressing.customeraddressing.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import za.co.addressing.customeraddressing.model.*;
import za.co.addressing.customeraddressing.repository.AddressRepository;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@RunWith( SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AddressServiceTest
{
	@MockBean
	AddressRepository addressRepository;

	@Test
	public void whenAddressIdIsProvided_thenRetrievedAddressIsCorrect(){
		AddressService mock = mock(AddressService.class);
		Province province = new Province("WC","ZA","Western Cape");
		Country country = new Country("ZA","South Africa");
		Address address = new Address(1L,"3rd ave","2nd street","Athlone","Cape Town",6650,province,country);
		Mockito.when(mock.getAddressById( 1L ))
				.thenReturn( address);
		Address addressTest= mock.getAddressById( 1L );
		assertEquals(addressTest,address);
	}

	@org.junit.Test
	public void testCreateAddress_usingMock(){
		Province province = new Province("WC","ZA","Western Cape");
		Country country = new Country("ZA","South Africa");
		Address address = new Address(1L,"3rd ave","2nd street","Athlone","Cape Town",6650,province,country);
		addressRepository.save( address );
		verify(addressRepository, times( 1)).save( address);
	}

}
