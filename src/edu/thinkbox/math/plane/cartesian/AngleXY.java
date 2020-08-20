package edu.thinkbox.math.plane.cartesian;

import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;

public class AngleXY extends XYObject{
        private Arc  angle;
        private Text angleValue;

        public AngleXY( XYPlane xyPlane ){
           super( xyPlane );
           color = Color.web( "509237" );
           highlightColor =  Color.web( "ff1a00" );
           size = 3.0;
           highlightSize = 5.0;
        }


        public void hightlight(){
            angle.setStroke( highlightColor );
            angle.setStrokeWidth( highlightSize );
        }

        public void unhighlight(){
            angle.setStroke( color );
            angle.setStrokeWidth( size );
        }

}
