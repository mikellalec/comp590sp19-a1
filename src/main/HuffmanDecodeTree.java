package main;

import java.io.IOException;
import java.util.List;

import io.InputStreamBitSource;
import io.InsufficientBitsLeftException;

public class HuffmanDecodeTree {

	private HuffmanNode _root;
	
	public HuffmanDecodeTree(List<SymbolWithCodeLength> symbols_with_code_lengths) throws Exception {

		// Create empty internal root node.
		_root = new InternalHuffmanNode();
		
		// Insert symbols into the tree
		for(int i = 0; i != 255; i++) _root.insertSymbol(symbols_with_code_lengths.get(i).codeLength(),symbols_with_code_lengths.get(i).value());
		
		// If all went well, your tree should be full
		assert _root.isFull();
	}

	public int decode(InputStreamBitSource bit_source) throws Exception {
		
		// Start at the root
		HuffmanNode n = _root;
		
		// Zeroes left, ones right
		while (!n.isLeaf()) {
			int next_bit = bit_source.next(1);
			if(next_bit==0) n=n.left();
			else			n=n.right();
		}
		
		// Return symbol at leaf
		return n.symbol();
	}

}
