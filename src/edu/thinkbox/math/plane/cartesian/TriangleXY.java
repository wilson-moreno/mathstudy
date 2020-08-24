package edu.thinkbox.math.plane.cartesian;

import javafx.scene.paint.Color;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.scene.shape.Line;

public class TriangleXY extends XYObject implements CoordinatesListener {
      private PointXY vertex1;
      private PointXY vertex2;
      private PointXY vertex3;
      private AngleXY vertexAngle1;

      public TriangleXY( XYPlane plane ){
          super( plane );
          create();
      }

      public void highlight(){}
      public void unhighlight(){}
      public void setColor( Color color ){}
      public void create(){
          vertex1 = new PointXY( 0.0, 5.0, plane );
          vertex2 = new PointXY( 5.0, 0.0, plane );
          vertex3 = new PointXY( -5.0, 0.0, plane );

          vertexAngle1 = new AngleXY( plane );

          vertexAngle1.setCenter( 0.0, 5.0 );

          vertex1.addEventFilter( MouseEvent.ANY, new MouseOverPointEventHandler( plane ) );
          vertex2.addEventFilter( MouseEvent.ANY, new MouseOverPointEventHandler( plane ) );
          vertex3.addEventFilter( MouseEvent.ANY, new MouseOverPointEventHandler( plane ) );

          vertex1.setOnContextMenuRequested( new PointContextMenuEventHandler( vertex1 ) );
          vertex2.setOnContextMenuRequested( new PointContextMenuEventHandler( vertex2 ) );
          vertex3.setOnContextMenuRequested( new PointContextMenuEventHandler( vertex3 ) );

          vertex1.connect( vertex2 );
          vertex2.connect( vertex3 );
          vertex3.connect( vertex1 );

          vertex2.addCoordinatesListener( vertex1 );
          vertex3.addCoordinatesListener( vertex2 );
          vertex1.addCoordinatesListener( vertex3 );

          vertex1.addCoordinatesListener( this );
          vertex2.addCoordinatesListener( this );
          vertex3.addCoordinatesListener( this );

          getChildren().add( vertex1 );
          getChildren().add( vertex2 );
          getChildren().add( vertex3 );

          getChildren().add( vertexAngle1 );
      }

      public void positionChanged( XYObject source, double x, double y ){
          update( source, x, y );
      }


      public void update( XYObject source, double x, double y ){
         double theta1 = 0.0;
         double theta2 = 0.0;

          vertexAngle1.setCenter( vertex1.getX(), vertex1.getY() );
          theta1 = direction( vertex1, vertex3 );
          theta2 = angle( vertex1, vertex2, vertex3 );
          vertexAngle1.setStartAngle( plane.toDegree( theta1 ) );
          vertexAngle1.setLength( plane.toDegree( theta2 ) );

          System.out.println( plane.toDegree( theta2 ) );
      }


      private double direction( PointXY verte1, PointXY vertex2 ){
         return plane.getDirection(  vertex2.getX() - vertex1.getX(), vertex2.getY() - vertex1.getY() );
      }

      private double slope( PointXY vertex1, PointXY vertex2 ){
         double slope = ( vertex2.getY() - vertex1.getY() ) / ( vertex2.getX() - vertex1.getX() );
         double x = vertex2.getX() - vertex1.getX();
         double y = vertex2.getY() - vertex1.getY();

         if( plane.getQuadrant( x, y ) == 1 ) slope *= +1;
         else if( plane.getQuadrant( x, y ) == 2 ) slope *= +1;
         else if( plane.getQuadrant( x, y ) == 3 ) slope *= +1;
         else if( plane.getQuadrant( x, y ) == 4 ) slope *= +1;

         return slope;
      }

      private double angle( PointXY vertex1, PointXY vertex2, PointXY vertex3 ){
         double m1 = slope( vertex1, vertex2 );
         double m2 = slope( vertex1, vertex3 );

         return Math.atan( Math.abs( ( m1 - m2 ) / ( 1 + m1*m2 ) ) );
      }
}
