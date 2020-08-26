package edu.thinkbox.math.plane.cartesian;

import javafx.event.EventHandler;
import javafx.scene.input.ContextMenuEvent;

public class TriangleContextMenuEventHandler implements EventHandler< ContextMenuEvent >{
    private TriangleXY triangle;
    private TriangleContextMenu contextMenu;

    public TriangleContextMenuEventHandler( TriangleXY triangle ){
        this.triangle = triangle;
        this.contextMenu = new TriangleContextMenu( triangle );
    }

    @Override
    public void handle( ContextMenuEvent e ){
        contextMenu.show( triangle, e.getScreenX(), e.getScreenY() );
    }

}
