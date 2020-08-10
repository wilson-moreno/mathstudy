package edu.thinkbox.math.matrix;

import java.util.Scanner;
import java.util.Random;
import java.time.LocalTime;

/**
* This class represents a <strong>Matrix</strong>, which is a set of numbers arranged in rows and columns so as to form
* a rectangular array. The numbers are called elements, or entries, of the matrix. Matrices have wide applications
* in engineering, physics, economics, and statistics as well as in various branches of mathematics.
* @author Wilson S. Moreno
*/
public class Matrix{
    private double entries[][];

    /**
    * Matrix constructor that accepts the number of rows and columns of the matrix.
    * @param rows the number of rows of the matrix
    * @param columns the number of columns of the matrix
    */
    public Matrix( int rows, int columns ){
      entries = new double[ rows ][ columns ];
    }

    /**
    * Gets the number of rows
    * @return the number of rows, in <code>integer</code>, of the matrix.
    */
    public int getRows(){ return entries.length; }

    /**
    * Gets the number columns
    * @return the number of columns, in <code>integer</code>, of the matrix.
    */
    public int getColumns(){ return entries[0].length; }

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
    * Set all the values of the entries at once of a specified row.
    * @param row the index of the row where the values will be set.
    * @param entries a String of values that will be set in the entries of the specified row.
    */
    public void setRowEntries( int row, String entries ){
        Scanner scanner = new Scanner( entries );

        int column = 0;
        while( scanner.hasNext() && column < getColumns() ){
          setEntry( row, column++, scanner.nextDouble() );
        }
    }

    /**
    * Set all the values of the entries at once of a specified column
    * @param column the index of the column where the values will be set.
    * @param entries a String of values that will be set in the entries of the specified column.
    */
    public void setColumnEntries( int column, String entries ){
      Scanner scanner = new Scanner( entries );

      int row = 0;
      while( scanner.hasNext() && column < getColumns() ){
        setEntry( row++, column, scanner.nextDouble() );
      }
    }

    public double determinant() throws NonSquareMatrixException {
        if( !isSquare() ) throw new NonSquareMatrixException();
        double determinant = 0.0;

        if( getRows() == 1 )  {
            determinant = entries[ 0 ][ 0 ];
        } else if( getRows() == 2 ) {
            determinant = ( entries[ 0 ][ 0 ] * entries[ 1 ][ 1 ] ) -
                          ( entries[ 0 ][ 1 ] * entries[ 1 ][ 0 ] );
        } else if( getRows() > 2 ){
            // Cofactor expansion: det( A ) = a11C11 + a12C12 + ... + a1nC1n
            for( int j = 0; j < getColumns(); j++ )
              determinant += entries[ 0 ][ j ] * cofactor( 0, j );
        }

        return determinant;
    }

    private double cofactor( int rowIndex, int columnIndex ) throws NonSquareMatrixException {
        if( !isSquare() ) throw new NonSquareMatrixException();

        double sign = Math.pow( -1, rowIndex + columnIndex );
        Matrix submatrix = duplicate();
        return sign * submatrix.removeRow( rowIndex ).removeColumn( columnIndex ).determinant();
    }

    public Matrix adjugate() throws NonSquareMatrixException {
        if ( !isSquare() ) throw new NonSquareMatrixException();

        Matrix cofactorMatrix = new Matrix( getRows(), getColumns() );

        for( int i = 0; i < getRows(); i++ )
         for( int j = 0; j < getColumns(); j++ )
            cofactorMatrix.setEntry( i, j, cofactor( i, j ) );

        return cofactorMatrix.transpose();
    }

    public Matrix removeRow( int row ){
        //if( getRows() > 1 ){
        Matrix submatrix = new Matrix( getRows() - 1, getColumns() );

        for( int i = 0, k = 0; i < getRows(); i++ ){
          if( row == i ) continue;
          for( int j = 0; j < getColumns(); j++ )
            submatrix.setEntry( k, j, entries[ i ][ j ] );
          k++;
        }

        return submatrix;
    }

