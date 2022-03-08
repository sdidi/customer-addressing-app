package za.co.addressing.customeraddressing.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.addressing.customeraddressing.model.*;

@Repository
public interface ProvinceRepository extends JpaRepository<Province,ProvinceId>
{
	Province findByCountryCodeAndProvinceCode( String countryCode, String provinceCode );
	List<Province> findByCountryCode(String country);
	List<Province>  findByProvinceCode(String province);
	List<Province> findByOrderByNameAsc();
	List<Province> findByCountryCodeOrderByNameAsc(String countryCode);

}

