/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import ant.Ant;
import model.BoardModel;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import model.Tile;

/**
 *
 * @author Laura Lüthi
 */
public class FXMLAntController implements Initializable {

	@FXML
	private TextField width;
	@FXML
	private TextField height;
	@FXML
	private TextField amountAnts;
	@FXML
	private Canvas canvas;
	@FXML
	private VBox vBox;

	private Ant tester = new Ant(0, 0, 0);
	
	private String xPosi = "0";
	private String yPosi = "0";
	private String dir = "0";	
	
	private BoardModel bModel;
	private ArrayList<Ant> antList = new ArrayList();

	@FXML
	private void clickedOk(ActionEvent event) {

		// TODO: Speiechere werte
		if (width.getText() != null || height.getText() != null) {
			//AntStarter.boardy = new Tile[Integer.parseInt(width.getText())][Integer.parseInt(height.getText())];
			createBoard(Integer.parseInt(width.getText()),Integer.parseInt(height.getText()));
			bModel = new BoardModel(Integer.parseInt(width.getText()),Integer.parseInt(height.getText()), false);
			//tester.setupBoard(Integer.parseInt(width.getText()),Integer.parseInt(height.getText()));
			

			for (int i = 0; i < Integer.parseInt(amountAnts.getText()); i++) {
				
				Button btn = new Button();
				Button okButton = new Button("SAVE");
				vBox.getChildren().add(btn);

				btn.setText("Ant " + (i+1));
				btn.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						
						TextField xPos = new TextField("0");
						TextField yPos = new TextField("0");
						TextField direction = new TextField("0");

						final Stage myDialog = new Stage();
						myDialog.initModality(Modality.WINDOW_MODAL);

						GridPane gp = new GridPane();
						gp.addRow(0, new Label("x-Position: "));
						gp.addColumn(1, xPos);
						gp.addRow(1, new Label("y-Position: "));
						gp.addColumn(1, yPos);
						gp.addRow(2, new Label("direction: "));
						gp.addColumn(1, direction);
						gp.addRow(0, okButton);
						
						okButton.setOnAction(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent aEvent) {
								xPosi = xPos.getText();
								yPosi = yPos.getText();
								dir = direction.getText();	
								System.out.println(dir);
								if(!xPosi.equals("") && !yPosi.equals("") && Integer.parseInt(dir) > -1 && Integer.parseInt(dir) < 4
										&& Integer.parseInt(xPosi) <= Integer.parseInt(width.getText()) && Integer.parseInt(xPosi) >= 0
										&& Integer.parseInt(yPosi) <= Integer.parseInt(height.getText()) && Integer.parseInt(yPosi) >= 0) {
									
									
									//ant.setupBoard(Integer.parseInt(xPosi),Integer.parseInt(yPosi));
									antList.add(new Ant(Integer.parseInt(xPosi),Integer.parseInt(yPosi), Integer.parseInt(dir)));									
									System.out.println();
									
									myDialog.close();
								}
							}

						});

						Scene myDialogScene = new Scene(gp);
						myDialog.setScene(myDialogScene);
						myDialog.setTitle("Ant configuration");
						myDialog.setMinHeight(150.0);
						myDialog.setMinWidth(400.0);
						myDialog.show();
					}
				});
			}
		}
	}
	
	public void createBoard(int a, int i) {
        Ant.boardX = a;
        Ant.boardY = i;
        for (int y = 0; y < i; y++) {
            for (int x = 0; x < a; x++) {
            	AntStarter.boardy[x][y] = new Tile(x, y, false);
            }
        }
        System.out.println(Ant.boardX + " : " + Ant.boardY);
    }
	
	@FXML
	public void startAnt(ActionEvent event) {
			double cellsize = 1;
			//canvas = new Canvas(Integer.parseInt(width.getText()) * cellsize, Integer.parseInt(height.getText()) * cellsize);

			GraphicsContext gc = canvas.getGraphicsContext2D();

			new AnimationTimer() {

				@Override
				public void handle(long now) {

					for (Ant a : antList) {
						gc.setFill(Color.WHITE);
						gc.fillRect(0, 0, Integer.parseInt(width.getText()) * cellsize, Integer.parseInt(height.getText()) * cellsize);

						for (int s = 0; s < 0.01; s++) {
							a.stepForward(a);
						}

						for (int i = 0; i < Integer.parseInt(height.getText()); i++) {
							for (int k = 0; k < Integer.parseInt(width.getText()); k++) {
								tester.setxPos(k);
								tester.setyPos(i);
								if (tester.checkIfBlack(tester)) {
									gc.setFill(Color.rgb(50, 80, 200));
									gc.fillRect(i * cellsize, k * cellsize, cellsize, cellsize);
								}

							}
						}
					}
				}

			}.start();
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
	}

}
