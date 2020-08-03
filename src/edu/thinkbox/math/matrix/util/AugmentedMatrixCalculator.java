package edu.thinkbox.math.matrix.util;

import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Map;
import java.util.HashMap;

public class AugmentedMatrixCalculator{
      private static Scanner commandScanner;
      private static boolean terminate = false;
      private static String command = new String();
      private static CommandParser commandParser = new CommandParser();
      private static AugmentedMatrix matrix = null;

      public static void main( String[] args ){
          commandScanner = new Scanner( System.in );

          System.out.println( "\nWelcome to Augmented Matrix Calculator!\n" );

          while(!terminate){
            command = getNextCommand();
            commandParser.parse( command );
            if( commandParser.isValid() )
              executeCommand( commandParser.getCommand(),
                              commandParser.getArguments() );
          }

          System.out.println( "Exiting augmented matrix command line." );
          commandScanner.close();
          System.exit( 0 );
      }

      private static void executeCommand( String command, Map< String, Object > arguments ){
          switch( command ){
                case "add"          : addMultipleTo( arguments ); break;
                case "create"       : createMatrix( arguments ); break;
                case "coefficient"  : setCoefficient( arguments ); break;
                case "constant"     : setConstant( arguments ); break;
                case "divide"       : divideBy( arguments ); break;
                case "gaussian"     : gaussianElimination( ); break;
                case "interchange"  : interchangeRows( arguments ); break;
                case "multiply"     : multiplyBy( arguments ); break;
                case "quit"         : terminate = true; break;
          }
      }

      private static void gaussianElimination(){
        matrix.gaussianElimination();
        System.out.println( "\n" + matrix.toString() );
      }

      private static void divideBy( Map< String, Object > arguments ){
          Integer row = (Integer) arguments.get( "row" );
          Double scalarValue = (Double) arguments.get( "scalar value" );
          matrix.divideRowBy( row.intValue(), scalarValue.doubleValue() );
          System.out.println( "\n" + matrix.toString() );
      }

      private static void multiplyBy( Map< String, Object > arguments ){
          Integer row = (Integer) arguments.get( "row" );
          Double scalarValue = (Double) arguments.get( "scalar value" );
          matrix.multiplyRowBy( row.intValue(), scalarValue.doubleValue() );
          System.out.println( "\n" + matrix.toString() );
      }

      private static void interchangeRows( Map< String, Object > arguments ){
          Integer firstRow = (Integer) arguments.get( "first row" );
          Integer secondRow = (Integer) arguments.get( "second row" );
          matrix.interchangeRows( firstRow.intValue(), secondRow.intValue() );
          System.out.println( "\n" + matrix.toString() );
      }

      private static void addMultipleTo( Map< String, Object > arguments ){
          Integer firstRow = (Integer) arguments.get( "first row" );
          Integer secondRow = (Integer) arguments.get( "second row" );
          Double scalarValue = (Double) arguments.get( "scalar value" );
          matrix.addMultipleTo( firstRow.intValue(), secondRow.intValue(), scalarValue.doubleValue() );
          System.out.println( "\n" + matrix.toString() );
      }

      private static void setConstant( Map< String, Object > arguments ){
          Integer row = (Integer) arguments.get( "row" );
          Double scalarValue = (Double) arguments.get( "scalar value" );
          matrix.setConstantAt( row.intValue(), scalarValue.doubleValue() );
          System.out.println( "\n" + matrix.toString() );
      }

      private static void setCoefficient( Map< String, Object > arguments ){
          Integer row = (Integer) arguments.get( "row" );
          Integer column = (Integer) arguments.get( "column" );
          Double scalarValue = (Double) arguments.get( "scalar value" );
          matrix.setCoefficientAt( row.intValue(), column.intValue(), scalarValue.doubleValue() );
          System.out.println( "\n" + matrix.toString() );
      }

      private static void createMatrix( Map< String, Object > arguments ){
          Integer rowSize = (Integer) arguments.get( "row size" );
          Integer columnSize = (Integer) arguments.get( "column size" );
          matrix = new AugmentedMatrix( rowSize.intValue(), columnSize.intValue() );
          System.out.println( "\n" + matrix.toString() );
      }

      private static String getNextCommand(){
          System.out.print("command>> ");
          return commandScanner.nextLine();
      }


}

class CommandParser{
      private String command;
      private boolean isValid;
      private Map< String, Object > arguments;

      public CommandParser(){
        command = new String();
        isValid = false;
        arguments = new HashMap< String, Object >();
      }

