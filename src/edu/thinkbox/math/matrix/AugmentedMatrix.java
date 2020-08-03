package edu.thinkbox.math.matrix;

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
            multiplyRowBy( row, ( 1.0 / value ) );
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

      public void gaussianElimination(){
            rowEchelon( this, 0, 0, rowSize, columnSize );
      }

      private void rowEchelon(AugmentedMatrix matrix, int row, int col, int rowSize, int colSize ){

            if( row >= rowSize || col >= colSize ) return;

            if( matrix.getCoefficientAt( row, col ) == 0.0 ){
              for( int i = row + 1; i < rowSize; i++){
                if( matrix.getCoefficientAt( i, col ) != 0.0){
                  matrix.interchangeRows( row, i );
                  break;
                }
              }
            }


            if( matrix.getCoefficientAt( row, col ) != 1.0 ){
              matrix.divideRowBy( row, matrix.getCoefficientAt( row, col ) );
            }


            for( int i = row + 1; i < rowSize; i++ ){
              if( matrix.getCoefficientAt( i, col ) != 0.0){
                matrix.addMultipleTo( row, i, matrix.getCoefficientAt( i, col ) * -1 );
              }
            }

            for( int i = row - 1; i >= 0; i-- ){
              if( matrix.getCoefficientAt( i, col ) != 0.0){
                matrix.addMultipleTo( row, i, matrix.getCoefficientAt( i, col ) * -1 );
              }
            }

            rowEchelon( matrix, row + 1, col + 1, rowSize, colSize );
      }

      public String toString(){

        String matrix = new String();

        for( int i = 0; i < rowSize; i++){
          matrix += String.format( "%2d: [", i);
          for( int j = 0; j < columnSize; j++ ){
            matrix += String.format("%5.2f ", coefficientMatrix[ i ][ j ] );
          }
          matrix += String.format("| %5.2f ]\n", constantMatrix[ i ] );
        }


        return matrix;
      }
}
