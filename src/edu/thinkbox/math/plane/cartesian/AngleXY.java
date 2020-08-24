package edu.thinkbox.math.plane.cartesian;

import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import edu.thinkbox.math.matrix.Matrix;

public class AngleXY extends XYObject{
        private Arc  angle;
        private Text angleValue;
        private Matrix previous = Matrix.createColumnMatrix( 2 );
        private double updatedAngle;
        private double previousAngle;
        private double radius;

        public AngleXY( XYPlane plane ){
           super( plane );
           radius = 20.0;
           angle = new Arc();
           angleValue = new Text();
           createAngle();
        }

        public AngleXY( double x, double y, XYPlane plane ){
           this( plane );
           setPlaneCoordinates( x, y );
        }

        public void setColor( Color color ){}

        public void highlight(){
            angle.setStroke( RED );
            angle.setStrokeWidth( getHighlightSize() );
        }

        public void unhighlight(){
            angle.setStroke( GREEN );
            angle.setStrokeWidth( getSize() );
        }

        private void updateAngleValue(){
             Double x = 40 * Math.cos( plane.getDirection( getCoordinates() ) / 2.0 );
             Double y = 40 * Math.sin( plane.getDirection( getCoordinates() ) / 2.0 );

             if( plane.getQuadrant( getCoordinates() ) == 1 ){
                   angleValue.setX( plane.getCenterX() + ( x - 10 ) );
                   angleValue.setY( plane.getCenterY() - y );
             } else if( plane.getQuadrant( getCoordinates() ) == 2 ){
                   angleValue.setX( plane.getCenterX() + ( x - 20 ) );
                   angleValue.setY( plane.getCenterY() - y );
             } else if( plane.getQuadrant( getCoordinates() ) == 3 ){
                   angleValue.setX( plane.getCenterX() + ( x - 25 ) );
                   angleValue.setY( plane.getCenterY() - y );
             } else if( plane.getQuadrant( getCoordinates() ) == 4 ){
                   angleValue.setX( plane.getCenterX() + ( x - 30 ) );
                   angleValue.setY( plane.getCenterY() - y );
             }

             angleValue.setText( String.format( "%2.2f", updatedAngle ) );
        }


        public void setPlaneCoordinates( double x, double y){
             super.setPlaneCoordinates( x, y );
             updateAngle();
        }

        public void updateAngle(){
            previousAngle = plane.toDegree( plane.getDirection( previous ) );
            updatedAngle  = plane.toDegree( plane.getDirection( getCoordinates() ) );
            angle.setLength( updatedAngle );
            updateAngleValue();
        }

        public void createAngle(){
            angle.setCenterX( plane.getCenterX() );
            angle.setCenterY( plane.getCenterY() );
            angle.setRadiusX( radius );
            angle.setRadiusY( radius );
            angle.setStartAngle( 0.0f);
            angle.setType( ArcType.ROUND );
            angle.setFill( null );
            angle.setStroke( GREEN );
            angle.setStrokeWidth( getSize() );
            updateAngle();
            getChildren().add( angle );
            getChildren().add( angleValue );
        }

        public void setCenter( double x, double y ){
            angle.setCenterX( plane.toSceneX( x ) );
            angle.setCenterY( plane.toSceneY( y ) );
        }

        public void setStartAngle( double degrees ){
            angle.setStartAngle( degrees );
        }

        public void setLength( double length ){
            angle.setLength( length );
        }

        public void setRadius( double radius ){
            angle.setRadiusX( radius );
            angle.setRadiusY( radius );
        }

        public void printCenter( String name ){
           System.out.println( String.format( "%s = [ %2.2f, %2.2f ]", name, angle.getCenterX(), angle.getCenterY() ) );
        }

        public double getLength(){ return angle.getLength(); }

}
