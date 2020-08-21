package edu.thinkbox.math.matrix.applications;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import edu.thinkbox.math.tools.CartesianPlane;
import edu.thinkbox.math.plane.cartesian.XYPlane;
import java.util.ArrayList;
import edu.thinkbox.math.matrix.Transformation;

public class LinearTransformations extends Application{
        private static final int WIDTH = 800;
        private static final int HEIGHT = 600;
        private static final int zoomFactor = 20;
        private XYPlane cartesianPlane;

        public LinearTransformations(){
            cartesianPlane = new XYPlane( WIDTH, HEIGHT, zoomFactor );
        }

        @Override
        public void start( Stage primaryStage ){
            primaryStage.setTitle( "Linear Transformations" );
            //primaryStage.setMaximized( true );
            //primaryStage.setFullScreen( true );
            Group root = new Group();
            root.getChildren().add( cartesianPlane );
            primaryStage.setScene( new Scene( root, WIDTH, HEIGHT ) );
            primaryStage.show();
        }

        public static void main( String args[] ){
            launch( args );
        }

}
