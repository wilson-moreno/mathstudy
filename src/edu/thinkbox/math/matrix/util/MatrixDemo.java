package edu.thinkbox.math.matrix.util;

import edu.thinkbox.math.matrix.Matrix;
import edu.thinkbox.math.matrix.AugmentedMatrix;
import java.math.BigInteger;

public class MatrixDemo{

    public static void main( String[] args ){
        System.out.println( "Welcome to the Matrix driver program!\n" );
        System.out.println( "This program will demonstrate the elementary and advanced matrix operation implementation in Java." );
        System.out.println( "The primary class that represents a matrix is named Matrix.\n\n" );

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
        coefficients.setRowLabels( "Work 1;Work 2" );
        constants.setColumnEntries( 0, " 24.60 23.90 " );
        constants.setColumnLabels( "Earnings ($)" );
        constants.setRowLabels( "Work 1;Work 2" );
        System.out.println( "John & Joe working hours: \n\n" + coefficients );
        System.out.println( "John & Joe earnings: \n\n" + constants );
        Matrix rates = constants.duplicate();
        rates.setColumnLabels( "Rates ($)" );
        rates.setRowLabels("John;Joe");
        coefficients.reducedRowEchelon( rates );
        System.out.println( "John & Joe rates: \n\n" + rates );
      }
}
