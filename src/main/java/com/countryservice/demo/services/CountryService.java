package com.countryservice.demo.services;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import com.countryservice.demo.beans.AddResponse;
import com.countryservice.demo.beans.Country;
import com.countryservice.demo.repositories.CountryRepository;

public class CountryService {
	
//	static HashMap<Integer,Country> countryIdMap;
//	
//	public CountryService() {
//		countryIdMap=new HashMap<Integer,Country>();
//		
//		Country indiaCountry = new Country(1,"India","Delhi");
//		Country usaCountry=new Country(2,"USA","Washington");
//		Country ukCountry=new Country(3,"UK","London");
//		
//		countryIdMap.put(1, indiaCountry);
//		countryIdMap.put(2, usaCountry);
//		countryIdMap.put(3, ukCountry);
		
	@Autowired
	CountryRepository countryRepo;
	
	public List<Country> getAllCountries() 
	{
		return countryRepo.findAll();
	}
	
	public Country getCountrybyId(int id) {
//		Optional<Country> country = countryRepo.findById(id);
//		return country.get();
		
		List<Country> countries = countryRepo.findAll();
		
		Country country = null;
		
		for(Country con : countries) {
			if(con.getId()==id) {
				country = con;
			}
		}
		
		return country;
	}
	
	public Country getCountrybyName(String countryName)
	{
		List<Country> countries = countryRepo.findAll();
		
	// the below statement will return one list containing one value only using stream api	
	//	List<Country> country = countries.stream().filter(name->name.getCountryName().equals(countryName)).collect(Collectors.toList());
		
		Country country=null;
		
		for(Country con : countries) {
			if(con.getCountryName().equals(countryName)) {
				country=con;
			}
		}
		
		return country;
		
	}
	
	public Country addCountry(Country country) {
		country.setId(getMaxId());
		countryRepo.save(country);
		
		return country;
	}
	
	//Utility method to get max id
	//We can increment using the Annotation
	public int getMaxId() {
		return countryRepo.findAll().size()+1;
	}
	
	public Country updateCountry(Country country) {
		countryRepo.save(country);
		return country;
	}
	
	public void deleteCountry(Country country) {
		/*
		 * AddResponse res=new AddResponse();
		 * res.setMsg("You have successfully deleted the"); res.setId(id);
		 * countryRepo.deleteById(id); return res;
		 */
		countryRepo.delete(country);
	//	countryRepo.deleteById(id);
		
	}
	
}
