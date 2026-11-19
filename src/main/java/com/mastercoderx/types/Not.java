package com.mastercoderx.types;

public class Not extends UnaryOperator {
	public Not() {
		
	}
	
	public Not(BooleanExpression a) {

	}
	
	public boolean eval() {
		return ! this.operand.eval();
	}
}
