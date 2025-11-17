package unisul.estoquebackend.category.domain;

public class Category {

	public enum Size {
		SMALL("Pequeno"), MEDIUM("Médio"), LARGE("Grande");
		
		private final String friendlyName;

		Size(String friendlyName) {
			this.friendlyName = friendlyName;
		}
		
		public String getFriendlyName() {
			return this.friendlyName;
		}
	}
	public enum Packaging {
		PAPER("Papel"), CARDBOARD("Papelão"), PLASTIC("Plástico"), TIN("Lata"), GLASS("Vidro"), WOOD("Madeira"), CLOTH("Tecido");
		
		private final String friendlyName;

		Packaging(String friendlyName) {
			this.friendlyName = friendlyName;
		}
		
		public String getFriendlyName() {
			return this.friendlyName;
		}
	}

	private Long id;
	private String name;
	private Size size;
	private Packaging packaging;
	
	public Category() {
	}
	
	public Category(Long id, String name, Size size, Packaging packaging) {
		this.setId(id);
		this.setName(name);
		this.setSize(size);
		this.setPackaging(packaging);
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		if (name == null || name.trim().isEmpty()) {
			throw new IllegalArgumentException("O nome da categoria não pode ser nulo ou vazio");
		}
		this.name = name.trim();
	}
	
	public Size getSize() {
		return size;
	}
	
	public void setSize(Size size) {
		if (size == null) {
			throw new IllegalArgumentException("O tamanho da categoria não pode ser nulo");
		}
		this.size = size;
	}
	
	public Packaging getPackaging() {
		return packaging;
	}
	
	public void setPackaging(Packaging packaging) {
		if (packaging == null) {
			throw new IllegalArgumentException("A embalagem da categoria não pode ser nula");
		}
		this.packaging = packaging;
	}
}
