package com.countryservice.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.countryservice.demo.beans.AddResponse;
import com.countryservice.demo.beans.Country;
import com.countryservice.demo.services.CountryService;

@RestController
public class CountryController {
	
	@Autowired
	AddResponse res;
	
	@Autowired
	CountryService countryService;
	
	@GetMapping("/getcountries")
	public ResponseEntity<List<Country>> getCountries(){
		try {
			List<Country> countries = countryService.getAllCountries();
			return new ResponseEntity<List<Country>>(countries,HttpStatus.FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/getcountries/{id}")
	public ResponseEntity<Country> getCountryById(@PathVariable("id") int id) {
		try {
			Country country=countryService.getCountrybyId(id);
			return new ResponseEntity<Country>(country,HttpStatus.FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/getcountries/countryname")
	public ResponseEntity<Country> getCountryByName(@RequestParam("name") String name) {
		try {
			Country countrybyName = countryService.getCountrybyName(name);
			return new ResponseEntity<Country>(countrybyName,HttpStatus.FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/addcountry")
	public Country addCountry(@RequestBody Country country) {
		countryService.addCountry(country);
		return country;
	}
	
	@PutMapping("/updatecountry/{id}")
	public ResponseEntity<Country> updateCountry(@PathVariable("id") int id,@RequestBody Country country) {
		
		try {
			Country existCountry = countryService.getCountrybyId(id);
		
			country.setId(existCountry.getId());
			country.setCountryName(existCountry.getCountryName());
			country.setCountryCapital(existCountry.getCountryCapital());
			
			countryService.addCountry(country);
		
			return new ResponseEntity<Country>(country,HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}
	
	@DeleteMapping("/deletecountry/{id}")
	public ResponseEntity<Country> deleteCountry(@PathVariable("id") int id) {
		Country country=null;
		try {
			country=countryService.getCountrybyId(id);
			countryService.deleteCountry(country);
			return new ResponseEntity<Country>(country,HttpStatus.FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
