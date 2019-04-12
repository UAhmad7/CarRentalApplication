package edu.albany.se.app.service;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import edu.albany.se.app.model.User;
import edu.albany.se.app.repository.UserRepository;
import org.springframework.stereotype.Service;

import edu.albany.se.app.model.Reservation;
import edu.albany.se.app.repository.ReservationRepository;

@Service
public class ReservationService
{
    public List<Reservation> getAllReservations()
    {
        ReservationRepository reservationRepository = new ReservationRepository();
        List<Reservation> reservations = reservationRepository.getAll();

        return reservations;
    }

    public String makeReservation(int userId, int carId, int pickUpLocation, int dropOffLocation, String startDateTime, String endDateTime)
    {
        try
        {
            ReservationRepository reservationRepository = new ReservationRepository();

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = simpleDateFormat.parse(startDateTime);
            Date endDate = simpleDateFormat.parse(endDateTime);

            Reservation reservation = new Reservation();
            reservation.setUserId(userId);
            reservation.setCarId(carId);
            reservation.setStartLocationId(pickUpLocation);
            reservation.setEndLocationId(dropOffLocation);
            reservation.setStartDateTime(new java.sql.Date(startDate.getTime()));
            reservation.setEndDateTime(new java.sql.Date(endDate.getTime()));

            reservationRepository.addReservation(reservation);

            return "success";
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    public Reservation getByUserId(int id)
    {
        ReservationRepository reservationRepository = new ReservationRepository();
        Reservation reservation = reservationRepository.getByUserId(id);

        return reservation;
    }
}
