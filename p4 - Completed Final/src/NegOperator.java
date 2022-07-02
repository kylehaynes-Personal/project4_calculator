//**************************************************************************************************************
// CLASS: NegOperator
//
// AUTHOR
// Kevin R. Burger (burgerk@asu.edu)
// Computer Science & Engineering Program
// Fulton Schools of Engineering
// Arizona State University, Tempe, AZ 85287-8809
// http://www.devlang.com
//**************************************************************************************************************

/**
 * Represents the negation operator which is a specific type of unary operator.
 */
public class NegOperator extends UnaryOperator {

	
	public NegOperator() {
		
	}
	
	@Override
	public Operand evaluate(Operand pOperand) {
		// TODO Auto-generated method stub
		double value = pOperand.getValue() * -1;
		pOperand.setValue(value);
		return pOperand;
	}
	
	@Override 
	public int precedence() {
		return 4;
	}
	
	@Override
	public int stackPrecedence() { 
		return 4; 
	}
	
	
}