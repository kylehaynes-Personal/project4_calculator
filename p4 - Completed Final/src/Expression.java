//**************************************************************************************************************
// CLASS: Expression
//
// AUTHOR
// Kevin R. Burger (burgerk@asu.edu)
// Computer Science & Engineering Program
// Fulton Schools of Engineering
// Arizona State University, Tempe, AZ 85287-8809
// http://www.devlang.com
//**************************************************************************************************************

/**
 * Represents an infix expression to be evaluated.
 */
public class Expression {

    /**
     * A queue which stores the tokens of the infix expression in the order in which they were input.
     */
    Queue<Token> mTokenQueue;

    /**
     * Expression(String)
     *
     * pExprStr is a string representing an infix expression, such as "(1 + 2) * -3". This ctor uses the
     * Tokenizer class to break the string into Token objects which are stored in the token queue instance
     * variable.
     *
     * PSEUDOCODE:
     * 1. Create a new Queue<Token> object and pass it to setTokenQueue (this initializes mTokenQueue)
     * 2. Declare and create a Tokenizer object named tokenizer passing pExprStr to the ctor
     * 3. Declare a Token object named prevToken initialized to null
     * -- Read the first token
     * 4. Declare a Token object named token and assign the return value from tokenizer.nextToken() to it
     * -- Keep reading tokens until tokenizer.nextToken() returns null
     * 5. While token is not null Do
     *     -- Check for and handle the negation operator.
     *     If token instanceof SubOperator Then
     *         token = negationCheck(token, prevToken)
     *     End if
     *     -- Add the token to the queue.
     *     Call getTokenQueue().enqueue(token)
     *     prevToken = token
     *     -- Read the next token.
     *     token = call tokenizer.nextToken()
     * End While
     */
    public Expression(String exp) {
    	
    	/* _________{ DEBUGER } _____________*/
    	System.out.println("\t\t- Expression: [" + exp + "]  created...");
    	/* ______{ END DUBUGGER }____________*/
    	
    	// 1
    	Queue<Token> que = new Queue<Token>();
    	this.setTokenQueue(que);
    	// 2
    	Tokenizer tokenizer = new Tokenizer(exp);
    	// 3
    	Token prevToken = null;
    	// 4 
    	Token token = tokenizer.nextToken();
    	
    	// 5
    	while (token != null) {
    		if (token instanceof SubOperator) {
    			token = negationCheck(token, prevToken);
    		}
    		getTokenQueue().enqueue(token);
    		prevToken = token;
    		token = tokenizer.nextToken();
    	}
    	
    	/* _________{ DEBUGER } _____________*/
    	System.out.println("\t\t- Queue Populated: " + getTokenQueue().toString());
    	/* ______{ END DUBUGGER }____________*/
    }

    /**
     * Evaluates the expression and returns the result as a Double.
     *
     * PSEUDOCODE:
     * 
     * 
     * (1) 	Declare and create a Stack<Operator> object named operatorStack
     * 		Declare and create a Stack<Operand> object named operandStack
     * 
     * 
     * (2) While mTokenQueue is not empty Do
     *        Declare and create a Token object named token assigning getTokenQueue().dequeue() to it
     *     
     *     
     * (3) If token instanceof Operand Then
     *         Push token onto the operand stack (type cast token to Operand)
     * (4) ElseIf token instanceof LeftParen Then
     *         Push token onto the operator stack (type cast token to LeftParen)
     * (5) ElseIf token instanceof RightParen Then
     *         While the operator on the top of the operator stack is not an instanceof LeftParen Do
     *             Call topEval(operatorStack, operandStack)
     *         End While
     *         Pop the top operator from the operator stack -- removes the LeftParen
     * (6)  Else
     *         Declare Operator object named operator and assign token to it (type cast to Operator)
     *         While keepEvaluating(operatorStack, operator) is true Do
     *             Call topEval(operatorStack, operandStack)
     *         EndWhile
     *         Push operator onto the operator stack
     *     End if
     * End While
     * 
     * (7)
     * While the operator stack is not empty Do
     *     Call topEval(operatorStack, operandStack)
     * End While
     * 
     * (8)
     * Pop the top Operand from the operand stack and return its value (call getValue() on the Operand).
     */
    public double evaluate() {
    	
    	/* _________{ DEBUGER } _____________*/
    	System.out.println("\t\t- Expression.evaluate()");
    	/* ______{ END DUBUGGER }____________*/
    	
    	// (1)
    	Stack<Operator> operatorStack = new Stack<Operator>();
    	Stack<Operand> operandStack = new Stack<Operand>();
    	
    	// (2)
    	while (!this.mTokenQueue.isEmpty()) {
    		Token token = getTokenQueue().dequeue();
    		// (3)
    		if (token instanceof Operand) {
    			operandStack.push((Operand) token);
    		}
    		// (4)
    		else if (token instanceof LeftParen) {
    			operatorStack.push((Operator) token);
    		}
    		// (5)
    		else if (token instanceof RightParen) {
    			while (!(operatorStack.peek() instanceof LeftParen)) {
    				topEval(operatorStack, operandStack);
    			}
    			operatorStack.pop();
    		}
    		// (6)
    		else {
    			Operator operator = (Operator) token;
    			while (keepEvaluating(operatorStack, operator)) {
    				topEval(operatorStack, operandStack);
    			}
    			operatorStack.push(operator);
    		}
    	}
    	// (7)
    	while (!operatorStack.isEmpty()) {
    		topEval(operatorStack, operandStack);
    	}
    	// (8)
    	double result = operandStack.pop().getValue();
    	return result;
    }

