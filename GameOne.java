/*
 * FILENAME: GameOne.java
 * NAME: Hanhong Lu
 * DATE: 30/11/2010
 * PURPOSE: Plays the first game
 */
import java.util.Random;

public class GameOne {

    //instance variables
    private TableEntry[] allSymbols;
    private String answer;

    /**
     * Constructs a GameOne object, and initializes all the instance variables
     */
    public GameOne() {
  allSymbols = new TableEntry[100];
	answer = "";
    }

    /**
     * Assigns the 16 symbols to numbers from 0 to 99,
     * and stores them to the table array
     */
    public void determineSymbols() {
	Symbol symbols = new Symbol();
	Random r =  new Random();
	//determine the answer before assign symbols
	answer = symbols.getSymbol(r.nextInt(16)); 
	//TEST:System.out.println(answer);
	for (int i = 0; i < 100; i++) {
	    int index = r.nextInt(16);
	    if (i%9 == 0) { 
		//if is the multiples of 9, then assign the answer to it
		//TEST:System.out.println(i + " " + answer);
		allSymbols[i]= new TableEntry<String>(Integer.toString(i),answer);
	    } else { //if not, assign random symbols
		//TEST:System.out.println(i + " " + symbols.getSymbol(index));
		allSymbols[i]= new TableEntry<String>(Integer.toString(i),symbols.getSymbol(index));
	    }
	}
    }

    /**
     * Returns the number and its corresponding symbol, eg. "12 #"
     *
     * @param index an integer representing the index of the desired location in the array
     * @return String the number and the symbol
     */
    public String getContent(int index) {
	String s = "";
	s = Integer.toString(index) + "  " + allSymbols[index].value();
	return s;
    }

    /**
     * Invokes determineSymbols,
     * returns and updates the final answer
     *
     * @return String the string representation of the final answer
     */
    public String getAnswer() {
	
	return answer;
    }

    public static void main (String[] args) {
	GameOne test = new GameOne();
	/*TEST:
	int i = 9;
	System.out.println(i%9);
	i = 32;
	System.out.println(i%9);
	i = 27;
	System.out.println(i%9);
	test.determineSymbols();
	System.out.println(test.getContent(18));
	System.out.println(test.getContent(57));
	System.out.println(test.getContent(32));
	System.out.println(test.getContent(0));
	System.out.println(test.getContent(99));
	
	System.out.println(test.getAnswer());
	System.out.println(test.getContent(0));
	*/
    }

}
