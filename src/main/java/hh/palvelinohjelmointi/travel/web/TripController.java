package hh.palvelinohjelmointi.travel.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import hh.palvelinohjelmointi.travel.domain.TrainTypeRepository;
import hh.palvelinohjelmointi.travel.domain.Trip;
import hh.palvelinohjelmointi.travel.domain.TripRepository;

@Controller
public class TripController {

	@Autowired
	public TripRepository tripRepo;

	@Autowired
	public TrainTypeRepository typeRepo;

	/*
	 * lists all trips and if the user has made a search, then lists trips by the
	 * search. Method uses request parameter and its value is from the search input.
	 * Default value for search is empty string and then all trips are shown by
	 * default
	 */
	@GetMapping("/timetable")
	public String showAlltrips(Model model, @RequestParam(name = "destination", defaultValue = "") String searchInput) {
		// first checks if searchInput is not empty string
		if (!(searchInput.equalsIgnoreCase(""))) {
			// returns message to user if no results found
			if (tripRepo.findByDestination(searchInput).isEmpty()) {
				model.addAttribute("notFoundMsg", "No results found");
			}
			// return trips by destination using searchInput
			model.addAttribute("trips", tripRepo.findByDestination(searchInput));
			return "timetable";
		}
		// otherwise returns all trips
		model.addAttribute("trips", tripRepo.findAll());
		return "timetable";
	}

	// This method directs to addtrip site. Only admin can add new trips
	@GetMapping("/addtrip")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String addNewTrip(Model model) {
		model.addAttribute("trip", new Trip());
		model.addAttribute("traintypes", typeRepo.findAll()); // train types will be in dropdown list
		return "addtrip";
	}

	// this method saves new trip to repository
	@PostMapping("/savetrip")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String saveTrip(Trip trip) {
		tripRepo.save(trip);
		return "redirect:/timetable";
	}

	// delete trip from repository using selected id
	@GetMapping("/deletetrip/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteTrip(@PathVariable(name = "id") Long id) {
		tripRepo.deleteById(id);
		return "redirect:/timetable";
	}

	// directs selected trip by id to editing page
	@GetMapping("/edittrip/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String editTrip(@PathVariable("id") Long id, Model model) {
		model.addAttribute("trip", tripRepo.findById(id));
		model.addAttribute("traintypes", typeRepo.findAll());
		return "edittrip";
	}

}
