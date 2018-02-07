package view;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.TicTacToeGame;

public class ScoresPanel extends JPanel implements Observer 
{
	private static final long serialVersionUID = -2684781923442627811L;

	private JLabel jlbNoughtWins;
	private JLabel jlbCrossWins;
	private JLabel jlbDraws;
	private TicTacToeGame game;
	
	public ScoresPanel(TicTacToeGame game) 
	{
		setLayout(new GridLayout(1, 3, 5, 5));
		this.game = game;
		jlbNoughtWins = new JLabel("Noughts won " + game.getNoughtWins());
		jlbCrossWins = new JLabel("Crosses won " + game.getCrossWins());
		jlbDraws = new JLabel("Draws " + game.getDraws());
		
		add(jlbNoughtWins);
		add(jlbCrossWins);
		add(jlbDraws);
	}

	@Override
	public void update(Observable o, Object arg) 
	{
		jlbNoughtWins.setText("Noughts won " + game.getNoughtWins());
		jlbCrossWins.setText("Crosses won " + game.getCrossWins());
		jlbDraws.setText("Draws " + game.getDraws());
		revalidate();
		repaint();	
	}	
}