package edu.thinkbox.math.plane.cartesian;

import javafx.scene.Group;
import javafx.scene.text.Text;
import javafx.scene.shape.Line;
import edu.thinkbox.math.matrix.Matrix;

public class RiseRunXY extends Group{
      private Line rise;
      private Line run;
      private Text riseValue;
      private Text runValue;
      private XYPlane plane;
      private Matrix coordinates = Matrix.createColumnMatrix( 2 );

      public RiseRunXY( XYPlane plane ){
          this.plane  = plane;
          this.rise   = new Line();
          this.run    = new Line();
          this.rise.getStrokeDashArray().addAll(2d);
          this.run.getStrokeDashArray().addAll(2d);
          this.riseValue = new Text();
          this.runValue = new Text();
          getChildren().add( rise );
          getChildren().add( run );
          getChildren().add( riseValue );
          getChildren().add( runValue );
      }


      public RiseRunXY( double x, double y, XYPlane plane ){
          this( plane );
          setPlaneCoordinates( x, y );
      }

      public void setSceneCoordinates( double x, double y ){
         setPlaneCoordinates( plane.toCoordinateX( x ),
                              plane.toCoordinateY( y ) );
      }

      public void setPlaneCoordinates( double x, double y ){
         coordinates.setEntry( 0, 0, x );
         coordinates.setEntry( 1, 0, y );
         updateRiseRun();
      }


      public void updateRiseRun(){

          rise.setStartX( plane.toSceneX( getX() ) );
          rise.setStartY( plane.toSceneY( 0 ) );
          rise.setEndX( plane.toSceneX( getX() ) );
          rise.setEndY( plane.toSceneY( getY() ) );
          run.setStartX( plane.toSceneX( 0 ) );
          run.setStartY( plane.toSceneY( getY() ) );
          run.setEndX( plane.toSceneX( getX() ) );
          run.setEndY( plane.toSceneY( getY() ) );

          updateRiseRunValue();
      }

      private void updateRiseRunValue(){
          riseValue.setX( plane.toSceneX( getX() ) );
          riseValue.setY( plane.toSceneY( getY() / 2.0 )  );
          riseValue.setText( String.format( "%4.2f", getY() ) );
          runValue.setX( plane.toSceneX( getX()  / 2.0  ) );
          runValue.setY( plane.toSceneY( getY() ) );
          runValue.setText( String.format( "%4.2f", getY() ) );

          if( plane.getQuadrant( coordinates ) == 1 ){
              runValue.setY( runValue.getY() - 10 );
              riseValue.setX( riseValue.getX() + 10 );
          } else if( plane.getQuadrant( coordinates ) == 2 ){
              runValue.setY( runValue.getY() - 10 );
              riseValue.setX( riseValue.getX() - 30 );
          } else if( plane.getQuadrant( coordinates ) == 3 ){
              runValue.setY( runValue.getY() + 20 );
              riseValue.setX( riseValue.getX() - 35 );
          } else if( plane.getQuadrant( coordinates ) == 4 ){
              runValue.setY( runValue.getY() + 20 );
              riseValue.setX( riseValue.getX() + 10 );
          }
      }

      private double getX(){ return coordinates.getEntry( 0, 0 ); }
      private double getY(){ return coordinates.getEntry( 1, 0 ); }

}
