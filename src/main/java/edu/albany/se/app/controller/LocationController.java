package edu.albany.se.app.controller;

import edu.albany.se.app.model.Location;
import edu.albany.se.app.service.LocationService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class LocationController
{
	@GetMapping("/location/all")
	public String allLocations()
	{
		LocationService locationService = new LocationService();
		List<Location> locations = locationService.getAllLocations();

		JSONArray jsonArray = new JSONArray();

		for (Location location : locations)
		{
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", location.getId());
			jsonObject.put("name", location.getName());
			jsonObject.put("description", location.getDescription());
			jsonObject.put("city", location.getCity());
			jsonObject.put("state", location.getState());
			jsonObject.put("country", location.getCountry());
			jsonObject.put("address", location.getAddress());
			jsonObject.put("ZIP_Code", location.getZipCode());
			jsonArray.put(jsonObject);
		}

		return jsonArray.toString();
	}

	@PostMapping("/Location/delete")
	public String deleteLocationById(@RequestParam int Id)
	{
		LocationService locationService = new LocationService();
		String result=locationService.deleteLocationById(Id);
        return result;
	}

}
