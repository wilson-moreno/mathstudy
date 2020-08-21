package edu.thinkbox.math.plane.cartesian;

import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import edu.thinkbox.math.matrix.Matrix;

public class AngleXY extends XYObject{
        private Arc  angle;
        private Text angleValue;

        public AngleXY( XYPlane plane ){
           super( plane );
           angle = new Arc();
           angleValue = new Text();
           //size = 3.0;
           //highlightSize = 5.0;
           createAngle();
        }

        public AngleXY( double x, double y, XYPlane plane ){
           this( plane );
           setPlaneCoordinates( x, y );
        }

        public void setColor( Color color ){}

        public void highlight(){
            angle.setStroke( RED );
            angle.setStrokeWidth( highlightSize );
        }

        public void unhighlight(){
            angle.setStroke( GREEN );
            angle.setStrokeWidth( size );
        }

        private void updateAngleValue(){
             Double x = 40 * Math.cos( plane.getDirection( coordinates ) / 2.0 );
             Double y = 40 * Math.sin( plane.getDirection( coordinates ) / 2.0 );

             if( plane.getQuadrant( coordinates ) == 1 ){
                   angleValue.setX( plane.getCenterX() + ( x - 10 ) );
                   angleValue.setY( plane.getCenterY() - y );
             } else if( plane.getQuadrant( coordinates ) == 2 ){
                   angleValue.setX( plane.getCenterX() + ( x - 20 ) );
                   angleValue.setY( plane.getCenterY() - y );
             } else if( plane.getQuadrant( coordinates ) == 3 ){
                   angleValue.setX( plane.getCenterX() + ( x - 25 ) );
                   angleValue.setY( plane.getCenterY() - y );
             } else if( plane.getQuadrant( coordinates ) == 4 ){
                   angleValue.setX( plane.getCenterX() + ( x - 30 ) );
                   angleValue.setY( plane.getCenterY() - y );
             }

             angleValue.setText( String.format( "%2.2f", plane.toDegree( plane.getDirection( coordinates ) ) ) );
        }

        public void setPlaneCoordinates( double x, double y){
             super.setPlaneCoordinates( x, y );
             updateAngle();
        }

        public void updateAngle(){
            angle.setLength( plane.toDegree( plane.getDirection( coordinates ) ) );
            updateAngleValue();
        }

        public void createAngle(){
            angle.setCenterX( plane.getCenterX() );
            angle.setCenterY( plane.getCenterY() );
            angle.setRadiusX( 25.0f );
            angle.setRadiusY( 25.0f );
            angle.setStartAngle( 0.0f);
            angle.setType( ArcType.ROUND );
            angle.setFill( null );
            angle.setStroke( GREEN );
            angle.setStrokeWidth( size );
            updateAngle();
            getChildren().add( angle );
            getChildren().add( angleValue );
        }
}
