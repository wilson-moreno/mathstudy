package edu.thinkbox.math.plane.cartesian;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import edu.thinkbox.math.matrix.Matrix;

public class VectorXY extends XYObject{
       private Line line;
       private ArrowHeadXY arrowHead;
       private AngleXY angle;
       private Text magnitude;
       private PointXY point;
       private double shrink;

       public VectorXY( XYPlane plane){
           super( plane );
           this.line = new Line();
           this.magnitude = new Text();
           this.arrowHead = new ArrowHeadXY( plane, ArrowHeadType.TRIANGLE );
           this.angle = new AngleXY( plane );
           this.point = new PointXY( plane );
           this.angle.setVisible( false );
           this.magnitude.setVisible( false );
           this.point.setVisible( false );
           create();
       }

       public VectorXY( double x, double y, XYPlane plane ){
           this( plane );
           setPlaneCoordinates( x, y );
       }

       public void setColor( Color color ){}

       public void highlight(){
           line.setStroke( RED );
           line.setStrokeWidth( getHighlightSize() );
           arrowHead.highlight();
           angle.highlight();
           point.highlight();
       }

       public void setMagnitudeVisible( boolean visible ){
           magnitude.setVisible( visible );
       }

       public void unhighlight(){
           line.setStroke( GREEN );
           line.setStrokeWidth( getSize() );
           arrowHead.unhighlight();
           angle.unhighlight();
           point.unhighlight();
       }

       private void update(){
           shrink = 1.0;
           if( point.isVisible() ) shrink = 1.0 - ( 0.40 / getMagnitude() );
           line.setEndX( plane.toSceneX( getX() * shrink ) );
           line.setEndY( plane.toSceneY( getY() * shrink ) );
           arrowHead.setPlaneCoordinates( getX() * shrink, getY() * shrink );
           angle.setPlaneCoordinates( getX(), getY() );
           point.setPlaneCoordinates( getX(), getY() );
           updateMagnitude();
       }

       public void setSceneCoordinates( double x, double y){
          super.setSceneCoordinates( x, y );
          setPlaneCoordinates(  plane.toCoordinateX( x ), plane.toCoordinateY( y )  );
       }

       public void setPlaneCoordinates( double x, double y ){
          super.setPlaneCoordinates( x, y );
          update();
       }

       public void setAngleVisible( boolean visible ){
          angle.setVisible( visible );
       }

       public void setPointVisible( boolean visible ){
          point.setVisible( visible );
          update();
       }

       private void create(){
          update();

          line.setStartX( plane.getCenterX() );
          line.setStartY( plane.getCenterY() );
          line.setStroke( GREEN );
          line.setStrokeWidth( getSize() );
          arrowHead.setColor( GREEN );
          getChildren().add( line );
          getChildren().add( arrowHead );
          getChildren().add( angle );
          getChildren().add( magnitude );
          getChildren().add( point );
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


       public XYObject transform( Matrix transformationMatrix ){
           XYObject object = super.transform( transformationMatrix );
           update();
           return object;
       }

       public XYObject scale( double scalar ){
           XYObject object = super.scale( scalar );
           update();
           return object;
       }

}
