package edu.thinkbox.math.plane.cartesian;

import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.Menu;
import edu.thinkbox.math.matrix.Transformation;

public class VectorContextMenu extends ContextMenu{
      private Menu rotateMenu = new Menu( "Rotate" );
      private MenuItem rotate30 = new MenuItem( "30 degrees" );
      private MenuItem rotate45 = new MenuItem( "45 degrees" );
      private MenuItem rotate60 = new MenuItem( "60 degrees" );
      private MenuItem rotate90 = new MenuItem( "90 degrees" );
      private MenuItem xProjectionMenu = new MenuItem( "Project Onto X-Axis" );
      private MenuItem yProjectionMenu = new MenuItem( "Project Onto Y-Axis" );
      private CheckMenuItem showCoordinatesMenu = new CheckMenuItem( "Show Coordinates" );
      private CheckMenuItem showMagnitudeMenu = new CheckMenuItem( "Show Magnitude" );
      private CheckMenuItem showAngleMenu = new CheckMenuItem( "Show Angle" );
      private CheckMenuItem showRiseRunMenu = new CheckMenuItem( "Show Rise/Run" );
      private CheckMenuItem showPointMenu = new CheckMenuItem( "Show Point" );
      private CheckMenuItem wholeNumberXYMenu = new CheckMenuItem( "Whole Number [ x, y ]" );
      private SeparatorMenuItem separatorMenuItem1 = new SeparatorMenuItem();
      private VectorXY vector;

      public VectorContextMenu( VectorXY vector){
          this.vector = vector;
          rotate30.setOnAction( new Rotate30Event() );
          rotate45.setOnAction( new Rotate45Event() );
          rotate60.setOnAction( new Rotate60Event() );
          rotate90.setOnAction( new Rotate90Event() );
          xProjectionMenu.setOnAction( new ProjectOntoXAxisEvent() );
          yProjectionMenu.setOnAction( new ProjectOntoYAxisEvent() );
          showCoordinatesMenu.setOnAction( new ShowCoordinatesEvent() );
          showMagnitudeMenu.setOnAction( new ShowMagnitudeEvent() );
          showAngleMenu.setOnAction( new ShowAngleEvent() );
          showRiseRunMenu.setOnAction( new ShowRiseRunEvent() );
          showPointMenu.setOnAction( new ShowPointEvent() );
          wholeNumberXYMenu.setOnAction( new WholeNumberXYEvent() );
          rotateMenu.getItems().addAll( rotate30 );
          rotateMenu.getItems().addAll( rotate45 );
          rotateMenu.getItems().addAll( rotate60 );
          rotateMenu.getItems().addAll( rotate90 );
          getItems().addAll( rotateMenu,
                             xProjectionMenu,
                             yProjectionMenu,
                             separatorMenuItem1,
                             showMagnitudeMenu,
                             showAngleMenu,
                             showRiseRunMenu,
                             showCoordinatesMenu,
                             showPointMenu,
                             wholeNumberXYMenu );
      }

      private class WholeNumberXYEvent implements EventHandler< ActionEvent >{
          public void handle( ActionEvent e ){
              vector.setWholeNumberCoordinates( wholeNumberXYMenu.isSelected() );
          }
      }

      private class ShowAngleEvent implements EventHandler<ActionEvent>{
          public void handle( ActionEvent e ){
              vector.setAngleVisible( showAngleMenu.isSelected() );
          }
      }


      private class ProjectOntoXAxisEvent implements EventHandler<ActionEvent>{
          public void handle( ActionEvent e ){
              vector.transform( Transformation.xProjectionMatrix() );
          }
      }


      private class ProjectOntoYAxisEvent implements EventHandler<ActionEvent>{
          public void handle( ActionEvent e ){
              vector.transform( Transformation.yProjectionMatrix() );
          }
      }

      private class ShowPointEvent implements EventHandler<ActionEvent>{
          public void handle( ActionEvent e ){
              vector.setPointVisible( showPointMenu.isSelected() );              
          }
      }


      private class ShowRiseRunEvent implements EventHandler<ActionEvent>{
          public void handle( ActionEvent e ){
              vector.setRiseRunVisible( showRiseRunMenu.isSelected() );
          }
      }

      private class ShowMagnitudeEvent implements EventHandler<ActionEvent>{
          public void handle( ActionEvent e ){
              vector.setMagnitudeVisible( showMagnitudeMenu.isSelected() );
          }
      }

      private class ShowCoordinatesEvent implements EventHandler<ActionEvent>{
          public void handle( ActionEvent e ){
              vector.setCoordinatesVisible( showCoordinatesMenu.isSelected() );
          }
      }

      private class Rotate30Event implements EventHandler<ActionEvent>{
          public void handle( ActionEvent e ){
              vector.transform( Transformation.rotationMatrix( Math.PI / 6.0 ) );
          }
      }

      private class Rotate45Event implements EventHandler<ActionEvent>{
          public void handle( ActionEvent e ){
              vector.transform( Transformation.rotationMatrix( Math.PI / 4.0 ) );
          }
      }

      private class Rotate60Event implements EventHandler<ActionEvent>{
          public void handle( ActionEvent e ){
              vector.transform( Transformation.rotationMatrix( Math.PI / 3.0 ) );
          }
      }

      private class Rotate90Event implements EventHandler<ActionEvent>{
          public void handle( ActionEvent e ){
              vector.transform( Transformation.rotationMatrix( Math.PI / 2.0 ) );
          }
      }
}
