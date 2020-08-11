package edu.thinkbox.math.matrix.applications;

import edu.thinkbox.math.matrix.Matrix;

public class MarkovChains{
        private Matrix initialState;
        private Matrix transitionMatrix;


        public MarkovChains( Matrix initialState, Matrix transitionMatrix ){
            this.initialState = initialState;
            this.transitionMatrix = transitionMatrix;
        }

        public Matrix getInitialState(){ return initialState; }
        public Matrix getTransitionMatrix(){ return transitionMatrix; }
        public Matrix getStateVector( int state ){
            if( state == 0 ) return initialState;
            Matrix nextState = transitionMatrix.multiply( getStateVector( state -1 ) );
            nextState.setRowLabels( initialState.getRowLabels() );
            nextState.setColumnLabels( initialState.getColumnLabels() );
            return nextState;
        }

        public static void main( String[] args ){
            Matrix transitionMatrix = new Matrix( 2, 2);
            Matrix initialState = new Matrix( 2, 1 );

            transitionMatrix.setRowEntries( 0, " 0.0 0.25 " );
            transitionMatrix.setRowEntries( 1, " 1.0 0.75 " );
            transitionMatrix.setColumnLabels( "Restaurant A;Restaurant B" );
            transitionMatrix.setRowLabels( "Restaurant A;Restaurant B" );
            initialState.setColumnEntries( 0, " 0.5 0.5 " );
            initialState.setColumnLabels( "Eats At (%)" );
            initialState.setRowLabels( "Restaurant A; Restaurant B" );

            MarkovChains system = new MarkovChains( initialState, transitionMatrix );

            System.out.println( "Transition Matrix: \n" + transitionMatrix );
            for( int s = 0; s < 20; s++ ){
              System.out.printf( " State %d \n %s \n", s, system.getStateVector( s ) );
            }
        }
}
