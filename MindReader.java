/*
 * FILENAME: MindReader.java
 * NAME: Hanhong Lu
 * DATE: 1/12/2010
 * PURPOSE: Creates and displays the GUI. Invokes Game 1 and Game 2 according
 * to the user's choice. Check the answer in the end.
 */

import java.awt.*; //import java's original AbstractWindowToolkit
import java.awt.event.*;//import events from java's original AbstractWindowToolkit
import javax.swing.*;//import events java's swing GUI classes
import javax.swing.event.*;//import events java's swing Gui classes
import java.util.LinkedList;

public class MindReader extends JApplet implements ActionListener{

    //instance variables
    private JLabel title, intro, rule1, rule2, question, showAnswer, answer;
    private JLabel[] symbolsLabel1, symbolsLabel2;
    private JButton game1Button, game2Button, continueButton, guessButton, yesButton, noButton, replayButton, quitButton, backButton;
    private JPanel symbolsPanel, labelPanel, buttonPanel;

    private GameOne game1;
    private GameTwo game2;
    private int level;

    /**
     * Constructs a MindReader object, and initializes the GUI variables
     */
    public MindReader() {

  symbolsLabel1 = new JLabel[100];
	symbolsLabel2 = new JLabel[16];

	//initialize labels
	title = new JLabel("Mind Reader", JLabel.CENTER);
	title.setFont(new Font("Verdana", Font.BOLD, 45));

	intro = new JLabel("Welcome! Please choose to play Game1 or Game2.");
	intro.setFont(new Font("Calibri", Font.PLAIN, 15));

	rule1 = new JLabel("<html>Please choose a number from 10 to 99, adds the two digits together, and subtracts the sum from the original number.(eg, if you choose 87, then your final number is 87-(8+7) = 72.) Then, find the relevant symbol to your final number. Now, click 'Continue'! </html>");
	rule1.setFont(new Font("Calibri", Font.PLAIN, 15));
	rule1.setPreferredSize(new Dimension(300, 300)); //word wrap

	rule2 = new JLabel("<html>Please choose one symbol from the sixteen symbols shown on the right. Now, click 'Guess'!</html>");
	rule2.setFont(new Font("Calibri", Font.PLAIN, 15));
	rule2.setPreferredSize(new Dimension(300, 300)); //word wrap

	showAnswer = new JLabel("Your symbol is: ");
	showAnswer.setFont(new Font("Calibri", Font.BOLD, 15));

	question = new JLabel("<html>Are your symbol among the symbols on the right?</html>");
	question.setFont(new Font("Calibri", Font.PLAIN, 15));
	question.setPreferredSize(new Dimension(300, 300)); //word wrap
	
	

	//initialize buttons
	game1Button = new JButton("Game1");
	game1Button.setForeground(Color.red);
	game1Button.setFont(new Font("Verdana", Font.PLAIN, 10));
	game1Button.addActionListener(this);

	game2Button = new JButton("Game2");
	game2Button.setForeground(Color.red);
	game2Button.setFont(new Font("Verdana", Font.PLAIN, 10));
	game2Button.addActionListener(this);

	quitButton = new JButton("QUIT");
	quitButton.setFont(new Font("Calibri", Font.PLAIN, 10));
	quitButton.addActionListener(this);

	backButton = new JButton("Back");
	backButton.setFont(new Font("Verdana", Font.PLAIN, 10));
	backButton.addActionListener(this);

	continueButton = new JButton("Continue!");
	continueButton.setForeground(Color.blue);
	continueButton.setFont(new Font("Verdana", Font.PLAIN, 15));
	continueButton.addActionListener(this);

	guessButton = new JButton("Guess!");
	guessButton.setForeground(Color.blue);
	guessButton.setFont(new Font("Verdana", Font.PLAIN, 15));
	guessButton.addActionListener(this);

	yesButton = new JButton("YES");
	yesButton.setForeground(Color.blue);
	yesButton.setFont(new Font("Verdana", Font.PLAIN, 15));
	yesButton.addActionListener(this);

	noButton = new JButton("NO");
	noButton.setForeground(Color.blue);
	noButton.setFont(new Font("Verdana", Font.PLAIN, 15));
	noButton.addActionListener(this);

	replayButton = new JButton("This is so Cool! Play Again!");
	replayButton.setForeground(Color.blue);
	replayButton.setFont(new Font("Verdana", Font.PLAIN, 15));
	replayButton.addActionListener(this);

	//initialize Panels
	labelPanel = new JPanel();
	symbolsPanel = new JPanel();
	buttonPanel = new JPanel();
    }

