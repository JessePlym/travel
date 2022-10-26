package hh.palvelinohjelmointi.travel.web;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import hh.palvelinohjelmointi.travel.domain.Booking;
import hh.palvelinohjelmointi.travel.domain.BookingRepository;
import hh.palvelinohjelmointi.travel.domain.TrainTypeRepository;
import hh.palvelinohjelmointi.travel.domain.Trip;
import hh.palvelinohjelmointi.travel.domain.TripRepository;
import hh.palvelinohjelmointi.travel.domain.User;
import hh.palvelinohjelmointi.travel.domain.UserRepository;

@Controller
public class BookingController {

	@Autowired
	public BookingRepository bookingRepo;

	@Autowired
	public TripRepository tripRepo;

	@Autowired
	public UserRepository userRepo;

	@Autowired
	public TrainTypeRepository typeRepo;

	// this method shows all bookings made by user
	// only the owner can make changes
	@GetMapping("/ownbookings")
	public String showUserBookings(Model model, Principal principal) {
		String username = principal.getName();
		User user = userRepo.findByUsername(username);
		model.addAttribute("bookings", bookingRepo.findByUser(user));
		return "booking";
	}

	// shows all bookings for testing
	@GetMapping("/allbookings")
	public String showAllBookings(Model model) {
		model.addAttribute("bookings", bookingRepo.findAll());
		return "booking";
	}

	/*
	 * This method directs to confirmation page and then the trip is added to users
	 * bookings list
	 */
	@GetMapping("/booktrip/{id}")
	public String bookThisTrip(@PathVariable(name = "id") Long id, Model model) {
		model.addAttribute("booking", new Booking());
		model.addAttribute("trip", tripRepo.findById(id));
		return "booktrip";
	}

	// adds booked trip to bookings
	@PostMapping("/book")
	public String saveBooking(Booking booking, Trip trip) {
		booking.setTrip(trip); // this sets the selected trip to new booking
		bookingRepo.save(booking);
		return "redirect:/allbookings";
	}

}
