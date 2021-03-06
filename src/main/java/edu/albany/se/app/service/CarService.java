package edu.albany.se.app.service;

import edu.albany.se.app.model.Location;
import edu.albany.se.app.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.albany.se.app.model.Car;
import edu.albany.se.app.repository.CarRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarService
{
    public List<Car> getAllCars()
    {
        CarRepository carRepository = new CarRepository();
        List<Car> cars = carRepository.getAll();

        return cars;
    }

    public void updateCar(int id,String make,String model,String type,int year,int capacity,Double pricePerDay,String imageURL,String location)
    {
        CarRepository carRepository = new CarRepository();
        Car car = new Car();
        car.setId(id);
        car.setMake(make);
        car.setModel(model);
        car.setType(type);
        car.setYear(year);
        car.setCapacity(capacity);
        car.setPricePerDay(pricePerDay);
        car.setImageUrl(imageURL);
        car.setLocation(location);
        carRepository.update(car);
    }
    public String deleteCarById(int Id)
    {
        String result="success";
        CarRepository carRepository = new CarRepository();
        Car car = carRepository.getById(Id);
        if(car==null)
        {
            return "Cannot find location";
        }
        carRepository.delete(car);
        return result;
    }

    public Car getById(int id)
    {
        CarRepository carRepository = new CarRepository();
        Car car = carRepository.getById(id);

        return car;
    }

    public void addCar(String make, String model, String type, int year, int capacity, Double pricePerDay, String imageUrl, String location)
    {
        CarRepository carRepository = new CarRepository();
        Car car = new Car();

        car.setMake(make);
        car.setModel(model);
        car.setType(type);
        car.setYear(year);
        car.setCapacity(capacity);
        car.setPricePerDay(pricePerDay);
        car.setImageUrl(imageUrl);
        car.setLocation(location);

        carRepository.add(car);
    }
}
