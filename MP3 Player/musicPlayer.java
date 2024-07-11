package music;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Hashtable;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
public class musicPlayer extends JFrame{
	//constructor for invoking
	
	//set coloring configurations
	public static final Color FRAME_COLOR=Color.DARK_GRAY;
	public static final Color TEXT_COLOR=Color.YELLOW;
	public static final Color TIME_STAMP=Color.WHITE;
	public static final Color DIALOG=Color.BLACK;
	
	private Implementation implementation;
	
	//allow us to use file explorer in our app
	private JFileChooser jfc;
	
	private JLabel songTitle,songArtist;   //making them global variables
	private JPanel playbackbtns;
	private JSlider playBack;
	public musicPlayer() {
		setTitle("MP3 Player");
		//setting width and height
		setSize(500,600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//launch the app at the center of the screen
		setLocationRelativeTo(null);
		
		setResizable(false);
		//allowing to control the x and y coordinates of the components 
		setLayout(null);
		
		//change the colour
		getContentPane().setBackground(FRAME_COLOR);   //container that holds all components
		
		jfc=new JFileChooser();
		implementation = new Implementation(this); // Initialize the Implementation object in our constructor
		
		//set default path for file explorer
		jfc.setCurrentDirectory(new File("src/resources"));
		addGuiComponents();
		
		//filter file chooser to show mp3 files only
		jfc.setFileFilter(new FileNameExtensionFilter("MP3","mp3"));
		
	}
	
	private void addGuiComponents() {
		//add toolbar
		toolBar();
		
		//Load Record Image
		JLabel recImg=new JLabel(loadImage("src/resources/images/record.png"));
		recImg.setBounds(0, 50, getWidth()-20, 220);
		add(recImg);
		
		
		//add Configuration settings for the title of the song
		songTitle=new JLabel("Song Title");
		songTitle.setBounds(0,285,getWidth()-20,30);
		songTitle.setFont(new Font("Fira Code",Font.PLAIN,20));
		songTitle.setForeground(TEXT_COLOR);
		songTitle.setHorizontalAlignment(SwingConstants.CENTER);
		add(songTitle);
		
		//add song artist
		songArtist=new JLabel("Artist");
		songArtist.setBounds(0, 315, getWidth()-20, 30);
		songArtist.setFont(new Font("Calibri",Font.PLAIN,20));
		songArtist.setForeground(TEXT_COLOR);
		songArtist.setHorizontalAlignment(SwingConstants.CENTER);
		add(songArtist);
		
		//add play back slider
		playBack=new JSlider(JSlider.HORIZONTAL,0,100,0);
		playBack.setBackground(null);
		playBack.setBounds(getWidth()/2-200, 400, 400, 35);
		playBack.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				//when the user is holding the tick we want to pause the song
				implementation.pauseCurrentSong();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				//when the user drops the tick
				JSlider source=(JSlider) e.getSource();
				
				//get the frame value from where the user wants to play back to
				int frame=source.getValue();
				
				//update the current frame in the music Player to this frame
				implementation.setCurrentFrame(frame);
				
				//update current time in milli as well
				implementation.setcurrtimemilli((int)(frame/2.08*implementation.getCurrentSong().getFrameRateperMilliSeconds()));
				
				//resume the song
				implementation.playCurrentSong();
				
				//toggle on pause button and toggle off play button
				togglePlayandPausefunc1();
			}
			
		});
		add(playBack);
		
		//add play back buttons-> prev,next,play,pause
		addPlayBackbuttons();
	}
	
	private void toolBar() {
		JToolBar tb=new JToolBar();
		tb.setBounds(0, 0, getWidth(), 22);
		
		//prevent toolbar from being moved
		tb.setFloatable(false);
		
		//add drop down menu
		JMenuBar jm=new JMenuBar();
		tb.add(jm);
		
		//adding song menu where there will be loading song option
		JMenu songM=new JMenu("Song");
		jm.add(songM);
		
		//adding playlist menu
		JMenu playlistM=new JMenu("Playlist");
		jm.add(playlistM);
		
		//adding items in  songMenu
		JMenuItem loadSong=new JMenuItem("Load file");
		
		
		loadSong.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				//this integer indicates us what the user did
				int result=jfc.showOpenDialog(musicPlayer.this);
				File selectedfile=jfc.getSelectedFile();
				
				//removes the problem of directly playing on loading the file
				//it checks also if the user pressed the "open" button or not
				
				if(result==JFileChooser.APPROVE_OPTION && selectedfile!=null) {
					//create a song object based on selected file
					Song song=new Song(selectedfile.getPath());
					
					//load song in music player
					implementation.LoadSong(song);
					
					//update song title and song artist variables
					updateSongTitleandArtist(song);
					
					//update play back slider
					updatePlaybackSlider(song);
					
					//disable play button
					togglePlayandPausefunc1();  
				}
			}
		});
		songM.add(loadSong);
		
		//adding items in playlistM
		JMenuItem createPlaylist=new JMenuItem("Create Playlist");
		playlistM.add(createPlaylist);
	    createPlaylist.addActionListener(new ActionListener() {
	    	@Override
	    	public void actionPerformed(ActionEvent ae) {
	    		//load music play list dialog box
	    		new playListDialogBox(musicPlayer.this).setVisible(true);
	    	}
	    });
		
		JMenuItem loadPlaylist=new JMenuItem("Load Playlist");
		loadPlaylist.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				JFileChooser jfc=new JFileChooser(); 
				jfc.setFileFilter(new FileNameExtensionFilter("Playlist","txt"));
				jfc.setCurrentDirectory(new File("src/resources/images"));
				
				int result=jfc.showOpenDialog(musicPlayer.this);
				File selectedFile=jfc.getSelectedFile();
				
				if(result==jfc.APPROVE_OPTION && selectedFile!=null) {
					//stop the song
					implementation.stopSong();
					
					//load play list
					implementation.loadPlaylist(selectedFile);
					
				}
			}
		});
		playlistM.add(loadPlaylist);
		
		add(tb);
	}
	
	//using ImageIcon class for loading the images
	private ImageIcon loadImage(String imagePath) {
		try {
			//read the file from the given image path
			BufferedImage img=ImageIO.read(new File(imagePath));
			
			//returns a image icon so that component can render the image
			return new ImageIcon(img);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		//could not find resource then return null
		return null;
	}
	
	private void addPlayBackbuttons() {
		
		//make separate jpanel for buttons
		playbackbtns=new JPanel();
		playbackbtns.setBackground(null);
		playbackbtns.setBounds(0,465,getWidth()-20,100);
		add(playbackbtns);
		
		//previous button
		JButton prevbtn=new JButton(loadImage("src/resources/images/previous.png"));
		prevbtn.setBorderPainted(false);
		prevbtn.setBackground(null);
		playbackbtns.add(prevbtn);
		//add functionality
		prevbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				//go to the next song
				implementation.prevSongfunc();
			}
		});
		
		//play button
		JButton playbtn=new JButton(loadImage("src/resources/images/play.png"));
		playbtn.setBorderPainted(false);
		playbtn.setBackground(null);
		//pause and resume functionality
		playbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				//toggle off play button and toggle on pause button
				togglePlayandPausefunc1();
				
				//play or resume song
				implementation.playCurrentSong();
			}
		});
		
		playbackbtns.add(playbtn);
		
		//pause button
		JButton pausebtn=new JButton(loadImage("src/resources/images/pause.png"));
		pausebtn.setBorderPainted(false);
		pausebtn.setBackground(null);
		pausebtn.setVisible(false);
		pausebtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				//toggle off pause button and set on the play button
				togglePlayandPausefunc2();
				
				//pause the song
				implementation.pauseCurrentSong();
			}
		});
		playbackbtns.add(pausebtn);
		
		//next button
		JButton nextbtn=new JButton(loadImage("src/resources/images/next.png"));
		nextbtn.setBorderPainted(false);
		nextbtn.setBackground(null);
		playbackbtns.add(nextbtn);
		
		//add functionality
		nextbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				//go to the next song
				implementation.nextSongfunc();
			}
		});
	}
	
	public void updateSongTitleandArtist(Song song) {
		songTitle.setText(song.getSongTitle());
		songArtist.setText(song.getSongArtist());
	}
	
	//displaying song length
	public void updatePlaybackSlider(Song song) {
		//update max count for slider
		playBack.setMaximum(song.getMp3File().getFrameCount());
		
		//create a song length label
		Hashtable<Integer,JLabel>labelTable=new Hashtable<>();
		
		//beginning will be 00::00
		JLabel labelbeg=new JLabel("00:00");
		labelbeg.setFont(new Font("Calibri",Font.PLAIN,16));
		labelbeg.setForeground(TIME_STAMP);
		
		//end will vary depending upon the song
		JLabel labelend=new JLabel(song.getSongLength());
		labelend.setFont(new Font("Calibri",Font.PLAIN,16));
		labelend.setForeground(TIME_STAMP);
		
		labelTable.put(0, labelbeg);
		labelTable.put(song.getMp3File().getFrameCount(), labelend);
		
		playBack.setLabelTable(labelTable);
		playBack.setPaintLabels(true);
		
		// Refresh the GUI
//        playBack.revalidate();
//        playBack.repaint();
	}
	
	//Play back functionality that will allow to use slider at any point
	public void setPlayBackSliderValue(int frame) {
		playBack.setValue(frame);
	}
	
	//for disabling play and enabling pause
	public void togglePlayandPausefunc1(){
		//retrieve reference to play button from play back panel
		JButton playbtn=(JButton)playbackbtns.getComponent(1);   //indexing it
		JButton pausebtn=(JButton)playbackbtns.getComponent(2);
		
		//turn off play button
		playbtn.setVisible(false);
		playbtn.setEnabled(false);
		
		//turn on pause button
		pausebtn.setVisible(true);
		pausebtn.setEnabled(true);
	}
	
	
	//for disabling pause and enabling play
	public void togglePlayandPausefunc2(){
		//retrieve reference to play button from play back panel
		JButton playbtn=(JButton)playbackbtns.getComponent(1);   //indexing it
		JButton pausebtn=(JButton)playbackbtns.getComponent(2);
		
		//turn off pause button
		pausebtn.setVisible(false);
		pausebtn.setEnabled(false);
		
		//turn on play button
		playbtn.setVisible(true);
		playbtn.setEnabled(true);
		
	}

}