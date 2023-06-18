package bitlab.techordaspring.services;

import bitlab.techordaspring.models.Brand;
import bitlab.techordaspring.models.Product;
import bitlab.techordaspring.repositories.BrandRepository;
import bitlab.techordaspring.repositories.ProductRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrandService {
  @Autowired
  private BrandRepository brandRepository;

  @Autowired
  private ProductRepository productRepository;
  public List<Brand> getBrands() {
    return brandRepository.findAll();
  }

  public Brand getBrandById(Long id) {
    return brandRepository.findById(id).orElseThrow();
  }

  public void addProductToBrand(Long brandId, Long productId) {
    Brand brand = getBrandById(brandId);
    Product product = productRepository.findById(productId).orElseThrow();
    List<Product> products = brand.getProducts();
    if (!products.contains(product)){
      products.add(product);
    }
    brandRepository.save(brand);
  }

  public void deleteProduct(Long brandId, Long productId) {
    Brand brand = getBrandById(brandId);
    Product product = productRepository.findById(productId).orElseThrow();
    List<Product> products = brand.getProducts();
    products.remove(product);
    brandRepository.save(brand);
  }
}
