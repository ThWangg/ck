module vku.pntq.inventorymanagement {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.sql;
    requires javafx.swing;
    requires io;
    requires kernel;
    requires layout;
    requires mysql.connector.j;
    requires java.mail;

    opens vku.pntq.inventorymanagement.view to javafx.fxml;
    exports vku.pntq.inventorymanagement.view;
    opens vku.pntq.inventorymanagement.controller to javafx.fxml;
    exports vku.pntq.inventorymanagement.controller;
    opens vku.pntq.inventorymanagement.model to javafx.fxml;
    exports vku.pntq.inventorymanagement.model;
}