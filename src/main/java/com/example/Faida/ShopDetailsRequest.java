import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.data.jpa.repository.JpaRepository;
public class ShopDetailsRequest {
    private String phoneNumber;
    private String shopName;
    private String shopLocation;

    // Getters and setters
}