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


public class CartesianPlane extends Group implements EventHandler< ContextMenuEvent > {
      private static final int    PADDING = 10;
      private static final double GRID_WIDTH = 0.15;
      private static final double AXES_WIDTH = 2.0;
      private static final Color  GRID_COLOR = Color.web( "4a688a" );
      private static final Color  AXES_COLOR = Color.web( "002f55" );
      private static final double ARROW_WIDTH = 8;
      private static final double ARROW_SIZE = 150.0 * ( Math.PI / 180.0 ) ;
      private static final double TICK_LENGTH = 3.0;
      private static final double TICK_WIDTH = 1.0;
      private static final Color  TICK_COLOR = Color.web( "002f55" );
      private int   width;
      private int   height;
      private int   moduleSize;
      private Group gridLines;
      private Group axes;
      private Group ticks;
      private Group vectors;
      private Group points;
      private double lastScreenX;
      private double lastScreenY;
      private Rectangle rectangle;
      private PlaneContextMenu contextMenu;
      private boolean showMouseCoordinates;

      public CartesianPlane( int width, int height, int moduleSize ){
          this.width = width;
          this.height = height;
          this.moduleSize = moduleSize;
          this.gridLines = createGridLines();
          this.axes = createAxes();
          this.ticks = createTicks();
          this.vectors = new Group();
          this.points = new Group();
          this.rectangle = new Rectangle( 0, 0, width, height );
          this.rectangle.setFill( Color.WHITE );
          this.rectangle.setOnContextMenuRequested( this );
          this.gridLines.setOnContextMenuRequested( this );
          this.axes.setOnContextMenuRequested( this );
          this.ticks.setOnContextMenuRequested( this );
          this.showMouseCoordinates = false;
          this.contextMenu = new PlaneContextMenu( this );
          getChildren().add( rectangle );
          getChildren().add( gridLines );
          getChildren().add( axes );
          getChildren().add( ticks );
          getChildren().add( vectors );
          getChildren().add( points );


          addEventFilter( MouseEvent.ANY, new MouseGridSnapEventHandler( this ) );
          addEventFilter( MouseEvent.MOUSE_CLICKED, this.contextMenu );
      }


      @Override
      public void handle( ContextMenuEvent event ){
          contextMenu.setAnchorLocation( AnchorLocation.WINDOW_TOP_LEFT );
          contextMenu.show( this, event.getScreenX(), event.getScreenY() );
      }

      public int getWidth(){ return width; }
      public int getHeight(){ return height; }
      public int getModuleSize(){ return moduleSize; }
      public int getCenterX(){ return width / 2; }
      public int getCenterY(){ return height / 2; }
      public void setWidth( int width ){ this.width = width; }
      public void setHeight( int height ){ this.height = height; }
      public void setModuleSize( int moduleSize ){ this.moduleSize = moduleSize; }
      public void showGridLines( boolean show ){ gridLines.setVisible( show ); }
      public void showAxes( boolean show ){ axes.setVisible( show ); }

      public double getXBound(){ return ( width / 2.0 ) / moduleSize; }

      private int getColumnCount(){ return width / moduleSize; }
      private int getRowCount(){ return height / moduleSize; }

      public void setMouseCoordinatesVisible( boolean visible ){
          this.showMouseCoordinates = visible;
      }

      public void setGridlinesVisible( boolean visible ){
          gridLines.setVisible( visible );
      }

      public void setAxesVisible( boolean visible ){
          axes.setVisible( visible );
      }

      public void setTicksVisible( boolean visible ){
          ticks.setVisible( visible );
      }

      public boolean isMouseCoordinateVisible(){
          return this.showMouseCoordinates;
      }


      public double toCoordinateX( double sceneX ){
          return ( sceneX - getCenterX() ) / moduleSize;
      }

      public double toCoordinateY( double sceneY ){
          return ( ( sceneY - getCenterY() ) / moduleSize ) * -1;
      }

      public double toSceneX( double xCoordinate ){
          return getCenterX() + ( xCoordinate * moduleSize );
      }

      public double toSceneY( double yCoordinate ){
          return getCenterY() - ( yCoordinate * moduleSize );
      }

