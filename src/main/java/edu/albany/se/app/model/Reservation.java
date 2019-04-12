package edu.albany.se.app.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "Reservation")
public class Reservation
{
	@Id
	private int id;

	private int userId;
	private int carId;
	private int startLocationId;
	private int endLocationId;
	private Date startDateTime;
	private Date endDateTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getCarId() {
		return carId;
	}

	public void setCarId(int carId) {
		this.carId = carId;
	}

	public int getStartLocationId() {
		return startLocationId;
	}

	public void setStartLocationId(int startLocationId) {
		this.startLocationId = startLocationId;
	}

	public int getEndLocationId() {
		return endLocationId;
	}

	public void setEndLocationId(int endLocationId) {
		this.endLocationId = endLocationId;
	}

	public Date getStartDateTime() {
		return startDateTime;
	}

	public void setStartDateTime(Date startDateTime) {
		this.startDateTime = startDateTime;
	}

	public Date getEndDateTime() {
		return endDateTime;
	}

	public void setEndDateTime(Date endDateTime) {
		this.endDateTime = endDateTime;
	}
}
