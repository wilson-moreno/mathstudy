package edu.thinkbox.math.tools;

import javafx.scene.Group;
import javafx.scene.text.Text;
import javafx.scene.shape.Line;
import edu.thinkbox.math.matrix.Matrix;

public class RiseRun extends Group{
      private CartesianPlane plane;
      private Line rise;
      private Line run;
      private Text riseValue;
      private Text runValue;

      public RiseRun( CartesianPlane plane ){
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


      public void setVector( Matrix vector ){
          rise.setStartX( plane.toSceneX( vector.getEntry( 0, 0 ) ) );
          rise.setStartY( plane.toSceneY( 0 ) );
          rise.setEndX( plane.toSceneX( vector.getEntry( 0, 0 ) ) );
          rise.setEndY( plane.toSceneY( vector.getEntry( 1, 0 ) ) );
          run.setStartX( plane.toSceneX( 0 ) );
          run.setStartY( plane.toSceneY( vector.getEntry( 1, 0 ) ) );
          run.setEndX( plane.toSceneX( vector.getEntry( 0, 0 ) ) );
          run.setEndY( plane.toSceneY( vector.getEntry( 1, 0 ) ) );

          setRiseRunValue( vector );
      }

      private void setRiseRunValue( Matrix vector ){
          riseValue.setX( plane.toSceneX( vector.getEntry( 0, 0 ) ) );
          riseValue.setY( plane.toSceneY( vector.getEntry( 1, 0 ) / 2.0 )  );
          riseValue.setText( String.format( "%4.2f", vector.getEntry( 1, 0 ) ) );
          runValue.setX( plane.toSceneX( vector.getEntry( 0, 0 )  / 2.0  ) );
          runValue.setY( plane.toSceneY( vector.getEntry( 1, 0 ) ) );
          runValue.setText( String.format( "%4.2f", vector.getEntry( 0, 0 ) ) );

          if( plane.getQuadrant( vector ) == 1 ){
              runValue.setY( runValue.getY() - 10 );
              riseValue.setX( riseValue.getX() + 10 );
          } else if( plane.getQuadrant( vector ) == 2 ){
              runValue.setY( runValue.getY() - 10 );
              riseValue.setX( riseValue.getX() - 30 );
          } else if( plane.getQuadrant( vector ) == 3 ){
              runValue.setY( runValue.getY() + 20 );
              riseValue.setX( riseValue.getX() - 35 );
          } else if( plane.getQuadrant( vector ) == 4 ){
              runValue.setY( runValue.getY() + 20 );
              riseValue.setX( riseValue.getX() + 10 );
          }
      }

}
