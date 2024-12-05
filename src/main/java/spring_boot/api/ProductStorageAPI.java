package spring_boot.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import spring_boot.dto.ProductDTO;
import spring_boot.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductStorageAPI {
	
	@Autowired
    private ProductService productService;
	
	@PostMapping
    public ProductDTO createProduct(@RequestBody ProductDTO model) {
        return productService.createProduct(model);
    }
	
	@PutMapping
    public ProductDTO updateProduct(@RequestBody ProductDTO model) {
        return productService.updateProduct(model);
    }

    @DeleteMapping
    public void deleteProduct(@RequestBody long id) {
        productService.deleteProductById(id);
    }

    @GetMapping("/{id}")
    public ProductDTO getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @GetMapping
    public List<ProductDTO> getAllProducts() {
        return productService.getAllProducts();
    }
}