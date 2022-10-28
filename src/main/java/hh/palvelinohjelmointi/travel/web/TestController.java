package hh.palvelinohjelmointi.travel.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

	// returns index page
	@GetMapping("/")
	public String showIndexPage() {
		return "redirect:/timetable";
	}

}
