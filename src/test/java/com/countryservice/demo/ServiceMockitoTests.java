package com.countryservice.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.countryservice.demo.beans.Country;
import com.countryservice.demo.repositories.CountryRepository;
import com.countryservice.demo.services.CountryService;

@SpringBootTest(classes = {ServiceMockitoTests.class})
public class ServiceMockitoTests {

	@Mock
	CountryRepository countryrep;
	
	@InjectMocks
	CountryService countryService;
		
	public List<Country> mycountries;
	
	@Test
	@Order(1)
	public void test_getAllCountries() {
		
		List<Country> mycountries=new ArrayList<Country>();
		mycountries.add(new Country(1,"India","Delhi"));
		mycountries.add(new Country(2,"USA","Washington"));
		
		when(countryrep.findAll()).thenReturn(mycountries);//Mocking
		
		assertEquals(2,countryService.getAllCountries().size());
		
	}
	
	@Test
	@Order(2)
	public void test_getCountryByID() {
		List<Country> mycountries=new ArrayList<Country>();
		mycountries.add(new Country(1,"India","Delhi"));
		mycountries.add(new Country(2,"USA","Washington"));
		
		int countryID=1;
		
		when(countryrep.findAll()).thenReturn(mycountries);
		
		assertEquals(countryID , countryService.getCountrybyId(countryID).getId());
		
	}
	
	@Test
	@Order(3)
	public void test_getCountryByName() {
		List<Country> mycountries=new ArrayList<Country>();
		mycountries.add(new Country(1,"India","Delhi"));
		mycountries.add(new Country(2,"USA","Washington"));
		
		String countryName="India";
		
		when(countryrep.findAll()).thenReturn(mycountries);
		assertEquals(countryName,countryService.getCountrybyName(countryName).getCountryName());
	}
	
	@Test
	@Order(4)
	public void test_addCountry() {
		Country country=new Country(3,"Germany","Berlin");
		
		when(countryrep.save(country)).thenReturn(country);
		assertEquals(country,countryService.addCountry(country));
	}
	
	public void test_updateCountry() {
		Country country = new Country(3, "Germany" , "Berlin");
		
		when(countryrep.save(country)).thenReturn(country);
		
		assertEquals(country, countryService.updateCountry(country));
		
	}
	public void test_deleteCountry() {
		Country country=new Country(2,"India","Delhi");
		
		countryService.deleteCountry(country);
		verify(countryrep , times(1)).delete(country); // mocking
	}
		
}
