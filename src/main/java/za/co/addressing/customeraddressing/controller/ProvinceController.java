package za.co.addressing.customeraddressing.controller;

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
	public List<Province> getProvinces(){
		return provinceService.getProvincies();
	}

	@GetMapping("/provincies/{countryCode}")
	public List<Province> getProvincesByCountry(@PathVariable String countryCode){
		//user to create drop down dependencies
		return provinceService.findByCountryCodeOrderByNameAsc(countryCode  );
	}

	@GetMapping("/provincies/{countryCode}/{provinceCode}")
	public Province getProvince(@PathVariable String countryCode, @PathVariable String provinceCode){
		return provinceService.findByProvinceCode( provinceCode );
	}



	@PostMapping("/addprovince")
	public ResponseEntity addProvince(@RequestBody Province province) throws URISyntaxException
	{
		Province savedProvince = provinceService.createProvince( province );
		return ResponseEntity.created(new URI("/provinceapi/"+savedProvince.getProvinceCode())).body( savedProvince );
	}

	@PutMapping("/editprovince/{countryCode}/{provincecode}/")
	public ResponseEntity updateProvince(@PathVariable String countryCode, @PathVariable String provinceCode, @RequestBody Province province){
		Province currentProvince = provinceService.updateProvince( province, countryCode, provinceCode);
		return ResponseEntity.ok(currentProvince);
	}

	@DeleteMapping("/deleteprovince")
	public ResponseEntity deleteProvince(@RequestParam(name = "countryCode")
			String countryCode, @RequestParam(name = "provinceCode") String provinceCode){
		provinceService.deleteProvince( countryCode, provinceCode );
		return ResponseEntity.ok().build();
	}

}
