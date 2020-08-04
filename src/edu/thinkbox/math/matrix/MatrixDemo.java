package edu.thinkbox.math.matrix;

public class MatrixDemo{

    public static void main( String[] args ){
        System.out.println( "Welcome to the Matrix driver program!" );
        System.out.println( "This program will demonstrate the elementary matrix operation implementation in a java class." );
        System.out.println( "The primary class that represents a matrix is named Matrix.\n\n" );
        System.out.println( "Create two matrix object named A and B. \n");
        Matrix a = Matrix.createSquareMatrix( 5 );
        Matrix b = Matrix.createSquareMatrix( 5 );
        System.out.println( "Matrix A: \n" + a );
        System.out.println( "Matrix B: \n" + b );
        a.generateRandomEntries();
        b.generateRandomEntries();
        System.out.println( "Generate random entries for Matrix A and B.\n");
        System.out.println( "Matrix A: \n" + a );
        System.out.println( "Matrix B: \n" + b );
        System.out.println( "Adding Matrix B to A.\n");
        a.add( b );
        System.out.println( "Matrix A: \n" + a );
        System.out.println( "Matrix B: \n" + b );
    }

}
