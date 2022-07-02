//**************************************************************************************************************
// CLASS: Operator
//
// AUTHOR
// Kevin R. Burger (burgerk@asu.edu)
// Computer Science & Engineering Program
// Fulton Schools of Engineering
// Arizona State University, Tempe, AZ 85287-8809
// http://www.devlang.com
//**************************************************************************************************************

/**
 * Operator is the superclass of all binary and unary operators.
 */

public abstract class Operator extends Token {
	
	public Operator() { }
	
	public abstract boolean isBinaryOperator();
	
	public abstract int precedence();
	
	public abstract int stackPrecedence();
}
