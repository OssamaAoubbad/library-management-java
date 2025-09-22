package net.ossama.librarymanagement.model.enums;

public enum UserRole {
    ADMIN("Administrateur"),
    STUDENT("Étudiant");

    private final String displayName;

    UserRole(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}