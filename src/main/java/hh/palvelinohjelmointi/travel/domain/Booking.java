package hh.palvelinohjelmointi.travel.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long bookingNumber;

}
