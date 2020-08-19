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

public class PlaneContextMenu extends ContextMenu implements EventHandler< MouseEvent >{
      private MenuItem addVectorMenu = new MenuItem( "Add Vector" );
      private MenuItem addPointMenu = new MenuItem( "Add Point" );
      private MenuItem clearVectorsMenu = new MenuItem( "Clear Vectors" );
      private MenuItem clearPointsMenu = new MenuItem( "Clear Points" );
      private CheckMenuItem showGridlinesMenu = new CheckMenuItem( "Show Gridlines" );
      private CheckMenuItem showAxesMenu = new CheckMenuItem( "Show Axes" );
      private CheckMenuItem showTicksMenu = new CheckMenuItem( "Show Ticks" );
      private CheckMenuItem showMouseCoordinatesMenu = new CheckMenuItem( "Show Mouse Coordinates" );
      private SeparatorMenuItem separatorMenuItem1 = new SeparatorMenuItem();
      private SeparatorMenuItem separatorMenuItem2 = new SeparatorMenuItem();
      private CartesianPlane cartesianPlane;
      private double lastSceneX;
      private double lastSceneY;

      public PlaneContextMenu( CartesianPlane cartesianPlane ){
          this.cartesianPlane = cartesianPlane;
          addVectorMenu.setOnAction( new AddVectorEvent() );
          addPointMenu.setOnAction( new AddPointEvent() );
          clearVectorsMenu.setOnAction( new ClearVectorsEvent() );
          clearPointsMenu.setOnAction( new ClearPointsEvent() );
          showGridlinesMenu.setOnAction( new ShowGridlinesEvent() );
          showAxesMenu.setOnAction( new ShowAxesEvent() );
          showTicksMenu.setOnAction( new ShowTicksEvent() );
          showMouseCoordinatesMenu.setOnAction( new ShowMouseCoordinatesEvent() );
          showGridlinesMenu.setSelected( true );
          showAxesMenu.setSelected( true );
          showTicksMenu.setSelected( true );
          showMouseCoordinatesMenu.setSelected( false );
          getItems().addAll(  addVectorMenu,
                              addPointMenu,
                              separatorMenuItem1,
                              clearVectorsMenu,
                              clearPointsMenu,
                              separatorMenuItem2,
                              showGridlinesMenu,
                              showAxesMenu,
                              showTicksMenu,
                              showMouseCoordinatesMenu );
      }


      @Override
      public void handle( MouseEvent e ){
          lastSceneX = e.getSceneX();
          lastSceneY = e.getSceneY();
      }

      private class AddVectorEvent implements EventHandler<ActionEvent>{
          public void handle( ActionEvent e ){
              double coordinateX = cartesianPlane.toCoordinateX( lastSceneX );
              double coordinateY = cartesianPlane.toCoordinateY( lastSceneY );
              cartesianPlane.addVector( coordinateX, coordinateY );
          }
      }

      private class AddPointEvent implements EventHandler<ActionEvent>{
          public void handle( ActionEvent e ){
              double coordinateX = cartesianPlane.toCoordinateX( lastSceneX );
              double coordinateY = cartesianPlane.toCoordinateY( lastSceneY );
              cartesianPlane.addPoint( coordinateX, coordinateY );
          }
      }

      private class ClearVectorsEvent implements EventHandler<ActionEvent>{
          public void handle( ActionEvent e ){
              cartesianPlane.clearVectors();
          }
      }

      private class ClearPointsEvent implements EventHandler<ActionEvent>{
          public void handle( ActionEvent e ){
              cartesianPlane.clearPoints();
          }
      }

      private class ShowGridlinesEvent implements EventHandler<ActionEvent>{
          public void handle( ActionEvent e ){
              cartesianPlane.setGridlinesVisible( showGridlinesMenu.isSelected() );
          }
      }

      private class ShowAxesEvent implements EventHandler<ActionEvent>{
          public void handle( ActionEvent e ){
              cartesianPlane.setAxesVisible( showAxesMenu.isSelected() );
          }
      }

      private class ShowTicksEvent implements EventHandler<ActionEvent>{
          public void handle( ActionEvent e ){
              cartesianPlane.setTicksVisible( showTicksMenu.isSelected() );
          }
      }

      private class ShowMouseCoordinatesEvent implements EventHandler<ActionEvent>{
          public void handle( ActionEvent e ){
              cartesianPlane.setMouseCoordinatesVisible( showMouseCoordinatesMenu.isSelected() );
          }
      }

}
