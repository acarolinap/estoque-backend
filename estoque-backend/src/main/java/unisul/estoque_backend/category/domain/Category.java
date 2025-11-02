package unisul.estoque_backend.category.domain;

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
		this.name = name;
	}
	
	public Size getSize() {
		return size;
	}
	
	public void setSize(Size size) {
		this.size = size;
	}
	
	public Packaging getPackaging() {
		return packaging;
	}
	
	public void setPackaging(Packaging packaging) {
		this.packaging = packaging;
	}
}
