package edu.thinkbox.math.plane.cartesian;

import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;

public class MouseOverVectorEventHandler implements EventHandler< MouseEvent >{
    private XYPlane plane;

    public MouseOverVectorEventHandler( XYPlane plane ){
        this.plane = plane;
    }

    @Override
    public void handle( MouseEvent e ){
        VectorXY vector = ( VectorXY ) e.getSource();

        if( e.getEventType() == MouseEvent.MOUSE_DRAGGED ){
            vector.highlight();
            vector.setSceneCoordinates( e.getX(), e.getY() );
        } else if( e.getEventType() == MouseEvent.MOUSE_RELEASED ){
            vector.unhighlight();
        } else if( e.getEventType() == MouseEvent.MOUSE_CLICKED ) {

        } else {
            if( e.getEventType() == MouseEvent.MOUSE_ENTERED ) vector.highlight();
            else if( e.getEventType() == MouseEvent.MOUSE_EXITED ) vector.unhighlight();
        }

    }
}
