package com.myproject.app.Controls;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.myproject.app.App;
import com.myproject.app.Classes.Student;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class StudentCreationController implements Initializable{
    
    @FXML
    private ChoiceBox<Integer> yearChoiceBox;

    @FXML
    private ChoiceBox<String> semesterChoiceBox;

    @FXML
    private TextField nameField;

    @FXML
    private TextField majorField;

    @FXML
    private CheckBox graduateBox;

    @FXML
    private Label errorText;

    private Integer[] years = {1,2,3,4};
    private String[] semester = {"Freshman", "Sophomore", "Junior", "Senior"};

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        yearChoiceBox.getItems().addAll(years);
        semesterChoiceBox.getItems().addAll(semester);
    }

    public void switchToManagementUI(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Management_UI.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void createNewStudent(ActionEvent event){
        try {
            evaluateStudentInfo();
            setErrorMessage("");
            App.getCurrentManagementSystem().addStudent(createStudentFromInfo());
            switchToManagementUI(event);
        } catch (Exception e) {
            setErrorMessage("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private Student createStudentFromInfo(){
        int semester;
        switch (semesterChoiceBox.getValue()) {
            case "Freshman":
                semester = 1;
                break;
            case "Sophomore":
                semester = 2;
                break;
            case "Junior":
                semester = 3;
                break;
            case "Senior":
                semester = 4;
            default:
                semester = 1;
                break;
        }
        Student student = new Student(nameField.getText(), majorField.getText(), yearChoiceBox.getValue(), semester, graduateBox.isSelected());
        return student;
    }

    public void setErrorMessage(String message){
        errorText.setText(message);
    }
    
    private void evaluateStudentInfo() throws Exception{
        boolean valid = true;
        String emptyFields = "Missing fields: ";
        if (nameField.getText().isBlank()){
            emptyFields += "Name, ";
            valid = false;
        }
        if (majorField.getText().isBlank()){
            emptyFields += "Major, ";
            valid = false;
        }
        if (yearChoiceBox.getValue() == null){
            emptyFields += "Year, ";
            valid = false;
        }
        if (semesterChoiceBox.getValue() == null){
            emptyFields += "Semester, ";
            valid = false;
        }
        if (!valid){
            throw new Exception(emptyFields);
        }
    }

}
