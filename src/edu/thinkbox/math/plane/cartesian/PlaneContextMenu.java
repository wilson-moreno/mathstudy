package edu.thinkbox.math.plane.cartesian;

import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Dialog;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import edu.thinkbox.math.matrix.Transformation;


public class PlaneContextMenu extends ContextMenu implements EventHandler< MouseEvent >{
      private MenuItem addVectorMenu = new MenuItem( "Add Vector" );
      private MenuItem addPointMenu = new MenuItem( "Add Point" );
      private MenuItem addLineMenu = new MenuItem( "Add Line" );
      private MenuItem addArrowHeadMenu = new MenuItem( "Add Arrow Head" );
      private MenuItem clearVectorsMenu = new MenuItem( "Clear Vectors" );
      private MenuItem clearPointsMenu = new MenuItem( "Clear Points" );
      private MenuItem clearArrowHeadsMenu = new MenuItem( "Clear Arrow Heads" );
      private CheckMenuItem showGridlinesMenu = new CheckMenuItem( "Show Gridlines" );
      private CheckMenuItem showAxesMenu = new CheckMenuItem( "Show Axes" );
      private CheckMenuItem showTicksMenu = new CheckMenuItem( "Show Ticks" );
      private CheckMenuItem showMouseCoordinatesMenu = new CheckMenuItem( "Show Mouse Coordinates" );
      private SeparatorMenuItem separatorMenuItem1 = new SeparatorMenuItem();
      private SeparatorMenuItem separatorMenuItem2 = new SeparatorMenuItem();
      private XYPlane plane;
      private double lastSceneX;
      private double lastSceneY;

      public PlaneContextMenu( XYPlane plane ){
          this.plane = plane;
          addVectorMenu.setOnAction( new AddVectorEvent() );
          addPointMenu.setOnAction( new AddPointEvent() );
          addLineMenu.setOnAction( new AddLineEvent() );
          addArrowHeadMenu.setOnAction( new AddArrowHeadEvent() );
          clearVectorsMenu.setOnAction( new ClearVectorsEvent() );
          clearPointsMenu.setOnAction( new ClearPointsEvent() );
          clearArrowHeadsMenu.setOnAction( new ClearArrowHeadsEvent() );
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
                              addArrowHeadMenu,
                              separatorMenuItem1,
                              clearVectorsMenu,
                              clearPointsMenu,
                              clearArrowHeadsMenu,
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

      private class AddLineEvent implements EventHandler< ActionEvent >{
          public void handle( ActionEvent e){
          }
      }

      private class AddVectorEvent implements EventHandler<ActionEvent>{
          public void handle( ActionEvent e ){
              double coordinateX = plane.toCoordinateX( lastSceneX );
              double coordinateY = plane.toCoordinateY( lastSceneY );
              //plane.addVector( coordinateX, coordinateY );
          }
      }

      private class AddPointEvent implements EventHandler<ActionEvent>{
          public void handle( ActionEvent e ){
              double coordinateX = plane.toCoordinateX( lastSceneX );
              double coordinateY = plane.toCoordinateY( lastSceneY );
              //plane.addPoint( coordinateX, coordinateY );
          }
      }

      private class AddArrowHeadEvent implements EventHandler<ActionEvent>{
          public void handle( ActionEvent e ){
              double coordinateX = plane.toCoordinateX( lastSceneX );
              double coordinateY = plane.toCoordinateY( lastSceneY );
              plane.addArrowHead( coordinateX, coordinateY );
          }
      }

      private class ClearVectorsEvent implements EventHandler<ActionEvent>{
          public void handle( ActionEvent e ){
              plane.clearVectors();
          }
      }

      private class ClearArrowHeadsEvent implements EventHandler<ActionEvent>{
          public void handle( ActionEvent e ){
              plane.clearArrowHeads();
          }
      }


      private class ClearPointsEvent implements EventHandler<ActionEvent>{
          public void handle( ActionEvent e ){
              plane.clearPoints();
          }
      }

      private class ShowGridlinesEvent implements EventHandler<ActionEvent>{
          public void handle( ActionEvent e ){
              plane.setGridlinesVisible( showGridlinesMenu.isSelected() );
          }
      }

      private class ShowAxesEvent implements EventHandler<ActionEvent>{
          public void handle( ActionEvent e ){
              plane.setAxesVisible( showAxesMenu.isSelected() );
          }
      }

      private class ShowTicksEvent implements EventHandler<ActionEvent>{
          public void handle( ActionEvent e ){
              plane.setTicksVisible( showTicksMenu.isSelected() );
          }
      }

      private class ShowMouseCoordinatesEvent implements EventHandler<ActionEvent>{
          public void handle( ActionEvent e ){
              plane.setMouseCoordinatesVisible( showMouseCoordinatesMenu.isSelected() );
          }
      }

}
