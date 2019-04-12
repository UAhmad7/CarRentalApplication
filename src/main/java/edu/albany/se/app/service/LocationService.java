package edu.albany.se.app.service;

import edu.albany.se.app.model.Car;
import edu.albany.se.app.repository.CarRepository;
import org.springframework.stereotype.Service;

import edu.albany.se.app.model.Location;
import edu.albany.se.app.repository.LocationRepository;

import java.util.List;

@Service
public class LocationService
{
	public List<Location> getAllLocations()
	{
		LocationRepository locationRepository = new LocationRepository();
		List<Location> locations = locationRepository.getAll();

		return locations;
	}

	public Location getById(int id)
	{
		LocationRepository locationRepository = new LocationRepository();
		Location location = locationRepository.getById(id);

		return location;
	}
}