    /**
     * Creates the initial GUI.
     */
    public void init() {
	//title panel. On the top of the frame
	JPanel titlePanel = new JPanel();
	titlePanel.setLayout(new BorderLayout(2, 2));
	//four choices
	JPanel choicePanel = new JPanel();
	choicePanel.add(game1Button);
	choicePanel.add(game2Button);
	choicePanel.add(backButton);
	choicePanel.add(quitButton);

	titlePanel.add(title, BorderLayout.CENTER);
	titlePanel.add(choicePanel, BorderLayout.SOUTH);

	//left side of the frame
	JPanel interactPanel = new JPanel();
	interactPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
	interactPanel.add(makeLabelPanel(0));
	interactPanel.add(makeButtonPanel(0));

	//the main part of the GUI
	JPanel eventPanel = new JPanel();
	eventPanel.setLayout(new GridLayout(1,2,10,10));
	eventPanel.add(interactPanel);
	eventPanel.add(makeSymbolsPanel(0));

	//add components to the Applet
	this.add(titlePanel, BorderLayout.NORTH);
	this.add(eventPanel, BorderLayout.CENTER);

    }

    /**
     * Creates different GUI for the right side of the frame
     * according to the input integer
     *
     * @param mark 0 - main menu, showing nothing; 1 - show game1's symbols;
     * 2 - show game2's symbols; 3 - show answer
     * @return JPanel the corresponding panel
     */
    private JPanel makeSymbolsPanel(int mark) {
	if (mark == 0) { //nothing
	} else if (mark == 1) {//game one - 100 symbols
	    symbolsPanel.setLayout(new GridLayout(10, 10, 5, 5));
	    for (int i = 99; i >= 0; i--) {
		symbolsLabel1[i] = new JLabel(game1.getContent(i), JLabel.CENTER);
		symbolsLabel1[i].setFont(new Font("Calibri", Font.PLAIN, 10));
		symbolsLabel1[i].setOpaque(true);
		symbolsLabel1[i].setBackground(Color.white);
		symbolsPanel.add(symbolsLabel1[i]);
	    }
	} else if (mark == 2) { //game two - 16 symbols
	    symbolsPanel.setLayout(new GridLayout(4, 4, 5, 5));
	    for (int i = 0; i < 16; i++) {
		symbolsLabel2[i] = new JLabel(game2.getContent(i), JLabel.CENTER);
		symbolsLabel2[i].setFont(new Font("Calibri", Font.PLAIN, 30));
		symbolsLabel2[i].setOpaque(true);
		symbolsLabel2[i].setBackground(Color.white);
		symbolsPanel.add(symbolsLabel2[i]);
	    }
	} else if (mark == 3) { //yes or no question, show 8 options
	    symbolsPanel.setLayout(new GridLayout(4, 2, 10, 10));
	    LinkedList<String> show = game2.showSymbols();
	    //TEST:System.out.println(show);
	    for (int i = 0; i < 8; i++) {
		symbolsLabel2[i] = new JLabel(show.get(i), JLabel.CENTER);
		symbolsLabel2[i].setFont(new Font("Calibri", Font.PLAIN, 30));
		symbolsLabel2[i].setOpaque(true);
		symbolsLabel2[i].setBackground(Color.white);
		symbolsPanel.add(symbolsLabel2[i]);
	    }
	} else if (mark == 4) { //show answer for game1
	    answer = new JLabel(game1.getAnswer());
	    answer.setFont(new Font("Calibri", Font.BOLD, 40));
	    answer.setForeground(Color.magenta);
	    symbolsPanel.add(answer);
	} else if (mark == 5) { //show answer for game2
	    answer = new JLabel(game2.playGameTwo());
	    answer.setFont(new Font("Calibri", Font.BOLD, 40));
	    answer.setForeground(Color.magenta);
	    symbolsPanel.add(answer);
	}
	return symbolsPanel;
    }

    /**
     * Creates different GUI of labels according to the input integer
     *
     * @param mark 0 - main menu, let the user choose; 1 - rule for the first game; 2 - rule for the second game; 3 - ready to show the answer
     * @return JPanel the corresponding panel
     */
    private JPanel makeLabelPanel(int mark) {
	if (mark == 0) { //main menu
	    labelPanel.removeAll();
	    labelPanel.add(intro);
	} else if (mark == 1) { //first game
	    labelPanel.removeAll();
	    labelPanel.add(rule1);
	} else if (mark == 2) { //second game
	    labelPanel.removeAll();
	    labelPanel.add(rule2);
	} else if (mark == 3) { //show answer
	    labelPanel.removeAll();
	    labelPanel.add(showAnswer);
	} else if (mark == 4) { //yes or no question
	    labelPanel.removeAll();
	    labelPanel.add(question);
	}
	labelPanel.updateUI();
	return labelPanel;
    }

