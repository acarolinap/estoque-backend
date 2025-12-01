package unisul.estoquebackend.report.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import unisul.estoquebackend.product.controller.representation.ProductOutput;
import unisul.estoquebackend.product.mapper.ProductMapper;
import unisul.estoquebackend.report.controller.representation.BalanceReportOutput;
import unisul.estoquebackend.report.controller.representation.CategoryReportOutput;
import unisul.estoquebackend.report.controller.representation.TopMovementOutput;
import unisul.estoquebackend.report.service.ReportService;

@RestController
@RequestMapping("/api/relatorios")
@CrossOrigin(origins = {"http://localhost:5173", "https://a3-sistemas-distribuidos-deploy-fro.vercel.app"}, allowCredentials = "true")
public class ReportController {
	
	@Autowired
	private ReportService service;
	
	@GetMapping("/balanco")
	public HttpEntity<Object> getBalanceReport() {
		BalanceReportOutput report = service.getBalanceReport();
		return ResponseEntity.ok(report);
	}
	
	@GetMapping("/top-movimentacoes")
	public HttpEntity<Object> getTopMovements() {
		List<TopMovementOutput> report = service.getTopMovements();
		return ResponseEntity.ok(report);
	}
	
	@GetMapping("/categorias")
	public HttpEntity<Object> getCategoryReport() {
		List<CategoryReportOutput> report = service.getCategoryReport();
		return ResponseEntity.ok(report);
	}
	
	@GetMapping("/estoque-baixo")
	public HttpEntity<Object> getLowStockReport() {
		List<ProductOutput> report = service.getLowStockReport().stream()
				.map(ProductMapper::toRepresentation)
				.toList();
		return ResponseEntity.ok(report);
	}
}
