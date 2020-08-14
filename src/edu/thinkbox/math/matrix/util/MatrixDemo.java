package edu.thinkbox.math.matrix.util;

import edu.thinkbox.math.matrix.Matrix;
import edu.thinkbox.math.matrix.AugmentedMatrix;
import java.math.BigInteger;

public class MatrixDemo{

    public static void main( String[] args ){
        System.out.println( "Welcome to the Matrix driver program!\n" );
        System.out.println( "This program will demonstrate the elementary and advanced matrix operation implementation in Java." );
        System.out.println( "The primary class that represents a matrix is named Matrix.\n\n" );


        Matrix matrix = Matrix.createSquareMatrix( 3 );
        matrix.generateRandomEntries();
        System.out.println( "Matrix: \n" + matrix );
        System.out.println( "Row echelon: \n" + matrix.rowEchelon() );
        System.out.println( "Determinant by cofactor expansion: \n" + matrix.determinant() );
        System.out.println( "Determinant by row echelon: \n" + matrix.determinant2() );
      }



}
