package edu.thinkbox.math.matrix;

public class NonInvertibleMatrixException extends RuntimeException{

      public NonInvertibleMatrixException(){ this( "Non invertible matrix." ); }

      public NonInvertibleMatrixException( String message ){
          super( message );
      }

}
