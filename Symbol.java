/*
 * FILENAME: Symbol.java
 * NAME: Hanhong Lu
 * DATE: 11/30/2010
 * PURPOSE: Reads the sixteen symbols from a file and stores them into an array
 */
import java.util.*; //to use scanner
import java.io.*; //java i/o package

public class Symbol {

    //instance variables
    private String[] symbols;

    /**
     * Constructs a Symbol object which reads from the "Symbols.txt" file
     * and stores the symbols into the array
     */
    public Symbol() {
  symbols = new String[16];
	int i = 0;
	try { //read from the file
	    Scanner reader = new Scanner(new File("Symbols.txt"));
	    while (reader.hasNext()) {
		symbols[i] = reader.nextLine();
		i++;
	    }
	} catch (FileNotFoundException ex) {
	    System.out.println("Can't find the symbols file");
	}
    }

    /**
     * Returns the symbol stored at the given index of the array.
     *
     * @param index The symbol's index in the array
     * @return String a string representation of the symbol
     */
    public String getSymbol(int index) {
	return symbols[index];
    }

    //test
    public static void main (String[] args) {
	Symbol test = new Symbol();
	for (int i = 0; i < 16; i++) {
	    System.out.print(test.getSymbol(i) + "; ");
	}
	System.out.println();
    }

}
