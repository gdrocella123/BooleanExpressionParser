package com.mastercoderx.types;

public class False implements BooleanExpression {

	private static False falseObj;
	
	protected False() {
	}
	
	@Override
	public boolean eval() {
		return false;
	}

	public static False getInstance() {
		if (falseObj == null) {
			falseObj = new False();
		}
		
		return falseObj;
	}
}
