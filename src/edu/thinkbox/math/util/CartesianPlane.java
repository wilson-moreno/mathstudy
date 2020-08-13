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
      private double CenterX = WIDTH / 2;
      private double CenterY = HEIGHT / 2;
      private double zoomFactor = 20.0;

      public static void main( String[] args ){
          launch( args );
      }

      @Override
      public void start( Stage primaryStage ){
        primaryStage.setTitle( "Cartesian Plane" );
        Group root = new Group();
        root.getChildren().add( getCartesianPlane() );
        root.getChildren().add( getVectors() );
        //root.getChildren().add( getPoints() );
        primaryStage.setScene( new Scene( root, WIDTH, HEIGHT ) );
        primaryStage.show();
      }

      private Group getVectors(){
        Group vectors = new Group();

        vectors.getChildren().add( createVectorLine(  5, 5 ) );
        vectors.getChildren().add( createVectorLine( -2, 7 ) );
        vectors.getChildren().add( createVectorLine( -8, -3 ) );
        vectors.getChildren().add( createVectorLine( 5, -6 ) );

        return vectors;
      }

      private Group createVectorLine( double x, double y ){
        Group vectorLine = new Group();

        Matrix vector = Matrix.createColumnMatrix( 2 );
        vector.setEntry( 0, 0, x );
        vector.setEntry( 1, 0, y );
        vectorLine.getChildren().add( plotVector( vector ) );
        vectorLine.getChildren().add( triangle( vector, Color.web( "509237" ) ) );

        return vectorLine;
      }

      private Group getPoints(){
        Group points = new Group();

        Matrix point = Matrix.createColumnMatrix( 2 );
        point.setEntry( 0, 0, 0.0 );
        point.setEntry( 1, 0, 5.0 );
        points.getChildren().add( plotPoint( point ) );

        return points;
      }

      private Group getCartesianPlane(){
        Group cartesianPlane = new Group();
        Text origin = new Text( CenterX + 5 , CenterY + 15 , "O" );
        origin.setFont( new Font( 12 ) );

        for( int i = 0; i <= ( HEIGHT / zoomFactor ); i++ ){
            Line line = new Line( 0, i * zoomFactor, WIDTH, i * zoomFactor );
            line.setStrokeWidth( 0.3 );
            line.setStroke( Color.web( "4a688a" ) );
            cartesianPlane.getChildren().add( line );
        }

        for( int j = 0; j <= ( WIDTH / zoomFactor ); j++ ){
            Line line = new Line( j * zoomFactor, 0, j * zoomFactor, HEIGHT );
            line.setStrokeWidth( 0.3 );
            line.setStroke( Color.web( "4a688a" ) );
            cartesianPlane.getChildren().add( line );
        }

        Line centerVerticalLine = new Line( CenterX, 5, CenterX, HEIGHT - 5 );
        Line centerHorizontalLine = new Line( 5, CenterY, WIDTH - 5, CenterY );

        centerVerticalLine.setStrokeWidth( 2.0 );
        centerVerticalLine.setStroke( Color.web( "002f55" ) );
        centerHorizontalLine.setStrokeWidth( 2.0 );
        centerHorizontalLine.setStroke( Color.web( "002f55" ) );

        cartesianPlane.getChildren().add( centerVerticalLine );
        cartesianPlane.getChildren().add( centerHorizontalLine );

        cartesianPlane.getChildren().add( origin );

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

        cartesianPlane.getChildren().add( openTriangle( arrow1, Color.web( "002f55" ) ) );
        cartesianPlane.getChildren().add( openTriangle( arrow2, Color.web( "002f55" ) ) );
        cartesianPlane.getChildren().add( openTriangle( arrow3, Color.web( "002f55" ) ) );
        cartesianPlane.getChildren().add( openTriangle( arrow4, Color.web( "002f55" ) ) );

        return cartesianPlane;
      }

      private Line plotVector( Matrix vector ){
        Line line = new Line( CenterX, CenterY,
                                CenterX + ( vector.getEntry( 0, 0 ) * zoomFactor ),
                                CenterY - ( vector.getEntry( 1, 0 ) * zoomFactor ) );


        System.out.println( direction( vector ));
        line.setStrokeWidth( 2 );
        line.setStroke( Color.web( "509237" ) );
        return line;
      }

      private Circle plotPoint( Matrix vector ){
        double radius = 4.0;

        Circle circle = new Circle( CenterX + ( vector.getEntry( 0, 0 ) * zoomFactor ),
                                    CenterY - ( vector.getEntry( 1, 0 ) * zoomFactor ),
                                    radius, Color.web( "002f55" ) );

        return circle;
      }

      private double slope( Matrix vector ){
          return  vector.getEntry( 0, 0 ) / vector.getEntry( 1, 0 );
      }

      private Group openTriangle( Matrix vector, Color color ){
          Group triangle = new Group();
          double direction = direction( vector );
          double size = 5.0;
          double theta = 140;

          double x = vector.getEntry( 0, 0 );
          double y = vector.getEntry( 1, 0 );
          double tx = CenterX + ( x * zoomFactor );
          double ty = CenterY - ( y * zoomFactor );
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

      private Polygon triangle( Matrix vector, Color color ){
          Polygon triangle = new Polygon();
          double direction = direction( vector );
          double size = 10.0;
          double theta = 160;

          double x = vector.getEntry( 0, 0 );
          double y = vector.getEntry( 1, 0 );
          double tx = CenterX + ( x * zoomFactor );
          double ty = CenterY - ( y * zoomFactor );
          double delta1 = direction - theta;
          double delta2 = direction + theta;
          double x2 = size * Math.cos( radians( delta1 ) );
          double y2 = size * Math.sin( radians( delta1 ) );
          double x3 = size * Math.cos( radians( delta2 ) );
          double y3 = size * Math.sin( radians( delta2 ) );

          //System.out.printf( "%4.2f %4.2f\n", delta1, delta2 );
          //System.out.printf( "tx=%4.2f\nty=%4.2f\nx2=%4.2f\ny2=%4.2f\nx3=%4.2f\ny3=%4.2f\n", tx, ty, tx+x2, ty-y2, tx+x3, ty-y3 );


          triangle.getPoints().addAll( new Double[] { tx, ty, tx+x2, ty-y2, tx+x3, ty-y3 } );
          triangle.setStroke( color );
          triangle.setFill( color );
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
