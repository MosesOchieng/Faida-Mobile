import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String phoneNumber;

    @NotBlank
    private String otp;

    @NotBlank
    private String username;

    @NotBlank
    @Email
    private String email;

    private String shopName;
    private String shopLocation;

    // Constructors, getters, and setters
}
