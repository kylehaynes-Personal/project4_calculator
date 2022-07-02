//**************************************************************************************************************
// CLASS: SubOperator
//
// AUTHOR
// Kevin R. Burger (burgerk@asu.edu)
// Computer Science & Engineering Program
// Fulton Schools of Engineering
// Arizona State University, Tempe, AZ 85287-8809
// http://www.devlang.com
//**************************************************************************************************************

/**
 * Represents the subtraction operator which is a specific type of binary operator.
 */
public class SubOperator extends BinaryOperator {

	public SubOperator() {
		
	}
	
	@Override
	public Operand evaluate(Operand pLhsOperand, Operand pRhsOperand) {
		// TODO Auto-generated method stub
		
		/* _________{ DEBUGER } _____________*/
		System.out.println("\t\t- Subtracting: (" + pLhsOperand.getValue() + " - " + pRhsOperand.getValue() + ")");
		/* ______{ END DUBUGGER }____________*/
		
		return new Operand(pLhsOperand.getValue() - pRhsOperand.getValue());
	}

	@Override
	public int precedence() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public int stackPrecedence() {
		// TODO Auto-generated method stub
		return 2;
	}
	
}