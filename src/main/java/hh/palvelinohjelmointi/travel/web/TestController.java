package hh.palvelinohjelmointi.travel.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

	// returns index page

	@GetMapping("/index")
	public String index(Model model) {
		return "index";
	}

}
