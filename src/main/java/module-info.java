module com.torjusam.datastructs.trigramgenerator.main {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.torjusam.datastructs.trigramgenerator.main to javafx.fxml;
    exports com.torjusam.datastructs.trigramgenerator.main;
}