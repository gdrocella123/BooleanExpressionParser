package com.mastercoderx.types;

public abstract class BinaryOperator extends Operator {
	protected BooleanExpression op1;
	protected BooleanExpression op2;
	
	public void setLeft(BooleanExpression left) {
		this.op1 = left;
	}
	
	public void setRight(BooleanExpression right) {
		this.op2 = right;
	}
}
