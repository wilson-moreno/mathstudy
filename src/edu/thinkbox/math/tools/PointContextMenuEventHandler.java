package edu.thinkbox.math.tools;

import javafx.event.EventHandler;
import javafx.scene.input.ContextMenuEvent;

public class PointContextMenuEventHandler implements EventHandler< ContextMenuEvent >{
    private Point point;
    private PointContextMenu contextMenu;

    public PointContextMenuEventHandler( Point point ){
        this.point = point;
        this.contextMenu = new PointContextMenu( point );
    }

    @Override
    public void handle( ContextMenuEvent e ){
        contextMenu.show( point, e.getScreenX(), e.getScreenY() );
    }

}
