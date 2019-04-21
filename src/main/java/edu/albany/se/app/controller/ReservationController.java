package edu.albany.se.app.controller;

import edu.albany.se.app.model.Car;
import edu.albany.se.app.model.Reservation;
import edu.albany.se.app.model.User;
import edu.albany.se.app.service.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class ReservationController
{
	@PostMapping("/reservation/add")
	public String makeReservation(@RequestParam String token, @RequestParam int carId, @RequestParam int pickUpLocation, @RequestParam int dropOffLocation, @RequestParam String startDateTime, @RequestParam String endDateTime, @RequestParam Double total)
	{
		AuthService authService = new AuthService();
		Integer userId = authService.validateToken(token);
		JSONObject jsonObject = new JSONObject();

		if (userId != null)
		{
			ReservationService reservationService = new ReservationService();
			String result = reservationService.makeReservation(userId, carId, pickUpLocation, dropOffLocation, startDateTime, endDateTime, total);

			if (result.equals("success"))
			{
				jsonObject.put("message", result);
			}
			else
			{
				jsonObject.put("message", result);
			}
		}
		else
		{
			jsonObject.put("message", "Please login before making a reservation.");
		}

		return jsonObject.toString();
	}

	@GetMapping("/reservation/all")
	public String allReservations()
	{
		UserService userService = new UserService();
		CarService carService = new CarService();
		LocationService locationService = new LocationService();
		ReservationService reservationService = new ReservationService();
		List<Reservation> reservations = reservationService.getAllReservations();

		JSONArray jsonArray = new JSONArray();

		for (Reservation reservation : reservations)
		{
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", reservation.getId());
			jsonObject.put("userId", reservation.getUserId());

			User user = userService.getById(reservation.getUserId());

			jsonObject.put("email", user.getEmail());
			jsonObject.put("firstName", user.getFirstName());
			jsonObject.put("lastName", user.getLastName());
			jsonObject.put("carId", reservation.getCarId());

			Car car = carService.getById(reservation.getCarId());
			String carName = car.getMake() + " " + car.getModel() + " " + car.getYear();

			jsonObject.put("car", carName);
			jsonObject.put("startLocationId", reservation.getStartLocationId());
			jsonObject.put("startLocation", locationService.getById(reservation.getStartLocationId()).getName());
			jsonObject.put("endLocationId", reservation.getEndLocationId());
			jsonObject.put("endLocation", locationService.getById(reservation.getEndLocationId()).getName());
			jsonObject.put("startDateTime", reservation.getStartDateTime());
			jsonObject.put("endDateTime", reservation.getEndDateTime());

			jsonArray.put(jsonObject);
		}

		return jsonArray.toString();
	}

	@GetMapping("/reservation/get_by_token")
	public String getReservationByToken(@RequestParam String token)
	{
		AuthService authService = new AuthService();
		int userId = authService.validateToken(token);

		UserService userService = new UserService();
		CarService carService = new CarService();
		LocationService locationService = new LocationService();
		ReservationService reservationService = new ReservationService();
		List<Reservation> reservations = reservationService.getByUserId(userId);

		JSONArray jsonArray = new JSONArray();

		for (Reservation reservation : reservations)
		{
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", reservation.getId());
			jsonObject.put("userId", reservation.getUserId());

			User user = userService.getById(reservation.getUserId());

			jsonObject.put("email", user.getEmail());
			jsonObject.put("firstName", user.getFirstName());
			jsonObject.put("lastName", user.getLastName());
			jsonObject.put("carId", reservation.getCarId());

			Car car = carService.getById(reservation.getCarId());
			String carName = car.getMake() + " " + car.getModel() + " " + car.getYear();

			jsonObject.put("car", carName);
			jsonObject.put("startLocationId", reservation.getStartLocationId());
			jsonObject.put("startLocation", locationService.getById(reservation.getStartLocationId()).getName());
			jsonObject.put("endLocationId", reservation.getEndLocationId());
			jsonObject.put("endLocation", locationService.getById(reservation.getEndLocationId()).getName());
			jsonObject.put("startDateTime", reservation.getStartDateTime());
			jsonObject.put("endDateTime", reservation.getEndDateTime());
			jsonObject.put("total", reservation.getTotal());

			jsonArray.put(jsonObject);
		}

		return jsonArray.toString();
	}
}
