package com.torjusam.datastructs.trigramgenerator.gui.output;

import javafx.scene.control.TextArea;

class OutputField extends TextArea {

    public OutputField() {
        setEditable(false);
        setMinHeight(250);
        setMaxSize(600, 350);
        setWrapText(true);
    }
}