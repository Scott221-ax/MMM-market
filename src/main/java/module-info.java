module main.mmmmarket {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    //requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    // requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.sql;

    exports main;
    //exports main.controller;
    //exports main.model;
    //opens main to javafx.controls, javafx.fxml;
    //exports main.controller;
    opens controller to javafx.controls, javafx.fxml;
    exports controller;
    //exports css;
}