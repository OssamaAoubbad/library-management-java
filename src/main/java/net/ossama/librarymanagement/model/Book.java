package net.ossama.librarymanagement.model;
import net.ossama.librarymanagement.model.enums.BookCategory;

import java.time.LocalDateTime;
import java.util.Objects;

public class Book {
    private Long id;
    private String title;
    private String author;
    private String isbn;
    private Integer publishYear;
    private BookCategory category;
    private int totalQuantity;
    private int availableQuantity;
    private String description;
    private boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Constructeur par défaut
    public Book() {
        this.totalQuantity = 1;
        this.availableQuantity = 1;
        this.category = BookCategory.OTHER;
        this.isActive = true;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    // Constructeur avec paramètres principaux
    public Book(String title, String author, BookCategory category) {
        this();
        this.title = title;
        this.author = author;
        this.category = category;
    }

    // Constructeur complet
    public Book(String title, String author, String isbn, Integer publishYear,
                BookCategory category, int totalQuantity) {
        this(title, author, category);
        this.isbn = isbn;
        this.publishYear = publishYear;
        this.totalQuantity = totalQuantity;
        this.availableQuantity = totalQuantity;
    }

    // Méthodes métier
    public boolean isAvailable() {
        return isActive && availableQuantity > 0;
    }

    public boolean decrementAvailable() {
        if (availableQuantity > 0) {
            availableQuantity--;
            setUpdatedAt(LocalDateTime.now());
            return true;
        }
        return false;
    }

    public boolean incrementAvailable() {
        if (availableQuantity < totalQuantity) {
            availableQuantity++;
            setUpdatedAt(LocalDateTime.now());
            return true;
        }
        return false;
    }

    public double getAvailabilityRate() {
        return totalQuantity > 0 ? (double) availableQuantity / totalQuantity : 0.0;
    }

    public boolean isPopular() {
        return getAvailabilityRate() < 0.3; // Moins de 30% de disponibilité
    }

    // Méthodes de validation
    public boolean isValidForLoan() {
        return isActive() && isAvailable() && title != null && !title.trim().isEmpty();
    }

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public Integer getPublishYear() { return publishYear; }
    public void setPublishYear(Integer publishYear) { this.publishYear = publishYear; }

    public BookCategory getCategory() { return category; }
    public void setCategory(BookCategory category) { this.category = category; }

    public int getTotalQuantity() { return totalQuantity; }
    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
        // S'assurer que availableQuantity ne dépasse pas totalQuantity
        if (this.availableQuantity > totalQuantity) {
            this.availableQuantity = totalQuantity;
        }
    }

    public int getAvailableQuantity() { return availableQuantity; }
    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = Math.max(0, Math.min(availableQuantity, totalQuantity));
    }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public boolean isActive() { return isActive; }
    public void setActive(boolean active) { isActive = active; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id) ||
                (Objects.equals(isbn, book.isbn) && isbn != null);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isbn);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", category=" + category +
                ", available=" + availableQuantity + "/" + totalQuantity +
                '}';
    }
}
