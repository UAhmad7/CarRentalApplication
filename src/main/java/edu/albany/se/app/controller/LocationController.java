package edu.albany.se.app.controller;

import edu.albany.se.app.model.Location;
import edu.albany.se.app.service.AuthService;
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
			jsonArray.put(jsonObject);
		}

		return jsonArray.toString();
	}
}