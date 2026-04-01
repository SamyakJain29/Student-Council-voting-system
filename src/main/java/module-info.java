module com.voting {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.voting to javafx.fxml;
    opens com.voting.controller to javafx.fxml;
    opens com.voting.model to javafx.base;

    exports com.voting;
}
