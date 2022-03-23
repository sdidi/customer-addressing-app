package za.co.addressing.customeraddressing.controller;

import java.util.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import za.co.addressing.customeraddressing.model.*;
import za.co.addressing.customeraddressing.service.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith( SpringRunner.class )
@WebMvcTest(value= AddressController.class )
public class AddressControllerTest
{
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AddressService addressService;
	Country mockCountry = new Country("ZA","South Africa");
	Province mockProvince = new Province("WC","ZA","Western Cape");
	Address mockAddress = new Address(1L,"3rd ave","2nd street","Athlone","Cape Town",6650,mockProvince,mockCountry);
	//passed
	@Test
	public void when_AllAddressList_checkIfReturnsAllAddresses(){
		List<Address> list = new ArrayList<>();
		Address address1 = new Address(1L,"3rd ave","2nd street","Athlone","Cape Town",6650,mockProvince,mockCountry);
		Address address2 = new Address(2L,"3rd ave2","2nd street2","Athlone2","Cape Town",6650,mockProvince,mockCountry);
		list.add( address1);
		list.add( address2);

		when(addressService.getAddresses()).thenReturn( list );

		List<Address> addressList = addressService.getAddresses();
		assertEquals( 2,addressList.size());
		verify( addressService, times( 1 ) ).getAddresses();
	}

	//passed
	@Test
	public void whenAddressIdProvided_returnCorrectAddress(){
		AddressService mock = mock(AddressService.class);
		when(mock.getAddressById( 1L )).thenReturn( mockAddress );
		Address address = mock.getAddressById( 1L );
		assertEquals( "Western Cape",address.getProvince() );
		assertEquals( "Athlone",address.getSuburb() );

	}


}
