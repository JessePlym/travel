package hh.palvelinohjelmointi.travel.domain;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Trip {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long tripId;

	private String departure;
	private String destination;
	private LocalDate date;
	private String departureTime;
	private String arrivalTime;
	private String trainType;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "trip")
	private List<Booking> bookings;

	public Trip() {
		super();
	}

	public Trip(String departure, String destination, LocalDate date, String departureTime, String arrivalTime,
			String trainType) {
		super();
		this.departure = departure;
		this.destination = destination;
		this.date = date;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.trainType = trainType;
	}

	public Long getId() {
		return tripId;
	}

	public void setId(Long id) {
		this.tripId = id;
	}

	public String getDeparture() {
		return departure;
	}

	public void setDeparture(String departure) {
		this.departure = departure;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}

	public String getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public String getTrainType() {
		return trainType;
	}

	public void setTrainType(String trainType) {
		this.trainType = trainType;
	}

	public List<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

	@Override
	public String toString() {
		return "Trip [id=" + tripId + ", departure=" + departure + ", destination=" + destination + ", date=" + date
				+ ", departureTime=" + departureTime + ", arrivalTime=" + arrivalTime + ", trainType=" + trainType
				+ "]";
	}

}
