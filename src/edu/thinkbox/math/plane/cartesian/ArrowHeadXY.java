package edu.thinkbox.math.plane.cartesian;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.text.Font;


public class ArrowHeadXY extends XYObject{
        private ArrowHeadType type;
        private double sideLength = 10.0;
        private double tipAngle = 150.0 * ( Math.PI / 180.0 ) ;
        private Line line1;
        private Line line2;

        public ArrowHeadXY( XYPlane plane, ArrowHeadType type ){
           super( plane );
           this.color = Color.web( "002f55" );
           this.line1 = new Line();
           this.line2 = new Line();
           this.type = type;

           if( type == ArrowHeadType.OPEN_BASE ) createOpenBaseArrowHead();
        }

        public void hightlight(){}
        public void unhighlight(){}

        public void setPlaneCoordinates( double x, double y ){
            super.setPlaneCoordinates( x, y );

            if( type == ArrowHeadType.OPEN_BASE ){
               updateOpenBaseArrowHead();
            }
        }

        private void updateOpenBaseArrowHead(){
            double direction = plane.getDirection( coordinates );

            double x = coordinates.getEntry( 0, 0 );
            double y = coordinates.getEntry( 1, 0 );
            double tx = plane.toScreenX( x );
            double ty = plane.toScreenY( y );
            double delta1 = direction - tipAngle;
            double delta2 = direction + tipAngle;
            double x2 = sideLength * Math.cos( delta1 );
            double y2 = sideLength * Math.sin( delta1 );
            double x3 = sideLength * Math.cos( delta2 );
            double y3 = sideLength * Math.sin( delta2 );

            line1.setStartX( tx );
            line1.setStartY( ty );
            line1.setEndX( tx+x2 );
            line1.setEndY( ty-y2 );

            line2.setStartX( tx );
            line2.setStartY( ty );
            line2.setEndX( tx+x3 );
            line2.setEndY( ty-y3 );
        }

        private void createOpenBaseArrowHead(){
           updateOpenBaseArrowHead();
           line1.setStrokeWidth( size );
           line1.setStroke( color );
           line2.setStrokeWidth( size );
           line2.setStroke( color );
           getChildren().add( line1 );
           getChildren().add( line2 );
        }
}
