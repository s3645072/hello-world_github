package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import javafx.event.ActionEvent;

import javafx.scene.control.Label;

/**
 * @author Shiting Yin
 * implement function for delete that apply with delete button on Main view
 */
public class DeleteController {
	@FXML
	private Label namel;
	@FXML
	private TextField name;
	@FXML
	private Button ok;
	@FXML
	private Button back;
	@FXML
	private Label msg;

	/**
	 * @param event
	 * Event Listener on Button[#ok].onAction
	 * control different limitation for delete person
	 */
	@FXML
	public void ok(ActionEvent event) {
		int i = AddController.find(name.getText(), AddController.personList);
		int p = DefRelation1.findRel(name.getText(), DefRelation1.relList);
		String r1 = "couple";
		String r2 = "parent";
		if (i != -1) {
			if(p == -1) {
				AddController.personList.remove(i);
				msg.setText("Delete successfully :)");
			}
			else if(p != -1 && (!DefRelation1.relList.get(p).getRelation().equals(r1)) && 
					(!DefRelation1.relList.get(p).getRelation().equals(r2)) ){
				DefRelation1.relList.remove(p);
				AddController.personList.remove(i);
				msg.setText("Delete successfully :)");
			}else {
				msg.setText("Cannot delete person with relation couple or parent.");
			}
		}else {
			msg.setText("Person not exist.");				
			}
		}
	
	/**
	 * @param e
	 * Event Listener on Button[#back].onAction
	 * apply action back to main interface
	 */
	public void back(ActionEvent e) {
		try {
			((Node)e.getSource()).getScene().getWindow().hide();
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/application/Main.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
