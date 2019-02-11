package main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Collections;

import io.InputStreamBitSource;
import io.InsufficientBitsLeftException;

public class HuffDecode {

	public static void main(String[] args) throws Exception {
		String input_file_name = "data/compressed.dat";
		String output_file_name = "data/uncompressed.txt";
		
		FileInputStream fis = new FileInputStream(input_file_name);

		InputStreamBitSource bit_source = new InputStreamBitSource(fis);

		List<SymbolWithCodeLength> symbols_with_length = new ArrayList<SymbolWithCodeLength>();

		// Read in huffman code lengths from input file and associate them with each symbol as a 
		// SymbolWithCodeLength object and add to the list symbols_with_length.
		for(int i = 0; i != 256; i++) {
			int code_length = bit_source.next(8);
			symbols_with_length.add(new SymbolWithCodeLength(i,code_length));
		}
		
		// Sort symbols (length then value)
		Collections.sort(symbols_with_length);
		
		// Shorter length values should be at front of list
		// for(int i = 0; i != 256; i++) System.out.println(symbols_with_length.get(i).codeLength());

		// Now construct the canonical huffman tree
		HuffmanDecodeTree huff_tree = new HuffmanDecodeTree(symbols_with_length);

		// Read in the next 32 bits from the input file  the number of symbols
		int num_symbols = bit_source.next(32);
		
		try {
			// Open up output file.
			FileOutputStream fos = new FileOutputStream(output_file_name);

			for (int i=0; i != num_symbols; i++) {
				int decoded_symbol = huff_tree.decode(bit_source);
				fos.write(decoded_symbol);
			}

			// Flush output and close files.
			fos.flush();
			fos.close();
			fis.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}