    public Matrix removeColumn( int column ){
        //if( getColumns() > 1 )
        Matrix submatrix = new Matrix( getRows(), getColumns() - 1 );

        for( int i = 0; i < getRows(); i++ ){
          for( int j = 0, k = 0; j < getColumns(); j++ ){
            if( j == column ) continue;
            submatrix.setEntry( i, k++, entries[ i ][ j ] );
          }
        }

        return submatrix;
    }


    public Matrix inverse() throws NonInvertibleMatrixException {
        if( determinant() == 0.0 ) throw new NonInvertibleMatrixException();
        return adjugate().scalarProduct( 1.0 / determinant() );
    }


    public Matrix reducedRowEchelon( Matrix matrix ) throws MatrixSizeMismatchException {
        if( getRows() != matrix.getRows() ) throw new MatrixSizeMismatchException();

        Matrix echelonForm = duplicate();

        reducedRowEchelon( 0, 0, echelonForm, matrix );

        return echelonForm;
    }

    private void reducedRowEchelon( int pivotRow, int pivotColumn, Matrix echelonForm, Matrix matrix){
        if( ( pivotRow < 0 || pivotRow >= getRows() ) ||
            ( pivotColumn < 0 || pivotColumn >= getColumns() ) ) return;

        // Find index of max value in column vector starting from pivot row
        int max = pivotRow;
        for( int i = pivotRow + 1; i < getRows(); i++ )
            if( Math.abs( echelonForm.getEntry( i, pivotColumn ) ) > Math.abs( echelonForm.getEntry( max, pivotColumn ) ) ) max = i;

        if( max != pivotRow ){
          echelonForm.switchRows( pivotRow, max );
          matrix.switchRows( pivotRow, max );
        }

        if( echelonForm.getEntry( pivotRow, pivotColumn ) == 0.0 ){
          reducedRowEchelon( pivotRow, pivotColumn + 1, echelonForm, matrix );
        } else {
          double nonzero = 0.0;

          nonzero = ( 1.0 / echelonForm.getEntry( pivotRow, pivotColumn ) );
          echelonForm.scale( pivotRow,  nonzero );
          matrix.scale( pivotRow, nonzero );

          for( int i = pivotRow + 1; i < getRows(); i++ ){
            if( echelonForm.getEntry( i, pivotColumn ) != 0.0 ){
              nonzero = echelonForm.getEntry( i, pivotColumn ) * -1.0;
              echelonForm.replace( i, pivotRow, nonzero );
              matrix.replace( i, pivotRow, nonzero );
            }
          }

          for( int j = pivotRow - 1; j >= 0; j--){
            if( echelonForm.getEntry( j, pivotColumn ) != 0.0 ){
              nonzero = echelonForm.getEntry( j, pivotColumn ) * -1.0;
              echelonForm.replace( j, pivotRow, nonzero );
              matrix.replace( j, pivotRow, nonzero );
            }
          }

          reducedRowEchelon( pivotRow + 1, pivotColumn + 1, echelonForm, matrix );
        }
    }

    public Matrix rowEchelon( Matrix matrix ) throws MatrixSizeMismatchException {
        if( getRows() != matrix.getRows() ) throw new MatrixSizeMismatchException();

        Matrix echelonForm = duplicate();

        rowEchelon( 0, 0, echelonForm, matrix );

        return echelonForm;
    }

