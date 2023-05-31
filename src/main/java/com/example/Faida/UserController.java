import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserRepository userRepository;
    private final SmsService smsService;

    public UserController(UserRepository userRepository, SmsService smsService) {
        this.userRepository = userRepository;
        this.smsService = smsService;
    }

    @PostMapping("/otp")
    public ResponseEntity<?> requestOTP(@RequestParam("phoneNumber") String phoneNumber) {
        // Generate OTP and send it to the user's phone number
        String otp = generateOTP();
        smsService.sendOTP(phoneNumber, otp);

        // Save the OTP and phone number in the repository
        User user = new User();
        user.setPhoneNumber(phoneNumber);
        user.setOtp(otp);
        userRepository.save(user);

        return ResponseEntity.ok("OTP sent successfully");
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody UserRegistrationRequest request) {
        // Retrieve the user by phone number
        User user = userRepository.findByPhoneNumber(request.getPhoneNumber())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Validate the OTP
        if (!request.getOtp().equals(user.getOtp())) {
            return ResponseEntity.badRequest().body("Invalid OTP");
        }

        // Update user details
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        userRepository.save(user);

        return ResponseEntity.ok("User signed up successfully");
    }

    @PostMapping("/shop")
    public ResponseEntity<?> addShopDetails(@RequestBody ShopDetailsRequest request) {
        // Retrieve the user by phone number
        User user = userRepository.findByPhoneNumber(request.getPhoneNumber())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Update shop details
        user.setShopName(request.getShopName());
        user.setShopLocation(request.getShopLocation());
        userRepository.save(user);

        return ResponseEntity.ok("Shop details added successfully");
    }

    @GetMapping("/dashboard")
    public ResponseEntity<?> getDashboard() {
        // Fetch and return the user's dashboard data
        // You can customize this method to retrieve the relevant data for the dashboard
        return ResponseEntity.ok("Welcome to the dashboard!");
    }

    private String generateOTP() {
        // Implement OTP generation logic based on your requirements
        // This is a simplified example
        return String.valueOf(new Random().nextInt(999999));
    }
}
