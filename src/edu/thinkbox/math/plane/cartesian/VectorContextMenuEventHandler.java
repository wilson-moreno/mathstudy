package edu.thinkbox.math.plane.cartesian;

import javafx.event.EventHandler;
import javafx.scene.input.ContextMenuEvent;

public class VectorContextMenuEventHandler implements EventHandler< ContextMenuEvent >{
    private VectorXY vector;
    private VectorContextMenu contextMenu;

    public VectorContextMenuEventHandler( VectorXY vector ){
        this.vector = vector;
        this.contextMenu = new VectorContextMenu( vector );
    }

    @Override
    public void handle( ContextMenuEvent e ){
        contextMenu.show( vector, e.getScreenX(), e.getScreenY() );
    }

}
