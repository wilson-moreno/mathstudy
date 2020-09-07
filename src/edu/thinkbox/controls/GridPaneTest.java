package edu.thinkbox.controls;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import edu.thinkbox.math.matrix.Matrix;


public class GridPaneTest extends Application{
      private Stage primaryStage;
      private int ROWS = 4;
      private int COLUMNS = 4;
      private int GAP = 2;
      private int ENTRY_SIZE = 60;

      public GridPaneTest(){}

      @Override
      public void start( Stage primaryStage ){
          this.primaryStage = primaryStage;
          primaryStage.setTitle( "Grid Pane Test" );

          MatrixField matrixField = new MatrixField();
          Matrix matrix = Matrix.createSquareMatrix( 4 );
          matrix.generateRandomEntries();
          matrixField.setMatrix( matrix );


          Scene primaryScene = new Scene( matrixField, 400, 400);
          primaryStage.setScene( primaryScene );
          primaryStage.show();
      }

      public static void main( String args[] ){
          launch( args );
      }

}
