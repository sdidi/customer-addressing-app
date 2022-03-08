package za.co.addressing.customeraddressing.service;


import java.util.List;
import za.co.addressing.customeraddressing.model.*;

public interface ProvinceService
{
	Province findByCountryCodeAndProvinceCode(String countryCode, String provinceCode);

	Province findByCountryCode(String countryCode);

	Province findByProvinceCode(String provinceCode);

	List<Province> getProvincies();

	Province createProvince(Province province);

	Province getProvinceById( ProvinceId id);

	/*Province updateProvince(Province province, ProvinceId provinceId);*/
	Province updateProvince(Province province, String countryCode, String provinceCode);

	String deleteProvince(ProvinceId provinceId);

	List<Province> findByCountryCodeOrderByNameAsc(String countryCode);

	void deleteProvince(String countryCode, String provinceCode);
}
