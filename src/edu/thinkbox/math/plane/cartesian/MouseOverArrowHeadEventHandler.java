package edu.thinkbox.math.plane.cartesian;

import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;

public class MouseOverArrowHeadEventHandler implements EventHandler< MouseEvent >{
    private XYPlane plane;

    public MouseOverArrowHeadEventHandler( XYPlane plane ){
        this.plane = plane;
    }

    @Override
    public void handle( MouseEvent e ){
        ArrowHeadXY arrowHead = ( ArrowHeadXY ) e.getSource();

        if( e.getEventType() == MouseEvent.MOUSE_DRAGGED ){
            arrowHead.highlight();
            arrowHead.setSceneCoordinates( e.getSceneX(), e.getSceneY() );
        } else if( e.getEventType() == MouseEvent.MOUSE_RELEASED ){
            arrowHead.unhighlight();
        } else {
            if( e.getEventType() == MouseEvent.MOUSE_ENTERED ) arrowHead.highlight();
            else if( e.getEventType() == MouseEvent.MOUSE_EXITED ) arrowHead.unhighlight();
        }

    }
}
