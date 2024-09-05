import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.lang.Math;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

class GUI implements ActionListener {

	private JFrame frame = new JFrame(); // making a Jframe Object
	private JPanel buttonPanel = new JPanel();
	private JPanel turnLablePanel = new JPanel();
	private JLabel playerturn = new JLabel();
	private JButton[] buttons = new JButton[9]; // we need a total of 9 buttons so we make that here
	static Font fontType = new Font("Random", Font.BOLD, 120);
	static JButton restartButton = new JButton("Restart");
	static String[] winningRows = {
			"012",
			"345",
			"678",
			"036",
			"147",
			"258",
			"048",
			"246"
	}; // the rows in which a player has made 3 in a row

	static boolean playerTurn; // If player 1 turn is true than player 2 goes second else vice versa
	static double ran = Math.random();
	int Xnumcount;
	int Onumcount;
	int movePossibility = 9;
	boolean Xwinner = false;
	boolean Owinner = false;
	int possibleWinner=0;

	public GUI() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // for the exit button
		frame.setSize(600, 600); // the size of the frame
		frame.getContentPane().setBackground(new Color(50, 50, 50)); // changing the background color of the frame to a
																																	// light black/gray
		// frame.setLocation(300,200);
		frame.setLayout(new BorderLayout());
		frame.setVisible(true);

		playerturn.setBackground(new Color(25, 25, 25));
		playerturn.setForeground(new Color(25, 255, 0));
		playerturn.setHorizontalAlignment(JLabel.CENTER);
		playerturn.setText("");
		playerturn.setOpaque(true);

		turnLablePanel.setLayout(new BorderLayout());
		turnLablePanel.setBounds(0, 0, 250, 250); // making sure it does not go over the bounds I set for it

		turnLablePanel.add(playerturn);

		frame.add(turnLablePanel, BorderLayout.NORTH); // adding the panel to the top

		restartButton.setPreferredSize(new Dimension(100, 50));
		restartButton.addActionListener(this); // this refers to the GUI class
		frame.add(restartButton, BorderLayout.EAST);

		buttonPanel.setLayout(new GridLayout(3, 3)); // intotal there will be 9 buttons
		buttonPanel.setBackground(new Color(150, 150, 150));

		for (int i = 0; i < 9; i++) {
			// This for loop is to make the buttons. Since we are using an array of JButtons
			// we can use a for loop to make each button
			buttons[i] = new JButton();
			buttonPanel.add(buttons[i]);
			buttons[i].setFont(fontType);
			buttons[i].setFocusable(false);
			buttons[i].addActionListener(this); // the this keyword is the GUI

			turnTaker(); // calling the turn taker method so that every time the button is made the
										// players turn is generated
		}

		frame.add(buttonPanel); // adding the buttons to the frame

