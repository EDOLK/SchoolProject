module com.myproject.app {
    requires transitive javafx.graphics;
    requires org.mongodb.driver.sync.client;
    requires org.mongodb.driver.core;
    requires org.mongodb.bson;
    requires javafx.controls;
    requires javafx.fxml;
    exports com.myproject.app;
    exports com.myproject.app.Classes;
    opens com.myproject.app.Controls to javafx.fxml;
}