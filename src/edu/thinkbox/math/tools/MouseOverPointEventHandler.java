package edu.thinkbox.math.tools;

import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;

public class MouseOverPointEventHandler implements EventHandler< MouseEvent >{
    private CartesianPlane cartesianPlane;

    public MouseOverPointEventHandler( CartesianPlane cartesianPlane ){
        this.cartesianPlane = cartesianPlane;
    }

    @Override
    public void handle( MouseEvent e ){
        Point point = ( Point ) e.getSource();

        if( e.getEventType() == MouseEvent.MOUSE_DRAGGED ){
          point.bigPoint();
          point.setCoordinates( cartesianPlane.toCoordinateX( e.getSceneX() ),
                                cartesianPlane.toCoordinateY( e.getSceneY() ) );
        } else if( e.getEventType() == MouseEvent.MOUSE_RELEASED ){
          point.regularPoint();
        } else {
          if( e.getEventType() == MouseEvent.MOUSE_ENTERED ) point.bigPoint();
          else if( e.getEventType() == MouseEvent.MOUSE_EXITED ) point.regularPoint();
        }

    }
}
