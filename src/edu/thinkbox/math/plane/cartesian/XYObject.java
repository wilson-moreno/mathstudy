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
       protected boolean wholeNumberCoordinates;
       protected boolean coordinatesVisible;
       private RiseRunXY riseRun;
       private CoordinatesXY coordinatesXY;

       public XYObject( XYPlane plane ){
          this.plane = plane;
          this.wholeNumberCoordinates = false;
          this.coordinatesVisible = false;
          this.riseRun = new RiseRunXY( plane );
          this.coordinatesXY = new CoordinatesXY( plane );
          setRiseRunVisible( false );
          setCoordinatesVisible( false );
          getChildren().add( riseRun );
          getChildren().add( coordinatesXY );
       }

       public abstract void hightlight();
       public abstract void unhighlight();

       public void setRiseRunVisible( boolean visible ){
          riseRun.setVisible( visible );
       }

       public void setWholeNumberCoordinates( boolean wholeNumbers ){
           this.wholeNumberCoordinates = wholeNumbers;
       }
       public void setCoordinatesVisible( boolean visible){
           this.coordinatesXY.setVisible( visible );
       }

       protected void setSceneCoordinates( double x, double y ){
          setPlaneCoordinates( plane.toCoordinateX( x ),
                               plane.toCoordinateY( y ) );
       }

       protected void setPlaneCoordinates( double x, double y ){
          if( wholeNumberCoordinates ){
              x = Math.round( x );
              y = Math.round( y );
          }
          coordinates.setEntry( 0, 0, x );
          coordinates.setEntry( 1, 0, y );
          riseRun.setPlaneCoordinates( x, y );
          coordinatesXY.setPlaneCoordinates( x, y );
       }

       protected double getX(){ return coordinates.getEntry( 0, 0 ); }
       protected double getY(){ return coordinates.getEntry( 1, 0 ); }
}
