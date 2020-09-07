package edu.thinkbox.controls;

import javafx.scene.layout.GridPane;
import edu.thinkbox.math.matrix.Matrix;

public class MatrixField extends GridPane{

      public MatrixField(){
          setHgap( DEFAULT_GAP );
          setVgap( DEFAULT_GAP );
      }

      public void setMatrix( Matrix matrix ){
          this.matrix = matrix;
          addElements();
      }

      public Matrix getMatrix(){
          return matrix;
      }

      private void addElements(){
        for( int i = 0; i < matrix.getRows(); i++ ){
          for( int j = 0; j < matrix.getColumns(); j++ ){
             add( new MatrixElement( i, j, matrix ), i, j );
          }
        }
      }

      private Matrix matrix;
      public static final int DEFAULT_ELEMENT_SIZE = 50;
      private static final int DEFAULT_GAP = 2;
}