    /**
     * Creates different GUI of buttons according to the input integer
     *
     * @param mark 0 - main menu, nothing; 1 - game one, continue button; 2 - yes/no choices; 3 - end of the game, replay
     * @return JPanel the corresponding panel
     */
    private JPanel makeButtonPanel(int mark) {
	if (mark == 0) { //nothing
	} else if (mark == 1) {//game 1
	    buttonPanel.add(continueButton);
	} else if (mark == 2) {//game 2
	    buttonPanel.add(guessButton);
	} else if (mark == 3) { //yes or no button
	    JPanel questionPanel = new JPanel();
	    questionPanel.setLayout(new GridLayout(1, 2, 15, 5));
	    questionPanel.add(yesButton);
	    questionPanel.add(noButton);
	    buttonPanel.add(questionPanel);
	} else if (mark == 4) {//replay the game - go back to main menu
	    buttonPanel.add(replayButton);
	}
	return buttonPanel;
    }

    /**
     * action method is called when users click on the buttons.
     *
     * @param event ActionEvent that the GUI is listening for
     */
    public void actionPerformed(ActionEvent event) {
	Object source = event.getSource();
	if (source.equals(quitButton)) {//quit the applet
	    System.exit(0);
	} else if (source.equals(backButton)) {//back to main menu
	    symbolsPanel.removeAll();
	    makeSymbolsPanel(0);
	    symbolsPanel.updateUI();
	    buttonPanel.removeAll();
	    makeButtonPanel(0);
	    buttonPanel.updateUI();
	    makeLabelPanel(0);
	} else if (source.equals(game1Button)) {//play game1
	    game1 = new GameOne();
	    game1.determineSymbols();
	    symbolsPanel.removeAll();
	    makeSymbolsPanel(1);
	    symbolsPanel.updateUI();
	    buttonPanel.removeAll();
	    makeButtonPanel(1);
	    buttonPanel.updateUI();
	    makeLabelPanel(1);
	} else if (source.equals(game2Button)) {//play game2
	    game2 = new GameTwo();
	    level = 1;
	    symbolsPanel.removeAll();
	    makeSymbolsPanel(2);
	    symbolsPanel.updateUI();
	    buttonPanel.removeAll();
	    makeButtonPanel(2);
	    buttonPanel.updateUI();
	    makeLabelPanel(2);
	} else if (source.equals(continueButton)) {//show the answer for game1
	    symbolsPanel.removeAll();
	    makeSymbolsPanel(4);
	    symbolsPanel.updateUI();
	    buttonPanel.removeAll();
	    makeButtonPanel(4);
	    buttonPanel.updateUI();
	    makeLabelPanel(3);
	} else if (source.equals(guessButton)) {//go to yes/no questions
	    symbolsPanel.removeAll();
	    makeSymbolsPanel(3);
	    symbolsPanel.updateUI();
	    buttonPanel.removeAll();
	    makeButtonPanel(3);
	    buttonPanel.updateUI();
	    makeLabelPanel(4);
	} else if (source.equals(yesButton)) {//user's symbol is among the given symbols
	    if (level != 5) {//still in the process of asking questions
		game2.playNextLevel(0);
		level++;
		symbolsPanel.removeAll();
		makeSymbolsPanel(3);
		symbolsPanel.updateUI();
	    } else {//know the answer noww. i.e., at the bottom level
		symbolsPanel.removeAll();
		makeSymbolsPanel(5);
		symbolsPanel.updateUI();
		buttonPanel.removeAll();
		makeButtonPanel(4);
		buttonPanel.updateUI();
		makeLabelPanel(3);
	    }
	} else if (source.equals(noButton)) {//user's symbol is not among the given symbols
	    if (level != 5) {//still in the process of asking questions
		game2.playNextLevel(1);
		level++;
		symbolsPanel.removeAll();
		makeSymbolsPanel(3);
		symbolsPanel.updateUI();
	    } else {//know the answer noww. i.e., at the bottom level
		symbolsPanel.removeAll();
		makeSymbolsPanel(5);
		symbolsPanel.updateUI();
		buttonPanel.removeAll();
		makeButtonPanel(4);
		buttonPanel.updateUI();
		makeLabelPanel(3);
	    }
	} else if (source.equals(replayButton)) {//end of game, replay
	    symbolsPanel.removeAll();
	    makeSymbolsPanel(0);
	    symbolsPanel.updateUI();
	    buttonPanel.removeAll();
	    makeButtonPanel(0);
	    buttonPanel.updateUI();
	    makeLabelPanel(0);
	}
    }

    /**
     * Creates the applet and shows the GUI,
     */
    private static void createAndShowGUI() {
	//enable window decorations
	JFrame.setDefaultLookAndFeelDecorated(true);
	//set up
	JFrame frame = new JFrame("Mind Reader");
	frame.setSize(800, 600);
	//exit when click on the close button
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	MindReader applet = new MindReader();
	applet.init();
	frame.add(applet, BorderLayout.CENTER);

	//Display the window
	frame.setVisible(true);
    }

    public static void main(String[] agrs) {
	javax.swing.SwingUtilities.invokeLater(new Runnable() {
		public void run() {
		    createAndShowGUI();
		}
	    });
    }

}
