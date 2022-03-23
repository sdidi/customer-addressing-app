package za.co.addressing.customeraddressing.service;

import org.junit.*;
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

@ActiveProfiles("test")
@RunWith( SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ProvinceServiceTest
{
	@MockBean
	ProvinceRepository provinceRepository;

	@Test
	public void whenProvinceCodeIsProvided_thenRetrievedProvinceIsCorerct(){
		ProvinceService mock = mock(ProvinceService.class);
		Province province = new Province("WC","ZA","Western Cape");
		when(mock.findByProvinceCode( "WC" ))
				.thenReturn( province );
		Province testProvince = mock.findByProvinceCode( "WC" );
		assertEquals(testProvince,province);
	}

	@Test
	public void testCreateProvince_usingMock(){
		Province province = new Province("WC","ZA","Western Cape");
		provinceRepository.save( province );
		verify(provinceRepository, times( 1)).save( province );
	}
}
