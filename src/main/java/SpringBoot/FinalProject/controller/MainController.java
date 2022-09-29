package SpringBoot.FinalProject.controller;

import SpringBoot.FinalProject.model.Employee;
import SpringBoot.FinalProject.model.User;
import SpringBoot.FinalProject.repository.JpaUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.util.UUID;

@Controller
public class MainController {

	@Autowired
	public JpaUserRepository jpaUserRepository;

	@GetMapping("/")
	public String showIndexPage(){
		return "index";
	}

	@GetMapping("/register")
	public String registerUserForm(){
		return "register";
	}

	@PostMapping("/register")
	public RedirectView registerUser(Model model,
									 @RequestParam("user_name") String userName,
									 @RequestParam("password") String password,
									 @RequestParam("first_name") String firstName,
									 @RequestParam("last_name") String lastName,
									 @RequestParam("email") String email,
									 @RequestParam("phone_number") String phoneNumber){
		jpaUserRepository.saveAndFlush(new User(UUID.randomUUID(), userName, password, firstName, lastName, email, phoneNumber));
		return new RedirectView("/");
	}
}
