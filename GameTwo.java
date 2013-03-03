/*
 * FILENAME: GameTwo.java
 * NAME: Hanhong Lu
 * DATE: 30/11/2010
 * PURPOSE: Plays the second game
 */

import java.lang.Double;
import java.util.LinkedList;
import java.util.Random;

public class GameTwo {

    //instance variables
    private String answer;
    private Tree<Integer> indexTree;
    private Symbol options;
    private LinkedList<String> possible;
    private LinkedList<String> impossible;

    /**
     * Constructs a GameTwo object, and initializes all the instance variables
     */
    public GameTwo() {
  answer = "";
	indexTree = makeTree(1,1);
	options = new Symbol();
	possible = new LinkedList<String>();
	for (int i = 0; i < 16; i++) { //initially, all symbols are possible
	    possible.add(options.getSymbol(i));
	}
	//Test:System.out.println(possible);
	impossible = new LinkedList<String>();
    }

    /**
     * Creates and returns an initial index tree, 
     * which coresponds to the symbols stored in the 
     *
     * @param nodeLabel the index value of the root
     * @return Tree<Integer> the initial tree 
     */
    public Tree<Integer> makeTree(int nodeLabel, int currentLevel) {
	if (currentLevel == 5) {//at the bottom level
	    Tree<Integer> tree = Tree.node(nodeLabel, new Tree<Integer>(), new Tree<Integer>());
	    /*TEST:
	    System.out.println(nodeLabel);
	    */
	    return tree;
	} else { //not the bottom level
	    Double d = Math.pow(2,currentLevel-1);
	    int rightChild = 8/d.intValue() + nodeLabel;
	    /*TEST:
	    System.out.println("Right child is: " + rightChild);
	    System.out.println("Root is: " + nodeLabel);
	    System.out.println("Level is : " + currentLevel);
	    */
	    currentLevel++; 
	    return Tree.node(nodeLabel, makeTree(nodeLabel,currentLevel), makeTree(rightChild,currentLevel));
	}
    }

    /**
     * Check what button is the user clicking,
     * (by the click input) 
     * and goes to either left child or right child
     *
     * @param click 0 represents left, 1 represents right
     */
    public void playNextLevel(int click) {
	//since items will be removed from the linked list, memorize the original size before removing anything
	int size = possible.size();
	if (click == 0) { //left tree
	    indexTree = Tree.getLeft(indexTree);
	    for (int i = size/2; i<size; i++) {
		/*TEST:
		System.out.println(i);
		System.out.println(possible.get(size/2));
		*/
		//remove all the symbols on the right to impossible
		impossible.add(possible.remove(size/2));
	    }
	} else {//right tree
	    indexTree = Tree.getRight(indexTree);
	    for (int i = 0; i<size/2; i++) {
		//TEST:System.out.println(possible.get(0));
		//remove all the symbols on the left to impossible
		impossible.add(possible.remove(0));
	    }
	}
	//TEST:System.out.println(indexTree);
    }

    /**
     * Show the first half symbols from the possible symbols,
     * and choose random symbols from the impossible symbols
     * to make number of the total chosen symbols equals to 8
     *
     * @return LinkedList<String> a list of all the symbols ready to be shown
     */
    public LinkedList<String> showSymbols() {
	LinkedList<String> show = new LinkedList<String>();
	for (int i = 0; i<possible.size()/2; i++) { //first half
	    show.add(possible.get(i));
	}
	int left = 8 - possible.size()/2;
	for (;left > 0;left--) { //to make the number equals to 8
	    show.add(impossible.get(left));
	}
	shuffle(show);
	//TEST:System.out.println(show);
	return show;
    }

    public void shuffle(LinkedList<String> newSymbols) {
	Random randomIndex = new Random();
	for (int i=0; i<8; i++) {
	    int randomPosition = randomIndex.nextInt(8);
	    String temp = newSymbols.get(i);
	    newSymbols.set(i, newSymbols.get(randomPosition));
	    newSymbols.set(randomPosition, temp);
	}
    }
    /**
     * Suppose has already reached the bottom level
     * return the current root
     * i.e., the answer
     *
     * @return String the answer
     */
    public String playGameTwo() {
	int index = Tree.getValue(indexTree) - 1; //indices stored in the tree start from 1 
	/*
	  TEST:
	  System.out.println("answer index: " + index);
	  for (int i = 0; i<16; i++)
	  System.out.println(options.getSymbol(i));
	*/
	return getContent(index);
    }

    /**
     * Return the symbol stored in the given index
     *
     * @return String the desired symbol
     */
    public String getContent(int index) {
	return options.getSymbol(index);
    }

    /*TEST:
    public static void main (String[] args) {
	GameTwo test = new GameTwo();
	System.out.println(test.indexTree);

	test.playNextLevel(1);
	test.showSymbols();
    }
    */
}