    /**
     * Accessor method for mTokenQueue.
     */
    protected Queue<Token> getTokenQueue() {
        return mTokenQueue;
    }

    /**
     * Returns true when we need to pop the operator on top of the operator stack and evaluate it. If the stack
     * is empty, returns false since there is no operator to pop. Otherwise, return true if the operator on top
     * of the operator stack has stack precedence greater than or equal to the normal precedence of pOperator.
     */
    private boolean keepEvaluating(Stack<Operator> pOperatorStack, Operator pOperator) {
        if (pOperatorStack.isEmpty()) {
            return false;
        } else {
            return pOperatorStack.peek().stackPrecedence() >= pOperator.precedence();
        }
    }

    /**
     * Since the negation and subtraction operators look the same we can identify negation when:
     *
     * 1. The previous pToken is null (negation can be the first operator in an expression but sub cannot)
     * 2. Or if the previous pToken was a binary operator (sub cannot be preceded by another binary operator)
     * 3. Or if the previous pToken was a left parenthesis (sub cannot be preceded by a left paren)
     *
     * This method determines if pToken is really a negation operator rather than a subtraction operator, and if
     * so, will return a negation operator pToken. If pToken represents subtraction, then we simply return pToken.
     */
    private Token negationCheck(Token pToken, Token pPrevToken) {
        if (pPrevToken == null || pPrevToken instanceof BinaryOperator || pPrevToken instanceof LeftParen) {
            /* _________{ DEBUGER } _____________*/
            System.out.println("\t\t- Negation Operator found");
            /* ______{ END DUBUGGER }____________*/
            
        	pToken = new NegOperator();
        }
        return pToken;
    }

    /**
     * Mutator method for mTokenQueue.
     */
    protected void setTokenQueue(Queue<Token> pTokenQueue) {
        mTokenQueue = pTokenQueue;
    }

    /**
     * topEval()
     *
     * Evaluates the "top" of the stack. If the top operator on the operator stack is a unary operator, we pop
     * one operand from the operand stack, evaluate the result, and push the result onto the operand stack. If
     * the top operator on the operator stack is a binary operator, we pop two operands from the operand stack,
     * evaluate the result of the operation, and push the result onto the operand stack.
     *
     * PSEUDOCODE:
     * 
     * 1. 	Declare and create Operand object named right = Call pOperandStack.pop()
     * 		Declare and create Operator object named operator = Call pOperatorStack.pop()
     * 2.	If operator instanceof UnaryOperator Then
     *     		Typecast operator to UnaryOperator and call evaluate(right) on it
     *     		Push the returned Operand from the above statement onto the operand stack
     * 3.	Else
     *     		Declare and create Operand object named left = Call pOperandStack.pop()
     *     		Typecast operator to BinaryOperator and call evaluate(left, right) on it
     *     		Push the returned Operand from the above statement onto the operand stack
     * 		End If
     */
    public void topEval(Stack<Operator> pOperatorStack, Stack<Operand> pOperandStack) {
    	
    	// 1
    	Operand right = pOperandStack.pop();
    	Operator op = pOperatorStack.pop();
    	// 2
    	if (op instanceof UnaryOperator) {
    		UnaryOperator uOp = (UnaryOperator) op;
    		Operand result = uOp.evaluate(right);
    		pOperandStack.push(result);
    	
    	} // 3
    	else {
    		Operand left = pOperandStack.pop();
    		BinaryOperator biOp = (BinaryOperator) op;
    		Operand result = biOp.evaluate(left, right);
    		pOperandStack.push(result);
    	}
    	
    }

}
