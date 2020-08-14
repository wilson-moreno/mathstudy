package edu.thinkbox.math.matrix.util;

import edu.thinkbox.math.matrix.Matrix;
import edu.thinkbox.math.matrix.AugmentedMatrix;
import java.math.BigInteger;

public class MatrixDemo{

    public static void main( String[] args ){
        System.out.println( "Welcome to the Matrix driver program!\n" );
        System.out.println( "This program will demonstrate the elementary and advanced matrix operation implementation in Java." );
        System.out.println( "The primary class that represents a matrix is named Matrix.\n\n" );


        Matrix matrix = Matrix.createSquareMatrix(  );

        matrix.generateRandomEntries();
        System.out.printf("Determinants: %8.2f\n", matrix.determinant2() );

      }



}
