package edu.albany.se.app.service;

import edu.albany.se.app.model.Car;
import edu.albany.se.app.model.Location;
import edu.albany.se.app.repository.CarRepository;
import edu.albany.se.app.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.albany.se.app.model.User;
import edu.albany.se.app.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService
{
    public List<User> getAllUsers()
    {
        UserRepository userRepository = new UserRepository();
        List<User> users = userRepository.getAll();

        return users;
    }

    public User getById(int id)
    {
        UserRepository userRepository = new UserRepository();
        User user = userRepository.getById(id);

        return user;
    }

    public User getByEmail(String email)
    {
        UserRepository userRepository = new UserRepository();
        User user = userRepository.getByEmail(email);

        return user;
    }
}
