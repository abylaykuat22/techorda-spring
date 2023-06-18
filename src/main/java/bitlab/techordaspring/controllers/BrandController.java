package bitlab.techordaspring.controllers;

import bitlab.techordaspring.models.Brand;
import bitlab.techordaspring.models.Product;
import bitlab.techordaspring.repositories.ProductRepository;
import bitlab.techordaspring.services.BrandService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BrandController {

  @Autowired
  private BrandService brandService;

  @Autowired
  private ProductRepository productRepository;

  @GetMapping("/brands")
  public String getBrands(Model model) {
    List<Brand> brands = brandService.getBrands();
    model.addAttribute("brands", brands);
    return "brands";
  }

  @GetMapping("/brand-details/{id}")
  public String getBrandById(@PathVariable Long id, Model model) {
    Brand brand = brandService.getBrandById(id);
    model.addAttribute("brand", brand);
    List<Product> products = productRepository.findAll();
    products.removeAll(brand.getProducts());
    model.addAttribute("products", products);
    return "brandDetails";
  }

  @PostMapping("/addProductToBrand")
  public String addProductToBrand(
      @RequestParam(name = "product_id") Long productId,
      @RequestParam(name = "brand_id") Long brandId
      ) {
    brandService.addProductToBrand(brandId, productId);
    return "redirect:/brand-details/"+brandId;
  }

  @PostMapping("/deleteProduct")
  public String deleteProduct(
      @RequestParam(name = "product_id") Long productId,
      @RequestParam(name = "brand_id") Long brandId
  ) {
    brandService.deleteProduct(brandId, productId);
    return "redirect:/brand-details/"+brandId;
  }
}
