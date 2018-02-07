package view;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import model.TicTacToeGame;

public class GameWindow extends JFrame 
{
	private static final long serialVersionUID = 7363695646035884010L;

	public GameWindow() 
	{
		setLayout(new BorderLayout(5, 5));

		// set up a panel to represent the deck of displayable cards
		final CardLayout cl = new CardLayout();
		final JPanel deck = new JPanel(cl);

		TicTacToeGame Game = new TicTacToeGame(3);
		GamePanel gp = new GamePanel(Game);
		ScoresPanel sp = new ScoresPanel(Game);
		
		Game.addObserver(gp);
		Game.addObserver(sp);
		Game.addObserver(ConsoleLogger.getInstance());
		Game.addObserver(ConsoleLogger.getInstance());
		
		//add the appropriate 'cards' to the deck
		deck.add(gp, "game");
		deck.add(sp, "scores");
		
		//add the deck to the centre of frame's content panel
		getContentPane().add(deck);
		
		
		// set up some radio buttons to be used in controlling which card is displayed
		final JRadioButton game = new JRadioButton("Game", true);
		final JRadioButton highScores = new JRadioButton("High Scores");

		// group the radio buttons
		ButtonGroup bg = new ButtonGroup();
		bg.add(game);
		bg.add(highScores);

		//define an action listener that will display the card based on which radio button is selected 
		ActionListener al = new ActionListener() 
		{
			public void actionPerformed(ActionEvent ae) 
			{
				if (game.isSelected()) 
				{
					cl.show(deck, "game");
				} 
				else 
				{
					cl.show(deck, "scores");
				}
			}
		};
		game.addActionListener(al);
		highScores.addActionListener(al);
		
		// create a panel to hold the radio buttons
		JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		buttons.add(game);
		buttons.add(highScores);
		
		// add the panel holding the radio buttons at the bottom of the frame's content panel
		getContentPane().add(buttons, BorderLayout.SOUTH);

		// set the size of the frame and set it to display
		setSize(400, 240);
		setVisible(true);
		
		this.setLocationRelativeTo(null);

		// set the default operation when the close button is clicked
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}