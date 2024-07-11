package music;

import java.io.File;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;

import com.mpatric.mp3agic.Mp3File;

//Describing the song
public class Song {
	private String songTitle;
	private String songArtist;
	private String songLength;
	private String  filePath;
	private Mp3File mp3File;
	private double frameRatePerMilliSeconds;
	
	public Song(String filePath) {
		this.filePath=filePath;
		try {
			//with this object we can get specific information about our mp3 file
			mp3File=new Mp3File(filePath);
			//if we multiply evt.getframe() by total frames of the song divided by ms(song length) we get the current frame to resume properly
			frameRatePerMilliSeconds=(double)mp3File.getFrameCount()/mp3File.getLengthInMilliseconds();
			songLength=getConvertToSongLengthFormat();
			
			//using jaudiotagger library to create an audio file object to read mp3 file's information
			AudioFile audiofile=AudioFileIO.read(new File(filePath));
			
			//read through the meta data of audio file
			Tag tag=audiofile.getTag();
			if(tag!=null) {
				songTitle=tag.getFirst(FieldKey.TITLE);
				songArtist=tag.getFirst(FieldKey.ARTIST);
			}
			else {
				//could not read through mp3 file meta data
				songTitle="N/A";
				songArtist="N/A";
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//converting total seconds to minute and second
	private String getConvertToSongLengthFormat() {
		long min=mp3File.getLengthInSeconds()/60;
		long sec=mp3File.getLengthInSeconds()%60;
		String formattedTime=String.format("%02d:%02d",min,sec);
		return formattedTime;
	}
	//getters

	public String getSongTitle() {
		return songTitle;
	}

	public String getSongArtist() {
		return songArtist;
	}

	public String getSongLength() {
		return songLength;
	}

	public String getFilePath() {
		return filePath;
	}
	
	public Mp3File getMp3File() {
		return mp3File;
	}
	public double getFrameRateperMilliSeconds() {
		return frameRatePerMilliSeconds;
	}
}
