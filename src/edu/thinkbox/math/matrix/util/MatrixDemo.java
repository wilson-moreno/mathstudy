package edu.thinkbox.math.matrix.util;

import edu.thinkbox.math.matrix.Matrix;
import edu.thinkbox.math.matrix.AugmentedMatrix;

public class MatrixDemo{

    public static void main( String[] args ){
        System.out.println( "Welcome to the Matrix driver program!" );
        System.out.println( "This program will demonstrate the elementary matrix operation implementation in Java." );
        System.out.println( "The primary class that represents a matrix is named Matrix.\n\n" );
        System.out.println( "Create two matrix object named A and B. \n");
        Matrix a = Matrix.createSquareMatrix( 5 );
        Matrix b = Matrix.createSquareMatrix( 5 );
        System.out.println( "Matrix A: \n" + a );
        System.out.println( "Matrix B: \n" + b );
        System.out.println( "Generate random entries for Matrix A and B.\n");
        a.generateRandomEntries();
        b.generateRandomEntries();
        b.scalarProduct( 2.0 );
        System.out.println( "Matrix A: \n" + a );
        System.out.println( "Matrix B: \n" + b );
        System.out.println( "Adding Matrix B to A.\n");
        a.add( b );
        System.out.println( "Matrix A: \n" + a );
        System.out.println( "Matrix B: \n" + b );
        System.out.println( "Sub-test: Testing Augmented Matrix.");
        System.out.println( "Create augmented matrix object named C. \n");
        AugmentedMatrix c = new AugmentedMatrix( 2, 2 );
        System.out.println( "Matrix C: \n" + c );
        System.out.println( "Set coefficients and constants of C. \n");
        c.setCoefficient( 0, 0, 1.0 );
        c.setCoefficient( 0, 1, 2.0 );
        c.setCoefficient( 1, 0, 2.0 );
        c.setCoefficient( 1, 1, 1.0 );
        c.setConstant( 0, -2.0 );
        c.setConstant( 1, 7.0 );
        System.out.println( "Matrix C: \n" + c );
        System.out.println( "Multiply equation 1 with -2 then add to equation 2. \n");
        c.addMultiple( 0, 1, -2.0 );
        System.out.println( "Matrix C: \n" + c );
        System.out.println( "Multiply 2 equations with -1/3. \n");
        c.multiply( 1, - ( 1.0 / 3.0 ));
        System.out.println( "Matrix C: \n" + c );
        System.out.println( "Multiply equation 2 with -2 then add to equation 1. \n");
        c.addMultiple( 1, 0, -2.0 );
        System.out.println( "Matrix C: \n" + c );
        System.out.println( "Create augmented matrix object named D. \n");
        AugmentedMatrix d = new AugmentedMatrix( 5, 5 );
        System.out.println( "Matrix D: \n" + d );
        System.out.println( "Generate random entries for Matrix D.\n");
        d.generateRandomValues();
        System.out.println( "Matrix D: \n" + d );
        System.out.println( "Apply gaussian elimination to matrix D.\n");
        d.gaussianElimination();
        System.out.println( "Matrix D: \n" + d );
    }

}
