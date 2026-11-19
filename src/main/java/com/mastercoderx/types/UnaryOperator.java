package com.mastercoderx.types;

public abstract class UnaryOperator extends Operator {
	protected BooleanExpression operand;
	
	public UnaryOperator() {
		
	}
	
	public void setOperand(BooleanExpression operand) {
		this.operand = operand;
	}
}
