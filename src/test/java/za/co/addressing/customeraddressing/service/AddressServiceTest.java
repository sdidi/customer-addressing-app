package za.co.addressing.customeraddressing.service;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import za.co.addressing.customeraddressing.model.*;
import za.co.addressing.customeraddressing.repository.AddressRepository;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class AddressServiceTest
{
	@Autowired
	AddressService addressService;
	@MockBean
	AddressRepository addressRepository;

	@Test
	public void whenIdProvided_returnCorrectAddress(){
		Province province = new Province("WC","ZA","Western Cape");
		Country country = new Country("ZA","South Africa");
		Address address = new Address(1L,"3rd ave","2nd street","Athlone","Cape Town",6650,province,country);
		doReturn( Optional.of(address) ).when( addressRepository ).findById( 1L);
		Address addressReturned = addressService.getAddressById( 1L );
		assertEquals( "Athlone" ,addressReturned.getSuburb());
	}


	@Test
	public void testCreateAddress_usingMock(){
		Province province = new Province("WC","ZA","Western Cape");
		Country country = new Country("ZA","South Africa");
		Address address = new Address(1L,"3rd ave","2nd street","Athlone","Cape Town",6650,province,country);
		addressRepository.save( address );
		verify(addressRepository, times( 1)).save( address);
	}

}
