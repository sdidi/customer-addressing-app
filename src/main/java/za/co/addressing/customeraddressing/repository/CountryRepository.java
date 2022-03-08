package za.co.addressing.customeraddressing.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.addressing.customeraddressing.model.*;

@Repository
public interface CountryRepository extends JpaRepository<Country,String>
{
	Country findCountryByCode(String code);
	List<Country> findByOrderByNameAsc();
}
