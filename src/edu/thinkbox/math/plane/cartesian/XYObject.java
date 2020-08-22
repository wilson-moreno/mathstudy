package edu.thinkbox.math.plane.cartesian;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import edu.thinkbox.math.matrix.Matrix;

public abstract class XYObject extends Group{
       protected static final Color RED = Color.web( "ff1a00" );
       protected static final Color GREEN = Color.web( "509237" );
       protected static final Color BLUE = Color.web( "002f55" );
       private Color  color = GREEN;
       private Color  highlightColor = RED;
       private double size = 2.0;
       private double highlightSize = 3.0;
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

       public abstract void highlight();
       public abstract void unhighlight();
       public void setColor( Color color ){
           this.color = color;
       }

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

       public void setSize( double size ){ this.size = size; }
       public void setHighlightSize( double size ){ this.highlightSize = size; }
       public void setHighlightColor( Color color ){ this.highlightColor = color; }
       public double getSize(){ return this.size; }
       public Color getColor(){ return this.color; }
       public double getHighlightSize(){ return highlightSize; }
       public Color getHighlightColor(){ return highlightColor; }
       protected double getX(){ return coordinates.getEntry( 0, 0 ); }
       protected double getY(){ return coordinates.getEntry( 1, 0 ); }
       public XYObject transform( Matrix transformation ){
           coordinates.copyEntries( transformation.multiply( coordinates ) );
           return this;
       }
       public XYObject scale( double scalar ){
           coordinates.copyEntries( coordinates.scalarProduct( scalar ) );
           return this;
       }

}
