package music;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.ArrayList;

import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;

public class Implementation extends PlaybackListener {
	
	//this will be used to update pause in more synchronized manner
	private static final Object playSignal=new Object();
	
	//need reference so that we can update GUI in this class
	private musicPlayer mpPlayer;
	
	//we will need a way to store our song details, so we will create a song class
	private Song currentSong;
	public Song getCurrentSong() {
		return currentSong;
	}
	private ArrayList<Song>playList;
	
	//we need to keep the track of the index we are in the playlist
	private int currentPlaylistIndex;
	
	//use JLayer Library to create an AdvancedPlayer object which will handle playing music
	private AdvancedPlayer advply;
	
	//pause boolean flag used to indicate whether the player has been paused
	private boolean isPaused;
	
	//used to tell when song is finished
	private boolean songFinished;
	
	private boolean pressedNext,pressedPrev;
	
	//stores the last frame when the playback is finished(pausing and resuming)
	private int currentFrame;
	public void setCurrentFrame(int frame) {
		currentFrame=frame;
	}
	
	//track how many milliseconds have been passed since playing the song (used for updating slider)
	private int currtimemilli;
	public void setcurrtimemilli(int timeinmilli) {
		currtimemilli=timeinmilli;
	}
	
	//invoking constructor
	public Implementation(musicPlayer mpPlayer) {
		this.mpPlayer=mpPlayer;
	}
	
