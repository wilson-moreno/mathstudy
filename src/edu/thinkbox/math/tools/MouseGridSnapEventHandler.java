package edu.thinkbox.math.tools;

import javafx.scene.Group;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import edu.thinkbox.math.matrix.Matrix;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.Menu;
import javafx.stage.Window;
import javafx.stage.PopupWindow.AnchorLocation;
import javafx.scene.Node;
import edu.thinkbox.math.matrix.Transformation;

public class MouseGridSnapEventHandler implements EventHandler< MouseEvent >{
    private CartesianPlane cartesianPlane;
    private Text coordinates = new Text( 0.0, 0.0, String.format("[%2.2f, %2.2f]", 0.0, 0.0) );

    public MouseGridSnapEventHandler( CartesianPlane cartesianPlane ){
        this.cartesianPlane = cartesianPlane;
        this.cartesianPlane.getChildren().add( coordinates );
    }

    @Override
    public void handle( MouseEvent e ){
        if( e.getEventType() == MouseEvent.MOUSE_MOVED && cartesianPlane.isMouseCoordinateVisible() ){
          coordinates.setX( e.getSceneX() );
          coordinates.setY( e.getSceneY() - 10 );
          coordinates.setText( String.format("( %4.2f, %4.2f )",
                               cartesianPlane.toCoordinateX( e.getSceneX() ),
                               cartesianPlane.toCoordinateY( e.getSceneY() ) ) );
          coordinates.setVisible( true );
        } else {
          coordinates.setVisible( false );
        }
    }
}
