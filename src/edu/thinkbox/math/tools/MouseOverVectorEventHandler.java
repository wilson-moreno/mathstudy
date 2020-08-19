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

public class MouseOverVectorEventHandler implements EventHandler< MouseEvent >{
    private CartesianPlane cartesianPlane;

    public MouseOverVectorEventHandler( CartesianPlane cartesianPlane ){
        this.cartesianPlane = cartesianPlane;
    }

    @Override
    public void handle( MouseEvent e ){
        Vector vector = ( Vector ) e.getSource();

        if( e.getEventType() == MouseEvent.MOUSE_DRAGGED ){
          vector.wideArrow();
          vector.setCoordinates( cartesianPlane.toCoordinateX( e.getSceneX() ), cartesianPlane.toCoordinateY( e.getSceneY() ) );
        } else if( e.getEventType() == MouseEvent.MOUSE_RELEASED ){
          vector.regularArrow();
        } else {
          if( e.getEventType() == MouseEvent.MOUSE_ENTERED ) vector.wideArrow();
          else if( e.getEventType() == MouseEvent.MOUSE_EXITED ) vector.regularArrow();
        }

    }
}
