package edu.thinkbox.math.plane.cartesian;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.text.Font;

public class GridlinesXY extends XYObject{

        public GridlinesXY( XYPlane plane ){
            super( plane );
            setColor( Color.web( "4a688a" ) );
            setSize( 0.15 );
            createGridLines();
        }

        private void createGridLines(){
            Text origin = new Text( plane.getCenterX() + 5 , plane.getCenterY() + 15 , "O" );
            origin.setFont( new Font( 12 ) );

            for( int i = 0; i <= plane.getRowCount(); i++ ){
                Line line = new Line( 0, i * plane.getModuleSize(), plane.getWidth(), i * plane.getModuleSize() );
                line.setStrokeWidth( getSize() );
                line.setStroke( getColor() );
                getChildren().add( line );
            }

            for( int j = 0; j <= plane.getColumnCount(); j++ ){
                Line line = new Line( j * plane.getModuleSize(), 0, j * plane.getModuleSize(), plane.getHeight() );
                line.setStrokeWidth( getSize() );
                line.setStroke( getColor() );
                getChildren().add( line );
            }

        }


        public void highlight(){}
        public void unhighlight(){}
        public void setPlaneCoordinates( double x, double y ){}

}
