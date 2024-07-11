package music;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class playListDialogBox extends JDialog{
	private musicPlayer mpPlayer;
	
	ArrayList<String>songList;
	public playListDialogBox(musicPlayer mpPlayer) {
		this.mpPlayer=mpPlayer;
		//instantiate the song paths list
		songList=new ArrayList<>();
		
		//store all the paths to be written to txt file(when we load a playlist)
		
		//Dialog Configuration
		setTitle("Create your PlayList");
		setSize(500,500);
		setResizable(false);
		getContentPane().setBackground(musicPlayer.DIALOG);
		setLayout(null);
		setModal(true);  // this property makes it so that the dialog has to be closed to give focus
		
		setLocationRelativeTo(mpPlayer);
		
		addDialogComponents();
	}
	
	private void addDialogComponents() {
		//container to hold each song path
		JPanel songContainer=new JPanel();
		songContainer.setLayout(new BoxLayout(songContainer,BoxLayout.Y_AXIS));
		songContainer.setBounds((int)(getWidth()*0.03), 13, (int)(getWidth()*0.91), (int)(getHeight()*0.75));
		add(songContainer);
		
		//add-song button
		JButton addSong=new JButton("ADD MP3 FILE");
		addSong.setBounds(35,(int)(getHeight()*0.82),130,25);
		addSong.setFont(new Font("Calibri",Font.PLAIN,14));
		addSong.setBackground(Color.YELLOW);
		addSong.setForeground(Color.BLUE);
		add(addSong);
		//add functionality
		addSong.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				//open file explorer
				JFileChooser jfc=new JFileChooser();
				jfc.setFileFilter(new FileNameExtensionFilter("MP3","mp3"));
				jfc.setCurrentDirectory(new File("src/resources/images"));
				int result=jfc.showOpenDialog(playListDialogBox.this);
				
				File selectedFile=jfc.getSelectedFile();
				if(result==jfc.APPROVE_OPTION && selectedFile!=null) {
					JLabel filepath=new JLabel(selectedFile.getPath());
					filepath.setFont(new Font("Calibri",Font.BOLD,16));
					filepath.setBorder(BorderFactory.createLineBorder(Color.BLACK));
					
					//add to the list
					songList.add(filepath.getText());
					
					//add to the container
					songContainer.add(filepath);
					
					//refreshes dialog to show newly added jLabel
					songContainer.revalidate();
				}
			}
		});
		
		//Save-playlist button
		JButton savePlaylist=new JButton("SAVE PLAYLIST");
		savePlaylist.setBounds(320,(int)(getHeight()*0.82),130,25);
		savePlaylist.setFont(new Font("Calibri",Font.PLAIN,14));
		savePlaylist.setBackground(Color.BLUE);
		savePlaylist.setForeground(Color.YELLOW);
		add(savePlaylist);
		
		//add functionality
		savePlaylist.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				
					try {
						JFileChooser jfc=new JFileChooser();
						jfc.setCurrentDirectory(new File("src/resources/images"));
						int result=jfc.showSaveDialog(playListDialogBox.this);
						if(result==jfc.APPROVE_OPTION) {
							//we use getselectedFile() to get reference to the file that we are about to save
							File selectedFile=jfc.getSelectedFile();
							
							//convert to .txt file if not done so already
							//this will check to see if the file does not have the .txt file extension
							//Extracting the Last Four Characters of the File Name
							if(!selectedFile.getName().substring(selectedFile.getName().length()-4).equalsIgnoreCase(".txt")) {
								selectedFile=new File(selectedFile.getAbsoluteFile()+".txt");
							}
							
							//create a new file at a target destination directory
						selectedFile.createNewFile();
						
						//now we will write all of the song paths into this file
						FileWriter fw=new FileWriter(selectedFile);
						BufferedWriter bw=new BufferedWriter(fw);
						
						//iterate through our song paths list and write each string into the file
						//each song will be written in their own row
						for(String it:songList) {
							bw.write(it+"\n");
						}
						bw.close();
						}
						
						//display success dialog
						JOptionPane.showMessageDialog(playListDialogBox.this, "PLAYLIST CREATED SUCCESSFULLY");
						
						//close this dialog
						playListDialogBox.this.dispose();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
		});
	}
}
