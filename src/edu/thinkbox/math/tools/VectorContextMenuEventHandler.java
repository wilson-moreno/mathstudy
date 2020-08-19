package edu.thinkbox.math.tools;

import javafx.event.EventHandler;
import javafx.scene.input.ContextMenuEvent;

public class VectorContextMenuEventHandler implements EventHandler< ContextMenuEvent >{
    private Vector vector;
    private VectorContextMenu contextMenu;

    public VectorContextMenuEventHandler( Vector vector ){
        this.vector = vector;
        this.contextMenu = new VectorContextMenu( vector );
    }

    @Override
    public void handle( ContextMenuEvent e ){
        contextMenu.show( vector, e.getScreenX(), e.getScreenY() );
    }

}
