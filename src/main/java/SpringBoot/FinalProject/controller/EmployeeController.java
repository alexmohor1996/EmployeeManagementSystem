package SpringBoot.FinalProject.controller;

import SpringBoot.FinalProject.model.Employee;
import SpringBoot.FinalProject.repository.JpaEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("employee")
public class EmployeeController {

	@Autowired
	public JpaEmployeeRepository jpaEmployeeRepository;

	@GetMapping("/")
	public String showEmployeeList(Model model){
		//model.addAttribute("employees", repo.getEmployees());
		model.addAttribute("employees", jpaEmployeeRepository.findAll());
		return "employee/list";
	}

	@GetMapping("/add")
	public String addEmployeeForm(Model model){
		return "employee/add";
	}

	@PostMapping("/add")
	public RedirectView addEmployee(Model model,
									@RequestParam("first_name") String firstName,
									@RequestParam("last_name") String lastName,
									@RequestParam("email") String email,
									@RequestParam("phone_number") String phoneNumber,
									@RequestParam("salary") int salary){
		//repo.addEmployee(new Employee(UUID.randomUUID(), firstName, lastName, email, phoneNumber, salary));
		//model.addAttribute("employee", repo.getEmployees());
		jpaEmployeeRepository.saveAndFlush(new Employee(UUID.randomUUID(), firstName, lastName, email, phoneNumber, salary));
		return new RedirectView("/employee/");
	}

	@GetMapping("/delete/{id}")
	public RedirectView deleteEmployee(Model model, @PathVariable("id") UUID employeeId){
		//repo.deleteEmployeeById(employeeId);
		//model.addAttribute("employee", repo.getEmployees());
		jpaEmployeeRepository.deleteById(employeeId);
		return new RedirectView("/employee/");
	}

	@GetMapping("/edit/{id}")
	public String editEmployeeForm(Model model, @PathVariable("id") UUID employeeId){
		Optional<Employee> employee = jpaEmployeeRepository.findById(employeeId);
		model.addAttribute("employee", employee.get());
		return "employee/edit";
	}
	@GetMapping ("/profile/{id}")
	public String showUsersProfile(Model model, @PathVariable("id") UUID employeeId){
		Optional<Employee> employee = jpaEmployeeRepository.findById(employeeId);
		model.addAttribute("employee", employee.get());
		return "employee/profile";
	}

	@PostMapping("/edit")
	public RedirectView editEmployee(Model model,
									 @RequestParam("employee_id") UUID id,
									 @RequestParam("first_name") String updatedFirstName,
									 @RequestParam("last_name") String updatedLastName,
									 @RequestParam("email") String updatedEmail,
									 @RequestParam("phone_number") String updatedPhoneNumber,
									 @RequestParam("salary") int updatedSalary){
		Optional<Employee> employee = jpaEmployeeRepository.findById(id);
		employee.get().setFirstName(updatedFirstName);
		employee.get().setLastName(updatedLastName);
		employee.get().setEmail(updatedEmail);
		employee.get().setPhoneNumber(updatedPhoneNumber);
		employee.get().setSalary(updatedSalary);
		jpaEmployeeRepository.save(employee.get());
		return new RedirectView("/employee/");
	}
}
