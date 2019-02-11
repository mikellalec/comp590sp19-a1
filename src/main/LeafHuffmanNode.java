package main;

public class LeafHuffmanNode implements HuffmanNode {

	private int _count;
	private int _symbol;
	
	public LeafHuffmanNode(int symbol, int count) {
		_count = count;
		_symbol = symbol;
	}
	
	@Override
	public int count() {
		return _count;
	}

	@Override
	public boolean isLeaf() {
		return true;
	}

	@Override
	public int symbol() {
		return _symbol;
	}

	@Override
	public int height() {
		return 0;
	}

	@Override
	public boolean isFull() {
		return true;
	}

	@Override
	public boolean insertSymbol(int length, int symbol) throws Exception {
		throw new Exception("Not applicable to leaf nodes.");
	}

	@Override
	public HuffmanNode left() throws Exception{
		throw new Exception("Not applicable to leaf nodes.");
	}

	@Override
	public HuffmanNode right() throws Exception{
		throw new Exception("Not applicable to leaf nodes.");
	}

}
