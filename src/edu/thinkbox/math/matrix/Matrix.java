package edu.thinkbox.math.matrix;

import java.util.Random;
import java.time.LocalTime;

/**
* This class represents a <strong>Matrix</strong>, which is a set of numbers arranged in rows and columns so as to form
* a rectangular array. The numbers are called elements, or entries, of the matrix. Matrices have wide applications
* in engineering, physics, economics, and statistics as well as in various branches of mathematics.
* @author Wilson S. Moreno
*/
public class Matrix{
    private int rows;     // 0 - m
    private int columns;  // 0 - n
    private double entries[][];

    /**
    * Matrix constructor that accepts the number of rows and columns of the matrix.
    * @param rows the number of rows of the matrix
    * @param columns the number of columns of the matrix
    */
    public Matrix( int rows, int columns ){
      this.rows = rows;
      this.columns = columns;
      entries = new double[ rows ][ columns ];
    }

    /**
    * Gets the number of rows
    * @return the number of rows, in <code>integer</code>, of the matrix.
    */
    public int getRows(){ return rows; }

    /**
    * Gets the number columns
    * @return the number of columns, in <code>integer</code>, of the matrix.
    */
    public int getColumns(){ return columns; }

    /**
    * Gets the value of the entry at the specified row and column index
    * @param row the index of the row in the matrix
    * @param column the index of the column in the matrix
    * @return the value of the entry at the specified row and column.
    * @throws IndexOutOfBoundsException in the case that rows and columns is not within the index range.
    */
    public double getEntry( int row, int column ) throws IndexOutOfBoundsException
    {
      return entries[ row ][ column ];
    }

    /**
    * Sets the value of the entry at the specified row and column index
    * @param row the index of the row in the matrix
    * @param column the index of the column in the matrix
    * @param entry the new value of the entry set at the specified row and column
    * @throws IndexOutOfBoundsException in the case that rows and columns is not within the index range.
    */
    public void setEntry( int row, int column, double entry ) throws IndexOutOfBoundsException
    {
      entries[ row ][ column ] = entry;
    }

    /**
    * Assigns randomly generated numbers to all entries of the matrix.
    */
    public void generateRandomEntries(){
        LocalTime now = LocalTime.now(); // the value of now.toNanoDay() will be use as a seed for the random generator
        Random random = new Random( now.toNanoOfDay() );

        for( int i = 0; i < rows; i++ )
          for( int j = 0; j < columns; j++ )
              entries[ i ][ j ] = 99 * random.nextDouble();
    }

    /**
    * Compares the matrix object with another matrix if they have the same number of rows and columns.
    * @param matrix another object of the Matrix class.
    * @return true if the size of the matrices are equal and false otherwise.
    */
    public boolean sizeEquals( Matrix matrix ){
        if( rows == matrix.getRows() &&
            columns == matrix.getColumns() )
            return true;
        else
            return false;
    }

    /**
    * Compares the matrix object with another matrix if they have the same value for every entries.
    * @param matrix another object of the Matrix class.
    * @return true if the entries of the matrices are equal and false otherwise.
    */
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

    /**
    * Returns a String value of the entries of the matrix arranged in rectangular format.
    * @return a rectangular representation of the entries in String.
    */
    public String toString(){
        String rectForm = new String();

        for( int i = 0; i < rows; i++){
            rectForm += "[ ";
          for( int j = 0; j < columns; j++ ){
            rectForm += String.format("%8.2f ", entries[ i ][ j ] );
          }
            rectForm += " ]\n";
        }

        return rectForm;
    }

    /**
    * Compares the matrix object with another matrix if their size and entries are equal.
    * @param matrix another object of the Matrix class.
    * @return true if the matrices are equal in size and entries and false otherwise.
    */
    public boolean equals( Matrix matrix ){
        return sizeEquals( matrix ) && entriesEquals( matrix );
    }

    /**
    * Adds the value of the entries of another matrix to the value of the entries of the matrix object. The matrices should be
    * of the same size. If A = a[ row ][ column ] and B = b[ row ][ column ], this takes the form A + B = a[ row ][ column ] + b[ row ][ column ].
    * Note that addition is not defined for matrices of different sizes.
    * @param matrix another object of the Matrix class.
    * @throws MatrixSizeMismatchException in the case that the matrices is of different sizes.
    */
    public void add( Matrix matrix ) throws MatrixSizeMismatchException {
        if( this.sizeEquals( matrix) ){
          for( int i = 0; i < rows; i++ )
            for( int j = 0; j < columns; j++ )
              entries[ i ][ j ] += matrix.getEntry( i, j );
        } else {
          throw new MatrixSizeMismatchException();
        }
    }

