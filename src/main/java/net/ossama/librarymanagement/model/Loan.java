package net.ossama.librarymanagement.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class Loan {
    private Long id;
    private Student student;
    private Book book;
    private LocalDate loanDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private boolean isReturned;
    private String notes;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Constructeur par défaut
    public Loan() {
        this.isReturned = false;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    // Constructeur avec paramètres
    public Loan(Student student, Book book, LocalDate loanDate, LocalDate dueDate) {
        this();
        this.student = student;
        this.book = book;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
    }

    // Méthodes métier
    public boolean isOverdue() {
        return !isReturned && dueDate != null && LocalDate.now().isAfter(dueDate);
    }

    public long getDaysOverdue() {
        if (!isOverdue()) {
            return 0;
        }
        return ChronoUnit.DAYS.between(dueDate, LocalDate.now());
    }

    public long getLoanDuration() {
        LocalDate endDate = isReturned ? returnDate : LocalDate.now();
        return loanDate != null ? ChronoUnit.DAYS.between(loanDate, endDate) : 0;
    }

    public boolean isDueSoon() {
        return !isReturned && dueDate != null &&
                ChronoUnit.DAYS.between(LocalDate.now(), dueDate) <= 3;
    }

    public void returnBook() {
        if (!isReturned) {
            this.isReturned = true;
            this.returnDate = LocalDate.now();
            this.updatedAt = LocalDateTime.now();

            // Incrémenter la quantité disponible du livre
            if (book != null) {
                book.incrementAvailable();
            }
        }
    }

    public String getStatus() {
        if (isReturned) {
            return "Retourné";
        } else if (isOverdue()) {
            return "En retard (" + getDaysOverdue() + " jours)";
        } else if (isDueSoon()) {
            return "À rendre bientôt";
        } else {
            return "En cours";
        }
    }

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }

    public Book getBook() { return book; }
    public void setBook(Book book) { this.book = book; }

    public LocalDate getLoanDate() { return loanDate; }
    public void setLoanDate(LocalDate loanDate) { this.loanDate = loanDate; }

    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }

    public LocalDate getReturnDate() { return returnDate; }
    public void setReturnDate(LocalDate returnDate) { this.returnDate = returnDate; }

    public boolean isReturned() { return isReturned; }
    public void setReturned(boolean returned) { isReturned = returned; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Loan loan = (Loan) o;
        return Objects.equals(id, loan.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Loan{" +
                "id=" + id +
                ", student=" + (student != null ? student.getDisplayName() : "null") +
                ", book=" + (book != null ? book.getTitle() : "null") +
                ", loanDate=" + loanDate +
                ", dueDate=" + dueDate +
                ", status=" + getStatus() +
                '}';
    }
}