package edu.thinkbox.math.plane.cartesian;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.SeparatorMenuItem;

public class TriangleContextMenu extends ContextMenu{
      private SeparatorMenuItem separatorMenuItem1 = new SeparatorMenuItem();
      private CheckMenuItem showAnglesMenu = new CheckMenuItem( "Show Angles" );
      private CheckMenuItem showCentroidMenu = new CheckMenuItem( "Show Centroid" );
      private CheckMenuItem showIncenterMenu = new CheckMenuItem( "Show Incenter" );
      private CheckMenuItem showCircumcenterMenu = new CheckMenuItem( "Show Circumcenter" );
      private CheckMenuItem showRiseRunMenu = new CheckMenuItem( "Show Rise/Run" );
      private CheckMenuItem showCoordinatesMenu = new CheckMenuItem( "Show Coordinates" );
      private CheckMenuItem showInscribedCircleMenu = new CheckMenuItem( "Show Inscribed Circle" );
      private CheckMenuItem showCircumscribedCircleMenu = new CheckMenuItem( "Show Circumscribed Circle" );
      private CheckMenuItem showSideLengthsMenu = new CheckMenuItem( "Show Side Lengths" );
      private CheckMenuItem showShadedAreaMenu = new CheckMenuItem( "Show Shaded Area" );
      private CheckMenuItem wholeNumberCoordinatesMenu = new CheckMenuItem( "Whole Numbers [ x, y ]" );
      private TriangleXY triangle;

      public TriangleContextMenu( TriangleXY triangle ){
          this.triangle = triangle;
          showAnglesMenu.setOnAction( new ShowAnglesEvent() );
          showCentroidMenu.setOnAction( new ShowCentroidEvent() );
          showIncenterMenu.setOnAction( new ShowIncenterEvent() );
          showCircumcenterMenu.setOnAction( new ShowCircumcenterEvent() );
          showRiseRunMenu.setOnAction( new ShowRiseRunEvent() );
          showCoordinatesMenu.setOnAction( new ShowCoordinatesEvent() );
          showInscribedCircleMenu.setOnAction( new ShowInscribedCircleEvent() );
          showCircumscribedCircleMenu.setOnAction( new ShowCircumscribedCircleEvent() );
          wholeNumberCoordinatesMenu.setOnAction( new WholeNumberCoordinatesEvent() );
          showShadedAreaMenu.setOnAction( new ShowShadedAreaEvent() );
          showSideLengthsMenu.setOnAction( new ShowSideLenghtsEvent() );
          getItems().addAll( showRiseRunMenu,
                             showAnglesMenu,
                             showCentroidMenu,
                             showIncenterMenu,
                             showCircumcenterMenu,
                             showCoordinatesMenu,
                             showInscribedCircleMenu,
                             showCircumscribedCircleMenu,
                             showSideLengthsMenu,
                             showShadedAreaMenu,
                             wholeNumberCoordinatesMenu );
      }


      private class ShowShadedAreaEvent implements EventHandler< ActionEvent >{
        public void handle( ActionEvent e ){
              triangle.setShadedAreaVisible( showShadedAreaMenu.isSelected() );
        }
      }


      private class ShowSideLenghtsEvent implements EventHandler< ActionEvent >{
        public void handle( ActionEvent e ){
              triangle.setSideLengthsVisible( showSideLengthsMenu.isSelected() );
        }
      }

      private class WholeNumberCoordinatesEvent implements EventHandler< ActionEvent >{
        public void handle( ActionEvent e ){
              triangle.setWholeNumberCoordinates( wholeNumberCoordinatesMenu.isSelected() );
        }
      }

      private class ShowAnglesEvent implements EventHandler< ActionEvent >{
          public void handle( ActionEvent e ){
              triangle.setAnglesVisible( showAnglesMenu.isSelected() );
          }
      }

      private class ShowCentroidEvent implements EventHandler< ActionEvent >{
          public void handle( ActionEvent e ){
              triangle.setCentroidVisible( showCentroidMenu.isSelected() );
          }
      }
      private class ShowIncenterEvent implements EventHandler< ActionEvent >{
          public void handle( ActionEvent e ){
              triangle.setIncenterVisible( showIncenterMenu.isSelected() );
          }
      }
      private class ShowCircumcenterEvent implements EventHandler< ActionEvent >{
          public void handle( ActionEvent e ){
              triangle.setCircumcenterVisible( showCircumcenterMenu.isSelected() );
          }
      }
      private class ShowRiseRunEvent implements EventHandler< ActionEvent >{
          public void handle( ActionEvent e ){
              triangle.setRiseRunVisible( showRiseRunMenu.isSelected() );
          }
      }


      private class ShowInscribedCircleEvent implements EventHandler< ActionEvent >{
          public void handle( ActionEvent e ){
              triangle.setInscribedCircleVisible( showInscribedCircleMenu.isSelected() );
          }
      }

      private class ShowCircumscribedCircleEvent implements EventHandler< ActionEvent >{
          public void handle( ActionEvent e ){
              triangle.setCircumscribedCircleVisible( showCircumscribedCircleMenu.isSelected() );
          }
      }

      private class ShowCoordinatesEvent implements EventHandler< ActionEvent >{
          public void handle( ActionEvent e ){
              triangle.setCoordinatesVisible( showCoordinatesMenu.isSelected() );
          }
      }




}
