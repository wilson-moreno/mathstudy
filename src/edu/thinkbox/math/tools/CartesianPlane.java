package edu.thinkbox.math.tools;

import javafx.scene.Group;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.shape.Polygon;
import javafx.scene.paint.Color;
import edu.thinkbox.math.matrix.Matrix;

public class CartesianPlane extends Group{
      private static final int    PADDING = 10;
      private static final double GRID_WIDTH = 0.3;
      private static final double AXES_WIDTH = 2.0;
      private static final Color  GRID_COLOR = Color.web( "4a688a" );
      private static final Color  AXES_COLOR = Color.web( "002f55" );
      private static final double ARROW_WIDTH = 8;
      private static final double ARROW_SIZE = 150.0 * ( Math.PI / 180.0 ) ;
      private int   width;
      private int   height;
      private int   moduleSize;
      private Group gridLines;
      private Group axes;

      public CartesianPlane( int width, int height, int moduleSize ){
          this.width = width;
          this.height = height;
          this.moduleSize = moduleSize;
          this.gridLines = createGridLines();
          this.axes = createAxes();
          getChildren().add( gridLines );
          getChildren().add( axes );
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

      private int getColumnCount(){ return width / moduleSize; }
      private int getRowCount(){ return height / moduleSize; }


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

      private double direction( Matrix vector ){
          double x = vector.getEntry( 0, 0 );
          double y = vector.getEntry( 1, 0 );

          if( x > 0.0 && y == 0.0 ) return 0.0;
          else if( x == 0.0 && y > 0.0 ) return Math.PI / 2.0;
          else if( x < 0.0 && y == 0.0 ) return Math.PI;
          else if( x == 0.0 && y < 0.0 ) return Math.PI * ( 3.0 / 2.0 );
          else {

              double radians = Math.atan( y / x );

              if( ( x < 0.0 && y > 0.0 ) || ( x < 0.0 && y < 0.0 ) ) radians = Math.PI + radians;

              return radians;
          }

      }


      public Vector addVector( double x, double y ){
          Vector vector = new Vector( x, y, getCenterX(), getCenterY(), getModuleSize() );
          getChildren().add( vector );
          return vector;
      }

      public class Vector extends Group{
            private final Color COLOR = Color.web( "509237" );
            private static final double STROKE_WIDTH = 2.0;
            private final Matrix vector = Matrix.createColumnMatrix( 2 );
            private double centerX;
            private double centerY;
            private double zoomFactor;
            private Line lineSegment;
            private Polygon arrow;


            public Vector( double x, double y, double centerX, double centerY, double zoomFactor ){
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


            public Vector transform( Matrix transformation ){
                vector.copyEntries( transformation.multiply( vector ) );
                lineSegment.setEndX( tx() );
                lineSegment.setEndY( ty() );
                arrow.getPoints().clear();
                arrow.getPoints().addAll( arrowPoints() );
                return this;
            }


            private double tx(){ return centerX + ( vector.getEntry( 0, 0 ) * zoomFactor ); }
            private double ty(){ return centerY - ( vector.getEntry( 1, 0 ) * zoomFactor ); }

      }

}
