
public class AugmentedMatrix{
      private int         rowSize = 0;
      private int         columnSize = 0;
      private double[][]  coefficientMatrix;
      private double[]    constantMatrix;

      public AugmentedMatrix( int rowSize, int columnSize ){
        this.rowSize = rowSize;
        this.columnSize = columnSize;

        coefficientMatrix = new double[ rowSize ][ columnSize ];
        constantMatrix = new double[ rowSize ];
      }

      public int getRowSize(){ return rowSize; }
      public int getColumnSize(){ return columnSize; }

      public void setCoefficientAt( int row, int column, double coefficient ){
        if( ( row >= 0 && row < rowSize ) && ( column >= 0 && column < columnSize ) ){
          coefficientMatrix[ row ][ column ] = coefficient;
        }
      }

      public double getCoefficientAt( int row, int column ){
        return coefficientMatrix[ row ][ column ];
      }

      public void setConstantAt( int row, double constant ){
        if( row >= 0 && row < rowSize ){
          constantMatrix[ row ] = constant;
        }
      }

      public double getConstantAt( int row ){
        return constantMatrix[ row ];
      }

      public void interchangeRows( int row1, int row2 ){
          double temp;

          for( int column = 0; column < columnSize; column++ ){
            temp = coefficientMatrix[ row1 ][ column ];
            coefficientMatrix[ row1 ][ column ] = coefficientMatrix[ row2 ][ column ];
            coefficientMatrix[ row2 ][ column ] = temp;
          }

          temp = constantMatrix[ row1 ];
          constantMatrix[ row1 ] = constantMatrix[ row2 ];
          constantMatrix[ row2 ] = temp;
      }

      public void divideRowBy( int row, double value ){
            multiplyBy( row, ( 1.0 / value ) );
      }

      public void multiplyRowBy( int row, double value ){

          for( int column = 0; column < columnSize; column++ )
            coefficientMatrix[ row ][ column ] *= value;

          constantMatrix[ row ] *= value;
      }

      public void addMultipleTo( int row1, int row2, double multiplier ){

        for( int column = 0; column < columnSize; column++ )
          coefficientMatrix[ row2 ][ column ] += coefficientMatrix[ row1 ][ column ] * multiplier;

        constantMatrix[ row2 ] += constantMatrix[ row1 ] * multiplier;
      }

      public String toString(){

        String matrix = new String();

        for( int i = 0; i < rowSize; i++){
          matrix += " [";
          for( int j = 0; j < columnSize; j++ ){
            matrix += String.format("%5.2f ", coefficientMatrix[ i ][ j ] );
          }
          matrix += String.format("| %5.2f ]\n", constantMatrix[ i ] );
        }


        return matrix;
      }
}
