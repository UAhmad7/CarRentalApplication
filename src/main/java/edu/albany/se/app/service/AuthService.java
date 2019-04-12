package edu.albany.se.app.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import edu.albany.se.app.model.Country;
import edu.albany.se.app.model.User;
import edu.albany.se.app.repository.UserRepository;

public class AuthService
{
    public int login(String email, String password)
    {
    	String message = "success";

    	UserRepository userRepository = new UserRepository();
	    User user = new User();
	    user.setEmail(email);
	    user.setPassword(password);

	    return userRepository.userExists(user);
    }

	public String register(String firstName, String lastName, String country, String licenseNumber, String email, String password)
	{
		String message = "success";
		Country countryObject = new Country();
		countryObject.setName(country);

		User user = new User();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setCountry(countryObject);
		user.setLicenseNumber(licenseNumber);
		user.setEmail(email);
		user.setPassword(password);

		UserRepository userRepository = new UserRepository();
		boolean success = userRepository.create(user);

		if (!success)
		{
			message = "An account with this email already exists.";
		}

		return message;
	}

	public String createToken(int userId)
	{
		String token = "";

		try
		{
			Algorithm algorithm = Algorithm.HMAC256("secret");
			token = JWT.create()
					.withIssuer("auth0")
					.withSubject(String.valueOf(userId))
					.sign(algorithm);
		}
		catch (JWTCreationException e)
		{
			//Invalid Signing configuration / Couldn't convert Claims.
			e.printStackTrace();
		}

		return token;
	}

	public Integer validateToken(String token)
	{
		try
		{
			Algorithm algorithm = Algorithm.HMAC256("secret");
			JWTVerifier verifier = JWT.require(algorithm)
					.withIssuer("auth0")
					.build();
			DecodedJWT verify = verifier.verify(token);
			return Integer.parseInt(verify.getSubject());
		}
		catch (JWTVerificationException e)
		{
			//Invalid signature/claims
			return null;
		}
	}
}
