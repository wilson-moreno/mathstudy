package edu.thinkbox.math.plane.cartesian;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;


public class VectorXY extends XYObject{
       private Line line;
       private ArrowHeadXY arrowHead;
       private AngleXY angle;
       private Text magnitude;

       public VectorXY( XYPlane plane){
           super( plane );
           this.line = new Line();
           this.magnitude = new Text();
           this.arrowHead = new ArrowHeadXY( plane, ArrowHeadType.TRIANGLE );
           this.angle = new AngleXY( plane );
           this.angle.setVisible( false );
           this.magnitude.setVisible( false );
           createVector();
       }

       public VectorXY( double x, double y, XYPlane plane ){
           this( plane );
           setPlaneCoordinates( x, y );
       }

       public void setColor( Color color ){}

       public void highlight(){
           line.setStroke( RED );
           line.setStrokeWidth( highlightSize );
           arrowHead.highlight();
           angle.highlight();
       }

       public void setMagnitudeVisible( boolean visible ){
           magnitude.setVisible( visible );
       }

       public void unhighlight(){
           line.setStroke( GREEN );
           line.setStrokeWidth( size );
           arrowHead.unhighlight();
           angle.unhighlight();
       }

       private void updateVector(){
           line.setEndX( plane.toSceneX( getX() ) );
           line.setEndY( plane.toSceneY( getY() ) );
           arrowHead.setPlaneCoordinates( getX(), getY() );
           angle.setPlaneCoordinates( getX(), getY() );
           updateMagnitude();
       }

       public void setSceneCoordinates( double x, double y){
          super.setSceneCoordinates( x, y );
          setPlaneCoordinates(  plane.toCoordinateX( x ), plane.toCoordinateY( y )  );
       }

       public void setPlaneCoordinates( double x, double y ){
          super.setPlaneCoordinates( x, y );
          updateVector();
       }

       public void setAngleVisible( boolean visible ){
          angle.setVisible( visible );
       }

       private void createVector(){
          updateVector();

          line.setStartX( plane.getCenterX() );
          line.setStartY( plane.getCenterY() );
          line.setStroke( GREEN );
          line.setStrokeWidth( size );
          arrowHead.setColor( GREEN );
          getChildren().add( line );
          getChildren().add( arrowHead );
          getChildren().add( angle );
          getChildren().add( magnitude );
       }

       public double getMagnitude(){
           return Math.sqrt( Math.pow( getX(), 2 ) + Math.pow( getY(), 2 ) );
       }

       private void updateMagnitude(){
           double midX = getX() / 2.0;
           double midY = getY() / 2.0;

           magnitude.setX( plane.toSceneX( midX ) );
           magnitude.setY( plane.toSceneY( midY ) );
           magnitude.setText( String.format( "%2.2f", getMagnitude() ) );
       }

}
