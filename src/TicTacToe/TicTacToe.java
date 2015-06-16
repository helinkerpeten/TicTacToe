package TicTacToe;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class TicTacToe {

	public static void main(String arg[]){

		JFrame frame = new JFrame("  <3 <3 Player 1 ");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Panel panel =new Panel(3,frame);
		frame.add(panel);
		frame.setSize(300,300);
		frame.setVisible(true);

	}

}

class Panel extends JPanel{

	static public boolean player=true;
	static public int [][] table = new int[3][3];
	static int turnNum=0;
	public JFrame frame;

	public Panel(int n,JFrame frame) {
		this.frame=frame;
		setLayout(new GridLayout(n,n));
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				JButton b = new JButton();
				b.addActionListener(new CardPanelButtonActionListener(i,j,frame));
				add(b);				
			}
		}

	}
	class CardPanelButtonActionListener implements ActionListener{
		int i, j;

		public CardPanelButtonActionListener(int i, int j, JFrame frame ){
			this.i = i;
			this.j = j;

		}
		public void actionPerformed(ActionEvent e) { 
			JButton b = (JButton) e.getSource();
			if(b.getIcon()==null){

				if (player){// player true iken birinci oyuncu oynuyor

					b.setIcon(new ImageIcon("xicon.jpg")); 
					table[i][j]=1;
					turnNum++;
					if (winCol(1)||winRow(1)||winDia(1)){
						JOptionPane.showMessageDialog(null, "Player 1 has won!!!");
						System.exit(0);
					}else if(turnNum==9){
						JOptionPane.showMessageDialog(null, "Game tied!!!");
						System.exit(0);
					}

					player=false;
					frame.setTitle("  <3 <3 Player 2 ");
				}else{// player false iken ikinci oyuncu oynuyor

					b.setIcon(new ImageIcon("oicon.jpg"));
					table[i][j]=2;
					turnNum++;
					if (winCol(2)||winRow(2)||winDia(2)){
						JOptionPane.showMessageDialog(null, "Player 2 has won!!!");
						System.exit(0);
					}else if(turnNum==9){
						JOptionPane.showMessageDialog(null, "Game tied!!!");
						System.exit(0);
					}
					player=true;
					frame.setTitle("  <3 <3 Player 1 ");
				}
			}
		}


		// WIN CONDITION
		private boolean winCol(int k) {
			for(int j = 0; j < 3; j++) {
				if(table[0][j] == k && 
						table[0][j] == table[1][j] && 
						table[1][j] == table[2][j]) {
					return true;
				}
			}
			return false;
		}
		private boolean winRow(int k) {
			for(int i = 0; i < 3; i++) {
				if(table[i][0] == k && 
						table[i][0] == table[i][1] && 
						table[i][1] == table[i][2]) {
					return true;
				}
			}
			return false;
		}

		private boolean winDia( int k) {
			if(table[0][0] == k && 
					table[0][0] == table[1][1] && 
					table[1][1] == table[2][2]) {
				return true;
			}
			if(table[0][2] != 0 && 
					table[0][2] == table[1][1] && 
					table[1][1] == table[2][0]) {
				return true;
			}
			return false;
		}

		public boolean hasTieCondition() {
			for(int i = 0; i < 3; i++) {
				for(int j = 0; j < 3; j++) {
					if(table[i][j] == 0)
						return false;
				}
			}

			return true;
		}

	}
}



