package edu.thinkbox.math.plane.cartesian;

import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import edu.thinkbox.math.matrix.Matrix;

public class AngleXY extends XYObject{
        private Arc  angle;
        private Text angleValue;

        public AngleXY( XYPlane xyPlane ){
           super( xyPlane );
           color = Color.web( "509237" );
           highlightColor =  Color.web( "ff1a00" );
           size = 3.0;
           highlightSize = 5.0;
        }

        public void hightlight(){
            angle.setStroke( highlightColor );
            angle.setStrokeWidth( highlightSize );
        }

        public void unhighlight(){
            angle.setStroke( color );
            angle.setStrokeWidth( size );
        }

        private void setAngleValue( Text angleValue ){
             Double x = 40 * Math.cos( xyPlane.getDirection( coordinates ) / 2.0 );
             Double y = 40 * Math.sin( xyPlane.getDirection( coordinates ) / 2.0 );

             if( xyPlane.getQuadrant( coordinates ) == 1 ){
                   angleValue.setX( xyPlane.getCenterX() + ( x - 10 ) );
                   angleValue.setY( xyPlane.getCenterY() - y );
             } else if( xyPlane.getQuadrant( coordinates ) == 2 ){
                   angleValue.setX( xyPlane.getCenterX() + ( x - 20 ) );
                   angleValue.setY( xyPlane.getCenterY() - y );
             } else if( xyPlane.getQuadrant( coordinates ) == 3 ){
                   angleValue.setX( xyPlane.getCenterX() + ( x - 25 ) );
                   angleValue.setY( xyPlane.getCenterY() - y );
             } else if( xyPlane.getQuadrant( coordinates ) == 4 ){
                   angleValue.setX( xyPlane.getCenterX() + ( x - 30 ) );
                   angleValue.setY( xyPlane.getCenterY() - y );
             }

             angleValue.setText( String.format( "%2.2f", xyPlane.toDegree( xyPlane.getDirection( coordinates ) ) ) );
        }

        public void setPlaneCoordinates( double x, double y){

        }

}
