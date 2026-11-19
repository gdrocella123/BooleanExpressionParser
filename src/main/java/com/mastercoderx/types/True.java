package com.mastercoderx.types;

public class True implements BooleanExpression {

	private static True trueObj = null;
	
	protected True() {};
	
	@Override
	public boolean eval() {
		return true;
	}

	public static True getInstance() {
		if (trueObj == null) {
			trueObj  = new True();
		}
		
		return trueObj;
	}
}
