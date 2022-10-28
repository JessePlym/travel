package hh.palvelinohjelmointi.travel.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long bookingNumber;

	@ManyToOne
	@JsonIgnoreProperties("bookings")
	@JoinColumn(name = "tripId")
	private Trip trip;

	@ManyToOne
	@JsonIgnoreProperties("bookings")
	@JoinColumn(name = "userId")
	private User user;

	public Booking() {
		super();
		this.trip = null;
		this.user = null;
	}

	public Booking(Trip trip, User user) {
		super();
		this.trip = trip;
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Trip getTrip() {
		return trip;
	}

	public void setTrip(Trip trip) {
		this.trip = trip;
	}

	public Long getBookingNumber() {
		return bookingNumber;
	}

	public void setBookingNumber(Long bookingNumber) {
		this.bookingNumber = bookingNumber;
	}

	@Override
	public String toString() {
		return "Booking [bookingNumber=" + bookingNumber + ", trip=" + this.trip.getDestination() + ", user="
				+ this.user.getUsername() + "]";
	}

}
