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
        c.setCoefficients( 0, "1.0 2.0");
        c.setCoefficients( 1, "2.0 1.0");
        c.setConstants( "-2.0 7.0" );
        System.out.println( "Matrix C: \n" + c );
        System.out.println( "Multiply equation 1 with -2 then add to equation 2. \n");
        c.replace( 1, 0, -2.0 );
        System.out.println( "Matrix C: \n" + c );
        System.out.println( "Multiply 2 equations with -1/3. \n");
        c.scale( 1, - ( 1.0 / 3.0 ));
        System.out.println( "Matrix C: \n" + c );
        System.out.println( "Multiply equation 2 with -2 then add to equation 1. \n");
        c.replace( 0, 1, -2.0 );
        System.out.println( "Matrix C: \n" + c );
        System.out.println( "Create augmented matrix object named D. \n");
        AugmentedMatrix d = new AugmentedMatrix( 3, 3 );
        System.out.println( "Matrix D: \n" + d );
        System.out.println( "Set entries for Matrix D.\n");
        d.setCoefficients( 0, "  1 -2  1 ");
        d.setCoefficients( 1, "  0  2 -8 ");
        d.setCoefficients( 2, " -4  5  9 ");
        d.setConstants(" 0 8 -9 ");
        System.out.println( "Matrix D: \n" + d );
        d.replace( 2, 0, 4.0 );
        System.out.println( "Replace row 3 with the sum of row 3 and row 1 times 4: \n" + d );
        d.scale( 1, ( 1.0 / 2.0 ));
        System.out.println( "Scale row 2 by 1/2: \n" + d );
        d.replace( 2, 1, 3.0 );
        System.out.println( "Replace row 3 with the sum of row 3 and row 1 times 3: \n" + d );
        AugmentedMatrix e = new AugmentedMatrix( 3, 3 );
        System.out.println( "Matrix E: \n" + e );
        System.out.println( "Set entries for Matrix E.\n");
        e.setCoefficients( 0, "  2  0 -6 " );
        e.setCoefficients( 1, "  0  1  2 " );
        e.setCoefficients( 2, "  3  6 -2 " );
        e.setConstants( " -8  3 -4 " );
        System.out.println( "Matrix E: \n" + e );
        System.out.println( "System of equations of matrix E: \n\n" + e.systemOfEquations() );
        e.gaussianElimination();
        System.out.println( "Gaussian elimination applied to matrix E: \n" + e );
        System.out.println( "System of equations of matrix E: \n\n" + e.systemOfEquations() );
    }

}