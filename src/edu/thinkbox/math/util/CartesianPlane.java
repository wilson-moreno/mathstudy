package edu.thinkbox.math.util;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.shape.Line;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import edu.thinkbox.math.matrix.Matrix;

public class CartesianPlane extends Application{
      private static final int WIDTH = 600;
      private static final int HEIGHT = 600;
      private static final int PADDING = 5;
      private double centerX = WIDTH / 2;
      private double centerY = HEIGHT / 2;
      private double zoomFactor = 20.0;
      private Vector2D line;

      public static void main( String[] args ){
          launch( args );
      }

      @Override
      public void start( Stage primaryStage ){
        primaryStage.setTitle( "Cartesian Plane" );
        Group root = new Group();
        root.getChildren().add( createPlane() );
        root.getChildren().add( getVectors() );
        //root.getChildren().add( getPoints() );
        line.reflect();
        primaryStage.setScene( new Scene( root, WIDTH, HEIGHT ) );
        primaryStage.show();
      }

      private Group createPlane(){
        return new Plane( WIDTH, HEIGHT, centerX, centerY, zoomFactor );
      }

      private Group getVectors(){
        Group vectors = new Group();

        //vectors.getChildren().add( createVectorLine(  5, 5 ) );
        //vectors.getChildren().add( createVectorLine( -2, 7 ) );
        //vectors.getChildren().add( createVectorLine( -8, -3 ) );
        //vectors.getChildren().add( createVectorLine( 5, -6 ) );
        line = createVector( 5, 5 );
        vectors.getChildren().add( line );

        return vectors;
      }

      private Vector2D createVector( double x, double y){
          return new Vector2D( x, y, centerX, centerY, zoomFactor );
      }

      private Group createPoint( double x, double y ){
          return new Point2D( centerX, centerY, x, y, zoomFactor );
      }

      private Group getPoints(){
        Group points = new Group();

        return points;
      }



}

class Point2D extends Group{
      private static final double RADIUS = 4.0;
      private static final Color  POINT_COLOR = Color.web( "002f55" );
      private double centerX;
      private double centerY;
      private double zoomFactor;
      private Matrix vector = Matrix.createColumnMatrix( 2 );

      public Point2D( double centerX, double centerY, double x, double y, double zoomFactor ){
          this.centerX = centerX;
          this.centerY = centerY;
          this.zoomFactor = zoomFactor;
          vector.setEntry( 0, 0, x );
          vector.setEntry( 1, 0, y );
          getChildren().add( point() );
      }

      private Circle point(){
        Circle circle = new Circle( centerX + ( vector.getEntry( 0, 0 ) * zoomFactor ),
                                    centerY - ( vector.getEntry( 1, 0 ) * zoomFactor ),
                                    RADIUS, POINT_COLOR );
        return circle;
      }

}

class Plane extends Group{
      private static final Color GRID_COLOR = Color.web( "4a688a" );
      private static double WIDTH;
      private static double HEIGHT;
      private double centerX;
      private double centerY;
      private double zoomFactor;

      public Plane( double width, double height, double centerX, double centerY, double zoomFactor ){
          this.WIDTH = width;
          this.HEIGHT = height;
          this.centerX = centerX;
          this.centerY = centerY;
          this.zoomFactor = zoomFactor;
          getChildren().add( gridLines() );
          getChildren().add( axes() );
      }


      private Group gridLines(){
          Group gridLines = new Group();
          Text origin = new Text( centerX + 5 , centerY + 15 , "O" );
          origin.setFont( new Font( 12 ) );

          for( int i = 0; i <= ( HEIGHT / zoomFactor ); i++ ){
              Line line = new Line( 0, i * zoomFactor, WIDTH, i * zoomFactor );
              line.setStrokeWidth( 0.3 );
              line.setStroke( GRID_COLOR );
              gridLines.getChildren().add( line );
          }

          for( int j = 0; j <= ( WIDTH / zoomFactor ); j++ ){
              Line line = new Line( j * zoomFactor, 0, j * zoomFactor, HEIGHT );
              line.setStrokeWidth( 0.3 );
              line.setStroke( GRID_COLOR );
              gridLines.getChildren().add( line );
          }

          return gridLines;
      }

