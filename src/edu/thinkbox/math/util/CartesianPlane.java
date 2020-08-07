package edu.thinkbox.math.util;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class CartesianPlane extends Application{
      private static final int WIDTH = 400;
      private static final int HEIGHT = 400;
      private double CenterX = WIDTH / 2;
      private double CenterY = HEIGHT / 2;
      private double zoomFactor = 20.0;
      private GraphicsContext gc;
      private Canvas drawingCanvas;

      public static void main( String[] args ){
          launch( args );
      }

      @Override
      public void start( Stage primaryStage ){
        primaryStage.setTitle( "Cartesian Plane" );
        Group root = new Group();
        drawingCanvas = new Canvas( WIDTH, HEIGHT );
        gc = drawingCanvas.getGraphicsContext2D();
        showGrid( gc );
        root.getChildren().add( drawingCanvas );
        primaryStage.setScene( new Scene( root ) );
        primaryStage.show();
      }

      private void showGrid( GraphicsContext gc ){
        gc.setLineWidth( 0.25 );
        for( int i = 0; ( i * zoomFactor ) < drawingCanvas.getHeight(); i++ )
          gc.strokeLine( 0, i * zoomFactor, drawingCanvas.getWidth(), i * zoomFactor );

        for( int j = 0; ( j * zoomFactor ) < drawingCanvas.getWidth(); j++ )
          gc.strokeLine( j * zoomFactor, 0 , j * zoomFactor, drawingCanvas.getHeight() );

        gc.setStroke( Color.BLACK );
        // Draw axes
        gc.setLineWidth( 0.75 );
        gc.strokeLine( 0 + 10, CenterY, drawingCanvas.getWidth() - 10, CenterY );
        gc.strokeLine( CenterX, 0 + 10, CenterX, drawingCanvas.getHeight() - 10 );
      }

}
