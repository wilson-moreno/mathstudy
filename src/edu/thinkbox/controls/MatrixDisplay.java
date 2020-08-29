package edu.thinkbox.controls;

import javafx.beans.property.SimpleBooleanProperty;
import edu.thinkbox.math.matrix.Matrix;
import javafx.geometry.Pos;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import java.util.List;
import java.util.ArrayList;

public class MatrixDisplay extends GridPane{


      public MatrixDisplay( int elementSize, int maxDimension, boolean bigFontFlag ){
            this.elementSize = elementSize;
            super.setPrefSize( maxDimension * elementSize * 1.20,
                               maxDimension * elementSize * 1.20 );
            setGridLinesVisible( true );
            setBorder( DEFAULT_BORDER );
            edited = new SimpleBooleanProperty( false );
      }

      public MatrixDisplay( int elementSize, int maxDimension ){
            this( elementSize, maxDimension, false );
      }

      public MatrixDisplay(){
            this( DEFAULT_ELEMENT_SIZE, 10 );
      }

      public final static int DEFAULT_ELEMENT_SIZE = 50;
      public final static String DEFAULT_FONT_FAMILY = "DejaVu Serif";
      public final static Font DEFAULT_FONT = Font.font( DEFAULT_FONT_FAMILY,
                                                         FontWeight.BOLD,
                                                         DEFAULT_ELEMENT_SIZE / 4 );
      private final static Border DEFAULT_BORDER = new Border( new BorderStroke( null,
                                                                  BorderStrokeStyle.SOLID,
                                                                  null,
                                                                  BorderStroke.MEDIUM ) );
      private SimpleBooleanProperty edited;
      private Matrix matrix;
      private int elementSize;
}

interface ElementChangedListener{
      public void elementChanged( Element e );
}

class Element extends TextField {
      private int elementSize = MatrixFields.DEFAULT_ELEMENT_SIZE;
      private String fontFamily = MatrixFields.DEFAULT_FONT_FAMILY;
      private Font font = MatrixFields.DEFAULT_FONT;
      private Matrix matrix;
      private List< ElementChangedListener > elementChangedListeners = new ArrayList< ElementChangedListener >();

      public Element( String element, int row, int column, Matrix matrix ){
            super( element );
            super.setPrefSize( elementSize, elementSize );
            setAlignment( Pos.CENTER );
            setFont( font );
            setStyle( "-fx-text-fill: red" );
            textProperty().addListener( o -> {
                  String newElement = textProperty().get();

                  try {
                       matrix.setEntry( row, column, Double.parseDouble( newElement ) );
                       fireElementChanged();
                  } catch( NumberFormatException ex ) {
                        textProperty().set( Double.toString( matrix.getEntry( row, column ) ) );
                  }
            } );
      }

      public void addElementChangedListener( ElementChangedListener elementChangedListener ){
            elementChangedListeners.add( elementChangedListener );
      }

      private void fireElementChanged(){
            for( ElementChangedListener listener : elementChangedListeners )
                listener.elementChanged( this );
      }
}
