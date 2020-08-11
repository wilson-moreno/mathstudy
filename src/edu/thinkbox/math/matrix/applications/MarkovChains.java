package edu.thinkbox.math.matrix.applications;

import edu.thinkbox.math.matrix.Matrix;

public class MarkovChains{
        private static double ONE_THIRD = 1.0 / 3.0;
        private static double ONE_HALF = 1.0 / 2.0;
        private static double ONE_FOURTH = 1.0 / 4.0;
        private static double TWO_THIRD = 2.0 / 3.0;
        private static double THREE_FOURTH = 3.0 / 4.0;

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
            mouseMaze();
        }


        public static void restaurants(){
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

        private static void mouseMaze(){
            Matrix P  = new Matrix( 5, 5 );
            Matrix s0 = new Matrix( 5, 1 );


            P.setRowEntries( 0, String.format( " 0  %f %f  0   %f ", ONE_HALF, ONE_FOURTH, ONE_HALF ) );
            P.setRowEntries( 1, String.format( " %f 0  0   %f  0  ", ONE_THIRD, ONE_THIRD ) );
            P.setRowEntries( 2, String.format( " %f 0  %f  %f  %f ", ONE_THIRD, ONE_FOURTH, ONE_THIRD, ONE_HALF ) );
            P.setRowEntries( 3, String.format( " 0 %f %f  %f  0  ",  ONE_HALF, ONE_FOURTH, ONE_THIRD ) );
            P.setRowEntries( 4, String.format( " %f  0  %f  0   0  ", ONE_THIRD, ONE_FOURTH ) );
            s0.setColumnEntries( 0, " 1.0 " );
            P.setColumnLabels( "Compartment 1;Compartment 2;Compartment 3;Compartment 4;Compartment 5" );
            P.setRowLabels( "Compartment 1;Compartment 2;Compartment 3;Compartment 4;Compartment 5" );
            s0.setColumnLabels( "Location (%)" );
            s0.setRowLabels( "Compartment 1;Compartment 2;Compartment 3;Compartment 4;Compartment 5" );

            MarkovChains system = new MarkovChains( s0, P );
            System.out.println( "Mouse Maze\n" );
            System.out.println( "Transition matrix: \n\n" + P );
            for( int s = 0; s < 10; s++ ){
              System.out.printf( " State %d \n %s \n", s, system.getStateVector( s ) );
            }
        }

        public static void workOnTime(){
           Matrix P = new Matrix( 2, 2 );
           Matrix s0 = new Matrix( 2, 1 );

           P.setRowEntries( 0, String.format( " %f %f ", ONE_HALF, TWO_THIRD ) );
           P.setRowEntries( 1, String.format( " %f %f ", ONE_HALF, ONE_THIRD ) );
           s0.setColumnEntries( 0, String.format( " %f %f ", ONE_FOURTH, THREE_FOURTH ) );
           P.setColumnLabels( "On Time;Late" );
           P.setRowLabels( "On Time;Late" );
           s0.setColumnLabels( "Probability (%)" );
           s0.setRowLabels( "On Time;Late" );

           MarkovChains system = new MarkovChains( s0, P );
           System.out.println( "Work On Time\n" );
           System.out.println( "Transition matrix: \n\n" + P );
           for( int s = 0; s < 10; s++ ){
             System.out.printf( " State %d \n %s \n", s, system.getStateVector( s ) );
           }
        }
}
