package edu.thinkbox.math.plane.cartesian;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import edu.thinkbox.math.matrix.Matrix;

public abstract class XYObject extends Group{
       protected Color  color = Color.BLACK;
       protected Color  highlightColor = Color.web( "ff1a00" );
       protected double size = 2.0;
       protected double highlightSize = 3.0;
       protected Matrix coordinates = Matrix.createColumnMatrix( 2 );
       protected XYPlane plane;

       public XYObject( XYPlane xyPlane ){
          this.plane = xyPlane;
       }

       public abstract void hightlight();
       public abstract void unhighlight();

       protected void setScreenCoordinates( double x, double y ){
          coordinates.setEntry( 0, 0, plane.toCoordinateX( x ) );
          coordinates.setEntry( 1, 0, plane.toCoordinateY( y ) );
       }

       protected void setPlaneCoordinates( double x, double y ){
          coordinates.setEntry( 0, 0, x );
          coordinates.setEntry( 1, 0, y );
       }

       protected double getX(){ return coordinates.getEntry( 0, 0 ); }
       protected double getY(){ return coordinates.getEntry( 1, 0 ); }
}
