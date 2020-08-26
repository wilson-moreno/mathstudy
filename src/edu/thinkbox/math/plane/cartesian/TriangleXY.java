package edu.thinkbox.math.plane.cartesian;

import javafx.scene.paint.Color;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.scene.shape.Line;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;

public class TriangleXY extends XYObject implements CoordinatesListener, EventHandler< MouseEvent > {
      private PointXY vertex1;
      private PointXY vertex2;
      private PointXY vertex3;
      private PointXY centroid;
      private PointXY incenter;
      private PointXY circumcenter;
      private AngleXY vertexAngle1;
      private AngleXY vertexAngle2;
      private AngleXY vertexAngle3;
      private Circle  inscribedCircle;
      private Circle  circumscribedCircle;


      public TriangleXY( XYPlane plane ){
          super( plane );
          create();
      }

      public void highlight(){}
      public void unhighlight(){}
      public void setColor( Color color ){}
      public void create(){
          vertex1 = createVertex( 0.0, 7.0 );
          vertex2 = createVertex( 8.0, -4.0 );
          vertex3 = createVertex( -8.0, -4.0 );

          vertexAngle1 = createVertexAngle(  0.0, 5.0 );
          vertexAngle2 = createVertexAngle(  5.0, 0.0 );
          vertexAngle3 = createVertexAngle( -5.0, 0.0 );

          vertex1.connect( vertex2 );
          vertex2.connect( vertex3 );
          vertex3.connect( vertex1 );

          vertex2.addCoordinatesListener( vertex1 );
          vertex3.addCoordinatesListener( vertex2 );
          vertex1.addCoordinatesListener( vertex3 );

          centroid = createCentroid( vertex1, vertex2, vertex3 );
          incenter = createIncenter( vertex1, vertex2, vertex3 );
          circumcenter = createCircumcenter( vertex1, vertex2, vertex3 );

          inscribedCircle = createInscribedCircle( incenter );
          circumscribedCircle = createCircumscribedCircle( circumcenter );

          update();
      }


      public void handle( MouseEvent e ){
          if( e.getEventType() == MouseEvent.MOUSE_RELEASED ){
            printObjectInformation();
          }
      }

      private void printObjectInformation(){
          System.out.println( String.format( "Triangle: {" +
                                             "\n\t Vertex 1 : { x = %2.2f, y = %2.2f }" +
                                             "\n\t Vertex 2 : { x = %2.2f, y = %2.2f }" +
                                             "\n\t Vertex 3 : { x = %2.2f, y = %2.2f }" +
                                             "\n\t Angle 1 : { theta = %2.2f }" +
                                             "\n\t Angle 2 : { theta = %2.2f }" +
                                             "\n\t Angle 3 : { theta = %2.2f }" +
                                             "\n\t Area : { %2.2f }" +
                                             "\n\t Centroid : { x = %2.2f, y = %2.2f }" +
                                             "\n\t Incenter : { x = %2.2f, y = %2.2f }" +
                                             "\n\t Circumcenter : { x = %2.2f, y = %2.2f }" +
                                             "\n}\n",
                                             vertex1.getX(), vertex1.getY(),
                                             vertex2.getX(), vertex2.getY(),
                                             vertex3.getX(), vertex3.getY(),
                                             Math.abs( vertexAngle1.getLength() ),
                                             Math.abs( vertexAngle2.getLength() ),
                                             Math.abs( vertexAngle3.getLength() ),
                                             getArea(), centroid.getX(), centroid.getY(),
                                             incenter.getX(), incenter.getY(),
                                             circumcenter.getX(), circumcenter.getY() ) );
      }


      private Circle createInscribedCircle( PointXY incenter ){
          Circle inscribedCircle = new Circle();
          inscribedCircle.setCenterX( incenter.getSceneX() );
          inscribedCircle.setCenterY( incenter.getSceneY() );
          inscribedCircle.setFill( null );
          inscribedCircle.setStroke( GREEN );
          inscribedCircle.setStrokeWidth( getSize() );
          getChildren().add( inscribedCircle );
          return inscribedCircle;
      }

