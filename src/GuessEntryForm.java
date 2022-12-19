package GuessingGame;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class GuessEntryForm {
	
	private int answer;
	private int counter = 0;
	private int roundCounter = 1;
	private long startTime = System.currentTimeMillis();
	private long endTime = System.currentTimeMillis();  
	private ArrayList<String> rounds = new ArrayList<String>();

	public void constructor() {
	}
	public void show() {
		  //max int 1000
	      final int MAX = 1000;
	        //generate rand
	        Random rand = new Random();
	        //rand answer
	        answer = rand.nextInt(MAX) + 1;
	        
	        //creates starting label
	        JLabel low = new JLabel("Guess the number from 1 - 1000 \n Press enter to submit your guess");
	        low.setHorizontalAlignment(SwingConstants.CENTER);
	        low.setVerticalAlignment(SwingConstants.TOP);
	        
	        //creates game frame
	        JFrame frame = new JFrame(); //creates frame
	        frame.setTitle("Guess The Number Game");
	        frame.add(low);
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exit appliction
	        frame.setResizable(false);
	        frame.setSize(500,500); //sets x and y dimension
	        frame.setLocationRelativeTo(null);
	        frame.setVisible(true); // makes frame visible
	        //creates input bar
	        JTextField guessInput = new JTextField();
	        //set size of the text box
	        guessInput.setBounds(115, 100, 250, 50);

	        frame.add(guessInput);

	        frame.getContentPane().setBackground(Color.yellow);
	        KeyListener keyListener = new KeyListener() {
	        		//guess +1 guess when enter key is pressed
	            public void keyPressed(KeyEvent keyEvent) {
	              if (keyEvent.getKeyCode()==KeyEvent.VK_ENTER) {
		              counter++;
	              }
	            
	          }
	            public void keyReleased(KeyEvent keyEvent) {
	              printIt("Released", keyEvent);
	            }
	            public void keyTyped(KeyEvent keyEvent) {
	              printIt("Typed", keyEvent);
	              System.out.println(guessInput.getText()); 
	              try {

	                int guess = Integer.parseInt(guessInput.getText());
	          
	                if (guess == answer) {
	                   endTime = System.currentTimeMillis();

	                    frame.getContentPane().setBackground(Color.green);
	                   low.setText("Good job, the number was " + guess);
	                   frame.add(low);
	                  //creates frame for stats
	                   JFrame play = new JFrame();
	                   play.setTitle("Guess The Number Game Results");
	                   play.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exit appliction
	                   play.setResizable(false);
	                   play.setSize(500,500); //sets x and y dimension
	                   play.setLocationRelativeTo(null);
	                   
	                   play.getContentPane().setBackground(Color.yellow);
	                   
	                   JLabel again = new JLabel("Would you like to play again?", JLabel.CENTER);
	                   play.setLocationRelativeTo(frame);

	                   String statMessage = "Total Guesses: "+ counter + " \n Total Duration: " + ((double)(endTime - startTime)) / 1000;;
	                   JLabel stat = new JLabel(statMessage, JLabel.CENTER);
	                   
	                   again.setBounds(115, 120, 250, 50);
	                   stat.setBounds(115,200, 300, 150);

	                   rounds.add("Round " + roundCounter + " " + statMessage);

	                   JButton yes = new JButton("Yes!");
	                   yes.setBounds(140, 330, 250, 50);
	                   yes.addActionListener(new ActionListener() {
	                    public void actionPerformed(ActionEvent e) {
	                      
	                      roundCounter++;

	                      counter = 0;
	                      // reset the start time for the next round
	                      startTime = System.currentTimeMillis();

	                      // create a new number answer for next round
	                      answer = rand.nextInt(MAX) + 1;

	                      guessInput.setText("");
	                      play.dispose();
	                    }
	                }); 
	                   //adds no button
	                   JButton no = new JButton("No");
	                   no.setBounds(140, 400, 250, 50);
	                   no.addActionListener(new ActionListener() {
	                    public void actionPerformed(ActionEvent e) {
	                       // show all the rounds, use html to let it wrap
	                       //when game ends all stats will display
	                        String endGameMessage = "";
	                        for (String round : rounds) {
	                          endGameMessage = endGameMessage  + round + "<br>";
	                      }
	                        stat.setText("<html>" + endGameMessage + "</html>");
	                   
	                    }
	                });
	                   // sets size and position of game frame for round ++
	                  play.getContentPane().setLayout(null);
	                   play.setSize(500,500);
	                   play.add(stat);
	                   play.add(again);
	                   play.setVisible(true);
	                   play.add(yes);
	                   play.add(no);
	                   
	                }
	                // when answer is lower than guess screen will turn red
	                else if(guess > answer){   
	                    frame.getContentPane().setBackground(Color.red);
	                    low.setText("The Number is Lower than " + guess);
	                    frame.add(low);
	                }
	                //when answer is higher than guess 
	                else if (guess < answer){ 
	                    frame.getContentPane().setBackground(Color.blue);
	                    low.setForeground(Color.white);
	                   low.setText("the number is greater then " + guess);
	                   frame.add(low);
	                } 
	                //handles exception
	              } catch (Exception e) {
	        
	              }
	        
	            }
	            //fix guess issue
	            private void printIt(String title, KeyEvent keyEvent) {
	              int keyCode = keyEvent.getKeyCode();
	              String keyText = KeyEvent.getKeyText(keyCode);
	              System.out.println(title + " : " + keyText);
	            }
	          };
	          
	          guessInput.addKeyListener(keyListener);
	      
	    }


	}