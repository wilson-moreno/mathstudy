package edu.thinkbox.math.plane.cartesian;

import javafx.event.EventHandler;
import javafx.scene.input.ContextMenuEvent;

public class ArrowHeadContextMenuEventHandler implements EventHandler< ContextMenuEvent >{
    private ArrowHeadXY arrowHead;
    private ArrowHeadContextMenu contextMenu;

    public ArrowHeadContextMenuEventHandler( ArrowHeadXY arrowHead ){
        this.arrowHead = arrowHead;
        this.contextMenu = new ArrowHeadContextMenu( arrowHead );
    }

    @Override
    public void handle( ContextMenuEvent e ){
        contextMenu.show( arrowHead, e.getScreenX(), e.getScreenY() );
    }

}
