package com.mastercoderx.types;

public class And extends BinaryOperator {
	public And() {
		
	}
	
	
	@Override
	public boolean eval() {
		return this.op1.eval() && this.op2.eval();
	}
}
