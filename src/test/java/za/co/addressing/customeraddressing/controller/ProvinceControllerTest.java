package za.co.addressing.customeraddressing.controller;

import java.util.*;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import za.co.addressing.customeraddressing.model.*;
import za.co.addressing.customeraddressing.service.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ProvinceControllerTest
{

	@MockBean
	private ProvinceService provinceService;
	Province mockProvince = new Province("WC","ZA","Western Cape");
	//passed
	@Test
	public void getAllProvincesTest(){
		List<Province> list = new ArrayList<>();
		Province p1 = new Province("WC","ZA","Western Cape");
		Province p2 = new Province("EC","ZA","Eastern Cape");
		Province p3 = new Province("NC","ZA","Northern Cape");
		list.add( p1 );
		list.add( p2 );
		list.add( p3);

		when(provinceService.getProvincies()).thenReturn( list );

		List<Province> provinceList = provinceService.getProvincies();
		assertEquals( 3,provinceList.size());
		verify( provinceService, times( 1 ) ).getProvincies();
	}

	//passed
	@Test
	public void getProvinceByCodeTest(){
		ProvinceService mock = mock(ProvinceService.class);
		when(mock.findByProvinceCode( "WC" )).thenReturn( mockProvince );
		Province province = mock.findByProvinceCode( "WC" );
		assertEquals( "Western Cape",province.getName() );
		assertEquals( "ZA",province.getCountryCode() );

	}



}
