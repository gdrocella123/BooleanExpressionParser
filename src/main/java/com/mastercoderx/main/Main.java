package com.mastercoderx.main;

import com.mastercoderx.bool.expression.parser.BooleanExpressionParser;
import com.mastercoderx.types.BooleanExpression;

public class Main {
	public static void main(String[] args) throws Exception {
		BooleanExpressionParser bep = new BooleanExpressionParser("T |~ F");
		BooleanExpression be = bep.parse();
		
		System.out.println(be.eval());
	}
}
