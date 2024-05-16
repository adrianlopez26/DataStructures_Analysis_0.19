module com.empresa.datastructures_analysis {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.empresa.datastructures_analysis to javafx.fxml;
    exports com.empresa.datastructures_analysis;
}