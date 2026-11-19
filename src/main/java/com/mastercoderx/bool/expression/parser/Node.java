package com.mastercoderx.bool.expression.parser;

public class Node {
	private String op;
	private Node left;
	private Node right;
	
	public Node() {
		
	}
	
	public String getOp() {
		return this.op;
	}
	
	public Node getLeft() {
		return this.left;
	}
	
	public Node getRight() {
		return this.right;
	}
	
	public void setOp(String op) {
		this.op = op;
	}
	
	public void setLeft(Node left) {
		this.left = left;
	}
	
	public void setRight(Node right) {
		this.right = right;
	}
}