    private void rowEchelon( int pivotRow, int pivotColumn, Matrix echelonForm, Matrix matrix){
        if( ( pivotRow < 0 || pivotRow >= getRows() ) ||
            ( pivotColumn < 0 || pivotColumn >= getColumns() ) ) return;

        // Find index of max value in column vector starting from pivot row
        int max = pivotRow;
        for( int i = pivotRow + 1; i < getRows(); i++ )
            if( Math.abs( echelonForm.getEntry(  i, pivotColumn ) ) > Math.abs( echelonForm.getEntry( max, pivotColumn ) ) ) max = i;

        if( max != pivotRow ){
          echelonForm.switchRows( pivotRow, max );
          matrix.switchRows( pivotRow, max );
        }

        if( echelonForm.getEntry( pivotRow, pivotColumn ) == 0.0 ){
          rowEchelon( pivotRow, pivotColumn + 1, echelonForm, matrix );
        } else {
          double nonzero = 0.0;

          nonzero = ( 1.0 / echelonForm.getEntry( pivotRow, pivotColumn ) );
          echelonForm.scale( pivotRow,  nonzero );
          matrix.scale( pivotRow, nonzero );


          for( int i = pivotRow + 1; i < getRows(); i++ ){
            if( echelonForm.getEntry( i, pivotColumn ) != 0.0 ){
              nonzero = echelonForm.getEntry( i, pivotColumn ) * -1.0;
              echelonForm.replace( i, pivotRow, nonzero );
              matrix.replace( i, pivotRow, nonzero );
            }
          }

          rowEchelon( pivotRow + 1, pivotColumn + 1, echelonForm, matrix );
        }
    }

    public void scale( int row, double nonzero ) throws ZeroValueException {
        if( nonzero != 0.0 )
          for( int j = 0; j < getColumns(); j++ )
            entries[ row ][ j ] = entries[ row ][ j ] * nonzero;
        else
          throw new ZeroValueException();
    }

