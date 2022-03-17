package za.co.addressing.customeraddressing.controller;

import io.swagger.annotations.*;
import java.net.*;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.addressing.customeraddressing.model.*;
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
	@ApiOperation( value = " Find all the countires in the database",
			notes= " This displays all the countries in the database",
			response = List.class)
	public List<Country> getCountries(){
		return countryService.getCountries();
	}

	@GetMapping("/countries/{code}")
	@ApiOperation( value =" Looks up a specific country by the id",
			notes = " Provides an id to look up a specific country from the database",
			response = Country.class)
	public Country getCountry(@ApiParam(value = " A country Code value you need to retrieve", required = true)
								@PathVariable String code){
		return countryService.findByCountryCode( code ).orElseThrow(RuntimeException::new);
	}

	@PostMapping("/addcountry")
	@ApiOperation( value =" Creates a new country record",
			notes = " creates a new country and save on the database ",
			response = Country.class)
	public ResponseEntity addCountry(@RequestBody Country country) throws URISyntaxException
	{
		Country savedCountry = countryService.createCountry( country );
		return ResponseEntity.created(new URI("/countryaapi/"+savedCountry.getCode())).body( savedCountry );
	}

	@PutMapping("/editcountry/{code}")
	@ApiOperation( value =" Updates an existing country",
			notes = " Updates an existing country and save in the database ",
			response = Country.class)
	public ResponseEntity updateCountry(@PathVariable String code, @RequestBody Country country){
		Country currentCountry = countryService.updateCountry( country, code );
		return ResponseEntity.ok(currentCountry);
	}

	@DeleteMapping("deletecountry/{code}")
	@ApiOperation( value =" deletes a country",
			notes = " Deletes a specific country identified with the id ",
			response = Country.class)
	public ResponseEntity deleteCountry(@PathVariable String code){
		log.info( "The delete rest endpoint is class with code "+code );
		countryService.deleteCountry( code );
		return ResponseEntity.ok().build();
	}
}