		for (int j = 0; j < 9; j++) {
			buttons[j].setBackground(Color.WHITE);
			// turning the colors of the buttons to white

		}

	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == restartButton) {

			for (int i = 0; i < 9; i++) {
				buttons[i].setText("");
			}
			for (int j = 0; j < 9; j++) {
				buttons[j].setBackground(Color.WHITE);
				buttons[j].setEnabled(true);

			}
			movePossibility = 9;
			possibleWinner=0;
			
			Xwinner = false;
			Owinner = false;
			turnTaker();

			// if the reset Button is clicked than reset all the buttons to an empty String

		}

		for (int i = 0; i < 9; i++) {
			// This is were I make sure that when a user clicks a button the text X or O
			// will show at that button that they pressed. Is a for loops that keeps
			// checking for the input so that it can add the Text to the buttons.
			if (e.getSource() == buttons[i]) {

				if (playerTurn) {
					if (buttons[i].getText() == "") {
						movePossibility--;
						buttons[i].setForeground(Color.RED);
						buttons[i].setText("X");
						playerTurn = false;
						playerturn.setText("Player O's turn");
						Who_WON();

					}
				} else {
					if (buttons[i].getText() == "") {
						movePossibility--;
						buttons[i].setForeground(Color.BLUE);
						buttons[i].setText("O");
						playerTurn = true;
						playerturn.setText("Player X's turn");
						Who_WON();

					}
				}

			}

		}
	}

	public void turnTaker() {
		// This method makes sure to randomize who turn is taking at the start of the
		// game. So it makes sure that the player can start of being X or O

		if (ran > .5) {
			playerTurn = true;
			playerturn.setText("Player X's turn");
		} else {
			playerTurn = false;
			playerturn.setText("Player O's turn");
		}

	}

	public void counterChecker() {

		// This method is used to check everytime a player wins. A pop up window will
		// appear depending of the winner. The winner will have how many time the player
		// X or O have won so far.

		if (Xwinner) {

			JOptionPane.showMessageDialog(frame, "The player X has won: " + Xnumcount + " time[s] " + movePossibility, "X win counter",
					JOptionPane.ERROR_MESSAGE);

		} else if (Owinner) {

			JOptionPane.showMessageDialog(frame, "The player O has won: " + Onumcount + " time[s] "+ movePossibility , "O win counter",
					JOptionPane.ERROR_MESSAGE);

		} else if (movePossibility == 0 && !Xwinner && !Owinner) {

			JOptionPane.showMessageDialog(frame,
					"The game was a draw, X has won: " + Xnumcount + " time[s] " + "O has won: " + Onumcount + " time[s]",
					"Tie checker", JOptionPane.INFORMATION_MESSAGE);

		}
		if(true){
			JOptionPane.showMessageDialog(frame,
					"possibleWinner: " + possibleWinner ,
					"Tie checker", JOptionPane.INFORMATION_MESSAGE);
		}

	}

	public void tieChecker() {

		

	if(possibleWinner==1){
		
	}else if ((possibleWinner==0 && movePossibility == 0 ) && (!Xwinner && !Owinner)) {
			for (int i = 0; i < 9; i++) {
				buttons[i].setEnabled(false);

			}

			playerturn.setText("It's a draw");
			counterChecker();
			

		}

	}

	public void Who_WON() {

		
		
			tieChecker();


		// This Methods checks to see if X or O had won. It does so by using the arr of
		// possible win combos and checks the button to see if it has the text X or O on
		// it if it does than it changes the buttons colors to green than disables all
		// the other buttons so other player will not be able to play.

		for (int i = 0; i < 9; i++) {
			int first = Integer.valueOf(winningRows[i].substring(0, 1));
			int second = Integer.valueOf(winningRows[i].substring(1, 2));
			int third = Integer.valueOf(winningRows[i].substring(2, 3));

			if (((buttons[first].getText()).equals("X")) && ((buttons[second].getText()).equals("X"))
					&& ((buttons[third].getText()).equals("X"))) {
				possibleWinner++;
				buttons[first].setBackground(Color.GREEN);
				buttons[second].setBackground(Color.GREEN);
				buttons[third].setBackground(Color.GREEN);
				for (int j = 0; j < 9; j++) {
					buttons[j].setEnabled(false);
				}
				playerturn.setText("X Wins");
				Xnumcount++;
				Xwinner = true;
				counterChecker();
				break;

			} else if (((buttons[first].getText()).equals("O")) && ((buttons[second].getText()).equals("O"))
					&& ((buttons[third].getText()).equals("O"))) {
				possibleWinner++;
				buttons[first].setBackground(Color.GREEN);
				buttons[second].setBackground(Color.GREEN);
				buttons[third].setBackground(Color.GREEN);
				for (int j = 0; j < 9; j++) {
					buttons[j].setEnabled(false);
				}

				Onumcount++;
				playerturn.setText("O Wins");
				Owinner = true;
				counterChecker();
				
				break;

			}

		}
		

	}

}