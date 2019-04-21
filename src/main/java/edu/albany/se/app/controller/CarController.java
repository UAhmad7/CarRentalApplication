package edu.albany.se.app.controller;

import edu.albany.se.app.model.Car;
import edu.albany.se.app.service.AuthService;
import edu.albany.se.app.service.CarService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class CarController
{
	@GetMapping("/car/all")
	public String allCars()
	{
		CarService carService = new CarService();
		List<Car> cars = carService.getAllCars();

		JSONArray jsonArray = new JSONArray();

		for (Car car : cars)
		{
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", car.getId());
			jsonObject.put("make", car.getMake());
			jsonObject.put("model", car.getModel());
			jsonObject.put("type", car.getType());
			jsonObject.put("year", car.getYear());
			jsonObject.put("capacity", car.getCapacity());
			jsonObject.put("pricePerDay", car.getPricePerDay());
			jsonObject.put("imageUrl", car.getImageUrl());
			jsonObject.put("location", car.getLocation());
			jsonObject.put("licenseNumber", car.getLicenseNumber());
			jsonObject.put("licenseState", car.getLicenseState());
			jsonObject.put("color", car.getColor());
			jsonObject.put("driveType", car.getDriveType());
			jsonArray.put(jsonObject);
		}

		return jsonArray.toString();
	}

	@PostMapping("/car/add")
	public String addCar(@RequestParam String token, @RequestParam String make, @RequestParam String model, @RequestParam String type, @RequestParam int year, @RequestParam int capacity, @RequestParam Double pricePerDay, @RequestParam String imageUrl, @RequestParam String location)
	{
		AuthService authService = new AuthService();
		int userId = authService.validateToken(token);

		JSONObject jsonObject = new JSONObject();

		if (userId != 0)
		{
			CarService carService = new CarService();
			carService.addCar(make, model, type, year, capacity, pricePerDay, imageUrl, location);

			jsonObject.put("message", "Car added successfully.");
		}
		else
		{
			jsonObject.put("message", "Invalid token, please login.");
		}

		return jsonObject.toString();
	}
}