      public void parse( String commandLine ){

          isValid = false;
          Scanner scanner = new Scanner( commandLine );

          if( scanner.hasNext() ){
            try{
              switch( scanner.next() ){
                  case "add"          : command = "add"; addCommand( scanner );  break;
                  case "create"       : command = "create"; createMatrixCommand( scanner ); break;
                  case "divide"       : command = "divide"; divideCommand( scanner ); break;
                  case "help"         : command = "help"; helpCommand( scanner ); break;
                  case "interchange"  : command = "interchange"; interchangeCommand( scanner ); break;
                  case "multiply"     : command = "multiply"; multiplyCommand( scanner ); break;
                  case "quit"         : command = "quit"; quitCommand( scanner ) ;break;
                  case "gaussian"     : command = "gaussian"; gaussianCommand( scanner ); break;
                  case "set"          : switch( scanner.next() ){
                                          case "coefficient"  : command = "coefficient";  coefficientCommand( scanner ); break;
                                          case "constant"     : command = "constant"; constantCommand( scanner ); break;
                                        } break;
                  default             : command = null;
                                        isValid = false;
                                        System.out.println( "Not a valid command" );
              }
            } catch ( InputMismatchException ime){
              System.out.println("Invalid command input");
              isValid = false;
            } catch ( NoSuchElementException nse ){
              System.out.println("Invalid command input");
              isValid = false;
            }
          }

          scanner.close();
      }

      public boolean isValid(){ return isValid; }
      public String getCommand(){ return command; }
      public Map< String, Object > getArguments(){ return arguments; }

      private void gaussianCommand( Scanner scanner ) throws InputMismatchException, NoSuchElementException {
        arguments.clear();
        if( scanner.hasNext() ) throw new InputMismatchException();
        isValid = true;
      }

      private void constantCommand( Scanner scanner ) throws InputMismatchException, NoSuchElementException {
        arguments.clear();
        int row = scanner.nextInt();
        double scalarValue = scanner.nextDouble();
        if( scanner.hasNext() ) throw new InputMismatchException();
        isValid = true;
        arguments.put( "row", row );
        arguments.put( "scalar value", scalarValue );
      }

      private void coefficientCommand( Scanner scanner ) throws InputMismatchException, NoSuchElementException {
        arguments.clear();
        int row = scanner.nextInt();
        int column = scanner.nextInt();
        double scalarValue = scanner.nextDouble();
        if( scanner.hasNext() ) throw new InputMismatchException();
        isValid = true;
        arguments.put( "row", row );
        arguments.put( "column", column );
        arguments.put( "scalar value", scalarValue );
      }


      private void quitCommand( Scanner scanner ) throws InputMismatchException, NoSuchElementException {
        arguments.clear();
        if( scanner.hasNext() ) throw new InputMismatchException();
        isValid = true;
      }

      private void divideCommand( Scanner scanner ) throws InputMismatchException, NoSuchElementException {
        arguments.clear();
        int row = scanner.nextInt();
        String by = scanner.next();
        double scalarValue = scanner.nextDouble();
        if( !by.equals("by") || scanner.hasNext() ) throw new InputMismatchException();
        isValid = true;
        arguments.put( "row", row );
        arguments.put( "scalar value", scalarValue );
      }


      private void multiplyCommand( Scanner scanner ) throws InputMismatchException, NoSuchElementException {
        arguments.clear();
        int row = scanner.nextInt();
        String by = scanner.next();
        double scalarValue = scanner.nextDouble();
        if( !by.equals("by") || scanner.hasNext() ) throw new InputMismatchException();
        isValid = true;
        arguments.put( "row", row );
        arguments.put( "scalar value", scalarValue );
      }

      private void interchangeCommand( Scanner scanner ) throws InputMismatchException, NoSuchElementException {
        arguments.clear();
        int firstRow = scanner.nextInt();
        int secondRow = scanner.nextInt();
        if( scanner.hasNext() ) throw new InputMismatchException();
        isValid = true;
        arguments.put( "first row", firstRow );
        arguments.put( "second row", secondRow );
      }

      private void helpCommand( Scanner scanner ) throws InputMismatchException, NoSuchElementException {
        arguments.clear();
        if( scanner.hasNext() ) throw new InputMismatchException();
        isValid = true;
      }

      private void createMatrixCommand( Scanner scanner ) throws InputMismatchException, NoSuchElementException {
        arguments.clear();
        String matrix = scanner.next();
        int rowSize = scanner.nextInt();
        int columnSize = scanner.nextInt();
        if( scanner.hasNext() ) throw new InputMismatchException();
        isValid = true;
        arguments.put( "row size", rowSize );
        arguments.put( "column size", columnSize );
      }

      private void addCommand( Scanner scanner ) throws InputMismatchException, NoSuchElementException{
          arguments.clear();
          int firstRow = scanner.nextInt();
          String times = scanner.next();
          double scalarValue = scanner.nextDouble();
          String to = scanner.next();
          int secondRow = scanner.nextInt();
          if( !times.equals( "times" ) || !to.equals( "to" ) || scanner.hasNext() ) throw new InputMismatchException();
          isValid = true;
          arguments.put( "scalar value", scalarValue );
          arguments.put( "first row", firstRow );
          arguments.put( "second row", secondRow );
      }
}
