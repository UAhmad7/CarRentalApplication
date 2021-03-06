package edu.albany.se.app.service;

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
	public void updateLocation(int id, String name, String description)
	{
		LocationRepository locationRepository = new LocationRepository();
		Location location = new Location();
		location.setId(id);
		location.setName(name);
		location.setDescription(description);
		locationRepository.update(location);
	}
	public String deleteLocationById(int Id)
	{
		String result="success";
		LocationRepository locationRepository = new LocationRepository();
		Location location = locationRepository.getById(Id);
        if(location==null)
		{
			return "Cannot find location";
		}
		locationRepository.delete(location);
        return result;
	}
}
