package SpringBoot.FinalProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomepageController {
	@GetMapping("/")
	public String homePage(Model model){
		return "home";
	}
}
