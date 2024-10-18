module com.torjusam.datastructs.trigramgenerator.main {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    exports com.torjusam.datastructs.trigramgenerator;
    opens com.torjusam.datastructs.trigramgenerator to javafx.fxml;
    exports com.torjusam.datastructs.trigramgenerator.services;
    opens com.torjusam.datastructs.trigramgenerator.services to javafx.fxml;
}