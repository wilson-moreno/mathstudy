package edu.thinkbox.controls;

import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import edu.thinkbox.math.matrix.Matrix;
import static edu.thinkbox.controls.MatrixField.DEFAULT_ELEMENT_SIZE;

public class MatrixElement extends TextField implements ChangeListener< String >{

       public MatrixElement( int row, int column, Matrix matrix ){
           super( String.format( "%2.2f", matrix.getEntry( row, column ) ) );
           setPrefSize( DEFAULT_ELEMENT_SIZE, DEFAULT_ELEMENT_SIZE );
           setAlignment( Pos.CENTER );
           textProperty().addListener( this );
           this.row = row;
           this.column = column;
           this.matrix = matrix;
       }

       public void changed( ObservableValue observable, String oldValue, String newValue ){
            try{
                matrix.setEntry( row, column, Double.parseDouble( newValue) );
            }catch( NumberFormatException ex ){
                textProperty().set( Double.toString( matrix.getEntry( row, column ) ) );
            }

       }


       private int row;
       private int column;
       private Matrix matrix;
}
