package net.ossama.librarymanagement.model.enums;

public enum BookCategory {
    SCIENCE("Sciences"),
    LITERATURE("Littérature"),
    HISTORY("Histoire"),
    TECHNOLOGY("Technologie"),
    MATHEMATICS("Mathématiques"),
    PHILOSOPHY("Philosophie"),
    OTHER("Autre");

    private final String displayName;

    BookCategory(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
