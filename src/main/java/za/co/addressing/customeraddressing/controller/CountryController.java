package za.co.addressing.customeraddressing.controller;

import java.net.*;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.addressing.customeraddressing.model.Country;
import za.co.addressing.customeraddressing.service.CountryService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/countryapi")
public class CountryController
{
	Logger log = Logger.getLogger( CountryController.class.getName() );
	@Autowired
	private CountryService countryService;

	@GetMapping("/countries")
	public List<Country> getCountries(){
		return countryService.getCountries();
	}

	@GetMapping("/countries/{code}")
	public Country getCountry(@PathVariable String code){
		return countryService.findByCountryCode( code ).orElseThrow(RuntimeException::new);
	}

	@PostMapping("/addcountry")
	public ResponseEntity addCountry(@RequestBody Country country) throws URISyntaxException
	{
		Country savedCountry = countryService.createCountry( country );
		return ResponseEntity.created(new URI("/countryaapi/"+savedCountry.getCode())).body( savedCountry );
	}

	@PutMapping("/editcountry/{code}")
	public ResponseEntity updateCountry(@PathVariable String code, @RequestBody Country country){
		Country currentCountry = countryService.updateCountry( country, code );
		return ResponseEntity.ok(currentCountry);
	}

	@DeleteMapping("deletecountry/{code}")
	public ResponseEntity deleteCountry(@PathVariable String code){
		log.info( "The delete rest endpoint is class with code "+code );
		countryService.deleteCountry( code );
		return ResponseEntity.ok().build();
	}
}
