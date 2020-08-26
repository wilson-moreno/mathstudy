package edu.thinkbox.math.matrix.applications;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import edu.thinkbox.math.plane.cartesian.XYPlane;
import java.util.ArrayList;
import edu.thinkbox.math.matrix.Transformation;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import edu.thinkbox.controls.CustomOutputStream;
import java.io.PrintStream;
import javafx.geometry.Pos;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

public class LinearTransformations extends Application{
        private static final int WIDTH = 600;
        private static final int HEIGHT = 600;
        private static final int zoomFactor = 20;
        private XYPlane cartesianPlane;
        private TextArea output;
        private Button clearOutput;

        public LinearTransformations(){
            cartesianPlane = new XYPlane( WIDTH, HEIGHT, zoomFactor );
            output = new TextArea();
            clearOutput = new Button( "Clear" );
            output.setEditable( false );
            PrintStream printStream = new PrintStream( new CustomOutputStream( output ) );
            System.setOut( printStream );
        }

        @Override
        public void start( Stage primaryStage ){
            primaryStage.setTitle( "Linear Transformations" );
            HBox root = new HBox( 2 );
            VBox outputSection = new VBox( 2 );
            outputSection.setAlignment( Pos.CENTER );
            output.setMinHeight( HEIGHT - 30 );
            clearOutput.setMinWidth( 290 );

            clearOutput.setOnAction(
               new EventHandler< ActionEvent >(){
                  public void handle( ActionEvent e){
                     output.setText("");
                  }
               }
            );

            root.getChildren().addAll( cartesianPlane, outputSection );
            outputSection.getChildren().addAll( output, clearOutput );
            primaryStage.setScene( new Scene( root, WIDTH + 300, HEIGHT ) );
            primaryStage.show();
        }

        public static void main( String args[] ){
            launch( args );
        }

}
