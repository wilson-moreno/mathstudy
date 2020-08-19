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

public class VectorContextMenu extends ContextMenu{
      private Menu rotateMenu = new Menu( "Rotate" );
      private MenuItem rotate30 = new MenuItem( "30 degrees" );
      private MenuItem rotate45 = new MenuItem( "45 degrees" );
      private MenuItem rotate60 = new MenuItem( "60 degrees" );
      private MenuItem rotate90 = new MenuItem( "90 degrees" );
      private CheckMenuItem showCoordinatesMenu = new CheckMenuItem( "Show Coordinates" );
      private CheckMenuItem showMagnitudeMenu = new CheckMenuItem( "Show Magnitude" );
      private CheckMenuItem showRiseRunMenu = new CheckMenuItem( "Show Rise/Run" );
      private SeparatorMenuItem separatorMenuItem1 = new SeparatorMenuItem();
      private Vector vector;

      public VectorContextMenu( Vector vector){
          this.vector = vector;
          rotate30.setOnAction( new Rotate30Event() );
          rotate45.setOnAction( new Rotate45Event() );
          rotate60.setOnAction( new Rotate60Event() );
          rotate90.setOnAction( new Rotate90Event() );
          showCoordinatesMenu.setOnAction( new ShowCoordinatesEvent() );
          showMagnitudeMenu.setOnAction( new ShowMagnitudeEvent() );
          showRiseRunMenu.setOnAction( new ShowRiseRunEvent() );
          rotateMenu.getItems().addAll( rotate30 );
          rotateMenu.getItems().addAll( rotate45 );
          rotateMenu.getItems().addAll( rotate60 );
          rotateMenu.getItems().addAll( rotate90 );
          getItems().addAll( rotateMenu, separatorMenuItem1, showMagnitudeMenu, showRiseRunMenu, showCoordinatesMenu );
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
