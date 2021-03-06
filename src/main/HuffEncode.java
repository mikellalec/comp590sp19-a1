package main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import io.InputStreamBitSource;
import io.OutputStreamBitSink;

public class HuffEncode {

	public static void main(String[] args) throws Exception {
		String input_file_name = "data/uncompressed.txt";
		String output_file_name = "data/recompressed.txt";

		FileInputStream fis = new FileInputStream(input_file_name);

		int[] symbol_counts = new int[256];
		int num_symbols = 0;
		
		// Read in each symbol (i.e. byte) of input file and 
		// update appropriate count value in symbol_counts
		// Should end up with total number of symbols 
		// (i.e., length of file) as num_symbols
		int next_byte = fis.read();
		
		while(next_byte!=-1) {
			symbol_counts[next_byte]++;
			num_symbols++;
			next_byte = fis.read();
		}
		
		// Close input file
		fis.close();
		
		// Initialize entropy counters
		double entropy = 0;
		double myEntropy = 0;
		
		// Create array of symbol values
		int[] symbols = new int[256];
		for (int i=0; i<256; i++) {
			symbols[i] = i;
			// Display probabilities for part 3
			Double probability = new Double((double)symbol_counts[i]/(double)num_symbols);
			// if(symbol_counts[i]>0) System.out.println(i + " has " + symbol_counts[i] + " occurrences, probability " + probability.toString());
			if(probability>0) entropy+=((double)probability*(-1)*(Math.log((double)probability))/Math.log(2));
		}
		
		//Bits per symbol theoretical compression limit
		System.out.println("Theoretical entropy, bits per symbol: " + entropy);
		
		// Create encoder using symbols and their associated counts from file.
		HuffmanEncoder encoder = new HuffmanEncoder(symbols, symbol_counts);
		
		// Open output stream.
		FileOutputStream fos = new FileOutputStream(output_file_name);
		OutputStreamBitSink bit_sink = new OutputStreamBitSink(fos);

		// Write out code lengths for each symbol as 8 bit value to output file.
		for(int i = 0; i != 256; i++) {
			Double probability = new Double((double)symbol_counts[i]/(double)num_symbols);
			if(probability > 0) myEntropy+=((double)probability*(double)encoder.getCode(i).length());
			bit_sink.write(encoder.getCode(i).length(),8);
		}
		
		//Bits per symbol entropy compression from recompressed file
		System.out.println("Recompressed entropy, bits per symbol: " + myEntropy);
		
		// Write out total number of symbols as 32 bit value.
		bit_sink.write(num_symbols,32);
		
		// Reopen input file.
		fis = new FileInputStream(input_file_name);
		
		// Go through input file, read each symbol (i.e. byte),
		// look up code using encoder.getCode() and write code
        // out to output file.
		next_byte = fis.read();
		while(next_byte!=-1) {
			encoder.encode(next_byte, bit_sink);
			next_byte = fis.read();
		}

		// Pad output to next word.
		bit_sink.padToWord();

		// Close files.
		fis.close();
		fos.close();
	}
}

