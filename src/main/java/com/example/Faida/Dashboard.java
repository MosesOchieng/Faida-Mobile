import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Dashboard {
    // Existing code...

    @OneToMany(mappedBy = "shopOwner")
    private List<Product> products = new ArrayList<>();

    // Getters and setters
}
