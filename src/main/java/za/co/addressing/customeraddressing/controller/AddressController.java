package za.co.addressing.customeraddressing.controller;

import io.swagger.annotations.*;
import java.net.*;
import java.util.*;
import java.util.logging.Logger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import za.co.addressing.customeraddressing.model.*;
import za.co.addressing.customeraddressing.repository.AddressRepository;
import za.co.addressing.customeraddressing.service.AddressService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/addressapi")
@Slf4j
public class AddressController
{
	@Autowired
 	private AddressService addressService;

	@Autowired
	private AddressRepository addressRepository;

	@GetMapping("/addresses")
	@ApiOperation( value = " Find all the addresses in the database",
			notes= " This displays all the address in the database",
			response = List.class)
	public List<Address> getAddresses(){
		log.info("Using a Get method to get all Addressess");
		return addressService.getAddresses();
	}


	@GetMapping("/addresses/{id}")
	@ApiOperation( value =" Looks up a specific address by the id",
			notes = " Provides an id to look up a specific address from the database",
			response = Address.class)
	public Address getAddress(@ApiParam(value = " an ID value for the address you need to retrieve", required = true)
								@PathVariable Long id){
		log.info("Using a Get method to get Address by Id : "+id);
					return addressService.getAddressById( id );
	}

	@PostMapping("/createaddresses")
	@ApiOperation( value =" Creates a new address",
			notes = " creates a new address and selects the country and province existing in the database ",
			response = Address.class)
	public ResponseEntity createAddress(@RequestBody Address address, @RequestParam(name = "countryCode")
			String countryCode,@RequestParam(name = "provinceCode") String provinceCode) throws URISyntaxException {
		log.info("Using Province code on the controller: "+provinceCode + "  country code: "+ countryCode+" to create an address");
		Address savedAddress = addressService.createAddress( address,countryCode,provinceCode );
		return ResponseEntity.created(new URI("/addressapi/"+savedAddress.getId())).body( savedAddress );
	}

	@PutMapping("/editaddresses/{id}")
	@ApiOperation( value =" Updates an existing address",
			notes = " Updates an existing address and selects the country and province existing in the database ",
			response = Address.class)
	public ResponseEntity updateAddress(@PathVariable Long id, @RequestBody Address address,  @RequestParam(name = "countryCode")
			String countryCode,@RequestParam(name = "provinceCode") String provinceCode){
		log.info("Using a Address by Id : "+id + " to update the address");
		Address currentAddress = addressService.updateAddress(address, id, countryCode,provinceCode  );
		return ResponseEntity.ok(currentAddress);
	}

	@DeleteMapping("/deleteaddresses/{id}")
	@ApiOperation( value =" deletes an address",
			notes = " Deletes a soecific address identified with the id ",
			response = Address.class)
	public ResponseEntity<Map<String,Boolean>> deleteAddress(@PathVariable Long id){
		log.info("Using a Address by Id : "+id + " to delete this address");
		Map<String,Boolean> response = addressService.deleteAddress( id );
		return ResponseEntity.ok(response);
	}

}
