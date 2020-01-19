package samples;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class StudentCell extends GridPane {

    @FXML
    private Label label1;
    @FXML
    private Label label2;

    public StudentCell() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("ListCell.fxml"));
            loader.setController(this);
            loader.setRoot(this);
            loader.load();
        } catch (IOException exc) {
            // handle exception1
        }
    }

    @FXML
    private void initialize(){

    }

    public void setLabel1Text(String label1Text) {
        this.label1.setText(label1Text);
    }

    public void setLabel2Text(String label2Text) {
        this.label2.setText(label2Text);
    }
}
