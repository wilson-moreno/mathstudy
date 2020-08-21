package edu.thinkbox.math.plane.cartesian;

import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.Font;


public class QuadrantsXY extends XYObject{
       private Text quadrant1;
       private Text quadrant2;
       private Text quadrant3;
       private Text quadrant4;
       private double fontSize = 80;



       public QuadrantsXY( XYPlane plane ){
           super( plane );
           color = Color.web( "rgba( 0, 0, 0, 0.1 )" );
           createQuadrants();
       }

       public void setColor( Color color ){}
       public void highlight(){}
       public void unhighlight(){}

       private void createQuadrants(){
            double quarterX = plane.getWidth() / 4.0;
            double quarterY = plane.getHeight() / 4.0;

            quadrant1 = new Text( quarterX * 3, quarterY + 20, "I" );
            quadrant2 = new Text( quarterX - 20, quarterY + 20, "II" );
            quadrant3 = new Text( quarterX - 40 , quarterY * 3 + 20, "III" );
            quadrant4 = new Text( quarterX * 3  - 40, quarterY * 3 + 20, "IV" );

            quadrant1.setFont( new Font( fontSize ) );
            quadrant2.setFont( new Font( fontSize ) );
            quadrant3.setFont( new Font( fontSize ) );
            quadrant4.setFont( new Font( fontSize ) );

            quadrant1.setFill( color );
            quadrant2.setFill( color );
            quadrant3.setFill( color );
            quadrant4.setFill( color );

            getChildren().add( quadrant1 );
            getChildren().add( quadrant2 );
            getChildren().add( quadrant3 );
            getChildren().add( quadrant4 );
       }
}
