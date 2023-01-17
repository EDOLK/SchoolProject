package com.myproject.app.Controls;

import java.io.IOException;

import com.myproject.app.App;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class IPSetterController{
    @FXML
    private Label errorText;

    @FXML
    private TextField ipInput;

    public void sendIP(ActionEvent event){
        try {
            App.getCurrentManagementSystem().setUri(ipInput.getText());
            try {
                switchToManagementUI(event);
            } catch (Exception e) {
                setErrorText("Error: Management UI missing");
            }
        } catch (Exception e) {
            setErrorText("Error: Invalid Address");
        }
    }

    public void resetErrorMessage(){
        setErrorText("");
    }

    public void setErrorText(String errorMessage){
        errorText.setText(errorMessage);
    }

    public void switchToManagementUI(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Management_UI.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
