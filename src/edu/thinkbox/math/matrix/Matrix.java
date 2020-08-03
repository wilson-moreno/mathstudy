package edu.thinkbox.math.matrix;

import java.util.Random;
import java.time.LocalTime;

public class Matrix{
    private int rows;
    private int columns;
    private double entries[][];

    public Matrix( int rows, int columns ){
      this.rows = rows;
      this.columns = columns;
      entries = new double[ rows ][ columns ];
    }

    public int getRows(){ return rows; }
    public int getColumns(){ return columns; }
    public double getEntry( int row, int column ){ return entries[ row ][ column ]; }
    public void setEntry( int row, int column, double entry ){ entries[ row ][ column ] = entry; }

    public void generateRandomEntries(){
        LocalTime now = LocalTime.now();
        Random random = new Random( now.toNanoOfDay() );

        for( int i = 0; i < rows; i++ )
          for( int j = 0; j < columns; j++ )
              entries[ i ][ j ] = 99 * random.nextDouble();
    }

    public boolean sizeEquals( Matrix matrix ){
        if( rows == matrix.getRows() &&
            columns == matrix.getColumns() )
            return true;
        else
            return false;
    }

    public boolean entriesEquals( Matrix matrix ){
        if( sizeEquals( matrix ) ){
            for( int i = 0; i < rows; i++ )
              for( int j = 0; j < columns; j++ )
                if( entries[ i ][ j ] != matrix.getEntry( i, j ))return false;

            return true;
        } else {
          return false;
        }
    }

    public String toString(){
        String matrixStr = new String();

        for( int i = 0; i < rows; i++){
            matrixStr += "[ ";
          for( int j = 0; j < columns; j++ ){
            matrixStr += String.format("%6.2f ", entries[ i ][ j ] );
          }
            matrixStr += " ]\n";
        }

        return matrixStr;
    }

    public boolean equals( Matrix matrix ){
        return sizeEquals( matrix ) && entriesEquals( matrix );
    }

    public void add( Matrix matrix ) throws MatrixSizeMismatchException {
        if( this.sizeEquals( matrix) ){
          for( int i = 0; i < rows; i++ )
            for( int j = 0; j < columns; j++ )
              entries[ i ][ j ] += matrix.getEntry( i, j );
        } else {
          throw new MatrixSizeMismatchException();
        }
    }

    public void subtract( Matrix matrix ) throws MatrixSizeMismatchException {
        if( this.sizeEquals( matrix) ){
          for( int i = 0; i < rows; i++ )
            for( int j = 0; j < columns; j++ )
              entries[ i ][ j ] -= matrix.getEntry( i, j );
        } else {
          throw new MatrixSizeMismatchException();
        }
    }

    public void transpose(){
        double[][] transposed = new double[ columns ][ rows ];

        for( int i = 0; i < rows; i++ )
          for( int j = 0; j < columns; j++ )
            transposed[ j ][ i ] = entries[ i ][ j ];

        this.entries = null;
        this.entries = transposed;
        int temp = rows;
        rows = columns;
        columns = temp;
    }

    public void copy( Matrix matrix ) throws MatrixSizeMismatchException{
        if( sizeEquals( matrix ) ){
          for( int i = 0; i < rows; i++ )
            for( int j = 0; j < columns; j++ )
              entries[ i ][ j ] = matrix.getEntry( i, j );
        } else {
          throw new MatrixSizeMismatchException();
        }
    }

    public void negative(){
      for( int i = 0; i < rows; i++ )
        for( int j = 0; j < columns; j++ )
          entries[ i ][ j ] *= -1;
    }

    public boolean isSquare(){
        return rows == columns;
    }

    public static Matrix createRowMatrix( int rows ){ return new Matrix( rows, 1 ); }
    public static Matrix createColumnMatrix( int columns ){ return new Matrix( 1, columns ); }
    public static Matrix createSquareMatrix( int n ){ return new Matrix( n, n ); }
}
