package edu.thinkbox.math.plane.cartesian;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.text.Font;

public class TicksXY extends XYObject{
       private double tickLength;

       public TicksXY( XYPlane plane ){
          super( plane );
          color = Color.web( "002f55" );
          size  = 1.0;
          tickLength = 3.0;
          createTicks();
       }

       public void hightlight(){}
       public void unhighlight(){}
       public void setPlaneCoordinates( double x, double y ){}

         private void createTicks(){

             for( int i = 0; i < plane.getRowCount() / 2; i++ ){
                 Line tick1 = new Line( plane.getCenterX() - tickLength , ( i * plane.getModuleSize() ) + plane.getCenterY(), plane.getCenterX() + tickLength, ( i * plane.getModuleSize() )  + plane.getCenterY() );
                 Line tick2 = new Line( plane.getCenterX() - tickLength , ( -1 * i * plane.getModuleSize() ) + plane.getCenterY(), plane.getCenterX() + tickLength, ( -1 * i * plane.getModuleSize() )  + plane.getCenterY() );
                 tick1.setStrokeWidth( size );
                 tick1.setStroke( color );
                 tick2.setStrokeWidth( size );
                 tick2.setStroke( color );
                 getChildren().add( tick1 );
                 getChildren().add( tick2 );
             }

             for( int j = 0; j < plane.getColumnCount() / 2; j++ ){
                 Line tick3 = new Line( ( j * plane.getModuleSize() ) + plane.getCenterX(), plane.getCenterY() + tickLength, ( j * plane.getModuleSize() ) + plane.getCenterX(), plane.getCenterY() - tickLength );
                 Line tick4 = new Line( ( -1 * j * plane.getModuleSize() ) + plane.getCenterX(), plane.getCenterY() + tickLength, ( -1 * j * plane.getModuleSize() ) + plane.getCenterX(), plane.getCenterY() - tickLength );
                 tick3.setStrokeWidth( size );
                 tick3.setStroke( color );
                 tick4.setStrokeWidth( size );
                 tick4.setStroke( color );
                 getChildren().add( tick3 );
                 getChildren().add( tick4 );
             }

         }

}
