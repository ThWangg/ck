package vku.pntq.inventorymanagement.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class InventoryManagement extends Application {
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/vku/pntq/inventorymanagement/fxml/LoginDesign.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Đăng nhập phần mềm");
        stage.show();
    }
    public static void main(String[] args){
        launch(args);
    }
}
