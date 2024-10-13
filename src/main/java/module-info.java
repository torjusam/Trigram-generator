module com.torjusam.datastructs.trigramgenerator.main {
    requires javafx.controls;
    requires javafx.fxml;


    exports com.torjusam.datastructs.trigramgenerator;
    opens com.torjusam.datastructs.trigramgenerator to javafx.fxml;
}