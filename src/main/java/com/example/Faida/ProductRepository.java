@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // Additional methods if needed
}

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    // Additional methods if needed
}

@Repository
public interface SubcategoryRepository extends JpaRepository<Subcategory, Long> {
    // Additional methods if needed
}
