package spring_boot.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring_boot.dto.ProductDTO;
import spring_boot.entity.ProductEntity;
import spring_boot.repository.ProductRepository;
import spring_boot.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public ProductDTO createProduct(ProductDTO productDTO) {
		ProductEntity product = new ProductEntity();
		product.setName(productDTO.getName());
		product.setDescription(productDTO.getDescription());
		product.setPrice(productDTO.getPrice());
		product.setQuantity(productDTO.getQuantity());
		product.setCreatedAt(productDTO.getCreatedAt());
		product.setUpdatedAt(productDTO.getUpdatedAt());
        product = productRepository.save(product);
        productDTO.setId(product.getId());
        return productDTO;
	}
	
	@Override
    public ProductDTO updateProduct(ProductDTO productDTO) {
        ProductEntity product = productRepository.findById(productDTO.getId()).orElseThrow();
		product.setName(productDTO.getName());
		product.setDescription(productDTO.getDescription());
		product.setPrice(productDTO.getPrice());
		product.setQuantity(productDTO.getQuantity());
		product.setUpdatedAt(productDTO.getUpdatedAt());
		product = productRepository.save(product);
        return productDTO;
    }
	
	@Override
	public void deleteProductById(Long id) {
		productRepository.deleteById(id);
	}

	@Override
    public ProductDTO getProductById(Long id) {
        ProductEntity product = productRepository.findById(id).orElseThrow();
        return new ProductDTO(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getQuantity(), product.getCreatedAt(), product.getUpdatedAt());
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream().map(product -> new ProductDTO(
				product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getQuantity(), product.getCreatedAt(), product.getUpdatedAt())).collect(Collectors.toList());
    }
}
