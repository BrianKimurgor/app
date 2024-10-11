module fee {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires java.sql;

    opens fee to javafx.fxml;
    exports fee;
}
