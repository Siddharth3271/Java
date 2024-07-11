package music;

import javax.swing.SwingUtilities;

public class App {

	public static void main(String[] args) {
		//this ensures that our GUI executes event dispatch thread to avoid potential threading issues
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new musicPlayer().setVisible(true);
				
//				Song song=new Song("src/resources/images/Milne Hai Mujhse Aayi.mp3");
//				System.out.println(song.getSongTitle());
//				System.out.println(song.getSongArtist());
			}
		});

	}

}
