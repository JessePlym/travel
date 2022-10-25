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

	public Booking(Trip trip) {
		super();
		this.trip = trip;
	}

	public Booking() {
		super();
		this.trip = null;
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
		return "Booking [bookingNumber=" + bookingNumber + ", trip=" + this.getTrip().getDestination() + "]";
	}

}
