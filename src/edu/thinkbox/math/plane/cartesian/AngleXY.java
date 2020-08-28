package edu.thinkbox.math.plane.cartesian;

import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import edu.thinkbox.math.matrix.Matrix;

public class AngleXY extends XYObject{
        private static char LABEL = 'A';
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
             Double x = 40 * Math.cos( plane.toRadians( angle.getLength() ) / 2.0 );
             Double y = 40 * Math.sin( plane.toRadians( angle.getLength() ) / 2.0 );

             angleValue.setX( getSceneX() + x - 20 );
             angleValue.setY( getSceneY() - y );
             String format = getLabel() == "" ? "%2.2f" : getLabel() + " = %2.2f";
             angleValue.setText( String.format( format, Math.abs( angle.getLength() ) ) );
        }


        public void setPlaneCoordinates( double x, double y){
             super.setPlaneCoordinates( x, y );
             updateAngle();
        }

        public void updateAngle(){
            setCenter( getX(), getY() );
            updateAngleValue();
        }

        public void createAngle(){
            angle.setCenterX( getSceneX() );
            angle.setCenterY( getSceneY() );
            angle.setRadiusX( radius );
            angle.setRadiusY( radius );
            angle.setStartAngle( 0.0f);
            angle.setType( ArcType.ROUND );
            angle.setFill( null );
            angle.setStroke( GREEN );
            angle.setStrokeWidth( getSize() );
            setLabel( String.valueOf( LABEL++ ) );
            updateAngle();
            getChildren().add( angle );
            getChildren().add( angleValue );
        }

        private void setCenter( double x, double y ){
            angle.setCenterX( plane.toSceneX( x ) );
            angle.setCenterY( plane.toSceneY( y ) );
        }

        public void setStartAngle( double degrees ){
            angle.setStartAngle( degrees );
            updateAngleValue();
        }

        public void setLength( double length ){
            angle.setLength( length );
            updateAngleValue();
        }

        public void setRadius( double radius ){
            angle.setRadiusX( radius );
            angle.setRadiusY( radius );
            updateAngleValue();
        }

        public void printCenter( String name ){
           System.out.println( String.format( "%s = [ %2.2f, %2.2f ]", name, angle.getCenterX(), angle.getCenterY() ) );
        }

        public double getLength(){ return angle.getLength(); }

}
