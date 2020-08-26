package edu.thinkbox.math.applications;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import edu.thinkbox.math.plane.cartesian.XYPlane;
import java.util.ArrayList;
import edu.thinkbox.math.matrix.Transformation;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import edu.thinkbox.io.CustomOutputStream;
import java.io.PrintStream;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

public class CartesianXYPlane extends Application{
        private static final int WIDTH = 600;
        private static final int HEIGHT = 600;
        private static final int zoomFactor = 20;
        private XYPlane cartesianPlane;
        private TextArea output;
        private Button clearOutput;
        private MenuBar menuBar;
        private Menu viewMenu;
        private CheckMenuItem showObjectInformationMenuItem;
        private VBox main;
        private HBox plane;
        private VBox outputSection;
        private Scene primaryScene;
        private Stage primaryStage;

        public CartesianXYPlane(){
            cartesianPlane = new XYPlane( WIDTH, HEIGHT, zoomFactor );
            menuBar = new MenuBar();
            viewMenu = new Menu( "View" );
            showObjectInformationMenuItem = new CheckMenuItem( "Show Object Information" );
            viewMenu.getItems().add( showObjectInformationMenuItem );
            menuBar.getMenus().add( viewMenu );

            showObjectInformationMenuItem.setOnAction(
                new EventHandler< ActionEvent >(){
                  public void handle( ActionEvent e){
                      outputSection.setVisible( showObjectInformationMenuItem.isSelected() );
                      if( showObjectInformationMenuItem.isSelected() ){
                         primaryStage.setWidth( WIDTH + 320 );
                      } else {
                         primaryStage.setWidth( WIDTH + 20 );
                      }
                  }
                }
            );

            output = new TextArea();
            clearOutput = new Button( "Clear" );
            output.setEditable( false );
            PrintStream printStream = new PrintStream( new CustomOutputStream( output ) );
            System.setOut( printStream );
        }

        @Override
        public void start( Stage primaryStage ){
            this.primaryStage = primaryStage;
            primaryStage.setTitle( "Cartesian Plane" );
            main = new VBox( 2 );
            plane = new HBox( 2 );
            outputSection = new VBox( 2 );
            outputSection.setAlignment( Pos.CENTER );
            outputSection.setPadding( new Insets( 2, 2, 2, 2 ) );
            output.setMinHeight( HEIGHT - 30 );
            clearOutput.setMinWidth( 290 );

            clearOutput.setOnAction(
               new EventHandler< ActionEvent >(){
                  public void handle( ActionEvent e){
                     output.setText("");
                  }
               }
            );

            outputSection.getChildren().addAll( output, clearOutput );
            plane.getChildren().addAll( cartesianPlane, outputSection );
            main.getChildren().addAll( menuBar, plane );
            primaryScene = new Scene( main, WIDTH, HEIGHT + 30 );
            showObjectInformationMenuItem.setSelected( false );
            outputSection.setVisible( false );
            primaryStage.setScene( primaryScene );
            primaryStage.setResizable( false );
            primaryStage.show();
        }

        public static void main( String args[] ){
            launch( args );
        }

}