    public void replace( int row2, int row1, double nonzero ) throws ZeroValueException {
      if( nonzero != 0.0 )
        for( int j = 0; j < getColumns(); j++ )
          entries[ row2 ][ j ] += entries[ row1 ][ j ] * nonzero;
      else
        throw new ZeroValueException();
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
    * Assigns randomly generated numbers to all entries of the matrix.
    */
    public void generateRandomEntries(){
        LocalTime now = LocalTime.now(); // the value of now.toNanoDay() will be use as a seed for the random generator
        Random random = new Random( now.toNanoOfDay() );

        for( int i = 0; i < getRows(); i++ )
          for( int j = 0; j < getColumns(); j++ )
              entries[ i ][ j ] = ( double ) ( -10 + random.nextInt( 20 ) );
    }

    /**
    * Compares the matrix object with another matrix if they have the same number of rows and columns.
    * @param matrix another object of the Matrix class.
    * @return true if the size of the matrices are equal and false otherwise.
    */
    public boolean sizeEquals( Matrix matrix ){
        if( getRows() == matrix.getRows() &&
            getColumns() == matrix.getColumns() )
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
            for( int i = 0; i < getRows(); i++ )
              for( int j = 0; j < getColumns(); j++ )
                if( entries[ i ][ j ] != matrix.getEntry( i, j ) ) return false;

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

        rectForm = "        ";
        for( int s = 1; s <= getColumns(); s++ )
            rectForm += String.format("%8s ", "x" + s );

        rectForm += "\n";

        for( int i = 0; i < getRows(); i++){
            rectForm += String.format("%4d: [ ", i + 1 );
          for( int j = 0; j < getColumns(); j++ ){
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
    public Matrix add( Matrix matrix ) throws MatrixSizeMismatchException {
        if( this.sizeEquals( matrix) ){
          Matrix sum = new Matrix( getRows(), getColumns() );

          for( int i = 0; i < getRows(); i++ )
            for( int j = 0; j < getColumns(); j++ )
              sum.setEntry( i, j, entries[ i ][ j ] + matrix.getEntry( i, j ) );

          return sum;
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
    public Matrix subtract( Matrix matrix ) throws MatrixSizeMismatchException {
        if( this.sizeEquals( matrix) ){
          Matrix difference = new Matrix( getRows(), getColumns() );

          for( int i = 0; i < getRows(); i++ )
            for( int j = 0; j < getColumns(); j++ )
              difference.setEntry( i, j, entries[ i ][ j ] - matrix.getEntry( i, j ) );

          return difference;
        } else {
          throw new MatrixSizeMismatchException();
        }
    }


    /**
    * Multiply every entries of the matrix object with a scalar value. More generally, if A is any matrix and k is any number,
    * the scalar multiple kA is the matrix obtained from A by multiplying each entry of A by k.
    * @param scalarValue is the value multiplied to each entry of matrix object.
    */
    public Matrix scalarProduct( double scalarValue){
        Matrix product = duplicate();

        for( int i = 0; i < getRows(); i++ )
          for( int j = 0; j < getColumns(); j++ )
            product.setEntry( i, j, entries[ i ][ j ] * scalarValue );

        return product;
    }

    public Matrix multiply( Matrix matrix ) throws MatrixSizeMismatchException{
        // Matrix-Vector Product: Ax = b
        // A is an m x n matrix
        // x is an n-vector
        // b is an m-vector
        if( getColumns() == matrix.getRows() ){
            Matrix product = new Matrix( getRows(), matrix.getColumns() );
            Matrix columnVector;
            for( int j = 0; j < matrix.getColumns(); j++ ){
                columnVector = vectorProduct( matrix.getColumnVector( j ) );
                product.setColumnVector( j, columnVector );
            }
            return product;
        } else {
          throw new MatrixSizeMismatchException();
        }
    }

    private Matrix vectorProduct( Matrix vector ) throws MatrixSizeMismatchException {
      if( getColumns() == vector.getRows() && vector.isVector() ){
        Matrix product = new Matrix( getRows(), vector.getColumns() );
        Matrix columnVector;
        for( int i = 0; i < getColumns(); i++ ){
          columnVector = getColumnVector( i );
          product = product.add( columnVector.scalarProduct( vector.getEntry( i, 0 ) ) );
        }
        return product;
      } else {
        throw new MatrixSizeMismatchException();
      }
    }

    public Matrix dotProduct( Matrix matrix ) throws MatrixSizeMismatchException {
        if(  getColumns() == matrix.getRows() ){
          Matrix product = new Matrix( getRows(), matrix.getColumns() );
          for( int i = 0; i < getRows(); i++ ){
            for( int j = 0; j < matrix.getColumns(); j++ ){
              double sum = 0.0;
              for( int k = 0; k < getColumns(); k++ ){
                sum += entries[ i ][ k ] * matrix.getEntry( k, j );
              }
              product.setEntry( i, j, sum );
            }
          }

          return product;
        } else {
          throw new MatrixSizeMismatchException();
        }
    }

    /**
    * Transpose the entries of the matrix object. If A is an m x n matrix, the transpose of A, is the n x m matrix whose
    * rows are just the columns of A in the same order.
    */
    public Matrix transpose(){
        Matrix transposedMatrix = new Matrix( getColumns(), getRows() );

        for( int i = 0; i < getRows(); i++ )
          for( int j = 0; j < getColumns(); j++ )
            transposedMatrix.setEntry( j, i, entries[ i ][ j ] );

        return transposedMatrix;
    }


    public Matrix power( int r ) throws NonSquareMatrixException {
        if( isSquare() ){
          Matrix matrixPower = createIdentityMatrix( getRows() );

          for( int s = 0; s < r; s++)
             matrixPower = matrixPower.multiply( this );

          return matrixPower;
        } else {
          throw new NonSquareMatrixException();
        }
    }

    /**
    * Copies the corresponding entries of another matrix object to the entries of the calling matrix object.
    * @param matrix another object of the Matrix class.
    * @throws MatrixSizeMismatchException in the case that the matrices is of different sizes.
    */
    public void copyEntries( Matrix matrix ) throws MatrixSizeMismatchException{
        if( sizeEquals( matrix ) ){
          for( int i = 0; i < getRows(); i++ )
            for( int j = 0; j < getColumns(); j++ )
              entries[ i ][ j ] = matrix.getEntry( i, j );
        } else {
          throw new MatrixSizeMismatchException();
        }
    }

    public Matrix duplicate(){
        Matrix duplicate = new Matrix( getRows(), getColumns() );

        for( int i = 0; i < getRows(); i++ )
          for( int j = 0; j < getColumns(); j++ )
            duplicate.setEntry( i, j, entries[ i ][ j ] );

        return duplicate;
    }

    /**
    * Turns the matrix object to its negative matrix. The negative of an m x n matrix A ( written -A ) is defined to be the
    * m x n matrix obtained by multiplying each entry of A by -1. If A = a[ row ][ column ], this becomes -A = -a[ row ][ column ].
    */
    public Matrix negative(){
      Matrix negativeMatrix = new Matrix( getRows(), getColumns() );

      for( int i = 0; i < getRows(); i++ )
        for( int j = 0; j < getColumns(); j++ )
          negativeMatrix.setEntry( i, j, entries[ i ][ j ] * -1);

      return negativeMatrix;
    }


    /**
    * Determines if the size of the row and column of the matrix object is equal.
    * @return true if the size of the row and column of the matrix object is equal, and false otherwise.
    */
    public boolean isSquare(){
        return getRows() == getColumns();
    }

    public Matrix getColumnVector( int column ){
        Matrix vector = createColumnMatrix( getRows() );

        for( int i = 0; i < getRows(); i++ )
            vector.setEntry( i, 0, entries[ i ][ column ] );

        return vector;
    }

    private void setColumnVector( int column, Matrix vector ) throws MatrixSizeMismatchException {
        if( vector.isColumnVector() && getRows() == vector.getRows() ){
          for( int i = 0; i < getRows(); i++ ){
            entries[ i ][ column ] = vector.getEntry( i, 0 );
          }
        } else {
          throw new MatrixSizeMismatchException();
        }
    }

    /**
    * Returns a row vector with entries from the row index.
    * @param row the index of the row where the entries will be copied.
    * @return Matrix object with 1 row and contains the entries of the matrix from row index.
    */
    public Matrix getRowVector( int row ){
        Matrix vector = createRowMatrix( getColumns() );

        for( int j = 0; j < getColumns(); j++ )
            vector.setEntry( 0, j, entries[ row ][ j ] );

        return vector;
    }

    /**
    * Determines whether the matrix is either a row or column vector.
    * @return true if the matrix is a row or column vector, and false otherwise.
    */
    public boolean isVector(){
        return isRowVector() || isColumnVector();
    }

    /**
    * Determines if the matrix is a row vector.
    * @return true if the matrix is a row vector, and false otherwise.
    */
    public boolean isRowVector(){
        return getRows() == 1;
    }

    /**
    * Determines if the matrix is a column vector.
    * @return true if the matrix is a column vector, and false otherwise.
    */
    public boolean isColumnVector(){
        return getColumns() ==  1;
    }

    /**
    * Determines whether the matrix is an identity matrix.
    * @return true if the matrix is an identity matrix, and false otherwise.
    */
    public boolean isIdentity(){
        if( !isSquare() ) return false;

        for( int i = 0; i < getRows(); i++ )
          for( int j = 0; j < getColumns(); j++ ){
             if( i == j && entries[ i ][ j ] != 1.0 ) return false;
             else if( i != j && entries[ i ][ j ] != 0.0 ) return false;
          }

        return true;
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

    /**
    * Creates a identity matrix object of size n x n. An identity matrix is a square matrix with 1's on the main
    * diagonal (upper left to lower right), and zeros elsewhere.
    * @param n is the number of rows and columns of the identity matrix.
    * @return a identity matrix object of size n x n.
    */
    public static Matrix createIdentityMatrix( int n ){
        Matrix identityMatrix = new Matrix( n, n );

        for( int i = 0; i < identityMatrix.getRows(); i++ )
          identityMatrix.setEntry( i, i, 1.0 );

        return identityMatrix;
    }
}
