package edu.thinkbox.math.matrix;

public class AugmentedMatrix{
        private Matrix coefficients;  // coefficients of the system of equations.
        private Matrix constants;     // constants of the system of equations.

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
        public double getConstant( int equation ){ return constants.getEntry( equation, 0 ); }
        public void setConstant( int equation, double constant ){
            constants.setEntry( equation, 0, constant );
        }

        public void generateRandomValues(){
            coefficients.generateRandomEntries();
            constants.generateRandomEntries();
        }

        public void interchange( int equation1, int equation2 ){
            coefficients.switchRows( equation1, equation2 );
            constants.switchRows( equation1, equation2 );
        }

        public void multiply( int equation, double nonzero ) throws ZeroValueException {
            if( nonzero == 0.0 ) throw new ZeroValueException();
            for( int variable = 0; variable < coefficients.getColumns(); variable++ ){
              coefficients.setEntry( equation, variable, coefficients.getEntry( equation, variable ) * nonzero );
            }
            constants.setEntry( equation, 0, constants.getEntry( equation, 0 ) * nonzero );
        }

        public void addMultiple( int equation1, int equation2, double multiple ){
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

        private void rowEchelon( AugmentedMatrix matrix, int row, int column, int rows, int columns ){
            if( (row < 0 || row >= rows) || ( column < 0 || column >= columns ) ) return;

            int max = row;
            for( int i =  row + 1; i < rows; i++ ){
              if( Math.abs( getCoefficient( i, column ) ) >  Math.abs( getCoefficient( max, column ) ) )
                max = i;
            }

            interchange( row, max );
            if( getCoefficient( row, column ) != 0.0 )
              multiply( row, ( 1.0 / getCoefficient( row, column ) ) );

            for( int i = row + 1; i < rows; i++ ){
                if( getCoefficient( i, column ) != 0.0 )
                  addMultiple( row, i, getCoefficient( i, column ) * -1 );
            }

            rowEchelon( matrix, row + 1, column + 1, rows, columns );
        }

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

}
