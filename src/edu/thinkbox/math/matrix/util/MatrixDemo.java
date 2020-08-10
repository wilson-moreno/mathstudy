package edu.thinkbox.math.matrix.util;

import edu.thinkbox.math.matrix.Matrix;
import edu.thinkbox.math.matrix.AugmentedMatrix;
import java.math.BigInteger;

public class MatrixDemo{

    public static void main( String[] args ){
        System.out.println( "Welcome to the Matrix driver program!" );
        System.out.println( "This program will demonstrate the elementary and advanced matrix operation implementation in Java." );
        System.out.println( "The primary class that represents a matrix is named Matrix.\n\n" );
        /*
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
        b.rowEchelon( a );
        System.out.println( "Matrix B row echelon: \n" + b );
        System.out.println( "Matrix A affected by B row echelon: \n" + a );
        System.out.println( "Get column vector 1 from matrix A: \n" + a.getColumnVector( 0 ) );
        System.out.println( "Get row vector 1 from matrix A: \n" + a.getRowVector( 0 ) );
        System.out.println( "Adding Matrix B to A.\n");
        a.add( b );
        System.out.println( "Matrix A: \n" + a );
        System.out.println( "Matrix B: \n" + b );
        System.out.println( "Sub-test: Testing Augmented Matrix.");
        System.out.println( "Create augmented matrix object named C. \n");
        AugmentedMatrix c = new AugmentedMatrix( 2, 2 );
        System.out.println( "Matrix C: \n" + c );
        System.out.println( "Set coefficients and constants of C. \n");
        c.setRowCoefficients( 0, "1.0 2.0");
        c.setRowCoefficients( 1, "2.0 1.0");
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
        d.setRowCoefficients( 0, "  1 -2  1 ");
        d.setRowCoefficients( 1, "  0  2 -8 ");
        d.setRowCoefficients( 2, " -4  5  9 ");
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
        e.setRowCoefficients( 0, "  2  0 -6 " );
        e.setRowCoefficients( 1, "  0  1  2 " );
        e.setRowCoefficients( 2, "  3  6 -2 " );
        e.setConstants( " -8  3 -4 " );
        System.out.println( "Matrix E: \n" + e );
        System.out.println( "System of equations of matrix E: \n\n" + e.systemOfEquations() );
        e.gaussianElimination();
        System.out.println( "Gaussian elimination applied to matrix E: \n" + e );
        System.out.println( "System of equations of matrix E: \n\n" + e.systemOfEquations() );
        System.out.println( "Creating a 3 x 4 matrix and 4-vector");
        Matrix f = new Matrix( 3, 4 );
        Matrix g = new Matrix( 4, 2 );
        f.setRowEntries( 0, "  2 -1  3  5 " );
        f.setRowEntries( 1, "  0  2 -3  1 " );
        f.setRowEntries( 2, " -3  4  1  2 " );
        g.setRowEntries( 0, "  2  2 " );
        g.setRowEntries( 1, "  1  3 " );
        g.setRowEntries( 2, "  0  1 " );
        g.setRowEntries( 3, " -2  0 " );
        Matrix i = new Matrix( 4, 2 );
        i.generateRandomEntries();
        System.out.println( "A 3 x 4 Matrix: \n" + f );
        System.out.println( "A 4-vector: \n" + g );
        System.out.println( "Demonstrating matrix product: \n" + f.multiply( g ) );
        System.out.println( "Matrix i: \n" + i );
        System.out.println( "Matrix f: \n" + f );
        System.out.println( "Demonstrating dot product of f and g: \n" + f.dotProduct( g ) );
        Matrix h = Matrix.createIdentityMatrix( 4 );
        System.out.println( "Identity matrix: \n" + h );
        System.out.println( " Dot product of I and x: \n" + h.dotProduct( g ) );

        a = new Matrix( 3, 4 );
        b = new Matrix( 4, 3 );
        a.setRowEntries( 0, " 2  -1  3  5 " );
        a.setRowEntries( 1, " 0   2 -3  1 " );
        a.setRowEntries( 2, "-3   4  1  2 " );
        b.setColumnEntries( 0, " 2  1 0 -2 " );
        b.setColumnEntries( 1, " 2  1 0 -2 " );
        b.setColumnEntries( 2, " 2  1 0 -2 " );
        System.out.println( "Matrix A entries: \n" + a );
        System.out.println( "Matrix B entries: \n" + b );
        System.out.println( "Multiply A by B: \n" + a.multiply( b ) );
        System.out.println( "A dot product B: \n" + a.dotProduct( b ) );

        Matrix coeffs = Matrix.createSquareMatrix( 3 );
        Matrix constants = Matrix.createColumnMatrix( 3 );
        coeffs.setRowEntries( 0, " 0  1  1 ");
        coeffs.setRowEntries( 1, " 1  1  2 ");
        coeffs.setRowEntries( 2, " 1  3  4 ");
        constants.setColumnEntries( 0, " 5 13 23 " );
        System.out.println( "Vitamin Brands and units of each vitamin per pill: \n\n" + coeffs );
        System.out.println( "Required unit per vitamins: \n\n" + constants );
        coeffs.reducedRowEchelon( constants );
        System.out.println( "Vitamin Brands and units of each vitamin per pill: \n\n" + coeffs );
        System.out.println( "Solution: \n\n" + constants );
        */

        PROBLEM_11_19();
      }


      private static void PROBLEM_11_19(){
        String problemStatement = "19. Workmen John and Joe earn a total of $24.60 when John works 2 hours and Joe works 3 hours.\n" +
                                  "    If John works 3 hours and Joe 2 hours, they get $23.90. Find their hourly rates.\n\n" +
                                  "Solution: \n\n";

        System.out.println( problemStatement );

        Matrix coefficients = new Matrix( 2, 2 );
        Matrix constants = new Matrix( 2, 1 );
        coefficients.setRowEntries( 0, " 2  3 ");
        coefficients.setRowEntries( 1, " 3  2 ");
        coefficients.setColumnLabels( "John;Joe" );
        constants.setColumnEntries( 0, " 24.60 23.90 " );
        constants.setColumnLabels( "Earnings" );
        System.out.println( "John & Joe working hours: \n\n" + coefficients );
        System.out.println( "John & Joe earnings: \n\n" + constants );
        Matrix rates = constants.duplicate();
        rates.setColumnLabels( "Rates" );
        coefficients.reducedRowEchelon( rates );
        System.out.println( "John & Joe rates: \n\n" + rates );
      }
}
