package edu.thinkbox.math.plane.cartesian;

import javafx.event.EventHandler;
import javafx.scene.input.ContextMenuEvent;

public class PointContextMenuEventHandler implements EventHandler< ContextMenuEvent >{
    private PointXY point;
    private PointContextMenu contextMenu;

    public PointContextMenuEventHandler( PointXY point ){
        this.point = point;
        this.contextMenu = new PointContextMenu( point );
    }

    @Override
    public void handle( ContextMenuEvent e ){
        contextMenu.show( point, e.getScreenX(), e.getScreenY() );
    }

}
