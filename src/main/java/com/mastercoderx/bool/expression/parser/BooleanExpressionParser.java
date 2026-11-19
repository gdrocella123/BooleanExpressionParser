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
	
	// ((T | T) & ( T & F))
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
				if (!off.equals("(")) {
					throw new Exception("Invalid Boolean Expression.");
				}
				
				BooleanExpression operand1 = expStack.pop();
				Operator op = (Operator)expStack.pop();
				
				if (op instanceof BinaryOperator) {
					BooleanExpression operand2 = expStack.pop();
					
					((BinaryOperator)op).setLeft(operand1);
					((BinaryOperator)op).setRight(operand2);
					expStack.push(op);
				}
				else {
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
		
		return expStack.pop();
	}
	
	
}
