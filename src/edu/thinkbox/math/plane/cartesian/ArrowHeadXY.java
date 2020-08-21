package edu.thinkbox.math.plane.cartesian;

import javafx.scene.shape.Polygon;
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
        private Polygon triangle;
        private RiseRunXY riseRun;

        public ArrowHeadXY( XYPlane plane, ArrowHeadType type ){
           super( plane );
           this.color = Color.web( "002f55" );
           this.line1 = new Line();
           this.line2 = new Line();
           this.triangle = new Polygon();
           this.type = type;
           this.riseRun = new RiseRunXY( getX(), getY(), plane );
           this.riseRun.setVisible( false );
           createArrowHead();
        }

        public ArrowHeadXY( double x, double y, XYPlane plane, ArrowHeadType type ){
           this( plane, type );
           setPlaneCoordinates( x, y );
        }

        public void hightlight(){}
        public void unhighlight(){}

        public void setPlaneCoordinates( double x, double y ){
            super.setPlaneCoordinates( x, y );
            updateArrowHead();
        }

        private void updateArrowHead(){
            double direction = plane.getDirection( coordinates );

            double tx = plane.toSceneX( getX() );
            double ty = plane.toSceneY( getY() );
            double delta1 = direction - tipAngle;
            double delta2 = direction + tipAngle;
            double x2 = sideLength * Math.cos( delta1 );
            double y2 = sideLength * Math.sin( delta1 );
            double x3 = sideLength * Math.cos( delta2 );
            double y3 = sideLength * Math.sin( delta2 );

            if( type == ArrowHeadType.OPEN_BASE ){
                line1.setStartX( tx );
                line1.setStartY( ty );
                line1.setEndX( tx+x2 );
                line1.setEndY( ty-y2 );

                line2.setStartX( tx );
                line2.setStartY( ty );
                line2.setEndX( tx+x3 );
                line2.setEndY( ty-y3 );
            } else if(  type == ArrowHeadType.TRIANGLE ){
                triangle.getPoints().clear();
                triangle.getPoints().addAll( new Double[] { tx, ty, tx+x2, ty-y2, tx+x3, ty-y3 } );
            }

            riseRun.setPlaneCoordinates( getX(), getY() );
        }

        private void createArrowHead(){
           updateArrowHead();

           if( type == ArrowHeadType.OPEN_BASE ){
              line1.setStrokeWidth( size );
              line1.setStroke( color );
              line2.setStrokeWidth( size );
              line2.setStroke( color );
              getChildren().add( line1 );
              getChildren().add( line2 );
           }else if( type == ArrowHeadType.TRIANGLE ){
              triangle.setStroke( color );
              triangle.setFill( color );
              triangle.setStrokeWidth( size );
              getChildren().add( triangle );
           }

           getChildren().add( riseRun );
        }
}
