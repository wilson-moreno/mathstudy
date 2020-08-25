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
      private AngleXY vertexAngle2;
      private AngleXY vertexAngle3;


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
          vertexAngle2 = new AngleXY( plane );
          vertexAngle3 = new AngleXY( plane );

          vertexAngle1.setPlaneCoordinates(  0.0, 5.0 );
          vertexAngle2.setPlaneCoordinates(  5.0, 0.0 );
          vertexAngle3.setPlaneCoordinates( -5.0, 0.0 );

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
          getChildren().add( vertexAngle2 );
          getChildren().add( vertexAngle3 );

          update();
      }

      public void positionChanged( XYObject source, double x, double y ){
          update();
      }


      public void update(){
          double theta1 = 0.0;
          double theta2 = 0.0;
          double theta3 = 0.0;
          double degree1 = 0.0;
          double degree2 = 0.0;
          double degree3 = 0.0;


          vertexAngle1.setPlaneCoordinates( vertex1.getX(), vertex1.getY() );
          theta1 = direction( vertex1, vertex2 );
          theta2 = direction( vertex1, vertex3 );
          degree1 = plane.toDegree( theta2 - theta1 );
          vertexAngle1.setStartAngle( plane.toDegree( theta1 ) );
          setLength( vertexAngle1, theta1, theta2 );

          vertexAngle2.setPlaneCoordinates( vertex2.getX(), vertex2.getY() );
          theta1 = direction( vertex2, vertex3 );
          theta2 = direction( vertex2, vertex1 );
          degree2 = plane.toDegree( theta2 - theta1 );
          vertexAngle2.setStartAngle( plane.toDegree( theta1 ) );
          setLength( vertexAngle2, theta1, theta2 );

          vertexAngle3.setPlaneCoordinates( vertex3.getX(), vertex3.getY() );
          theta1 = direction( vertex3, vertex2 );
          theta2 = direction( vertex3, vertex1 );
          degree3 = plane.toDegree( theta2 - theta1 );
          vertexAngle3.setStartAngle( plane.toDegree( theta1 ) );
          setLength( vertexAngle3, theta1, theta2 );
      }

      private void setLength( AngleXY vertexAngle, double theta1, double theta2 ){
          double angle = plane.toDegree( theta2 - theta1 );
          double sign = Math.signum( angle );

          if( Math.abs( angle ) > 180.0 ){
              angle = 360 - Math.abs( angle );
              if( theta2 > theta1 ) angle *= -1;
          }

          vertexAngle.setLength( angle );
      }



      private double acuteAngle( double degree ){
          double sign = Math.signum( degree );

          if( isObtuse( Math.abs( degree ) ) ){
             degree = 360.0 - Math.abs( degree );
             degree *= sign;
          }

          return degree;
      }

      private boolean isAcute( double t ){
          return ( t > 0.0 && t < ( 90.0 ) );
      }

      private boolean isObtuse( double t ){
          return ( t > ( 90.0 ) && t < 180.0 );
      }

      private double direction( PointXY p1, PointXY p2 ){
         return plane.getDirection(  p2.getX() - p1.getX(), p2.getY() - p1.getY() );
      }

      private double slope( PointXY p1, PointXY p2 ){
         double slope = ( p2.getY() - p1.getY() ) / ( p2.getX() - p1.getX() );
         double x = p2.getX() - p1.getX();
         double y = p2.getY() - p1.getY();
         return slope;
      }

      private double angle( PointXY p1, PointXY p2, PointXY p3 ){
         double m1 = slope( p1, p2 );
         double m2 = slope( p1, p3 );
         double m3 = ( m1 - m2 ) / ( 1 + m1*m2 );
         double radians = Math.atan( Math.abs( m3 ) );

         if( m3 < 0.0 ) radians = Math.PI - radians;

         return radians;
      }
}
