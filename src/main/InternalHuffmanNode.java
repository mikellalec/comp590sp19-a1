package main;

public class InternalHuffmanNode implements HuffmanNode {

	private HuffmanNode _left;
	private HuffmanNode _right;
	
	public InternalHuffmanNode() {
		_left=null;
		_right=null;
	}
	
	public InternalHuffmanNode(HuffmanNode left, HuffmanNode right) {
		_left=left;
		_right=right;
	}
	
	@Override
	public int count() {
		return _left.count() + _right.count();
	}

	@Override
	public boolean isLeaf() {
		return false;
	}

	@Override
	public int symbol() throws Exception {
		throw new Exception("Not applicable to internal nodes:");
	}

	@Override
	public int height() {
		return Math.max(1 + _left.height(), 1 + _right.height());
	}

	@Override
	public boolean isFull() {
		if(_left==null || _right==null) return false;
		else return _left.isFull() && _right.isFull();
	}

	@Override
	public boolean insertSymbol(int length, int symbol) throws Exception {
		if(_left!=null) {
			if(!_left.isFull()) {
				return _left.insertSymbol(length-1,symbol);
			}else if(_right!=null) {
				if(!_right.isFull()) {
					return _right.insertSymbol(length-1, symbol);
				}else {
					return false; // Left and right are both not null, full
				}
			}else {
				if(length==1) {
					_right = new LeafHuffmanNode(symbol, 0);
					return true; // Create right leaf
				}else {
					_right = new InternalHuffmanNode();
					return _right.insertSymbol(length-1, symbol);
				}
			}
		}else {
			if(length==1) {
				_left = new LeafHuffmanNode(symbol, 0);
				return true; // Create left leaf
			}else {
				_left = new InternalHuffmanNode();
				return _left.insertSymbol(length-1, symbol);
			}
		}
	}

	@Override
	public HuffmanNode left() {
		return _left;
	}

	@Override
	public HuffmanNode right() {
		return _right;
	}

}
