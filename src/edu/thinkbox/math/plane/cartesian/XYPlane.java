package edu.thinkbox.math.plane.cartesian;

import javafx.scene.Group;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import edu.thinkbox.math.matrix.Matrix;
import javafx.event.EventHandler;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;

public class XYPlane extends Group implements EventHandler< ContextMenuEvent >{
        private double            width;
        private double            height;
        private double            moduleSize;
        private Rectangle         plane;
        private GridlinesXY       gridlines;
        private AxesXY            axes;
        private TicksXY           ticks;
        private PlaneContextMenu  contextMenu;
        private Group             vectors;
        private Group             points;
        private Group             arrowHeads;
        private boolean           mouseCoordinateVisible;


        public XYPlane( double width, double height, double moduleSize ){
            this.width = width;
            this.height = height;
            this.moduleSize = moduleSize;
            this.plane = new Rectangle( width, height );
            this.plane.setFill( Color.WHITE );
            this.gridlines = new GridlinesXY( this );
            this.axes = new AxesXY( this );
            this.ticks = new TicksXY( this );
            this.contextMenu = new PlaneContextMenu( this );
            this.plane.setOnContextMenuRequested( this );
            this.vectors = new Group();
            this.points = new Group();
            this.arrowHeads = new Group();
            this.mouseCoordinateVisible = false;

            getChildren().add( plane );
            getChildren().add( gridlines );
            getChildren().add( axes );
            getChildren().add( ticks );
            getChildren().add( vectors );
            getChildren().add( points );
            getChildren().add( arrowHeads );

            addEventFilter( MouseEvent.ANY, new MouseMovementEventHandler( this ) );
            plane.addEventFilter( MouseEvent.MOUSE_CLICKED, this.contextMenu );
        }

        @Override
        public void handle( ContextMenuEvent event ){
            contextMenu.show( this, event.getScreenX(), event.getScreenY() );
        }


        public ArrowHeadXY addArrowHead( double x, double y ){
            ArrowHeadXY arrowHead = new ArrowHeadXY( x, y, this, ArrowHeadType.TRIANGLE );
            //point.setOnContextMenuRequested( new PointContextMenuEventHandler( point ) );
            //point.addEventFilter( MouseEvent.ANY, new MouseOverPointEventHandler( this) );
            arrowHeads.getChildren().add( arrowHead );
            return arrowHead;
        }
        public void clearVectors(){ vectors.getChildren().clear(); }
        public void clearPoints(){ points.getChildren().clear(); }
        public void clearArrowHeads(){ arrowHeads.getChildren().clear(); }
        public boolean isMouseCoordinateVisible(){ return mouseCoordinateVisible; }
        public void setMouseCoordinatesVisible( boolean visible ){ this.mouseCoordinateVisible = visible; }
        public void setGridlinesVisible( boolean visible ){ gridlines.setVisible( visible ); }
        public void setAxesVisible( boolean visible ){ axes.setVisible( visible ); }
        public void setTicksVisible( boolean visible ){ ticks.setVisible( visible ); }
        public double getHeight(){ return height; }
        public double getWidth(){ return width; }
        public double getModuleSize(){ return moduleSize; }
        public int getRowCount(){ return (int) ( height / moduleSize ); }
        public int getColumnCount(){ return  (int) ( width / moduleSize ); }
        public int getXBound(){ return getColumnCount() / 2; }
        public int getYBound(){ return getRowCount() / 2; }
        public double getCenterX(){ return width / 2.0; }
        public double getCenterY(){ return height / 2.0; }
        public double toSceneX( double xCoordinate ){
               return getCenterX() + ( xCoordinate * moduleSize );
        }
        public double toSceneY( double yCoordinate ){
               return getCenterY() - ( yCoordinate * moduleSize );
        }
        public double toCoordinateX( double screenX ){
               return ( screenX - getCenterX() ) / moduleSize;
        }
        public double toCoordinateY( double screenY ){
               return ( getCenterY() - screenY ) / moduleSize;
        }
        public void setColor( Color color ){
               plane.setFill( color );
        }
        public double getQuadrant( Matrix coordinates ){
              double x = coordinates.getEntry( 0, 0 );
              double y = coordinates.getEntry( 1, 0 );
              int quadrant = 0;

              if( x > 0.0 && y > 0.0 ) quadrant = 1;
              else if( x < 0.0 && y > 0.0 ) quadrant = 2;
              else if( x < 0.0 && y < 0.0 ) quadrant = 3;
              else if( x > 0.0 && y < 0.0 ) quadrant = 4;

              return quadrant;
        }

        public double getDirection( Matrix coordinates ){
            double x = coordinates.getEntry( 0, 0 );
            double y = coordinates.getEntry( 1, 0 );

            if( x > 0.0 && y == 0.0 ) return 0.0;
            else if( x == 0.0 && y > 0.0 ) return Math.PI / 2.0;
            else if( x < 0.0 && y == 0.0 ) return Math.PI;
            else if( x == 0.0 && y < 0.0 ) return Math.PI * ( 3.0 / 2.0 );
            else {
                double radians = Math.atan( y / x );
                if( ( x < 0.0 && y > 0.0 ) || ( x < 0.0 && y < 0.0 ) ) radians += Math.PI;
                else if( ( x > 0.0 && y < 0.0 ) ) radians += 2 * Math.PI;
                return radians;
            }

        }
        public double toDegree( double radians ){
            return radians * ( 180.0 / Math.PI );
        }

}
