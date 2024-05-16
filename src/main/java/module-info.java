module com.empresa.datastructures_analysis {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires mongo.java.driver;


    opens com.empresa.datastructures_analysis to javafx.fxml;
    exports com.empresa.datastructures_analysis;
}