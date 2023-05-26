import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.*;

// User.java
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String phoneNumber;

    @NotBlank
    private String countryCode;

    private String otp;

    // Constructors, getters, and setters
}

// UserRepository.java
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByPhoneNumberAndCountryCode(String phoneNumber, String countryCode);
}

// UserController.java
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepository;
    private final SmsService smsService; // Implement your own SMS service

    public UserController(UserRepository userRepository, SmsService smsService) {
        this.userRepository = userRepository;
        this.smsService = smsService;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticateUser(@RequestBody Map<String, String> request) {
        String phoneNumber = request.get("phoneNumber");
        String countryCode = request.get("countryCode");

        // Generate OTP and send it to the user's phone number
        String otp = generateOTP();
        smsService.sendOTP(phoneNumber, otp); // Implement your own SMS service

        // Save user details in the database
        User user = userRepository.findByPhoneNumberAndCountryCode(phoneNumber, countryCode)
                .orElseGet(() -> new User());
        user.setPhoneNumber(phoneNumber);
        user.setCountryCode(countryCode);
        user.setOtp(otp);
        userRepository.save(user);

        return ResponseEntity.ok("OTP sent successfully");
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verifyOTP(@RequestBody Map<String, String> request) {
        String phoneNumber = request.get("phoneNumber");
        String otp = request.get("otp");

        // Retrieve user by phone number
        Optional<User> userOptional = userRepository.findByPhoneNumberAndCountryCode(phoneNumber);
        if (userOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid phone number");
        }

        User user = userOptional.get();

        // Check if OTP matches
        if (otp.equals(user.getOtp())) {
            // OTP is valid, allow the user to enter name and email
            return ResponseEntity.ok("OTP verification successful");
        } else {
            return ResponseEntity.badRequest().body("Invalid OTP");
        }
    }

    @PostMapping("/details")
    public ResponseEntity<?> saveUserDetails(@RequestBody User user) {
        // Save the user details in the database
        User existingUser = userRepository.findById(user.getId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        userRepository.save(existingUser);

        return ResponseEntity.ok("User details saved successfully");
    }

    private String generateOTP() {
        // Implement OTP generation logic based on your requirements
        // This is a simplified example
        return String.valueOf(new Random().nextInt(999999));
    }
}