      private Group axes(){
          Group axes = new Group();
          Line centerVerticalLine = new Line( centerX, 5, centerX, HEIGHT - 5 );
          Line centerHorizontalLine = new Line( 5, centerY, WIDTH - 5, centerY );
          Text origin = new Text( centerX + 5 , centerY + 15 , "O" );
          Text yAxis = new Text( centerX - 15, 15, "y");
          Text xAxis = new Text( WIDTH - 20, centerY + 15, "x");

          origin.setFont( new Font( 12 ) );
          yAxis.setFont( new Font( 15 ) );
          xAxis.setFont( new Font( 15 ) );

          centerVerticalLine.setStrokeWidth( 2.0 );
          centerVerticalLine.setStroke( Color.web( "002f55" ) );
          centerHorizontalLine.setStrokeWidth( 2.0 );
          centerHorizontalLine.setStroke( Color.web( "002f55" ) );

          Matrix arrow1 = Matrix.createColumnMatrix( 2 );
          Matrix arrow2 = Matrix.createColumnMatrix( 2 );
          Matrix arrow3 = Matrix.createColumnMatrix( 2 );
          Matrix arrow4 = Matrix.createColumnMatrix( 2 );

          arrow1.setEntry( 0, 0, 0.0 );
          arrow1.setEntry( 1, 0, ( ( HEIGHT - 5 ) / zoomFactor) / 2 );
          arrow2.setEntry( 0, 0, 0.0 );
          arrow2.setEntry( 1, 0,  -1 * ( ( ( HEIGHT - 5 ) / zoomFactor) / 2 ) );
          arrow3.setEntry( 0, 0, ( ( WIDTH - 5 ) / zoomFactor) / 2  );
          arrow3.setEntry( 1, 0, 0.0 );
          arrow4.setEntry( 0, 0, -1 * ( ( ( WIDTH - 5 ) / zoomFactor) / 2 ) );
          arrow4.setEntry( 1, 0, 0.0 );

          axes.getChildren().add( arrow( arrow1, Color.web( "002f55" ) ) );
          axes.getChildren().add( arrow( arrow2, Color.web( "002f55" ) ) );
          axes.getChildren().add( arrow( arrow3, Color.web( "002f55" ) ) );
          axes.getChildren().add( arrow( arrow4, Color.web( "002f55" ) ) );

          axes.getChildren().add( centerVerticalLine );
          axes.getChildren().add( centerHorizontalLine );

          axes.getChildren().add( origin );
          axes.getChildren().add( yAxis );
          axes.getChildren().add( xAxis );

          return axes;
      }

      private Group arrow( Matrix vector, Color color ){
          Group triangle = new Group();
          double direction = direction( vector );
          double size = 5.0;
          double theta = 140;

          double x = vector.getEntry( 0, 0 );
          double y = vector.getEntry( 1, 0 );
          double tx = centerX + ( x * zoomFactor );
          double ty = centerY - ( y * zoomFactor );
          double delta1 = direction - theta;
          double delta2 = direction + theta;
          double x2 = size * Math.cos( radians( delta1 ) );
          double y2 = size * Math.sin( radians( delta1 ) );
          double x3 = size * Math.cos( radians( delta2 ) );
          double y3 = size * Math.sin( radians( delta2 ) );

          Line line1 = new Line( tx, ty, tx+x2, ty-y2 );
          Line line2 = new Line( tx, ty, tx+x3, ty-y3 );
          line1.setStrokeWidth( 2.0 );
          line1.setStroke( color );
          line2.setStrokeWidth( 2.0 );
          line2.setStroke( color );
          triangle.getChildren().add( line1 );
          triangle.getChildren().add( line2 );

          return triangle;
      }

      private double radians( double degree ){ return degree * ( Math.PI / 180 ); }

      private double direction( Matrix vector ){
          double x = vector.getEntry( 0, 0 );
          double y = vector.getEntry( 1, 0 );

          if( x > 0.0 && y == 0.0 ) return 0.0;
          else if( x == 0.0 && y > 0.0 ) return 90.0;
          else if( x < 0.0 && y == 0.0 ) return 180.0;
          else if( x == 0.0 && y < 0.0 ) return 270.0;
          else {

              double degree = Math.atan( y / x ) * ( 180.0 / Math.PI ) ;

              if( ( x < 0.0 && y > 0.0 ) || ( x < 0.0 && y < 0.0 ) ) degree = 180.0 + degree;

              return degree;
          }

      }

}

class Vector2D extends Group{
      private static final Color COLOR = Color.web( "509237" );
      private static final double STROKE_WIDTH = 2.0;
      private final Matrix vector = Matrix.createColumnMatrix( 2 );
      private double centerX;
      private double centerY;
      private double zoomFactor;
      private Line lineSegment;
      private Polygon arrow;

      public Vector2D( double x, double y, double centerX, double centerY, double zoomFactor ){
          vector.setEntry( 0, 0, x );
          vector.setEntry( 1, 0, y );
          this.centerX = centerX;
          this.centerY = centerY;
          this.zoomFactor = zoomFactor;
          lineSegment = lineSegment();
          arrow = arrow();
          getChildren().add( lineSegment );
          getChildren().add( arrow );
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
        Line line = new Line( centerX, centerY, tx(), ty() );
        line.setStrokeWidth( STROKE_WIDTH );
        line.setStroke( COLOR );
        return line;
      }

      private double radians( double degree ){ return degree * ( Math.PI / 180 ); }

      public void reflect(){
        Matrix matrix = Matrix.createSquareMatrix( 2 );
        matrix.setRowEntries( 0, " 1  0 " );
        matrix.setRowEntries( 1, " 0 -1 " );
        vector.copyEntries( matrix.multiply( vector ) );
        lineSegment.setEndX( tx() );
        lineSegment.setEndY( ty() );
        arrow.getPoints().clear();
        arrow.getPoints().addAll( arrowPoints() );
      }

      private double tx(){ return centerX + ( vector.getEntry( 0, 0 ) * zoomFactor ); }
      private double ty(){ return centerY - ( vector.getEntry( 1, 0 ) * zoomFactor ); }

}
