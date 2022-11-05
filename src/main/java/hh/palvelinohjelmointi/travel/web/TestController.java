package hh.palvelinohjelmointi.travel.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import hh.palvelinohjelmointi.travel.domain.BookingRepository;

@Controller
public class TestController {

	@Autowired
	private BookingRepository repository;

	// returns index page
	/*
	 * @GetMapping("/") public String showIndexPage() { return
	 * "redirect:/timetable"; }
	 */
	@GetMapping("/index")
	public String index(Model model) {
		return "index";
	}

}
