

public class MatrixOperationDemo{

    public static void main( String[] args ){

      AugmentedMatrix matrix = new AugmentedMatrix( 2, 2 );

      matrix.setCoefficientAt( 0, 0, 1.0 );
      matrix.setCoefficientAt( 0, 1, 2.0 );
      matrix.setCoefficientAt( 1, 0, 2.0 );
      matrix.setCoefficientAt( 1, 1, 1.0 );
      matrix.setConstantAt( 0, -2.0 );
      matrix.setConstantAt( 1, 7.0 );

      System.out.println( "Original value of matrix:" );
      System.out.println( matrix.toString() );
      System.out.println( "\nStep 1: Subtract twice the first row from the second." );
      matrix.addMultipleTo( 0, 1, -2.0 );
      System.out.println( matrix.toString() );
      System.out.println( "Step 2: Multiply second row by 1/3." );
      matrix.multiplyRowBy( 1, - ( 1.0 / 3.0 ) );
      System.out.println( matrix.toString() );
      System.out.println( "Step 3: Subtract twice the second row from the first row." );
      matrix.addMultipleTo( 1, 0, -2.0 );
      System.out.println( matrix.toString() );

      System.exit( 0 );
    }

}
