package edu.thinkbox.math.plane.cartesian;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class VectorXY extends XYObject{
       private Line line;
       private ArrowHeadXY arrowHead;

       public VectorXY( XYPlane plane){
           super( plane );
           this.line = new Line();
           this.arrowHead = new ArrowHeadXY( plane, ArrowHeadType.TRIANGLE );
           createVector();
       }

       public VectorXY( double x, double y, XYPlane plane ){
           this( plane );
           setPlaneCoordinates( x, y );
       }

       public void hightlight(){}
       public void unhighlight(){}

       private void updateVector(){
           line.setEndX( plane.toSceneX( getX() ) );
           line.setEndY( plane.toSceneY( getY() ) );
           arrowHead.setPlaneCoordinates( getX(), getY() );
       }

       public void setSceneCoordinates( double x, double y){
          super.setSceneCoordinates( x, y );
          setPlaneCoordinates(  plane.toCoordinateX( x ), plane.toCoordinateY( y )  );
       }

       public void setPlaneCoordinates( double x, double y ){
          super.setPlaneCoordinates( x, y );
          updateVector();
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
       }
}
