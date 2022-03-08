package za.co.addressing.customeraddressing.service;

import java.util.*;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.addressing.customeraddressing.model.Country;
import za.co.addressing.customeraddressing.repository.CountryRepository;

@Service
public class CountryServiceImpl implements  CountryService
{
	Logger log = Logger.getLogger( CountryServiceImpl.class.getName() );
	@Autowired
	private CountryRepository countryRepository;

	@Override
	public Optional<Country> findByCountryCode( String code )
	{
		return Optional.of(countryRepository.findCountryByCode( code ));
	}

	@Override
	public List<Country> getCountries()
	{
		return countryRepository.findByOrderByNameAsc();
	}

	@Override
	public Country createCountry( Country country )
	{
		return  countryRepository.save( country);
	}

	@Override
	public Country updateCountry( Country country, String code )
	{
		Country currentCountry = countryRepository.findCountryByCode( code );
		currentCountry.setCode( country.getCode() );
		currentCountry.setName( country.getName() );
		return  countryRepository.save( currentCountry);
	}

	@Override
	public String deleteCountry( String code )
	{
		log.info( "The country code is "+code );
		countryRepository.deleteById( code );
		return "success";
	}
}
