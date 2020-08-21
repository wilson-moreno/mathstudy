package edu.thinkbox.math.plane.cartesian;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.text.Font;


public class AxesXY extends XYObject{
       private ArrowHeadXY northArrowHead;
       private ArrowHeadXY southArrowHead;
       private ArrowHeadXY eastArrowHead;
       private ArrowHeadXY westArrowHead;

       public AxesXY( XYPlane plane ){
          super( plane );
          color = Color.web( "002f55" );
          size = 2.0;
          createAxes();
          createArrowHeads();
       }

       public void hightlight(){}
       public void unhighlight(){}
       public void setPlaneCoordinates( double x, double y ){}

       private void createArrowHeads(){
           northArrowHead = new ArrowHeadXY( plane, ArrowHeadType.OPEN_BASE );
           southArrowHead = new ArrowHeadXY( plane, ArrowHeadType.OPEN_BASE );
           eastArrowHead  = new ArrowHeadXY( plane, ArrowHeadType.OPEN_BASE );
           westArrowHead  = new ArrowHeadXY( plane, ArrowHeadType.OPEN_BASE );

           northArrowHead.setPlaneCoordinates( 0, plane.getYBound() - 0.25 );
           southArrowHead.setPlaneCoordinates( 0, -1 * ( plane.getYBound() - 0.25 ) );
           eastArrowHead.setPlaneCoordinates( plane.getXBound() - 0.25, 0 );
           westArrowHead.setPlaneCoordinates( ( plane.getXBound() - 0.25 ) * -1, 0 );

           getChildren().add( northArrowHead );
           getChildren().add( southArrowHead );
           getChildren().add( eastArrowHead );
           getChildren().add( westArrowHead );
       }

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