      private Circle createCircumscribedCircle( PointXY circumcenter ){
          Circle circumscribedCircle = new Circle();
          circumscribedCircle.setCenterX( circumcenter.getSceneX() );
          circumscribedCircle.setCenterY( circumcenter.getSceneY() );
          circumscribedCircle.setFill( null );
          circumscribedCircle.setStroke( GREEN );
          circumscribedCircle.setStrokeWidth( getSize() );
          getChildren().add( circumscribedCircle );
          return circumscribedCircle;
      }


      private void updateInscribedCircle( PointXY incenter, PointXY axy , PointXY bxy, PointXY cxy ){
          double radius = inscribedCircleRadius( axy , bxy, cxy );
          inscribedCircle.setCenterX( incenter.getSceneX() );
          inscribedCircle.setCenterY( incenter.getSceneY() );
          inscribedCircle.setRadius( radius * plane.getModuleSize() );
      }

      private void updateCircumscribedCircle( PointXY circumcenter, PointXY axy , PointXY bxy, PointXY cxy ){
          double radius = circumscribedCircleRadius( axy , bxy, cxy );
          circumscribedCircle.setCenterX( circumcenter.getSceneX() );
          circumscribedCircle.setCenterY( circumcenter.getSceneY() );
          circumscribedCircle.setRadius( radius * plane.getModuleSize() );
      }


      private PointXY createCircumcenter( PointXY axy , PointXY bxy, PointXY cxy ){
          double A = plane.toRadians( vertexAngle1.getLength() );
          double B = plane.toRadians( vertexAngle2.getLength() );
          double C = plane.toRadians( vertexAngle3.getLength() );
          double denominator = ( Math.sin( 2 * A ) + Math.sin( 2 * B ) + Math.sin( 2 * C ) );
          double cx = ( axy.getX() * Math.sin( 2 * A ) + bxy.getX() * Math.sin( 2 * B ) + cxy.getX() * Math.sin( 2 * C ) ) / denominator;
          double cy = ( axy.getY() * Math.sin( 2 * A ) + bxy.getY() * Math.sin( 2 * B ) + cxy.getY() * Math.sin( 2 * C ) ) / denominator;
          PointXY circumcenter = new PointXY( cx, cy, plane );
          circumcenter.setVisible( false );
          getChildren().add( circumcenter );
          return circumcenter;
      }

      private void updateCircumcenter( PointXY axy , PointXY bxy, PointXY cxy ){
          double A = plane.toRadians( vertexAngle1.getLength() );
          double B = plane.toRadians( vertexAngle2.getLength() );
          double C = plane.toRadians( vertexAngle3.getLength() );
          double denominator = ( Math.sin( 2 * A ) + Math.sin( 2 * B ) + Math.sin( 2 * C ) );
          double cx = ( axy.getX() * Math.sin( 2 * A ) + bxy.getX() * Math.sin( 2 * B ) + cxy.getX() * Math.sin( 2 * C ) ) / denominator;
          double cy = ( axy.getY() * Math.sin( 2 * A ) + bxy.getY() * Math.sin( 2 * B ) + cxy.getY() * Math.sin( 2 * C ) ) / denominator;
          circumcenter.setPlaneCoordinates( cx, cy );
      }


      private PointXY createIncenter( PointXY axy , PointXY bxy, PointXY cxy ){
          double a = plane.computeDistance( bxy, cxy );
          double b = plane.computeDistance( axy, cxy );
          double c = plane.computeDistance( axy, bxy );
          double ix = ( ( axy.getX() * a ) + ( bxy.getX() * b ) + ( cxy.getX() * c ) ) / ( a + b + c);
          double iy = ( ( axy.getY() * a ) + ( bxy.getY() * b ) + ( cxy.getY() * c ) ) / ( a + b + c);
          PointXY incenter = new PointXY( ix, iy, plane );
          incenter.setVisible( false );
          getChildren().add( incenter );
          return incenter;
      }

      private void updateIncenter( PointXY axy , PointXY bxy, PointXY cxy ){
          double a = plane.computeDistance( bxy, cxy );
          double b = plane.computeDistance( axy, cxy );
          double c = plane.computeDistance( axy, bxy );
          double ix = ( ( axy.getX() * a ) + ( bxy.getX() * b ) + ( cxy.getX() * c ) ) / ( a + b + c);
          double iy = ( ( axy.getY() * a ) + ( bxy.getY() * b ) + ( cxy.getY() * c ) ) / ( a + b + c);
          incenter.setPlaneCoordinates( ix, iy );
      }

