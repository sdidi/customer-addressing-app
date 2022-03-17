package za.co.addressing.customeraddressing.controller;

import io.swagger.annotations.ApiOperation;
import java.net.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.addressing.customeraddressing.model.*;
import za.co.addressing.customeraddressing.service.ProvinceService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/provinceapi")
public class ProvinceController
{
	@Autowired
	private ProvinceService provinceService;

	@GetMapping("/provincies")
	@ApiOperation( value = " Find all the provinces in the database",
			notes= " This displays all the provincies in the database",
			response = List.class)
	public List<Province> getProvinces(){
		return provinceService.getProvincies();
	}

	@GetMapping("/provincies/{countryCode}")
	@ApiOperation( value =" Looks up a specific province by a country",
			notes = " Provides an id to look up a province by country from the database",
			response = Province.class)
	public List<Province> getProvincesByCountry(@PathVariable String countryCode){
		//user to create drop down dependencies
		return provinceService.findByCountryCodeOrderByNameAsc(countryCode  );
	}

	@GetMapping("/provincies/{countryCode}/{provinceCode}")
	@ApiOperation( value =" Looks up a specific province by the code",
			notes = " Provides an id to look up a specific province from the database",
			response = Province.class)
	public Province getProvince(@PathVariable String countryCode, @PathVariable String provinceCode){
		return provinceService.findByProvinceCode( provinceCode );
	}



	@PostMapping("/addprovince")
	@ApiOperation( value =" Creates a new province record",
			notes = " creates a new province and save on the database ",
			response = Province.class)
	public ResponseEntity addProvince(@RequestBody Province province) throws URISyntaxException
	{
		Province savedProvince = provinceService.createProvince( province );
		return ResponseEntity.created(new URI("/provinceapi/"+savedProvince.getProvinceCode())).body( savedProvince );
	}

	@PutMapping("/editprovince/{countryCode}/{provincecode}/")
	@ApiOperation( value =" Updates an existing province",
			notes = " Updates an existing province and save in the database ",
			response = Province.class)
	public ResponseEntity updateProvince(@PathVariable String countryCode, @PathVariable String provinceCode, @RequestBody Province province){
		Province currentProvince = provinceService.updateProvince( province, countryCode, provinceCode);
		return ResponseEntity.ok(currentProvince);
	}

	@DeleteMapping("/deleteprovince")
	@ApiOperation( value =" deletes a province",
			notes = " Deletes a specific province identified with the id ",
			response = Province.class)
	public ResponseEntity deleteProvince(@RequestParam(name = "countryCode")
			String countryCode, @RequestParam(name = "provinceCode") String provinceCode){
		provinceService.deleteProvince( countryCode, provinceCode );
		return ResponseEntity.ok().build();
	}

}
