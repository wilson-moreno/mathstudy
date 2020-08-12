package edu.thinkbox.math.numbers;

public class Fraction{
        public static final double ONE_HALF = 1.0 / 2.0; 
        private int numerator;
        private int denominator;

        public Fraction( int numerator, int denominator ){
            this.numerator = numerator;
            this.denominator = denominator;
        }

        public int getNumerator(){ return numerator; }
        public int getDenominator(){ return denominator; }
        public void setNumerator( int numerator ){ this.numerator = numerator; }
        public void setDenominator( int denominator ){ this.denominator = denominator; }
        public double toDecimal(){ return numerator / denominator; }
}
