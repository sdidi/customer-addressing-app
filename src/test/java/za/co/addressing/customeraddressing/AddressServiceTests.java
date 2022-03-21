package za.co.addressing.customeraddressing;

import java.util.*;
import org.junit.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import za.co.addressing.customeraddressing.model.*;
import za.co.addressing.customeraddressing.repository.AddressRepository;
import za.co.addressing.customeraddressing.service.*;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class AddressServiceTests
{

	@Autowired
	private MockMvc mvc;

	private static Address address;
	@TestConfiguration
	static class EmployeeServiceImplTestContextConfiguration {

		@Bean
		public AddressService addressService() {
			return new AddressServiceImpl();
		}
	}

	@Autowired
	private AddressService addressService;

	@MockBean
	private AddressRepository addressRepository;


	@BeforeAll
	public void setUp() {
		Province province = new Province("WC","ZA","Western Cape");
		Country country = new Country("ZA","South Africa");
		address = new Address(1L,"3rd ave","2nd street","Athlone","Cape Town",6650,province,country);

		Mockito.when(addressRepository.findById( 1L ))
				.thenReturn( Optional.of(address));
	}

	@Test
	public void listAllAddresses() throws Exception {
		List<Address> allAddresses = Arrays.asList(address);
		Mockito.when(addressService.getAddresses()).thenReturn(allAddresses);
		mvc.perform( MockMvcRequestBuilders.get("http://localhost:8080/addressapi/addresses").accept( MediaType.APPLICATION_JSON))
				.andExpect( MockMvcResultMatchers.status().isOk());

	}

	@Test
	public void whenValidID_thenAddressShouldBeFound() {
		Long id = 1L;
		Address found = addressService.getAddressById( id );

		assertEquals(false,is(false));

	}
}
