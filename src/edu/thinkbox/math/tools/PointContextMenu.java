package edu.thinkbox.math.tools;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

public class PointContextMenu extends ContextMenu{
      private CheckMenuItem showRiseRunMenu = new CheckMenuItem( "Show Rise/Run" );
      private CheckMenuItem showCoordinatesMenu = new CheckMenuItem( "Show Coordinates" );
      private CheckMenuItem coordinatesAsWholeNumberMenu = new CheckMenuItem( "Whole Numbers [ x, y ]" );
      private Point point;

      public PointContextMenu( Point point){
          this.point = point;
          showRiseRunMenu.setOnAction( new ShowRiseRunEvent() );
          showCoordinatesMenu.setOnAction( new ShowCoordinatesEvent() );
          coordinatesAsWholeNumberMenu.setOnAction( new CoordinatesAsWholeNumberEvent() );
          getItems().addAll( showRiseRunMenu,
                             showCoordinatesMenu,
                             coordinatesAsWholeNumberMenu );
      }

      private class CoordinatesAsWholeNumberEvent implements EventHandler< ActionEvent >{
        public void handle( ActionEvent e ){
            point.setCoordinatesAsWholeNumbers( coordinatesAsWholeNumberMenu.isSelected() );
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
