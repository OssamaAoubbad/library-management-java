package net.ossama.librarymanagement.model;

import net.ossama.librarymanagement.model.enums.UserRole;

public class Admin extends User {
    private String department;

    // Constructeur par défaut
    public Admin() {
        super();
        setRole(UserRole.ADMIN);
    }

    // Constructeur avec paramètres
    public Admin(String firstName, String lastName, String email, String password, String department) {
        super(firstName, lastName, email, password, UserRole.ADMIN);
        this.department = department;
    }

    @Override
    public String getDisplayName() {
        return getFullName() + " - " + (department != null ? department : "Administration");
    }

    @Override
    public boolean hasSpecialPrivileges() {
        return true; // Les admins ont tous les privilèges
    }

    // Méthodes métier spécifiques aux admins
    public boolean canManageUsers() {
        return isActive();
    }

    public boolean canManageBooks() {
        return isActive();
    }

    public boolean canManageLoans() {
        return isActive();
    }

    public boolean canViewStatistics() {
        return isActive();
    }

    // Getters et Setters
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
}
