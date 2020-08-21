package edu.thinkbox.math.plane.cartesian;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

public class ArrowHeadContextMenu extends ContextMenu{
      private CheckMenuItem showRiseRunMenu = new CheckMenuItem( "Show Rise/Run" );
      private CheckMenuItem showCoordinatesMenu = new CheckMenuItem( "Show Coordinates" );
      private CheckMenuItem wholeNumberCoordinatesMenu = new CheckMenuItem( "Whole Numbers [ x, y ]" );
      private ArrowHeadXY arrowHead;

      public ArrowHeadContextMenu( ArrowHeadXY arrowHead ){
          this.arrowHead = arrowHead;
          showRiseRunMenu.setOnAction( new ShowRiseRunEvent() );
          showCoordinatesMenu.setOnAction( new ShowCoordinatesEvent() );
          wholeNumberCoordinatesMenu.setOnAction( new WholeNumberCoordinatesEvent() );
          getItems().addAll( showRiseRunMenu,
                             showCoordinatesMenu,
                             wholeNumberCoordinatesMenu );
      }

      private class WholeNumberCoordinatesEvent implements EventHandler< ActionEvent >{
        public void handle( ActionEvent e ){
              arrowHead.setWholeNumberCoordinates( wholeNumberCoordinatesMenu.isSelected() );
        }
      }

      private class ShowRiseRunEvent implements EventHandler< ActionEvent >{
          public void handle( ActionEvent e ){
              arrowHead.setRiseRunVisible( showRiseRunMenu.isSelected() );
          }
      }

      private class ShowCoordinatesEvent implements EventHandler< ActionEvent >{
          public void handle( ActionEvent e ){
              arrowHead.setCoordinatesVisible( showCoordinatesMenu.isSelected() );
          }
      }




}
