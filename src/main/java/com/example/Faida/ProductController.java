import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping
("/api/products")
public class ProductController {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final SubcategoryRepository subcategoryRepository;

    public ProductController(ProductRepository productRepository, UserRepository userRepository,
                             CategoryRepository categoryRepository, SubcategoryRepository subcategoryRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.subcategoryRepository = subcategoryRepository;
    }

    @PostMapping("/")
    public ResponseEntity<?> addProduct(@RequestParam("shopOwnerId") Long shopOwnerId,
                                        @RequestBody ProductRequest request) {
        // Retrieve the shop owner from the repository
        User shopOwner = userRepository.findById(shopOwnerId)
                .orElseThrow(() -> new RuntimeException("Shop owner not found"));

        // Create the product
        Product product = new Product();
        product.setName(request.getName());

        // Add the product to the shop owner's list of products
        shopOwner.getProducts().add(product);

        // Save the product and shop owner
        productRepository.save(product);
        userRepository.save(shopOwner);

        return ResponseEntity.ok("Product added successfully");
    }

    // Other product-related endpoints
}
