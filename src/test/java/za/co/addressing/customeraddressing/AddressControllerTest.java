package za.co.addressing.customeraddressing;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.*;
import java.util.List;
import java.util.logging.Logger;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.*;
import za.co.addressing.customeraddressing.model.*;
import za.co.addressing.customeraddressing.service.AddressService;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(AddressControllerTest.class)
public class AddressControllerTest
{
	/*Logger log = Logger.getLogger( AddressControllerTest.class.getName() );
	@Autowired
	MockMvc mockMvc;
	@Autowired
	ObjectMapper objectMapper;

	@MockBean
	AddressService addressService;

	Province province = new Province("WC","ZA","Western Cape");
	Province province2 = new Province("EC","ZA","Eastern Cape");
	Country country = new Country("ZA", "South Africa");
	Address address1 = new Address(1L,"21 reid","4th St","Diep River","8900",
			"Cape Town",province,country);
	Address address2 = new Address(2L,"22 reid","4th St","Diep River","8900",
			"Cape Town",province,country);
	Address address3 = new Address(3L,"23 smith","5th St","Wynberg","6700",
			"Eastern Cape",province2,country);


	@Test
	public void getAllAddressesSuccess() throws Exception {
		log.info("is addressService null" + (null == addressService));
		List<Address> addressList = new ArrayList<>(Arrays.asList(address1,address2,address3));

		Mockito.when( addressService.getAddresses() ).thenReturn( addressList);

		MvcResult result = mockMvc.perform( MockMvcRequestBuilders
				.get("/addressapi/addresses")
				.contentType( MediaType.APPLICATION_JSON ))
				.andExpect( status().isOk() )
				.andExpect(jsonPath("$", hasSize(3)))
				.andReturn();

				String content = result.getResponse().getContentAsString();
				System.out.println(content);
	}

	@Test
	public void createAddressRecordSuccess() throws Exception {
		Address address = Address.builder()
				.id( 1l )
				.line1( "1 Sabre rd" )
				.line2("2nd street")
				.suburb( "City square" )
				.postalCode( "7600" )
				.city( "Cape Town" )
				.province( new Province("WC","ZA","Western Cape") )
				.country( new Country("ZA","South Africa") )
				.build();
		try
		{
			Mockito.when( addressService.createAddress( address, "ZA", "WC" ) ).thenReturn( address );


		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/createaddresses")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(this.objectMapper.writeValueAsString(address));

		mockMvc.perform(mockRequest)
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", notNullValue()))
				.andExpect(jsonPath("$.city", is("Cape Town")));
		} catch(Exception e){
			e.printStackTrace();
		}
	}*/
}


