package spring_boot.dto;

public class BookDTO {
	private Long id;
	private String title;
    private String author;
    private String genre;
	private String description;
    private double price;
    private int stock;
    
    public BookDTO() {}

    public BookDTO(String title, String author, String genre, String description, double price, int stock) {
		this.title = title;
		this.author = author;
		this.genre = genre;
		this.description = description;
		this.price = price;
		this.stock = stock;
	}
    
    public BookDTO(Long id, String title, String author, String genre, String description, double price, int stock) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.genre = genre;
		this.description = description;
		this.price = price;
		this.stock = stock;
	}
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
    
}