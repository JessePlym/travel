package hh.palvelinohjelmointi.travel.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hh.palvelinohjelmointi.travel.domain.Booking;
import hh.palvelinohjelmointi.travel.domain.BookingRepository;

@RestController
public class BookingRestController {

	@Autowired
	private BookingRepository repository;

	// returns all bookings as JSON form
	@RequestMapping("/bookings")
	public Iterable<Booking> getBookings() {
		return repository.findAll();
	}

}