	public void loadPlaylist(File playlistfile) {
		playList=new ArrayList<>();
		
		//store the paths from the text file into a Playlist arraylist
		try {
			FileReader fr=new FileReader(playlistfile);
			BufferedReader br=new BufferedReader(fr);
			
			//read each line from the text file and store it in a songPath variable
			String songPath;
			while((songPath=br.readLine())!=null) {
				Song song=new Song(songPath);
				
				//add to playlist arraylist
				playList.add(song);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		if(playList.size()>0) {
			//reset playback slider
			mpPlayer.setPlayBackSliderValue(0);
			currtimemilli=0;
			
			//update current song to the first song in the playlist
			currentSong=playList.get(0);
			
			//start from the beginning frame
			currentFrame=0;
			
			//update GUI
			mpPlayer.togglePlayandPausefunc1();
			mpPlayer.updateSongTitleandArtist(currentSong);
			mpPlayer.updatePlaybackSlider(currentSong);
			
			//start song
			playCurrentSong();
		}
	}
	
	public void LoadSong(Song song) {
		currentSong=song;
		playList=null;
		
		//stop the song if possible
		if(!songFinished) {
			stopSong();
		}
		
		//play the current song if not null
		if(currentSong!=null) {
			//reset frame
			currentFrame=0;
			
			//reset current time in milli
			currtimemilli=0;
			
			//update GUI
			mpPlayer.setPlayBackSliderValue(0);
			playCurrentSong();
		}
	}
	
	public void pauseCurrentSong() {
		if(advply!=null) {
			//update pause flag
			isPaused=true;
			
			//then we want to stop the player
			stopSong();
		}
	}
	
	public void stopSong() {
		if(advply!=null) {
			advply.stop();
			advply.close();
			advply=null;
		}
	}
	
	public void playCurrentSong() {
		//remove exception of selecting
		if(currentSong==null) return;
		try {
			//read mp3 data
			FileInputStream fis=new FileInputStream(currentSong.getFilePath());
			BufferedInputStream bis=new BufferedInputStream(fis);
			
			//create a Advanced Player
			advply=new AdvancedPlayer(bis);
			advply.setPlayBackListener(this);
			
			//start music
			startMusicThread();
			
			//start play back slider thread
			startPlayBackSliderThread();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void nextSongfunc() {
		//if there is nothing in playlist, then do nothing
		if(playList==null) {
			return;
		}
		
		//check to see if we have reached the end of the playlist
		if(currentPlaylistIndex+1>playList.size()-1) {
			return;
		}
		
		pressedNext=true;
		//stop the song if possible
		if(!songFinished) {
			stopSong();
		}
		
		
		//increase current playlist index
		currentPlaylistIndex++;
		
		//update current song
		currentSong=playList.get(currentPlaylistIndex);
		
		//reset frame
		currentFrame=0;
		
		//reset current time in milli
		currtimemilli=0;
		
		//update GUI
		mpPlayer.togglePlayandPausefunc1();
		mpPlayer.updatePlaybackSlider(currentSong);
		mpPlayer.updateSongTitleandArtist(currentSong);
		
		//play the song
		playCurrentSong();
	}
	
	public void prevSongfunc() {
		//if there is nothing in playlist, then do nothing
		if(playList==null) {
			return;
		}
		//check to see if we can go to previous song
		if(currentPlaylistIndex-1<0) {
			return;
		}
		pressedPrev=true;
		//stop the song if possible
		if(!songFinished) {
			stopSong();
		}
				
		//decrease current playlist index
		currentPlaylistIndex--;
				
		//update current song
		currentSong=playList.get(currentPlaylistIndex);
				
		//reset frame
		currentFrame=0;
				
		//reset current time in milli
		currtimemilli=0;
				
		//update GUI
		mpPlayer.togglePlayandPausefunc1();
		mpPlayer.updatePlaybackSlider(currentSong);
		mpPlayer.updateSongTitleandArtist(currentSong);
				
		//play the song
		playCurrentSong();
	}
	
	//creating a thread for handling play of music
	private void startMusicThread() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("isPaused "+ isPaused);
				try {
					
					if(isPaused) {
						//creating synchronized block
						synchronized(playSignal) {
							//update flag
							isPaused=false;
							
							//notify to other  thread to continue(makes sure that isPaused is updated to false properly)
							playSignal.notify();
						}
						//resume music from last frame
						// Integer.MAX_VALUE ensures it plays until the end of the file
						advply.play(currentFrame, Integer.MAX_VALUE);
					}
					else {
						//play music from beginning
						advply.play();
					}
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	//create a thread that will handle updating the slider
	private void startPlayBackSliderThread() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				if(isPaused) {
					try {
						//wait till it gets notified by other thread to continue
						//makes sure that isPaused flag becomes false before continuing
						synchronized(playSignal) {
							playSignal.wait();
						}
					}
					catch(Exception e) {
						e.printStackTrace();
					}
				}
				
				System.out.println("isPaused: "+isPaused);
				while(!isPaused && !songFinished && !pressedNext && !pressedPrev) {
					try {
						//increment current time milli
						currtimemilli++;
						
//						System.out.println(currtimemilli*2.08);
						
						//calculate into frame value
						int calculatedTime=(int)((double) currtimemilli*2.08*currentSong.getFrameRateperMilliSeconds());
						
						//update GUI
						mpPlayer.setPlayBackSliderValue(calculatedTime);
						
						//mimic 1 millisecond using thread.sleep
						Thread.sleep(1);
					}
					catch(Exception e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
	@Override
	public void playbackStarted(PlaybackEvent evt) {
		//this method gets call in the beginning of the song
		System.out.println("Playback Started");
		songFinished=false;
		pressedNext=false;
		pressedPrev=false;
		
	}
	
	@Override
	public void playbackFinished(PlaybackEvent evt) {
		//this method gets call in the ending of the song or when the player closes
		System.out.println("Playback finished");
//		System.out.println("Actual Stop "+evt.getFrame());
		//we can get the current position of the song when it has paused(in milliseconds) and this value can be used to resume from that position
		
		if(isPaused) {
			currentFrame+=(int)((double)evt.getFrame()*currentSong.getFrameRateperMilliSeconds());
//			currentFrame+=evt.getFrame();
			//System.out.println("Stopped @"+currentFrame);
		}
		else {
			//if the user pressed next or prev we don't need to execute the rest of the code
			if(pressedNext || pressedPrev) return;
			//when the song ends in the play list
			songFinished=true;
			if(playList==null) {
				//update GUI
				mpPlayer.togglePlayandPausefunc2();
			}
			else {
				//last song in the playlist
				if(currentPlaylistIndex==playList.size()-1) {
					//update GUI
					mpPlayer.togglePlayandPausefunc2();
				}
				else {
					//go to the next song in the play list
					nextSongfunc();
				}
			}
			
		}
		//if multiply evt.getframe() by total frames of the song divided by ms(song length) we get the current frame to resume properly
		
	}
	
	
}
