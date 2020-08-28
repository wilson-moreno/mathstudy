package edu.thinkbox.math.plane.cartesian;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import java.util.HashMap;
import java.util.Map;

public class PointXY extends XYObject implements CoordinatesListener {
       private Map< PointXY, Line > vertices;
       private Map< Line, Text > weights;
       private Circle point;
       private Group edges;
       private Group edgeWeights;

       public PointXY( XYPlane plane ){
           super( plane );
           setSize( 5.0 );
           super.setColor( BLUE );
           setHighlightSize( 7.0 );
           create();
       }

       public PointXY( double x, double y, XYPlane plane ){
           this( plane );
           setPlaneCoordinates( x, y );
       }

       public void highlight(){
           point.setFill( getHighlightColor() );
           point.setRadius( getHighlightSize() );
       }
       public void unhighlight(){
           point.setFill( getColor() );
           point.setRadius( getSize() );
       }

       @Override
       public void setColor( Color color ){
           super.setColor( color );
           point.setFill( getColor() );
           for( Line edge : vertices.values() )
              edge.setStroke( getColor() );
       }

       private void create(){
           vertices = new HashMap< PointXY, Line >();
           weights = new HashMap< Line, Text >();
           point = new Circle( plane.toSceneX( getX() ),
                               plane.toSceneY( getY() ),
                               getSize(), getColor() );
           edges = new Group();
           edgeWeights = new Group();
           getChildren().add( edges );
           getChildren().add( edgeWeights );
           getChildren().add( point );
       }

       public void connect( PointXY point ){
           Line edge = new Line( plane.toSceneX( getX() ),
                                 plane.toSceneY( getY() ),
                                 plane.toSceneX( point.getX() ),
                                 plane.toSceneY( point.getY() ) );
           double d = plane.computeDistance( this, point );
           double tx = ( getSceneX() + point.getSceneX() ) / 2.0;
           double ty = ( getSceneY() + point.getSceneY() ) / 2.0;
           Text weight = new Text( tx, ty, String.format( "%2.2f", d ) );
           edge.setStrokeWidth( 2.0 );
           edges.getChildren().add( edge );
           edgeWeights.getChildren().add( weight );
           vertices.put( point, edge );
           weights.put( edge, weight );
       }

       public void setPlaneCoordinates( double x, double y ){
           super.setPlaneCoordinates( x, y );
           update();
       }

       public void setEdgeWeightsVisible( boolean visible ){
           edgeWeights.setVisible( visible );
       }

       private void update(){
           point.setCenterX( plane.toSceneX( getX() ) );
           point.setCenterY( plane.toSceneY( getY() ) );

           for( PointXY vertex : vertices.keySet() ){
              Line edge = (Line) vertices.get( vertex );
              edge.setStartX( getSceneX() );
              edge.setStartY( getSceneY() );
              Text weight = (Text) weights.get( edge );
              double d = plane.computeDistance( this, vertex );
              double tx = ( getSceneX() + vertex.getSceneX() ) / 2.0;
              double ty = ( getSceneY() + vertex.getSceneY() ) / 2.0;
              weight.setX( tx );
              weight.setY( ty );
              weight.setText( String.format( "%2.2f", d ) );
           }
       }

       public void positionChanged( XYObject source, double x, double y){
            Line edge = (Line) vertices.get( source );
            edge.setEndX( plane.toSceneX( x ) );
            edge.setEndY( plane.toSceneY( y ) );
            update();
       }
}
