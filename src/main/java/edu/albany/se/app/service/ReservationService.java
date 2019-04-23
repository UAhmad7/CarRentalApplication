package edu.albany.se.app.service;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

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

    public String makeReservation(int userId, int carId, int pickUpLocation, int dropOffLocation, String startDateTime, String endDateTime, Double total)
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
            reservation.setTotal(total);

            reservationRepository.addReservation(reservation);

            return "success";
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    public Reservation getById(int id)
    {
        ReservationRepository reservationRepository = new ReservationRepository();
        Reservation location = reservationRepository.getById(id);

        return location;
    }
    public List<Reservation> getByUserId(int id)
    {
        ReservationRepository reservationRepository = new ReservationRepository();
        List<Reservation> reservations = reservationRepository.getByUserId(id);

        return reservations;
    }

    public String deleteReservationById(int Id)
    {
        String result="success";
        ReservationRepository reservationRepository = new ReservationRepository();
        Reservation reservation = reservationRepository.getById(Id);
        if(reservation==null)
        {
            return "Cannot find reservation";
        }
        reservationRepository.delete(reservation);
        return result;
    }
}
