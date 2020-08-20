package edu.thinkbox.math.plane.cartesian;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import edu.thinkbox.math.matrix.Matrix;

public abstract class XYObject extends Group{
       protected Color  color = Color.BLACK;
       protected Color  highlightColor = Color.BLUE;
       protected double size = 2.0;
       protected double hightlightSize = 3.0;
       protected Matrix coordinates = Matrix.createColumnMatrix( 2 );
       protected XYPlane xyPlane;

       public XYObject( XYPlane xyPlane ){
          this.xyPlane = xyPlane;
       }

       
}
