package unisul.estoquebackend.report.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import unisul.estoquebackend.category.domain.Category;
import unisul.estoquebackend.category.service.CategoryService;
import unisul.estoquebackend.movement.domain.Movement;
import unisul.estoquebackend.movement.service.MovementService;
import unisul.estoquebackend.product.domain.Product;
import unisul.estoquebackend.product.service.ProductService;
import unisul.estoquebackend.report.controller.representation.BalanceReportOutput;
import unisul.estoquebackend.report.controller.representation.CategoryReportOutput;
import unisul.estoquebackend.report.controller.representation.TopMovementOutput;

@Service
public class ReportService {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private MovementService movementService;
	
	@Transactional(readOnly = true)
	public BalanceReportOutput getBalanceReport() {
		List<Product> products = productService.findAll();
		
		int totalProducts = products.size();
		int totalQuantity = products.stream()
				.mapToInt(Product::getQuantity)
				.sum();
		
		// Cálculo de valor usando BigDecimal
		BigDecimal totalValue = products.stream()
				.map(p -> p.getPrice().multiply(new BigDecimal(p.getQuantity())))
				.reduce(BigDecimal.ZERO, BigDecimal::add);
		
		return new BalanceReportOutput(totalProducts, totalQuantity, totalValue);
	}
	
	@Transactional(readOnly = true)
	public List<TopMovementOutput> getTopMovements() {
		List<Movement> movements = movementService.findAll();
		
		Map<Long, Integer> movementsByProduct = new HashMap<>();
		Map<Long, String> productNames = new HashMap<>();
		
		for (Movement movement : movements) {
			Long productId = movement.getProductId();
			movementsByProduct.put(productId, 
					movementsByProduct.getOrDefault(productId, 0) + movement.getQuantity());
			
			if (!productNames.containsKey(productId)) {
				Product product = productService.find(productId);
				productNames.put(productId, product.getName());
			}
		}
		
		return movementsByProduct.entrySet().stream()
				.map(entry -> new TopMovementOutput(
						entry.getKey(),
						productNames.get(entry.getKey()),
						entry.getValue(),
						"TOTAL"
				))
				.sorted((a, b) -> b.getTotalMovements().compareTo(a.getTotalMovements()))
				.limit(10)
				.collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public List<CategoryReportOutput> getCategoryReport() {
		List<Category> categories = categoryService.findAll();
		List<CategoryReportOutput> report = new ArrayList<>();
		
		for (Category category : categories) {
			List<Product> products = productService.findByCategoryId(category.getId());
			
			int totalProducts = products.size();
			int totalQuantity = products.stream()
					.mapToInt(Product::getQuantity)
					.sum();
					
			// Cálculo de valor usando BigDecimal
			BigDecimal totalValue = products.stream()
					.map(p -> p.getPrice().multiply(new BigDecimal(p.getQuantity())))
					.reduce(BigDecimal.ZERO, BigDecimal::add);
			
			report.add(new CategoryReportOutput(
					category.getId(),
					category.getName(),
					totalProducts,
					totalQuantity,
					totalValue
			));
		}
		
		return report;
	}
	
	@Transactional(readOnly = true)
	public List<Product> getLowStockReport() {
		return productService.findByLowQuantity();
	}
}