      private double circumscribedCircleRadius( PointXY axy , PointXY bxy, PointXY cxy ){
          double a = plane.computeDistance( bxy, cxy );
          double b = plane.computeDistance( axy, cxy );
          double c = plane.computeDistance( axy, bxy );
          double s = ( a + b + c) / 2.0;
          double numerator = a * b * c;
          double denominator = 4.0 * Math.sqrt( s * ( s - a ) * ( s - b ) * ( s - c ) );
          return numerator / denominator;
      }

      private double inscribedCircleRadius( PointXY axy , PointXY bxy, PointXY cxy ){
          double a = plane.computeDistance( bxy, cxy );
          double b = plane.computeDistance( axy, cxy );
          double c = plane.computeDistance( axy, bxy );
          double s = ( a + b + c) / 2.0;
          double theta = plane.toRadians( Math.abs( vertexAngle1.getLength() ) / 2.0 );
          double radius = ( s - a ) * Math.tan( theta );
          return radius;
      }


      private double computeArea( PointXY a, PointXY b, PointXY c ){
          double A = a.getX() * ( b.getY() - c.getY() );
          double B = b.getX() * ( c.getY() - a.getY() );
          double C = c.getX() * ( a.getY() - b.getY() );
          return Math.abs( ( A + B + C ) / 2.0 );
      }

      private double getArea(){
          return computeArea( vertex1, vertex2, vertex3 );
      }

      private PointXY createCentroid( PointXY a, PointXY b, PointXY c ){
          double OX = ( a.getX() + b.getX()  + c.getX() ) / 3.0;
          double OY = ( a.getY() + b.getY()  + c.getY() ) / 3.0;
          PointXY centroid = new PointXY( OX, OY, plane );
          centroid.setVisible( false );
          getChildren().add( centroid );
          return centroid;
      }

      public AngleXY createVertexAngle( double x, double y ){
         AngleXY vertexAngle = new AngleXY( plane );
         vertexAngle.setPlaneCoordinates(  0.0, 5.0 );
         getChildren().add( vertexAngle );
         return vertexAngle;
      }

      public PointXY createVertex( double x, double y ){
          PointXY vertex = new PointXY( x, y, plane );
          vertex.addEventFilter( MouseEvent.ANY, new MouseOverPointEventHandler( plane ) );
          vertex.addEventFilter( MouseEvent.MOUSE_RELEASED, this );
          vertex.setOnContextMenuRequested( new PointContextMenuEventHandler( vertex ) );
          vertex.addCoordinatesListener( this );
          getChildren().add( vertex );
          return vertex;
      }

      public void positionChanged( XYObject source, double x, double y ){
          update();
      }


      public void update(){
          updateAngle( vertexAngle1, vertex1, vertex2, vertex3 );
          updateAngle( vertexAngle2, vertex2, vertex3, vertex1 );
          updateAngle( vertexAngle3, vertex3, vertex1, vertex2 );
          updateCentroid( vertex1, vertex2, vertex3 );
          updateIncenter( vertex1, vertex2, vertex3 );
          updateCircumcenter( vertex1, vertex2, vertex3 );
          updateInscribedCircle( incenter, vertex1, vertex2, vertex3 );
          updateCircumscribedCircle( circumcenter, vertex1, vertex2, vertex3 );
          updateArea();
      }

      private void updateArea(){}

      private void updateCentroid( PointXY a, PointXY b, PointXY c ){
          double OX = ( a.getX() + b.getX()  + c.getX() ) / 3.0;
          double OY = ( a.getY() + b.getY()  + c.getY() ) / 3.0;
          centroid.setPlaneCoordinates( OX, OY );
      }


      private void updateAngle( AngleXY a, PointXY v1, PointXY v2, PointXY v3 ){
          double degree = 0.0;
          double theta1 = 0.0;
          double theta2 = 0.0;
          a.setPlaneCoordinates( v1.getX(), v1.getY() );
          theta1 = direction( v1, v2 );
          theta2 = direction( v1, v3 );
          degree = plane.toDegree( theta2 - theta1 );
          a.setStartAngle( plane.toDegree( theta1 ) );
          setLength( a, theta1, theta2 );
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
