package edu.thinkbox.math.plane.cartesian;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.text.Font;


public class AxesXY extends XYObject{

       public AxesXY( XYPlane plane ){
          super( plane );
          color = Color.web( "002f55" );
          size = 2.0;
          createAxes();
       }

       public void hightlight(){}
       public void unhighlight(){}
       public void setPlaneCoordinates( double x, double y ){}

       private void createAxes(){
           Line centerVerticalLine = new Line( plane.getCenterX(), 5, plane.getCenterX(), plane.getHeight() - 5 );
           Line centerHorizontalLine = new Line( 5, plane.getCenterY(), plane.getWidth() - 5, plane.getCenterY() );
           Text origin = new Text( plane.getCenterX() + 5 , plane.getCenterY() + 15 , "O" );
           Text yAxis = new Text( plane.getCenterX() - 15, 15, "y");
           Text xAxis = new Text( plane.getWidth() - 20, plane.getCenterY() + 15, "x");

           origin.setFont( new Font( 12 ) );
           yAxis.setFont( new Font( 15 ) );
           xAxis.setFont( new Font( 15 ) );

           centerVerticalLine.setStrokeWidth( size );
           centerVerticalLine.setStroke( color );
           centerHorizontalLine.setStrokeWidth( size );
           centerHorizontalLine.setStroke( color );

           getChildren().add( centerVerticalLine );
           getChildren().add( centerHorizontalLine );
         }


}
