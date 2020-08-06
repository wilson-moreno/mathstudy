package edu.thinkbox.math.matrix;

import java.util.Scanner;

public class AugmentedMatrix{
        private Matrix   coefficients;  // coefficients of the system of equations.
        private Matrix   constants;     // constants of the system of equations.

        public AugmentedMatrix( int equations, int variables ){
            coefficients = new Matrix( equations, variables );
            constants = Matrix.createColumnMatrix( equations );
        }

        /**
        * Gets the number of equations in the augmented matrix.
        * @return the number of equations in <code>integer</code>.
        */
        public int getEquations(){ return coefficients.getRows(); }

        /**
        * Gets the number of variables in the augmented matrix.
        * @return the number of variables in <code>integer</code>.
        */
        public int getVariables(){ return coefficients.getColumns(); }


        public double getCoefficient( int equation, int variable ){ return coefficients.getEntry( equation, variable ); }
        public void setCoefficient( int equation, int variable, double coefficient ){
            coefficients.setEntry( equation, variable, coefficient );
        }
        public void setRowCoefficients( int equation, String coefficients ){
            this.coefficients.setRowEntries( equation, coefficients );
        }

        public double getConstant( int equation ){ return constants.getEntry( equation, 0 ); }
        public void setConstant( int equation, double constant ){
            constants.setEntry( equation, 0, constant );
        }
        public void setConstants( String constants ){
            Scanner input = new Scanner( constants );

            int equation = 0;

            while( input.hasNext() ){
                this.constants.setEntry( equation, 0, input.nextDouble() );
                equation++;
            }
        }

        public void generateRandomValues(){
            coefficients.generateRandomEntries();
            constants.generateRandomEntries();
        }

        public void interchange( int equation1, int equation2 ){
            coefficients.switchRows( equation1, equation2 );
            constants.switchRows( equation1, equation2 );
        }

        public void scale( int equation, double nonzero ) throws ZeroValueException {
            if( nonzero == 0.0 ) throw new ZeroValueException();
            for( int variable = 0; variable < coefficients.getColumns(); variable++ ){
              coefficients.setEntry( equation, variable, coefficients.getEntry( equation, variable ) * nonzero );
            }
            constants.setEntry( equation, 0, constants.getEntry( equation, 0 ) * nonzero );
        }

        public void replace( int equation2, int equation1, double multiple ){
            double sum = 0;

            for( int variable = 0; variable < coefficients.getColumns(); variable++ ){
               sum = coefficients.getEntry( equation2, variable ) +
                     ( coefficients.getEntry( equation1, variable ) * multiple);
              coefficients.setEntry( equation2, variable, sum );
            }

            sum = constants.getEntry( equation2, 0 ) + ( constants.getEntry( equation1, 0) * multiple );
            constants.setEntry( equation2, 0,  sum );
        }

        public void gaussianElimination(){
            rowEchelon( this, 0, 0, getEquations() , getVariables() );
        }


        private void rowEchelon( AugmentedMatrix matrix, int pivotRow, int pivotColumn, int rows, int columns ){

            // Visual representation of the matrix when rowEchelon is first called
            //
            //                  V pivot column
            //  pivot row ->  [ 5  9  2  5 | -2 ]
            //                [ 1  9  5  5 |  1 ]
            //                [ 7  3  1  0 |  9 ]
            //                [ 9  9  0  5 |  8 ]
            //
            // The p


            // Base case of the recursive method
            //  Checks whether pivot row and pivot column is within the valid index range for rows and columns
            if( ( pivotRow < 0 || pivotRow >= rows) || ( pivotColumn < 0 || pivotColumn >= columns ) ) return;


            int max = pivotRow; // Saves the index of the pivot row and assumes it points to the maximum value in the pivot column

            // Find the largest coefficient in the current column and saves the index
            for( int i =  pivotRow + 1; i < rows; i++ ){
              if( Math.abs( getCoefficient( i, pivotColumn ) ) >  Math.abs( getCoefficient( max, pivotColumn ) ) )
                max = i;
            }

            // If the pivot row does not contain the largest coefficient in the pivot column, switch rows
            if( pivotRow != max ) interchange( pivotRow, max );

            // If the coefficient at pivot index ( row, column ) is a nonzero value, scale the equation
            // at row by the coefficient at ( row, column ).
            if( getCoefficient( pivotRow, pivotColumn ) != 0.0 ){
                scale( pivotRow, ( 1.0 / getCoefficient( pivotRow, pivotColumn ) ) );

                // Set all the coefficients in the pivot column below the pivot row to 0.
                for( int i = pivotRow + 1; i < rows; i++ ){
                  if( getCoefficient( i, pivotColumn ) != 0.0 )
                    replace( i, pivotRow, getCoefficient( i, pivotColumn ) * -1 );
                }

                // Calls rowEcholon for the next pivot, which is ( row + 1, column + 1 )
                rowEchelon( matrix, pivotRow + 1, pivotColumn + 1, rows, columns );
            } else {
                rowEchelon( matrix, pivotRow, pivotColumn + 1, rows, columns );
            }
        }

        /**
        * Returns a visual representation of the Augmented Matrix in rectangular format.
        * @return String that represents the Augmented Matrix
        */
        public String toString(){

          String rectFormat = new String();

          for( int i = 0; i < coefficients.getRows(); i++){
            rectFormat += String.format( "%2d: [", i );
            for( int j = 0; j < coefficients.getColumns(); j++ ){
              rectFormat += String.format("%5.2f ", coefficients.getEntry( i, j ) );
            }
            rectFormat += String.format("| %5.2f ]\n", getConstant( i ) );
          }

          return rectFormat;
        }

        public String systemOfEquations(){
            String systemOfEquations = new String();
            String equation;
            double sign;

            for( int i = 0; i < getEquations(); i++ ) {
              equation = new String();
              sign = 0.0;
              for( int j = 0; j < getVariables(); j++ ){
                if( getCoefficient( i, j ) != 0.0 ){
                  sign = Math.signum( getCoefficient( i, j ) );
                  if( j < getVariables() ) {
                    if( sign == -1.0 ){
                       if( equation.length() == 0 ) equation += " -";
                       else equation += " - ";
                    }
                    else
                       if( equation.length() > 0 )equation += " + ";
                  }

                  if( getCoefficient( i, j ) == 1.0 )
                    equation += String.format( "%c ", j + 'x' );
                  else
                    equation += String.format( "%5.2f%c ", getCoefficient( i, j ) * sign , j + 'x' );
                }
              }
              equation += String.format( " = %5.2f\n", getConstant( i ) );
              systemOfEquations += String.format( "%40s", equation );
            }

            return systemOfEquations;
        }

}
