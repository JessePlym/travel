package hh.palvelinohjelmointi.travel.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

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

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;

	public Booking() {
		super();
		this.trip = null;
		this.user = null;
		this.date = null;
	}

	public Booking(Trip trip, User user, Date date) {
		super();
		this.trip = trip;
		this.user = user;
		this.date = date;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Booking [bookingNumber=" + bookingNumber + ", trip=" + this.trip.getDestination() + ", user="
				+ this.user.getUsername() + " date=" + date + "]";
	}

}
