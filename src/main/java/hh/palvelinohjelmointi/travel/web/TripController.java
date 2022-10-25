package hh.palvelinohjelmointi.travel.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import hh.palvelinohjelmointi.travel.domain.TripRepository;

@Controller
public class TripController {

	@Autowired
	public TripRepository tripRepo;

	// lists all trips
	@GetMapping("/timetable")
	public String showAlltrips(Model model) {
		model.addAttribute("trips", tripRepo.findAll());
		return "timetable";
	}
}
