package edu.thinkbox.math.plane.cartesian;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

public class PointContextMenu extends ContextMenu{
      private CheckMenuItem showRiseRunMenu = new CheckMenuItem( "Show Rise/Run" );
      private CheckMenuItem showCoordinatesMenu = new CheckMenuItem( "Show Coordinates" );
      private CheckMenuItem wholeNumberCoordinatesMenu = new CheckMenuItem( "Whole Numbers [ x, y ]" );
      private PointXY point;

      public PointContextMenu( PointXY point ){
          this.point = point;
          showRiseRunMenu.setOnAction( new ShowRiseRunEvent() );
          showCoordinatesMenu.setOnAction( new ShowCoordinatesEvent() );
          wholeNumberCoordinatesMenu.setOnAction( new WholeNumberCoordinatesEvent() );
          getItems().addAll( showRiseRunMenu,
                             showCoordinatesMenu,
                             wholeNumberCoordinatesMenu );
      }

      private class WholeNumberCoordinatesEvent implements EventHandler< ActionEvent >{
        public void handle( ActionEvent e ){
              point.setWholeNumberCoordinates( wholeNumberCoordinatesMenu.isSelected() );
        }
      }

      private class ShowRiseRunEvent implements EventHandler< ActionEvent >{
          public void handle( ActionEvent e ){
              point.setRiseRunVisible( showRiseRunMenu.isSelected() );
          }
      }

      private class ShowCoordinatesEvent implements EventHandler< ActionEvent >{
          public void handle( ActionEvent e ){
              point.setCoordinatesVisible( showCoordinatesMenu.isSelected() );
          }
      }




}
