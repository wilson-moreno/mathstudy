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

public class Point extends Group{
      private static final double RADIUS = 4.0;
      private final Color  POINT_COLOR = Color.web( "002f55" );
      private double centerX;
      private double centerY;
      private double zoomFactor;
      private Matrix vector = Matrix.createColumnMatrix( 2 );

      public Point( double centerX, double centerY, double x, double y, double zoomFactor ){
          this.centerX = centerX;
          this.centerY = centerY;
          this.zoomFactor = zoomFactor;
          vector.setEntry( 0, 0, x );
          vector.setEntry( 1, 0, y );
          getChildren().add( point() );
      }

      private Circle point(){
        Circle circle = new Circle( centerX + ( vector.getEntry( 0, 0 ) * zoomFactor ),
                                    centerY - ( vector.getEntry( 1, 0 ) * zoomFactor ),
                                    RADIUS, POINT_COLOR );
        return circle;
      }

}
