package net.ossama.librarymanagement.model;

import java.util.ArrayList;
import java.util.List;

public class Student extends User {
    private String cne; // Code National Étudiant
    private List<Loan> activeLoans;

    // Constructeur par défaut
    public Student() {
        super();
        this.activeLoans = new ArrayList<>();
        setRole(UserRole.STUDENT);
    }

    // Constructeur avec paramètres
    public Student(String firstName, String lastName, String email, String password, String cne) {
        super(firstName, lastName, email, password, UserRole.STUDENT);
        this.cne = cne;
        this.activeLoans = new ArrayList<>();
    }

    @Override
    public String getDisplayName() {
        return getFullName() + " (" + cne + ")";
    }

    @Override
    public boolean hasSpecialPrivileges() {
        return false; // Les étudiants n'ont pas de privilèges spéciaux
    }

    // Méthodes métier
    public boolean canBorrow() {
        return isActive() && getActiveLoanCount() < getMaxLoansAllowed() && !hasOverdueLoans();
    }

    public int getActiveLoanCount() {
        return (int) activeLoans.stream()
                .filter(loan -> !loan.isReturned())
                .count();
    }

    public int getMaxLoansAllowed() {
        return 3; // Peut être configuré via les paramètres système
    }

    public boolean hasOverdueLoans() {
        return activeLoans.stream()
                .anyMatch(loan -> !loan.isReturned() && loan.isOverdue());
    }

    // Getters et Setters
    public String getCne() { return cne; }
    public void setCne(String cne) { this.cne = cne; }

    public List<Loan> getActiveLoans() {
        return new ArrayList<>(activeLoans); // Copie défensive
    }

    public void setActiveLoans(List<Loan> activeLoans) {
        this.activeLoans = activeLoans != null ? new ArrayList<>(activeLoans) : new ArrayList<>();
    }

    public void addLoan(Loan loan) {
        if (loan != null && !activeLoans.contains(loan)) {
            activeLoans.add(loan);
        }
    }

    public void removeLoan(Loan loan) {
        activeLoans.remove(loan);
    }
}