      private Group createGridLines(){
          Group gridLines = new Group();
          Text origin = new Text( getCenterX() + 5 , getCenterY() + 15 , "O" );
          origin.setFont( new Font( 12 ) );

          for( int i = 0; i <= getRowCount(); i++ ){
              Line line = new Line( 0, i * moduleSize, width, i * moduleSize );
              line.setStrokeWidth( GRID_WIDTH );
              line.setStroke( GRID_COLOR );
              gridLines.getChildren().add( line );
          }

          for( int j = 0; j <= getColumnCount(); j++ ){
              Line line = new Line( j * moduleSize, 0, j * moduleSize, height );
              line.setStrokeWidth( GRID_WIDTH );
              line.setStroke( GRID_COLOR );
              gridLines.getChildren().add( line );
          }

          return gridLines;
      }

      private Group createAxes(){
        Group axes = new Group();
        Line centerVerticalLine = new Line( getCenterX(), 5, getCenterX(), height - 5 );
        Line centerHorizontalLine = new Line( 5, getCenterY(), width - 5, getCenterY() );
        Text origin = new Text( getCenterX() + 5 , getCenterY() + 15 , "O" );
        Text yAxis = new Text( getCenterX() - 15, 15, "y");
        Text xAxis = new Text( width - 20, getCenterY() + 15, "x");

        origin.setFont( new Font( 12 ) );
        yAxis.setFont( new Font( 15 ) );
        xAxis.setFont( new Font( 15 ) );

        centerVerticalLine.setStrokeWidth( AXES_WIDTH );
        centerVerticalLine.setStroke( AXES_COLOR );
        centerHorizontalLine.setStrokeWidth( AXES_WIDTH );
        centerHorizontalLine.setStroke( AXES_COLOR );

        Matrix arrow1 = Matrix.createColumnMatrix( 2 );
        Matrix arrow2 = Matrix.createColumnMatrix( 2 );
        Matrix arrow3 = Matrix.createColumnMatrix( 2 );
        Matrix arrow4 = Matrix.createColumnMatrix( 2 );

        arrow1.setEntry( 0, 0, 0.0 );
        arrow1.setEntry( 1, 0, ( ( ( height ) / moduleSize ) / 2 ) );
        arrow2.setEntry( 0, 0, 0.0 );
        arrow2.setEntry( 1, 0,  -1 * ( ( ( height ) / moduleSize ) / 2 ) );
        arrow3.setEntry( 0, 0, ( ( width ) / moduleSize ) / 2  );
        arrow3.setEntry( 1, 0, 0.0 );
        arrow4.setEntry( 0, 0, -1 * ( ( ( width ) / moduleSize ) / 2 ) );
        arrow4.setEntry( 1, 0, 0.0 );

        axes.getChildren().add( createArrow( arrow1 ) );
        axes.getChildren().add( createArrow( arrow2 ) );
        axes.getChildren().add( createArrow( arrow3 ) );
        axes.getChildren().add( createArrow( arrow4 ) );

        axes.getChildren().add( centerVerticalLine );
        axes.getChildren().add( centerHorizontalLine );

        axes.getChildren().add( origin );
        axes.getChildren().add( yAxis );
        axes.getChildren().add( xAxis );

        return axes;
      }

      public int getQuadrant( Matrix vector ){
          double x = vector.getEntry( 0, 0 );
          double y = vector.getEntry( 1, 0 );
          int quadrant = 0;

          if( x > 0.0 && y > 0.0 ) quadrant = 1;
          else if( x < 0.0 && y > 0.0 ) quadrant = 2;
          else if( x < 0.0 && y < 0.0 ) quadrant = 3;
          else if( x > 0.0 && y < 0.0 ) quadrant = 4;

          return quadrant;
      }

