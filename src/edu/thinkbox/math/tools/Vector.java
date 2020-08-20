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

public class Vector extends Group{
      private final Color COLOR = Color.web( "509237" );
      private final Color WIDE_COLOR = Color.web( "ff1a00" );
      private static final double STROKE_WIDTH = 3.0;
      private static final double WIDE_STROKE = 5.0;
      private final Matrix vector = Matrix.createColumnMatrix( 2 );
      private Line lineSegment;
      private Polygon arrow;
      private Text xLabel = new Text();
      private Text yLabel = new Text();
      private Text magnitudeLabel = new Text();
      private RiseRun riseRun;
      private Point point;
      private Angle angle;
      private CartesianPlane plane;
      private boolean wholeNumberVectors;

      public Vector( double x, double y, CartesianPlane plane){
          this.plane = plane;
          wholeNumberVectors = false;
          vector.setEntry( 0, 0, x );
          vector.setEntry( 1, 0, y );
          lineSegment = lineSegment();
          arrow = arrow();
          point = new Point( x, y, plane );
          xLabel.setFont( Font.font( 12.0 ) );
          yLabel.setFont( Font.font( 12.0 ) );
          magnitudeLabel.setFont( Font.font( 12.0 ) );
          riseRun = new RiseRun( plane );
          angle = new Angle( plane );
          riseRun.setVector( vector );
          angle.setVector( vector );
          setCoordinatesVisible( false );
          setMagnitudeVisible( false );
          setRiseRunVisible( false );
          setPointVisible( false );
          setAngleVisible( false );
          setXLabel( xLabel );
          setYLabel( yLabel );
          setMagnitudeLabel( magnitudeLabel );
          getChildren().add( lineSegment );
          getChildren().add( arrow );
          getChildren().add( xLabel );
          getChildren().add( yLabel );
          getChildren().add( magnitudeLabel );
          getChildren().add( riseRun );
          getChildren().add( point );
          getChildren().add( angle );
      }

      public void setWholeNumberVectors( boolean wholeNumberVectors ){
          this.wholeNumberVectors = wholeNumberVectors;
      }

      public void setPointVisible( boolean visible ){
          point.setVisible( visible );
      }

      public void setMagnitudeVisible( boolean visible ){
          magnitudeLabel.setVisible( visible );
      }

      public void setAngleVisible( boolean visible ){
          angle.setVisible( visible );
      }

      public double direction(){
          double x = vector.getEntry( 0, 0 );
          double y = vector.getEntry( 1, 0 );

          if( x > 0.0 && y == 0.0 )      return 0.0;              // Direction is 0 degree
          else if( x == 0.0 && y > 0.0 ) return Math.PI / 2.0;    // Direction is 90 degrees
          else if( x < 0.0 && y == 0.0 ) return Math.PI;          // Directions is 180 degrees
          else if( x == 0.0 && y < 0.0 ) return Math.PI * ( 3.0 / 2.0 ); // Direction is 270 degrees
          else {

              double radians = Math.atan( y / x );

              // Computes the true direction when the vector is in the 2nd Quadrant ( + , - ) or
              // in the 3rd Quadrant ( - , - ). PI to the radians.
              if( ( x < 0.0 && y > 0.0 ) || ( x < 0.0 && y < 0.0 ) ) radians = Math.PI + radians;

              return radians;
          }

      }

      public double getMagnitude(){
          return Math.sqrt( Math.pow( vector.getEntry( 0, 0 ), 2 ) + Math.pow( vector.getEntry( 1, 0 ), 2 ) );
      }

      public void wideArrow(){
          lineSegment.setStrokeWidth( WIDE_STROKE );
          arrow.setStrokeWidth( WIDE_STROKE );
          lineSegment.setFill( WIDE_COLOR );
          lineSegment.setStroke( WIDE_COLOR );
          arrow.setFill( WIDE_COLOR );
          arrow.setStroke( WIDE_COLOR );
          point.bigPoint();
          angle.wideStroke();
      }

      public void regularArrow(){
          lineSegment.setStrokeWidth( STROKE_WIDTH );
          arrow.setStrokeWidth( STROKE_WIDTH );
          lineSegment.setFill( COLOR );
          lineSegment.setStroke( COLOR );
          arrow.setFill( COLOR );
          arrow.setStroke( COLOR );
          point.regularPoint();
          angle.regularStroke();
      }

      private Polygon arrow(){
          Polygon triangle = new Polygon();
          double size = 10.0;
          double theta = radians( 160.0 );

          double delta1 = direction() - theta;
          double delta2 = direction() + theta;
          double x2 = size * Math.cos( delta1 );
          double y2 = size * Math.sin( delta1 );
          double x3 = size * Math.cos( delta2 );
          double y3 = size * Math.sin( delta2 );

          triangle.getPoints().addAll( new Double[] { tx(), ty(), tx()+x2, ty()-y2, tx()+x3, ty()-y3 } );
          triangle.setStroke( COLOR );
          triangle.setFill( COLOR );
          triangle.setStrokeWidth( STROKE_WIDTH );
          return triangle;
      }

