package za.co.addressing.customeraddressing.service;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import za.co.addressing.customeraddressing.model.*;
import za.co.addressing.customeraddressing.repository.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ProvinceServiceTest
{
	@Autowired
	ProvinceService provinceService;
	@MockBean
	ProvinceRepository provinceRepository;

	@org.junit.jupiter.api.Test
	public void whenProvinceCodeAndCountryCodeIsProvided_returnCorrectProvince(){
		Province province = new Province("WC","ZA","Western Cape");
		Country country = new Country("ZA","South Africa");
		doReturn( province ).when( provinceRepository ).findByCountryCodeAndProvinceCode( "WC","ZA" );
		Province province1 = provinceService.findByCountryCodeAndProvinceCode( "WC","ZA" );
		assertEquals( "Western Cape",province1.getName());
	}

	@Test
	public void whenProviceIsProvided_CheckSaving(){
		Province province = new Province("WC","ZA","Western Cape");
		provinceRepository.save( province );
		verify(provinceRepository, times( 1)).save( province );
	}
}