      private Group createTicks(){
          Group ticks = new Group();

          for( int i = 0; i < getRowCount() / 2; i++ ){
              Line tick1 = new Line( getCenterX() - TICK_LENGTH , ( i * moduleSize ) + getCenterY(), getCenterX() + TICK_LENGTH, ( i * moduleSize )  + getCenterY() );
              Line tick2 = new Line( getCenterX() - TICK_LENGTH , ( -1 * i * moduleSize ) + getCenterY(), getCenterX() + TICK_LENGTH, ( -1 * i * moduleSize )  + getCenterY() );
              tick1.setStrokeWidth( TICK_WIDTH );
              tick1.setStroke( TICK_COLOR );
              tick2.setStrokeWidth( TICK_WIDTH );
              tick2.setStroke( TICK_COLOR );
              ticks.getChildren().add( tick1 );
              ticks.getChildren().add( tick2 );
          }

          for( int j = 0; j < getColumnCount() / 2; j++ ){
              Line tick3 = new Line( ( j * moduleSize ) + getCenterX(), getCenterY() + TICK_LENGTH, ( j * moduleSize ) + getCenterX(), getCenterY() - TICK_LENGTH );
              Line tick4 = new Line( ( -1 * j * moduleSize ) + getCenterX(), getCenterY() + TICK_LENGTH, ( -1 * j * moduleSize ) + getCenterX(), getCenterY() - TICK_LENGTH );
              tick3.setStrokeWidth( TICK_WIDTH );
              tick3.setStroke( TICK_COLOR );
              tick4.setStrokeWidth( TICK_WIDTH );
              tick4.setStroke( TICK_COLOR );
              ticks.getChildren().add( tick3 );
              ticks.getChildren().add( tick4 );
          }

          return ticks;
      }

      private Group createArrow( Matrix vector ){
          Group  arrow = new Group();
          double direction = direction( vector );

          double x = vector.getEntry( 0, 0 );
          double y = vector.getEntry( 1, 0 );
          double tx = getCenterX() + ( x * moduleSize );
          double ty = getCenterY() - ( y * moduleSize );
          double delta1 = direction - ARROW_SIZE;
          double delta2 = direction + ARROW_SIZE;
          double x2 = ARROW_WIDTH * Math.cos( delta1 );
          double y2 = ARROW_WIDTH * Math.sin( delta1 );
          double x3 = ARROW_WIDTH * Math.cos( delta2 );
          double y3 = ARROW_WIDTH * Math.sin( delta2 );

          Line line1 = new Line( tx, ty, tx+x2, ty-y2 );
          Line line2 = new Line( tx, ty, tx+x3, ty-y3 );
          line1.setStrokeWidth( AXES_WIDTH );
          line1.setStroke( AXES_COLOR );
          line2.setStrokeWidth( AXES_WIDTH );
          line2.setStroke( AXES_COLOR );
          arrow.getChildren().add( line1 );
          arrow.getChildren().add( line2 );

          return arrow;
      }

      public double direction( Matrix vector ){
          double x = vector.getEntry( 0, 0 );
          double y = vector.getEntry( 1, 0 );

          if( x > 0.0 && y == 0.0 ) return 0.0;
          else if( x == 0.0 && y > 0.0 ) return Math.PI / 2.0;
          else if( x < 0.0 && y == 0.0 ) return Math.PI;
          else if( x == 0.0 && y < 0.0 ) return Math.PI * ( 3.0 / 2.0 );
          else {
              double radians = Math.atan( y / x );
              if( ( x < 0.0 && y > 0.0 ) || ( x < 0.0 && y < 0.0 ) ) radians += Math.PI;
              else if( ( x > 0.0 && y < 0.0 ) ) radians += 2 * Math.PI;
              return radians;
          }

      }

      public double toDegree( double radians ){
          return radians * ( 180.0 / Math.PI );
      }

      public Vector addVector( double x, double y ){
          Vector vector = new Vector( x, y, this );
          vector.setOnContextMenuRequested( new VectorContextMenuEventHandler( vector ) );
          vector.addEventFilter( MouseEvent.ANY, new MouseOverVectorEventHandler( this ) );
          vectors.getChildren().add( vector );
          return vector;
      }

      public Point addPoint( double x, double y ){
          Point point = new Point( x, y, this );
          point.setOnContextMenuRequested( new PointContextMenuEventHandler( point ) );
          point.addEventFilter( MouseEvent.ANY, new MouseOverPointEventHandler( this) );
          points.getChildren().add( point );
          return point;
      }

      public void clearVectors(){
          vectors.getChildren().clear();
      }

      public void clearPoints(){
          points.getChildren().clear();
      }


      private double translateX( double screenX ){
          return ( screenX - getCenterX() ) / moduleSize;
      }

      private double translateY( double screenY ){
          return -1 * ( ( screenY - getCenterY() ) / moduleSize );
      }

      public static class PointContextMenu extends ContextMenu{
            private Point point;

            public PointContextMenu( Point point ){
                this.point = point;
            }
      }



}
