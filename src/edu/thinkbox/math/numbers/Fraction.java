package edu.thinkbox.math.numbers;

public class Fraction{
        public static final double ONE_HALF     = 1.0 / 2.0;
        public static final double ONE_THIRD    = 1.0 / 3.0;
        public static final double ONE_FOURTH   = 1.0 / 4.0;
        public static final double ONE_FIFTH    = 1.0 / 5.0;
        public static final double ONE_SIXTH    = 1.0 / 6.0;
        public static final double ONE_SEVENTH  = 1.0 / 7.0;
        public static final double ONE_EIGHTH   = 1.0 / 8.0;
        public static final double ONE_NINTH    = 1.0 / 9.0;
        public static final double ONE_TENTH    = 1.0 / 10.0;
        private int numerator;
        private int denominator;

        public Fraction( int numerator, int denominator ){
            this.numerator = numerator;
            this.denominator = denominator;
        }

        public Fraction(){
            this.numerator = 1;
            this.denominator = 1;
        }

        public int getNumerator(){ return numerator; }
        public int getDenominator(){ return denominator; }
        public void setNumerator( int numerator ){ this.numerator = numerator; }
        public void setDenominator( int denominator ){ this.denominator = denominator; }
        public double toDecimal(){ return numerator / denominator; }
        public Fraction add( Fraction fraction ){

          int numerator   = ( this.numerator * fraction.getDenominator() ) + ( fraction.getNumerator() * this.denominator );
          int denominator = this.denominator * fraction.getDenominator();

          return new Fraction( numerator, denominator );
        }

        public Fraction subract( Fraction fraction ){

          int numerator   = ( this.numerator * fraction.getDenominator() ) - ( fraction.getNumerator() * this.denominator );
          int denominator = this.denominator * fraction.getDenominator();

          return new Fraction( numerator, denominator );
        }

        public Fraction multiply( Fraction fraction ){
            return new Fraction( this.numerator * fraction.getNumerator(), this.denominator * fraction.getDenominator() );
        }

        public Fraction divide( Fraction fraction ){
            return new Fraction( this.numerator * fraction.getDenominator(), this.denominator * fraction.getNumerator() );
        }

        public Fraction multiply( int integer ){
            return new Fraction( this.numerator * integer, this.denominator );
        }
}
