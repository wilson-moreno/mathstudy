package edu.thinkbox.math.matrix;




public final class Transformation{

        public static Matrix rotationMatrix( double radian ){
              Matrix transformation = Matrix.createSquareMatrix( 2 );

              transformation.setRowEntries( 0, String.format( " %f %f ", Math.cos( radian ), -1.0 * Math.sin( radian ) ) );
              transformation.setRowEntries( 1, String.format( " %f %f ", Math.sin( radian ), Math.cos( radian ) ) );

              return transformation;
        }

        public static Matrix yProjectionMatrix(){
              Matrix transformation = Matrix.createSquareMatrix( 2 );

              transformation.setRowEntries( 0, " 0  0 " );
              transformation.setRowEntries( 1, " 0  1 " );

          return transformation;
        }

        public static Matrix xProjectionMatrix(){
              Matrix transformation = Matrix.createSquareMatrix( 2 );

              transformation.setRowEntries( 0, " 1  0 " );
              transformation.setRowEntries( 1, " 0  0 " );

          return transformation;
        }


}
