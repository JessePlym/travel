package hh.palvelinohjelmointi.travel.web;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import hh.palvelinohjelmointi.travel.domain.BookingRepository;
import hh.palvelinohjelmointi.travel.domain.User;
import hh.palvelinohjelmointi.travel.domain.UserRepository;

@Controller
public class BookingController {

	@Autowired
	public BookingRepository bookingRepo;

	@Autowired
	public UserRepository userRepo;

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

}
