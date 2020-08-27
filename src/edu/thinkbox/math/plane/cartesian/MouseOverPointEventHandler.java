package edu.thinkbox.math.plane.cartesian;

import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;

public class MouseOverPointEventHandler implements EventHandler< MouseEvent >{
    private XYPlane plane;

    public MouseOverPointEventHandler( XYPlane plane ){
        this.plane = plane;
    }

    @Override
    public void handle( MouseEvent e ){
        PointXY point = ( PointXY ) e.getSource();

        if( e.getEventType() == MouseEvent.MOUSE_DRAGGED ){
            point.highlight();
            point.setSceneCoordinates( e.getX(), e.getY() );
        } else if( e.getEventType() == MouseEvent.MOUSE_RELEASED ){
            point.unhighlight();
        } else {
            if( e.getEventType() == MouseEvent.MOUSE_ENTERED ) point.highlight();
            else if( e.getEventType() == MouseEvent.MOUSE_EXITED ) point.unhighlight();
        }

    }
}
