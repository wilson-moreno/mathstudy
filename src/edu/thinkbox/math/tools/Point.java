package edu.thinkbox.math.tools;

import javafx.scene.Group;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import edu.thinkbox.math.matrix.Matrix;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.Menu;
import javafx.stage.Window;
import javafx.stage.PopupWindow.AnchorLocation;
import javafx.scene.Node;
import edu.thinkbox.math.matrix.Transformation;
import javafx.scene.text.Text;

public class Point extends Group{
      private static final double RADIUS = 4.0;
      private static final double RADIUS_BIG = 6.0;
      private final Color  POINT_COLOR = Color.web( "002f55" );
      private final Color  BIG_COLOR = Color.web( "ff1a00" );
      private Matrix vector = Matrix.createColumnMatrix( 2 );
      private CartesianPlane plane;
      private Circle point;
      private RiseRun riseRun;
      private Text coordinatesLabel;

      public Point( double x, double y, CartesianPlane plane ){
          this.plane = plane;
          riseRun = new RiseRun( plane );
          coordinatesLabel = new Text();
          vector.setEntry( 0, 0, x );
          vector.setEntry( 1, 0, y );
          riseRun.setVector( vector );
          riseRun.setVisible( false );
          setCoordinatesVisible( false );
          setCoordinatesLabel( coordinatesLabel );
          getChildren().add( this.point = point() );
          getChildren().add( riseRun );
          getChildren().add( coordinatesLabel );
      }

      private void setCoordinatesLabel( Text label ){
          label.setText( String.format( "[ %2.2f, %2.2f ]", vector.getEntry( 0, 0 ), vector.getEntry( 1, 0 ) ) );

          if( plane.getQuadrant( vector ) == 1 ){
             label.setX( plane.toSceneX( vector.getEntry( 0, 0 ) ) - 30 );
             label.setY( plane.toSceneY( vector.getEntry( 1, 0 ) ) - 20 );
          } else if( plane.getQuadrant( vector ) == 2 ){
             label.setX( plane.toSceneX( vector.getEntry( 0, 0 ) ) - 30 );
             label.setY( plane.toSceneY( vector.getEntry( 1, 0 ) ) - 20 );
          } else if( plane.getQuadrant( vector ) == 3 ){
             label.setX( plane.toSceneX( vector.getEntry( 0, 0 ) ) - 30 );
             label.setY( plane.toSceneY( vector.getEntry( 1, 0 ) ) + 30 );
          } else if( plane.getQuadrant( vector ) == 4 ){
             label.setX( plane.toSceneX( vector.getEntry( 0, 0 ) ) - 30 );
             label.setY( plane.toSceneY( vector.getEntry( 1, 0 ) ) + 30 );
          }

      }

      private Circle point(){
        Circle circle = new Circle( plane.toSceneX( vector.getEntry( 0, 0 ) ),
                                    plane.toSceneY( vector.getEntry( 1, 0 ) ),
                                    RADIUS, POINT_COLOR );
        return circle;
      }

      public void setVector( Matrix vector ){
          setCoordinates( vector.getEntry( 0, 0 ), vector.getEntry( 1, 0 ) );
      }

      public void setCoordinatesVisible( boolean visible ){
          coordinatesLabel.setVisible( visible );
      }

      public void setCoordinates( double x, double y ){
          vector.setEntry( 0, 0, x );
          vector.setEntry( 1, 0, y );
          point.setCenterX( plane.toSceneX( x ) );
          point.setCenterY( plane.toSceneY( y ) );
          riseRun.setVector( vector );
          setCoordinatesLabel( coordinatesLabel );
      }

      public void setRiseRunVisible( boolean visible ){
          riseRun.setVisible( visible );
      }


      public void bigPoint(){
          point.setRadius( RADIUS_BIG );
          point.setStroke( BIG_COLOR );
          point.setFill( BIG_COLOR );
      }

      public void regularPoint(){
          point.setRadius( RADIUS );
          point.setStroke( POINT_COLOR );
          point.setFill( POINT_COLOR );
      }

}
