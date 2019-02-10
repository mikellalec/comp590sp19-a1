package main;

public class InternalHuffmanNode implements HuffmanNode {

	private int _count;
	private boolean _isLeaf;
	private int _symbol;
	private int _height;
	
	public InternalHuffmanNode() {
		
	}
	
	@Override
	public int count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isLeaf() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int symbol() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int height() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isFull() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insertSymbol(int length, int symbol) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public HuffmanNode left() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HuffmanNode right() {
		// TODO Auto-generated method stub
		return null;
	}

}
