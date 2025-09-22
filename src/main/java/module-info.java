module net.ossama.librarymanagement {
    requires javafx.controls;
    requires javafx.fxml;


    opens net.ossama.librarymanagement to javafx.fxml;
    exports net.ossama.librarymanagement;
}