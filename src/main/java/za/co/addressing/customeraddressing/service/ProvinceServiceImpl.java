package za.co.addressing.customeraddressing.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.addressing.customeraddressing.model.*;
import za.co.addressing.customeraddressing.repository.ProvinceRepository;

@Service
public class ProvinceServiceImpl implements ProvinceService
{
	@Autowired
	private ProvinceRepository provinceRepository;

	@Override
	public Province findByCountryCodeAndProvinceCode( String countryCode, String provinceCode )
	{
		return provinceRepository.findByCountryCodeAndProvinceCode( countryCode, provinceCode );
	}

	@Override
	public List<Province> getProvincies()
	{
		return provinceRepository.findByOrderByNameAsc();
	}

	@Override
	public List<Province> findByCountryCodeOrderByNameAsc( String countryCode )
	{
		return provinceRepository.findByCountryCodeOrderByNameAsc(countryCode  );
	}

	@Override
	public Province createProvince( Province province )
	{
		Province savedProvince = provinceRepository.save( province );
		return savedProvince;
	}

	@Override
	public Province getProvinceById( ProvinceId provinceId )
	{
		return provinceRepository.findById( provinceId).orElseThrow(RuntimeException::new);
	}

	@Override
	public Province updateProvince( Province province, String countryCode, String provinceCode )
	{
		Province currentProvince = provinceRepository.findByCountryCodeAndProvinceCode( countryCode, provinceCode );
		currentProvince.setProvinceCode( province.getProvinceCode() );
		currentProvince.setCountryCode( province.getCountryCode() );
		return provinceRepository.save(currentProvince);
	}

	@Override
	public String deleteProvince( ProvinceId provinceId )
	{
		provinceRepository.deleteById(provinceId );
		return "success";
	}

	@Override
	public Province findByCountryCode( String countryCode )
	{
		return provinceRepository.findByCountryCode(countryCode  ).get( 0 );

	}

	@Override
	public Province findByProvinceCode( String provinceCode )
	{
		return provinceRepository.findByProvinceCode( provinceCode ).get( 0 );
	}

	@Override
	public void deleteProvince( String countryCode, String provinceCode )
	{
		 provinceRepository.deleteById( new ProvinceId(countryCode,provinceCode) );
	}
}
