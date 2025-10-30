package randomiser;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Random;





public class Window {
	
	//Fenster erstellen
	
	JFrame window = new JFrame("Guess the Number!");
	int nmbr;
	JTextField input;
	JButton guessButton;
	JLabel output;
	JLabel feedbackLabel;
	JButton restart;
	int counter;
	JLabel counterText;
	JLabel highscore;
	int fastestScore = 999;

	
	public Window() {
		
		generateNewNumber();
		
		window.setSize(400, 200);
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLayout(null);
		
		
		
		input = new JTextField();
		input.setBounds(270, 30 , 60, 30);
		window.add(input);
		
		guessButton = new JButton("Guess");
		guessButton.setBounds(260, 100, 80, 30);
		window.add(guessButton);
		
		output = new JLabel();
		output.setBounds(30, 50, 300, 30);
		window.add(output);
		
		feedbackLabel = new JLabel("Guess a number between 1 and 100");
		feedbackLabel.setBounds(30, 30, 300, 30);
		window.add(feedbackLabel);
		
		restart = new JButton("Restart");
		restart.setBounds(50, 100, 80, 30);
		restart.setVisible(false);
		window.add(restart);
		
		counterText = new JLabel("Tries: " + counter);
		counterText.setBounds(270, 60, 80, 30);
		window.add(counterText);
		
		highscore = new JLabel("Highscore: " + fastestScore);
		highscore.setBounds(270, 0, 100, 30);
		window.add(highscore);
		
		
		
		guessButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text = input.getText();
				output.setText("Your guess is " + text);
			}
		});
		
		input.addActionListener(e -> {
			String text = input.getText();
			output.setText("Your guess is " + text);
			input.setText("");
		});
		
		restart.addActionListener(e -> {
			generateNewNumber();
			feedbackLabel.setText("Guess a number between 1 and 100");
			input.setText("");
			counter = 0;
			counterText.setText("Tries: " + counter);
			output.setText("");
			restart.setVisible(false);
			input.setVisible(true);
			input.requestFocusInWindow();
		});
		
		input.addActionListener(e -> checkGuess());
		
		window.setVisible(true);
		input.requestFocusInWindow();
		
	}
	
	private void checkHighscore() {
		if (fastestScore > counter) {
			fastestScore = counter;
			highscore.setText("Highscore: " + fastestScore);
		}
	}
	
	private void generateNewNumber() {
		Random randomiser = new Random();
		nmbr = randomiser.nextInt(100) + 1;
	}
	
	private void checkGuess() {
		
		try {
			int guess = Integer.parseInt(input.getText());
			counter++;
			counterText.setText("Tries: " + counter);
			if (guess < 1 || guess > 100) {
				feedbackLabel.setText("Please enter a number between 1 and 100");
			} else if (guess > nmbr) {
				feedbackLabel.setText("You guessed too high. Try again!");
			} else if (guess < nmbr) {
				feedbackLabel.setText("You guessed too low. Try again!");
			} else {
				feedbackLabel.setText("You guessed correctly!");
				checkHighscore();
				input.setVisible(false);
				restart.setVisible(true);
			}
		}
		catch (NumberFormatException ex) {
			feedbackLabel.setText("Invalid input. Please enter a number between 1 and 100");
		}
	}
			
}
