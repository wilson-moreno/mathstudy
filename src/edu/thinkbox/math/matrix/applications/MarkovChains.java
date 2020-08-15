package edu.thinkbox.math.matrix.applications;

import edu.thinkbox.math.matrix.Matrix;
import java.util.Scanner;

public class MarkovChains{
        private static double ONE_HALF = 1.0 / 2.0;
        private static double ONE_THIRD = 1.0 / 3.0;
        private static double ONE_FOURTH = 1.0 / 4.0;
        private static double ONE_FIFTH = 1.0 / 5.0;

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

            System.out.println( "Welcome to Marko Chains driver program!" );
            Scanner input = new Scanner( System.in );
            int choice = 0;
            boolean exit = false;

            do{
              System.out.println( "\nPlease select a sample problem or choose quit.\n" );
              System.out.println( "1. Where is he going to eat next?" );
              System.out.println( "2. Where is that mouse?" );
              System.out.println( "3. You are late again!" );
              System.out.println( "4. Someday, I will be rich." );
              System.out.println( "5. Quit.\n" );
              System.out.print(   "Please enter your choice: " );
              choice = input.nextInt();

              switch( choice ){
                case 1: restaurants(); break;
                case 2: mouseMaze(); break;
                case 3: workOnTime(); break;
                case 4: socialClasses(); break;
                case 5: System.out.println( "Thank you for using the program." );
                        input.close();
                        exit = true;
                        break;
                default: System.out.println( "Please try again." );
              }

            } while ( !exit );


            System.exit( 0 );
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


            P.setRowEntries( 0, String.format( " 0  %f  %f  0   %f ", ONE_HALF, ONE_FIFTH, ONE_HALF ) );
            P.setRowEntries( 1, String.format( " %f  0  0   %f  0  ", ONE_THIRD, ONE_FOURTH ) );
            P.setRowEntries( 2, String.format( " %f  0  %f  %f  %f ", ONE_THIRD, 2 * ONE_FIFTH, ONE_FOURTH, ONE_HALF ) );
            P.setRowEntries( 3, String.format( " 0  %f  %f  %f  0  ", ONE_HALF, ONE_FIFTH, 2 * ONE_FOURTH ) );
            P.setRowEntries( 4, String.format( " %f  0  %f  0   0  ", ONE_THIRD, ONE_FIFTH ) );
            s0.setColumnEntries( 0, " 1.0 " );
            P.setColumnLabels( "Compartment 1;Compartment 2;Compartment 3;Compartment 4;Compartment 5" );
            P.setRowLabels( "Compartment 1;Compartment 2;Compartment 3;Compartment 4;Compartment 5" );
            s0.setColumnLabels( "Location (%)" );
            s0.setRowLabels( "Compartment 1;Compartment 2;Compartment 3;Compartment 4;Compartment 5" );

            MarkovChains system = new MarkovChains( s0, P );
            System.out.println( "Mouse Maze\n" );
            System.out.println( "Transition matrix: \n\n" + P );
            for( int s = 0; s < 20; s++ ){
              System.out.printf( " State %d \n %s \n", s, system.getStateVector( s ) );
            }
        }

        public static void workOnTime(){
           Matrix P = new Matrix( 2, 2 );
           Matrix s0 = new Matrix( 2, 1 );

           P.setRowEntries( 0, String.format( " %f %f ", ONE_HALF, 2 * ONE_THIRD ) );
           P.setRowEntries( 1, String.format( " %f %f ", ONE_HALF, ONE_THIRD ) );
           s0.setColumnEntries( 0, String.format( " %f %f ", ONE_FOURTH, 3 * ONE_FOURTH ) );
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

        public static void socialClasses(){
           Matrix P = new Matrix( 3, 3 );
           Matrix s0 = new Matrix( 3, 1 );

           P.setColumnEntries( 0, " 0.7 0.1 0.2 " );
           P.setColumnEntries( 1, " 0.1 0.8 0.1 " );
           P.setColumnEntries( 2, " 0.1 0.3 0.6 " );
           P.setColumnLabels( "Upper;Middle;Lower" );
           P.setRowLabels( "Upper;Middle;Lower" );

           s0.setColumnLabels( "Probability (%)" );
           s0.setRowLabels( "Upper;Middle;Lower" );
           s0.setColumnEntries( 0, "0.0 0.0 1.0" );

           MarkovChains system = new MarkovChains( s0, P );
           System.out.println( "Social Classes: \n" + P );
           System.out.println( "Lower Class Parents: \n" + s0 );
           System.out.println( "First Generation: \n" + system.getStateVector( 1 ) );
           System.out.println( "Second Generation: \n" + system.getStateVector( 2 ) );
           System.out.println( "Long-term Breakdown: \n" + system.getStateVector( 200 ) );
        }
}
