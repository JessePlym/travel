package hh.palvelinohjelmointi.travel.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import hh.palvelinohjelmointi.travel.domain.TrainType;
import hh.palvelinohjelmointi.travel.domain.TrainTypeRepository;

@Controller
public class TrainTypeController {

	@Autowired
	private TrainTypeRepository trainTypeRepository;

	// this method directs to traintype adding page
	// only admin can add new types
	@GetMapping("/addtype")
	// @PreAuthorize("hasAuthority('ADMIN')")
	public String addTrainType(Model model) {
		model.addAttribute("traintype", new TrainType());
		return "addtype";
	}

	// saves new train type to repository
	@PostMapping("/savetype")
	// @PreAuthorize("hasAuthority('ADMIN')")
	public String saveType(TrainType traintype) {
		trainTypeRepository.save(traintype);
		return "redirect:/timetable";
	}

	// this method deletes selected traintype by id
	@GetMapping("/deletetype/{id}")
	// @PreAuthorize("hasAuthority('ADMIN')")
	public String deleteType(@PathVariable(name = "id") Long id) {
		trainTypeRepository.deleteById(id);
		return "redirect:/timetable";
	}

}
