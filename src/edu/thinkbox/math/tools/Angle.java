package edu.thinkbox.math.tools;

import javafx.scene.Group;
import javafx.scene.text.Text;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import edu.thinkbox.math.matrix.Matrix;
import javafx.scene.paint.Color;

public class Angle extends Group{
       private final Color COLOR = Color.web( "509237" );
       private final Color WIDE_COLOR = Color.web( "ff1a00" );
       private static final double STROKE_WIDTH = 3.0;
       private static final double WIDE_STROKE = 5.0;
       private CartesianPlane plane;
       private Matrix vector = Matrix.createColumnMatrix( 2 );
       private Arc angle;
       private Text angleLabel;

       public Angle( CartesianPlane plane ){
            this.plane = plane;
            this.angleLabel = new Text();
            setAngleLabel( angleLabel );
            getChildren().add( angle = angle() );
            getChildren().add( angleLabel );
       }

       private void setAngleLabel( Text label ){
            Double x = 40 * Math.cos( plane.direction( vector ) / 2.0 );
            Double y = 40 * Math.sin( plane.direction( vector ) / 2.0 );

            if( plane.getQuadrant( vector ) == 1 ){
                label.setX( plane.getCenterX() + ( x - 10 ) );
                label.setY( plane.getCenterY() - y );
            } else if( plane.getQuadrant( vector ) == 2 ){
                label.setX( plane.getCenterX() + ( x - 20 ) );
                label.setY( plane.getCenterY() - y );
            } else if( plane.getQuadrant( vector ) == 3 ){
                  label.setX( plane.getCenterX() + ( x - 25 ) );
                  label.setY( plane.getCenterY() - y );
            } else if( plane.getQuadrant( vector ) == 4 ){
                  label.setX( plane.getCenterX() + ( x - 30 ) );
                  label.setY( plane.getCenterY() - y );
            }

            label.setText( String.format( "%2.2f", plane.toDegree( plane.direction( vector ) ) ) );
       }

       private Arc angle(){
            Arc angle = new Arc();

            angle.setCenterX( plane.getCenterX() );
            angle.setCenterY( plane.getCenterY() );
            angle.setRadiusX( 25.0f );
            angle.setRadiusY( 25.0f );
            angle.setStartAngle( 0.0f);
            angle.setLength( plane.toDegree( plane.direction( vector ) ) );
            angle.setType( ArcType.ROUND );
            angle.setFill( null );
            angle.setStroke( COLOR );
            angle.setStrokeWidth( STROKE_WIDTH );

            return angle;
       }

       public void setVector( Matrix vector ){
            this.vector.copyEntries( vector );
            angle.setLength( plane.toDegree( plane.direction( vector ) ) );
            setAngleLabel( angleLabel );
       }

       public void wideStroke(){
            angle.setStroke( WIDE_COLOR );
            angle.setStrokeWidth( WIDE_STROKE );
       }

       public void regularStroke(){
            angle.setStroke( COLOR );
            angle.setStrokeWidth( STROKE_WIDTH );
       }

}
