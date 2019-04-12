package edu.albany.se.app.controller;

import edu.albany.se.app.model.Car;
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
			jsonArray.put(jsonObject);
		}

		return jsonArray.toString();
	}
}
