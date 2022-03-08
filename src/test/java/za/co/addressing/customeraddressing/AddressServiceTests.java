package za.co.addressing.customeraddressing;

import java.util.Optional;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import za.co.addressing.customeraddressing.model.*;
import za.co.addressing.customeraddressing.repository.AddressRepository;
import za.co.addressing.customeraddressing.service.*;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith( SpringRunner.class)
public class AddressServiceTests
{
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


	@Before
	public void setUp() {
		Province province = new Province("WC","ZA","Western Cape");
		Country country = new Country("ZA","South Africa");
		Address address = new Address(1L,"3rd ave","2nd stree","Athlone","Cape Town",6650,province,country);

		Mockito.when(addressRepository.findById( 1L ))
				.thenReturn( Optional.of(address));
	}

	@Test
	public void whenValidID_thenAddressShouldBeFound() {
		Long id = 1L;
		Address found = addressService.getAddressById( id );

		//assertThat(false,is(false));

	}
}
