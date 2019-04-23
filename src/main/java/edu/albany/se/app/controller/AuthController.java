package edu.albany.se.app.controller;

import edu.albany.se.app.model.Location;
import edu.albany.se.app.model.User;
import edu.albany.se.app.service.AuthService;
import edu.albany.se.app.service.LocationService;
import edu.albany.se.app.service.UserService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class AuthController
{
	@PostMapping("/login")
	public String login(@RequestParam String email, @RequestParam String password)
	{
		AuthService authService = new AuthService();
		int userId = authService.login(email, password);

		JSONObject jsonObject = new JSONObject();

		if (userId != 0)
		{
			jsonObject.put("message", "success");
			jsonObject.put("token", authService.createToken(userId));
		}
		else
		{
			jsonObject.put("message", "Wrong email or password.");
		}

		return jsonObject.toString();
	}

	@PostMapping("/deleteUserById")
	public String delete(@RequestParam int id)
	{
		UserService userService=new UserService();
		String result= userService.deleteUserById(id);
		return result;
	}
	@PostMapping("/register")
	public String register(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String country, @RequestParam String licenseNumber, @RequestParam String email, @RequestParam String password)
	{
		AuthService authService = new AuthService();
		String result = authService.register(firstName, lastName, country, licenseNumber, email, password);

		JSONObject jsonObject = new JSONObject();

		if (result.equals("success"))
		{
			int userId = authService.login(email, password);

			jsonObject.put("message", "success");
			jsonObject.put("token", authService.createToken(userId));
		}
		else
		{
			jsonObject.put("message", result);
		}

		return jsonObject.toString();
	}
	@PostMapping("/user/update")
	public void update(@RequestParam int id, @RequestParam String firstName, @RequestParam String lastName, @RequestParam String licenseNumber, @RequestParam String email, @RequestParam String password)
	{
		UserService userService = new UserService();
		userService.updateUser(id,firstName,lastName,licenseNumber,email,password);
	}

	@GetMapping("/user/all")
	public String allUsers()
	{
		UserService userService = new UserService();
		List<User> users = userService.getAllUsers();

		JSONArray jsonArray = new JSONArray();

		for (User user : users)
		{
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", user.getId());
			jsonObject.put("firstName", user.getFirstName());
			jsonObject.put("lastName", user.getLastName());
			jsonObject.put("country", user.getCountry() != null ? user.getCountry().getName() : "");
			jsonObject.put("licenseNumber", user.getLicenseNumber());
			jsonObject.put("email", user.getEmail());
			jsonArray.put(jsonObject);
		}

		return jsonArray.toString();
	}

	@GetMapping("/user/get_by_token")
	public String getUserByToken(@RequestParam String token)
	{
		AuthService authService = new AuthService();
		int userId = authService.validateToken(token);

		UserService userService = new UserService();
		User user = userService.getById(userId);

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("id", user.getId());
		jsonObject.put("firstName", user.getFirstName());
		jsonObject.put("lastName", user.getLastName());
		jsonObject.put("country", user.getCountry() != null ? user.getCountry().getName() : "");
		jsonObject.put("licenseNumber", user.getLicenseNumber());
		jsonObject.put("email", user.getEmail());

		return jsonObject.toString();
	}

	@GetMapping("/user/get_by_email")
	public String getUserByEmail(@RequestParam String email)
	{
		UserService userService = new UserService();
		User user = userService.getByEmail(email);

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("id", user.getId());
		jsonObject.put("firstName", user.getFirstName());
		jsonObject.put("lastName", user.getLastName());
		jsonObject.put("country", user.getCountry() != null ? user.getCountry().getName() : "");
		jsonObject.put("licenseNumber", user.getLicenseNumber());
		jsonObject.put("email", user.getEmail());

		return jsonObject.toString();
	}
}
