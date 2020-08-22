package edu.thinkbox.math.plane.cartesian;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class PointXY extends XYObject{
       private Circle point;

       public PointXY( XYPlane plane ){
           super( plane );
           setSize( 5.0 );
           setHighlightSize( 7.0 );
           super.setColor( BLUE );
           create();
       }

       public PointXY( double x, double y, XYPlane plane ){
           this( plane );
           setPlaneCoordinates( x, y );
       }

       public void highlight(){
           point.setFill( getHighlightColor() );
           point.setRadius(getHighlightSize() );
       }
       public void unhighlight(){
           point.setFill( getColor() );
           point.setRadius( getSize() );
       }

       public void setColor( Color color ){}

       private void create(){
           point = new Circle( plane.toSceneX( getX() ),
                               plane.toSceneY( getY() ),
                               getSize(), getColor() );
           getChildren().add( point );
       }

       public void setPlaneCoordinates( double x, double y ){
           super.setPlaneCoordinates( x, y );
           update();
       }

       private void update(){
           point.setCenterX( plane.toSceneX( getX() ) );
           point.setCenterY( plane.toSceneY( getY() ) );
       }
}
