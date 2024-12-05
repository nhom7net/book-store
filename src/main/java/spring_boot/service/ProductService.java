package spring_boot.service;

import java.util.List;
import spring_boot.dto.ProductDTO;

public interface ProductService {
    ProductDTO createProduct(ProductDTO productDTO);
    ProductDTO updateProduct(ProductDTO productDTO);
    void deleteProductById(Long id);
    ProductDTO getProductById(Long id);
    List<ProductDTO> getAllProducts();
}
