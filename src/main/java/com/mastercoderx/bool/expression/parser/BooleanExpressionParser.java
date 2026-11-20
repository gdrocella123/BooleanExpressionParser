package com.mastercoderx.bool.expression.parser;

import java.util.ArrayDeque;
import java.util.Deque;

import com.mastercoderx.types.And;
import com.mastercoderx.types.BinaryOperator;
import com.mastercoderx.types.BooleanExpression;
import com.mastercoderx.types.False;
import com.mastercoderx.types.Not;
import com.mastercoderx.types.Operator;
import com.mastercoderx.types.Or;
import com.mastercoderx.types.True;
import com.mastercoderx.types.UnaryOperator;

public class BooleanExpressionParser {
	private String boolExpression;
	
	public BooleanExpressionParser(String boolExpression) {
		this.boolExpression = boolExpression;
	}
	
	public BooleanExpression parse() throws Exception {
		int curr = 0;
		int expSz = boolExpression.length();
		
		Deque<String> stack = new ArrayDeque<>();
		Deque<BooleanExpression> expStack = new ArrayDeque<>();
				
		while(curr < expSz) {
			String currStr = boolExpression.substring(curr, curr+1);
			
			if (currStr.equals("(")) {
				stack.push(currStr);
			}
			else if (currStr.equals(")")) {
				String off = stack.pop();
				
				BooleanExpression operand1 = expStack.pop();
				
				if (operand1 instanceof True || operand1 instanceof False) {
					expStack.push(operand1);
					curr++;
					continue;
				}
				
				Operator op = (Operator)expStack.pop();
				
				if (op instanceof BinaryOperator) {
					BooleanExpression operand2 = expStack.pop();
					
					((BinaryOperator)op).setLeft(operand1);
					((BinaryOperator)op).setRight(operand2);
					expStack.push(op);
				}
				else if (op instanceof UnaryOperator) {
					((UnaryOperator)op).setOperand(operand1);
					expStack.push(op);
				}
			}
			else if (currStr.equals("T")) {
				expStack.push(True.getInstance());
			}
			else if (currStr.equals("F")) {
				expStack.push(False.getInstance());
			}
			else if (currStr.equals("&")) {
				expStack.push(new And());
			}
			else if (currStr.equals("|")) {
				expStack.push(new Or());
			}
			else if (currStr.equals("~")) {
				expStack.push(new Not());
			}
			else if (currStr.equals(" ")) {
				curr++;
				continue;
			}
			
			curr++;
		}
		
		if (!stack.isEmpty()) {
			throw new Exception("Parentheses are unbalanced.");
		}
		
		BooleanExpression retExp = null;
		
		while (expStack.size() > 1) {
			//System.out.println("Executing");
			BooleanExpression currExp = expStack.pop();

			BooleanExpression nextExp = expStack.pop();

			if (nextExp instanceof Operator && nextExp instanceof UnaryOperator) {
				UnaryOperator op = (UnaryOperator)nextExp;
				op.setOperand(currExp);
				
				expStack.push((BooleanExpression)op);
			}
			else if (nextExp instanceof Operator && nextExp instanceof BinaryOperator) {
				BinaryOperator bop = (BinaryOperator)nextExp;
				BooleanExpression leftExp = expStack.pop();
				
				bop.setLeft(leftExp);
				bop.setRight(currExp);
				expStack.push(bop);
			}
			else {
				throw new Exception("Invalid Boolean Expression.");
			}
		}
		
		retExp = expStack.pop();
		
		return retExp;
	}
	
	
}
