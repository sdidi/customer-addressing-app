package za.co.addressing.customeraddressing.service;

import java.util.*;
import za.co.addressing.customeraddressing.model.*;

public interface CountryService
{
	Optional<Country> findByCountryCode(String code);

	List<Country> getCountries();

	Country createCountry(Country country);

	Country updateCountry(Country country, String code);

	String deleteCountry(String code);
}
