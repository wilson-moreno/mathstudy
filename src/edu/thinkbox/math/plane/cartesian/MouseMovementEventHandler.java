package edu.thinkbox.math.plane.cartesian;

import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class MouseMovementEventHandler implements EventHandler< MouseEvent >{
    private XYPlane plane;
    private Text coordinates = new Text( 0.0, 0.0, String.format("[%2.2f, %2.2f]", 0.0, 0.0) );

    public MouseMovementEventHandler( XYPlane plane ){
        this.plane = plane;
        this.plane.getChildren().add( coordinates );
    }

    @Override
    public void handle( MouseEvent e ){
        if( e.getEventType() == MouseEvent.MOUSE_MOVED && plane.isMouseCoordinateVisible() ){
          coordinates.setX( e.getSceneX() );
          coordinates.setY( e.getSceneY() - 10 );
          coordinates.setText( String.format("( %4.2f, %4.2f )",
                               plane.toCoordinateX( e.getSceneX() ),
                               plane.toCoordinateY( e.getSceneY() ) ) );
          coordinates.setVisible( true );
        } else {
          coordinates.setVisible( false );
        }
    }
}
