package hh.palvelinohjelmointi.travel.web;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import hh.palvelinohjelmointi.travel.domain.Booking;
import hh.palvelinohjelmointi.travel.domain.BookingRepository;
import hh.palvelinohjelmointi.travel.domain.Trip;
import hh.palvelinohjelmointi.travel.domain.TripRepository;
import hh.palvelinohjelmointi.travel.domain.User;
import hh.palvelinohjelmointi.travel.domain.UserRepository;

@CrossOrigin
@Controller
public class BookingController {

	@Autowired
	private BookingRepository bookingRepo;

	@Autowired
	private TripRepository tripRepo;

	@Autowired
	private UserRepository userRepo;

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
	@PreAuthorize("hasAuthority('ADMIN')")
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
		try {
			model.addAttribute("booking", new Booking());
			model.addAttribute("trip", tripRepo.findById(id).get()); // returns trip object by id
			return "booktrip";
		} catch (Exception e) {
			model.addAttribute("error", "error trying book trip");
			e.printStackTrace();
			return "error";
		}
	}

	// adds booked trip and current user to bookings
	@PostMapping("/book")
	public String saveBooking(Booking booking, Trip trip, Principal principal) {
		booking.setTrip(trip); // this sets the selected trip to new booking
		booking.setUser(userRepo.findByUsername(principal.getName())); // this sets the current user to new booking
		bookingRepo.save(booking);
		return "redirect:/ownbookings";
	}

	// this method deletes booking from repository
	// gets request param from thymeleaf modal
	@GetMapping("/deletebooking")
	public String deleteBooking(@RequestParam(name = "bookingNumber") Long id, Principal principal) {
		// this checks that the logged user is same as booking owner. Prevents users to
		// delete other bookings than own.
		if (bookingRepo.findById(id).get().getUser().getUsername().equals(principal.getName())) {
			bookingRepo.deleteById(id);
		}
		return "redirect:/ownbookings";
	}

	// this method gets users booking by id to web page
	@GetMapping("/editbooking/{id}")
	public String editBooking(@PathVariable(name = "id") Long id, Model model) {
		model.addAttribute("booking", bookingRepo.findById(id));
		return "editbooking";
	}

	@PostMapping("/edit")
	public String saveEditedBooking(Booking booking, Principal principal) {
		if (booking.getUser().getUsername().equals(principal.getName())) {
			bookingRepo.save(booking);
		}
		return "redirect:/ownbookings";

	}

}
