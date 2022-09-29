//package SpringBoot.FinalProject.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class UserService {
//
//	@Autowired
//	private final JpaUserRepository jpaUserRepository;
//
//	public UserService(JpaUserRepository jpaUserRepository) {
//		this.jpaUserRepository = jpaUserRepository;
//	}
//
//
//	public User registerUser(String userName, String password, String email, String phoneNumber, String firstName, String lastName){
//		if (userName == null && password ==null){
//			return null;
//		}else{
//			User user = new User();
//			user.setUserName(userName);
//			user.setPassword(password);
//			user.setEmail(email);
//			user.setPhoneNumber(phoneNumber);
//			user.setFirstName(firstName);
//			user.setLastName(lastName);
//			return jpaUserRepository.save(user);
//		}
//	}
//
//	public User authenticate(String userName, String password){
//		return jpaUserRepository.findByUserNameAndPassword(userName, password).orElse(null);
//	}
//}