      private Double[] arrowPoints(){
        double size = 10.0;
        double theta = radians( 160.0 );

        double delta1 = direction() - theta;
        double delta2 = direction() + theta;
        double x2 = size * Math.cos( delta1 );
        double y2 = size * Math.sin( delta1 );
        double x3 = size * Math.cos( delta2 );
        double y3 = size * Math.sin( delta2 );

        return new Double[] { tx(), ty(), tx()+x2, ty()-y2, tx()+x3, ty()-y3 };
      }

      private Line lineSegment() {
        Line line = new Line( plane.getCenterX(), plane.getCenterY(), tx(), ty() );
        line.setStrokeWidth( STROKE_WIDTH );
        line.setStroke( COLOR );
        return line;
      }

      private double radians( double degree ){ return degree * ( Math.PI / 180 ); }


      public Vector transform( Matrix transformation ){
          vector.copyEntries( transformation.multiply( vector ) );
          setVector( vector );
          return this;
      }

      private void setVector( Matrix vector ){
          setCoordinates( vector.getEntry( 0, 0 ), vector.getEntry( 1, 0 ) );
      }

      public void setCoordinates( double x, double y ){
          if( wholeNumberVectors ){
              x = Math.round( x );
              y = Math.round( y );
          }
          vector.setEntry( 0, 0, x );
          vector.setEntry( 1, 0, y );
          lineSegment.setEndX( tx() );
          lineSegment.setEndY( ty() );
          setXLabel( xLabel );
          setYLabel( yLabel );
          setMagnitudeLabel( magnitudeLabel );
          arrow.getPoints().clear();
          arrow.getPoints().addAll( arrowPoints() );
          riseRun.setVector( vector );
          point.setVector( vector );
          angle.setVector( vector );
      }


      private double tx(){ return plane.getCenterX() + ( vector.getEntry( 0, 0 ) * plane.getModuleSize() ); }
      private double ty(){ return plane.getCenterY() - ( vector.getEntry( 1, 0 ) * plane.getModuleSize() ); }

      public void setCoordinatesVisible( boolean visible ){
          xLabel.setVisible( visible );
          yLabel.setVisible( visible );
      }

      public void setRiseRunVisible( boolean visible ){
          riseRun.setVisible( visible );
      }

      private void setMagnitudeLabel( Text label ){
          double midX = vector.getEntry( 0, 0 ) / 2.0;
          double midY = vector.getEntry( 1, 0 ) / 2.0;

          label.setX( toSceneX( midX ) );
          label.setY( toSceneY( midY ) );
          label.setText( String.format( "%2.2f", getMagnitude() ) );
      }

      private double toSceneX( double x){
          return plane.getCenterX() + ( x * plane.getModuleSize() );
      }

      private double toSceneY( double y ){
          return plane.getCenterY() - ( y * plane.getModuleSize() );
      }

      private void setXLabel( Text label ){
        double x;
        double y;

        if( ( vector.getEntry( 0, 0 ) > 0.0 && vector.getEntry( 1, 0 ) > 0.0 ) ) {
            x = tx() + 10;
            y = ty() - 10;
        } else if ( vector.getEntry( 0, 0 ) < 0.0 && vector.getEntry( 1, 0 ) > 0.0  ) {
            x = tx() - 50;
            y = ty() - 10;
        } else if ( vector.getEntry( 0, 0 ) < 0.0 && vector.getEntry( 1, 0 ) < 0.0  ) {
            x = tx() - 50;
            y = ty() + 20;
        } else {
            x = tx() + 10;
            y = ty() + 20;
        }

        label.setX( x );
        label.setY( y );
        label.setText( String.format( "[ %02.2f ]", vector.getEntry( 0, 0 ) ) );
      }

      private void setYLabel( Text label ){
        double x;
        double y;

        if( ( vector.getEntry( 0, 0 ) > 0.0 && vector.getEntry( 1, 0 ) > 0.0 ) ) {
            x = tx() + 10;
            y = ty() + 5;
        } else if ( vector.getEntry( 0, 0 ) < 0.0 && vector.getEntry( 1, 0 ) > 0.0  ) {
            x = tx() - 50;
            y = ty() + 5;
        } else if ( vector.getEntry( 0, 0 ) < 0.0 && vector.getEntry( 1, 0 ) < 0.0  ) {
            x = tx() - 50;
            y = ty() + 35;
        } else {
            x = tx() + 10;
            y = ty() + 35;
        }

        label.setX( x );
        label.setY( y );
        label.setText( String.format( "[ %02.2f ]", vector.getEntry( 1, 0 ) ) );
      }

}
