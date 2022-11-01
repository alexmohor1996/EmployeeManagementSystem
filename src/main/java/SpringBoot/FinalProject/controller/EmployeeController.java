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

	/**
	 *
	 * @param model parameter used to model the main employee list
	 * @return method used to show the employee list page
	 */
	@GetMapping("/")
	public String showEmployeeList(Model model){
		model.addAttribute("employees", jpaEmployeeRepository.findAll());
		return "employee/list";
	}

	/**
	 *
	 * @param model parameter used to model the Employee hiring page
	 * @return method used to show the Employee hiring page
	 */
	@GetMapping("/add")
	public String addEmployeeForm(Model model){
		return "employee/add";
	}

	/**
	 *
	 * @param firstName parameter used to add the first name of a new employee
	 * @param lastName parameter used to add the last name of a new employee
	 * @param email parameter used to add the email of a new employee
	 * @param phoneNumber parameter used to add the phone number of a new employee
	 * @param salary parameter used to add the salaray of a new employee
	 * @return method used to redirect the Employee Hiring page to the Employee List, where we can see the new hired employee
	 */

	@PostMapping("/add")
	public RedirectView addEmployee(@RequestParam("first_name") String firstName,
									@RequestParam("last_name") String lastName,
									@RequestParam("email") String email,
									@RequestParam("phone_number") String phoneNumber,
									@RequestParam("salary") int salary){
		jpaEmployeeRepository.saveAndFlush(new Employee(UUID.randomUUID(), firstName, lastName, email, phoneNumber, salary));
		return new RedirectView("/employee/");
	}

	/**
	 *
	 * @param employeeId the ID, of the employee, used to delete the selected employee
	 * @return the method used to show
	 */

	@GetMapping("/delete/{id}")
	public RedirectView deleteEmployee(@PathVariable("id") UUID employeeId){
		jpaEmployeeRepository.deleteById(employeeId);
		return new RedirectView("/employee/");
	}

	/**
	 *
	 * @param model used to model the application data
	 * @param employeeId parameter used show the edit page of the selected employee from the list, as the ID is unique (by using UUID)
	 * @return returns a string which contains the path to the Edit Employee page
	 */
	@GetMapping("/edit/{id}")
	public String editEmployeeForm(Model model, @PathVariable("id") UUID employeeId){
		Optional<Employee> employee = jpaEmployeeRepository.findById(employeeId);
		model.addAttribute("employee", employee.get());
		return "employee/edit";
	}

	/**
	 *
	 * @param model used to model the application data
	 * @param employeeId parameter used to show the profile page of the selected employee from the list, as the ID is unique (by using UUID)
	 * @return returns a string which contains the path to the Employee Profile pagae
	 */
	@GetMapping ("/profile/{id}")
	public String showUsersProfile(Model model, @PathVariable("id") UUID employeeId){
		Optional<Employee> employee = jpaEmployeeRepository.findById(employeeId);
		model.addAttribute("employee", employee.get());
		return "employee/profile";
	}


	/**
	 *
	 * @param id is used for the application user to edit the selected employee's details
	 * @param updatedFirstName the employee's first name after it has been updated by the application user
	 * @param updatedLastName the employee's last name after it has been updated by the application user
	 * @param updatedEmail the employee's email after it has been updated by the application user
	 * @param updatedPhoneNumber the employee's phone number after it has been updated by the application user
	 * @param updatedSalary the employee's salary after it has been updated by the application user
	 * @return method used to redirect the page view, to the employee's list which will show the updated details for the employees
	 */
	@PostMapping("/edit")
	public RedirectView editEmployee(@RequestParam("employee_id") UUID id,
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
