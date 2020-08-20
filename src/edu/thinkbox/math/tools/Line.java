package edu.thinkbox.math.tools;

import javafx.scene.Group;

public class Line extends Group{
       private double m;
       private double x;
       private double y;
       private double b;
       private CartesianPlane plane;
       private javafx.scene.shape.Line line;

       public Line( double x, double y, double b, CartesianPlane plane ){
              this.x = x;
              this.y = y;
              this.m = m;
              this.b = y - ( m * x );
              this.plane = plane;
              getChildren().add( line = createLine() );
       }

       public Line( double x1, double y1, double x2, double y2, CartesianPlane plane ){
              this.x = x1;
              this.y = y1;
              this.m = ( y2 - y1 ) / ( x2 - x1 );
              this.b = this.y - ( this.m * this.x );
              this.plane = plane;
              getChildren().add( line = createLine() );
       }

       private javafx.scene.shape.Line createLine(){
              javafx.scene.shape.Line line = new javafx.scene.shape.Line();

              line.setStartX( plane.toSceneX( plane.getXBound() * -1 ) );
              line.setStartY( plane.toSceneY( y( plane.getXBound() * -1 ) ) );
              line.setEndX( plane.toSceneX( plane.getXBound() ) );
              line.setEndY( plane.toSceneY( y( plane.getXBound() ) ) );

              return line;
       }

       private double y( double x ){
              return ( m * x )  + b;
       }

}
