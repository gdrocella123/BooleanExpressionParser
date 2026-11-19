package com.mastercoderx.types;

public class Or extends BinaryOperator {
	public Or() {
		
	}
	
	@Override
	public boolean eval() {
		return this.op1.eval() || this.op2.eval();
	}


}
