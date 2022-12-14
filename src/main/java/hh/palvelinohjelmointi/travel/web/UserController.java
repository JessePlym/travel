package hh.palvelinohjelmointi.travel.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import hh.palvelinohjelmointi.travel.domain.SignUpForm;
import hh.palvelinohjelmointi.travel.domain.User;
import hh.palvelinohjelmointi.travel.domain.UserRepository;

@Controller
public class UserController {

	@Autowired
	private UserRepository userRepo;

	@GetMapping("/signup")
	public String addUser(Model model) {
		model.addAttribute("signupform", new SignUpForm());
		return "signup";
	}

	@PostMapping("/saveuser")
	public String saveUser(@Valid @ModelAttribute("signupform") SignUpForm form, BindingResult bindingResult) {
		if (form.getPassword().length() < 7) {
			bindingResult.rejectValue("password", "err.password", "Password too short");
			return "signup";
		}
		if (!bindingResult.hasErrors()) {
			if (form.getPassword().equals(form.getPasswordCheck())) {
				// encrypts password that users inputs
				String password = form.getPassword();
				BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
				String hashPassword = bc.encode(password);

				User newUser = new User();
				newUser.setPasswordHash(hashPassword);
				newUser.setEmail(form.getUsername()); // email is used as username
				newUser.setRole("USER");
				if (userRepo.findByUsername(form.getUsername()) == null) {
					userRepo.save(newUser); // if user doesn't exist already then save new user
				} else {
					bindingResult.rejectValue("username", "err.username", "Username alreade exists");
					return "signup";
				}
			} else {
				bindingResult.rejectValue("passwordCheck", "err.passCheck", "Passwords does not match");
				return "signup";
			}
		} else {
			return "signup";
		}
		return "redirect:/login";
	}

	// deletes user from user repository. if user role is admin delete won't work.
	@GetMapping("/deleteuser/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteUser(@PathVariable(name = "id") Long id) {
		if (!(userRepo.findById(id).get().getRole().equals("ADMIN"))) {
			userRepo.deleteById(id);
		}
		return "redirect:/timetable";
	}

}