    /**
    * Subtracts the value of the entries of another matrix to the value of the entries of the matrix object. The matrices should be
    * of the same size. If A = a[ row ][ column ] and B = b[ row ][ column ], this takes the form A - B = a[ row ][ column ] - b[ row ][ column ].
    * Note that subtraction is not defined for matrices of different sizes.
    * @param matrix another object of the Matrix class.
    * @throws MatrixSizeMismatchException in the case that the matrices is of different sizes.
    */
    public void subtract( Matrix matrix ) throws MatrixSizeMismatchException {
        if( this.sizeEquals( matrix) ){
          for( int i = 0; i < rows; i++ )
            for( int j = 0; j < columns; j++ )
              entries[ i ][ j ] -= matrix.getEntry( i, j );
        } else {
          throw new MatrixSizeMismatchException();
        }
    }


    /**
    * Multiply every entries of the matrix object with a scalar value. More generally, if A is any matrix and k is any number,
    * the scalar multiple kA is the matrix obtained from A by multiplying each entry of A by k.
    * @param scalarValue is the value multiplied to each entry of matrix object.
    */
    public void scalarProduct( double scalarValue){
        for( int i = 0; i < rows; i++ )
          for( int j = 0; j < columns; j++ )
            entries[ i ][ j ] *= scalarValue;
    }

    /**
    * Transpose the entries of the matrix object. If A is an m x n matrix, the transpose of A, is the n x m matrix whose
    * rows are just the columns of A in the same order.
    */
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

    /**
    * Copies the corresponding entries of another matrix object to the entries of the calling matrix object.
    * @param matrix another object of the Matrix class.
    * @throws MatrixSizeMismatchException in the case that the matrices is of different sizes.
    */
    public void copy( Matrix matrix ) throws MatrixSizeMismatchException{
        if( sizeEquals( matrix ) ){
          for( int i = 0; i < rows; i++ )
            for( int j = 0; j < columns; j++ )
              entries[ i ][ j ] = matrix.getEntry( i, j );
        } else {
          throw new MatrixSizeMismatchException();
        }
    }

    /**
    * Turns the matrix object to its negative matrix. The negative of an m x n matrix A ( written -A ) is defined to be the
    * m x n matrix obtained by multiplying each entry of A by -1. If A = a[ row ][ column ], this becomes -A = -a[ row ][ column ].
    */
    public void negative(){
      for( int i = 0; i < rows; i++ )
        for( int j = 0; j < columns; j++ )
          entries[ i ][ j ] *= -1;
    }

    /**
    * Switches the values of row1 and row2
    * @param row1 index of the first row to switch with row2
    * @param row2 index of the second row to switch with row1
    */
    public void switchRows( int row1, int row2 ) throws IndexOutOfBoundsException{
      double[] temp;
      temp = entries[ row1 ];
      entries[ row1 ] = entries[ row2 ];
      entries[ row2 ] = temp;
    }

    /**
    * Determines if the size of the row and column of the matrix object is equal.
    * @return true if the size of the row and column of the matrix object is equal, and false otherwise.
    */
    public boolean isSquare(){
        return rows == columns;
    }

    /**
    * Creates a matrix object of size 1 x n. A 1 x n matrix is called a row matrix.
    * @param columns is the number of columns in the row matrix.
    * @return a matrix object of size 1 x n.
    */
    public static Matrix createRowMatrix( int columns ){ return new Matrix( 1, columns ); }

    /**
    * Creates a matrix object of size n x 1. An n x 1 matrix is called a column matrix.
    * @param rows is the number of rows in the row matrix.
    * @return a matrix object of size n x 1.
    */
    public static Matrix createColumnMatrix( int rows ){ return new Matrix( rows, 1 ); }

    /**
    * Creates a matrix object of size n x n. An n x n matrix is called a square matrix.
    * @param n is the the number of rows and columns of the square matrix.
    * @return a matrix object of size n x n.
    */
    public static Matrix createSquareMatrix( int n ){ return new Matrix( n, n ); }
}
