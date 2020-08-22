package edu.thinkbox.math.plane.cartesian;

import javafx.scene.Group;
import javafx.scene.text.Text;
import javafx.scene.shape.Line;
import edu.thinkbox.math.matrix.Matrix;

public class CoordinatesXY extends Group{
      private XYPlane plane;
      private Text text;
      private Matrix coordinates;

      public CoordinatesXY( XYPlane plane ){
         this.plane = plane;
         text = new Text();
         coordinates = Matrix.createColumnMatrix( 2 );
         getChildren().add( text );
      }

      public void setSceneCoordinates( double x, double y ){
         setPlaneCoordinates( plane.toCoordinateX( x ),
                              plane.toCoordinateY( y ) );
      }

      public void setPlaneCoordinates( double x, double y ){
         coordinates.setEntry( 0, 0, x );
         coordinates.setEntry( 1, 0, y );
         updateCoordinates();
      }

      private void updateCoordinates(){
          text.setText( String.format( "[ %2.2f, %2.2f ]", getX(), getY() ) );

          if( plane.getQuadrant( coordinates ) == 1 ){
             text.setX( plane.toSceneX( getX() ) - 30 );
             text.setY( plane.toSceneY( getY() ) - 20 );
          } else if( plane.getQuadrant( coordinates ) == 2 ){
             text.setX( plane.toSceneX( getX() ) - 30 );
             text.setY( plane.toSceneY( getY() ) - 20 );
          } else if( plane.getQuadrant( coordinates ) == 3 ){
             text.setX( plane.toSceneX( getX() ) - 30 );
             text.setY( plane.toSceneY( getY() ) + 30 );
          } else if( plane.getQuadrant( coordinates ) == 4 ){
             text.setX( plane.toSceneX( getX() ) - 30 );
             text.setY( plane.toSceneY( getY() ) + 30 );
          } else if( plane.getQuadrant( coordinates ) == 90.0 ||
                     plane.getQuadrant( coordinates ) == 270.0 ){
                     text.setX( plane.toSceneX( getX() ) + 20 );
                     text.setY( plane.toSceneY( getY() ) );
          } else {
                text.setX( plane.toSceneX( getX() ) - 30 );
                text.setY( plane.toSceneY( getY() ) - 20 );
          }

      }

      private double getX(){ return coordinates.getEntry( 0, 0 ); }
      private double getY(){ return coordinates.getEntry( 1, 0 ); }

}
