import java.util.Scanner;
import java.text.DecimalFormat;
import java.text.Format;
/**CS121 Section #035
 * 
 * @author[Robbie Gill]
 *
 * This program manages a user's songs given by user input to create a 
 * simplified playlist that then prints to the screen.
 * 
 * */
public class PlayList {
	
	//these are the String values that are prompted to the user, when prompted to add a song
	private static String promptSong = "Enter title: ";
	private static String promptArtist = "Enter artist: ";
	private static String promptTime = "Enter play time (mm:ss): ";// where mm is the number of minutes and ss is the number of seconds.
	private static String promptPath = "Enter file name: ";
	
	//these are the string values that prompt the user when averaging the play times of the songs
	private static String promptAverage = "Average play time for songs: ";
	//Prompts user which song is closest to 4 minutes. 
	private static String promptClosestPlayTime ="Song with play time closest to 240 secs: ";
	//design border
	private static String border = "==============================================================================";
	private static String categoryTitles = "Title                Artist               File Name                  Play Time";

	
	//these are temporary values that are used to create new Song objects..		
	private static String songTitle;
	private static String songArtist;
	private static String playTime;
	private static String filePath;
	
	
	
	private static Scanner reader = new Scanner(System.in);

	/**
	 * method that converts numbers in (mm:ss)
	 * to an integer representing total seconds
	 * @param mmss takes a String number value in format (mm:ss)
	 * @return returns an integer value representing the total number 
	 * of seconds represented in mmss.
	 */
	public static int minutesToSeconds(String mmss) {
				int lengthOfInput = mmss.length();
				
				//finds where seconds begin being represented.
				int formatBreak = mmss.indexOf(":");
				//creates a sub string of the minutes
				
				String minutesStr = mmss.substring(0,formatBreak);
				
				//converts them to integer values 
				//multiples them by 60
				int minutesAsSeconds = ((Integer.parseInt(minutesStr))*60);
				
				//creates a substring of the seconds 
				
				String secondsStr = mmss.substring(formatBreak + 1,lengthOfInput);
				//converts them to integer values 
				int seconds = Integer.parseInt(secondsStr);
				//adds all integer seconds together 
				int totalSeconds = minutesAsSeconds + seconds;
				// returns integer seconds to be used.
				return totalSeconds;
				}
	
	/**
	 * prints prompts to the user about Song, Artist, playtime and 
	 * file path.
	 * 
	 */
	public static void newSongUserInput() {
		//===================
		//these are the String values that are prompted to the user.
	
		//=================
		System.out.print(promptSong);
		songTitle = reader.nextLine();
		System.out.print(promptArtist);
		songArtist = reader.nextLine();
		System.out.print(promptTime);
		playTime = reader.nextLine();
		System.out.print(promptPath);
		filePath = reader.nextLine();
		
	}
	/**
	 * prints a formatted String to the console, based on a Song object.
	 * 
	 * @param songName specifies the song object who's attributes you want to print.
	 * 
	 */
	public static void SongInfoPrintOut(Song songName) {
		//prints input session for one song in order song title, song artist, play time, file path.
		System.out.println(promptSong + songName.getTitle());
		System.out.println(promptArtist + songName.getArtist());
		System.out.println(promptTime + songName.getPlayTime());
		System.out.println(promptPath + songName.getFilePath());
	}
	/**
	 * Takes three song playtime values and returns the average of the three.
	 * @param song0 takes a song object and calls its getPlayTime() method.
	 * @param song1 takes a song object and calls its getPlayTime() method.
	 * @param song2 takes a song object and calls its getPlayTime() method.
	 * @return a string value that represents the average playtime of the 
	 * three Song objects.
	 */
	public static String averagePlayTime(Song song0,Song song1, Song song2) {
		//returns a double average of the three songs to two decimal places.
		DecimalFormat fmt = new DecimalFormat("0.##");
		
		double averagePlayTime = ((song0.getPlayTime() + song1.getPlayTime() + song2.getPlayTime())/3.0);
		
		String formattedAveragePlaytime = fmt.format(averagePlayTime);
		
			return formattedAveragePlaytime;
	}
	
	
	/**
	 * //represents absolute value of seconds away
	 *  from 4 minutes the song is and returns a String of the closest one.
	 * @param song0 Song object whos playtime is to be compared.
	 * @param song1 Song object whos playtime is to be compared.
	 * @param song2 Song object whos playtime is to be compared.
	 * @return String value representing the title of the closest song.
	 */
	public static String closestToFour(Song song0,Song song1,Song song2) {
		
		double song0Diff = Math.abs(song0.getPlayTime() - 240.0);
		double song1Diff = Math.abs(song1.getPlayTime() - 240.0);
		double song2Diff = Math.abs(song2.getPlayTime() - 240.0);
		//checks if song0 is smallest difference from 4 minutes.
		if (song0Diff < song1Diff && song0Diff < song2Diff) {
			
			return song0.getTitle();
		}
		//checks if song1 is smallest difference from 4 minutes.
		if (song1Diff < song0Diff && song1Diff < song2Diff) {
			
			return song1.getTitle();
		}
		//checks if song2 is smallest difference from 4 minutes.
		if (song2Diff < song1Diff && song2Diff < song0Diff) {
			
			return song2.getTitle();
		}
		
		
		else {
			return "Something went wrong!";
		}	
	}
	/**
	 * Orders print statements in ascending order of Song Objects based on their 
	 * play times.
	 * @param song0 Song object who's playtime is to be compared.
	 * @param song1 Song object who's playtime is to be compared.
	 * @param song2 Song object who's playtime is to be compared.
	 */
	public static void sortedPlayList(Song song0,Song song1,Song song2) {
		int playTime0 = song0.getPlayTime();
		int playTime1 = song1.getPlayTime();
		int playTime2 = song2.getPlayTime();
		
		//checks for smallest value.
		if ((playTime2 > playTime0) && (playTime1 > playTime0)) {
			System.out.println(song0.toString());
			//checks for next smallest value.
			if ((playTime1) < (playTime2)) {
				System.out.println(song1.toString());
				System.out.println(song2.toString());
			}
			else {
				System.out.println(song2.toString());
				System.out.println(song1.toString());
			}
		}
		else if ((playTime0 > playTime1) && (playTime2 > playTime1)) {
			System.out.println(song1.toString());
			//checks for next smallest value.
			if ((playTime2) < (playTime0)) {
				System.out.println(song2.toString());
				System.out.println(song0.toString());
			}
			else {
				System.out.println(song0.toString());
				System.out.println(song2.toString());
			}
		}
		else if ((playTime1 > playTime2) && (playTime0 > playTime2)) {
			System.out.println(song2.toString());
			//checks for next smallest value.
			if ((playTime0) < (playTime1)) {
				System.out.println(song0.toString());
				System.out.println(song1.toString());
			}
			else {
				System.out.println(song1.toString());
				System.out.println(song0.toString());
			}
		}
		//prints if all are of equal value.
		else {
			System.out.println(song0.toString());
			System.out.println(song1.toString());
			System.out.println(song2.toString());
		}
		
	}

	
	public static void main(String[] args) {
		
		newSongUserInput();
		Song song0 = new Song(songTitle, songArtist, minutesToSeconds(playTime), filePath);
		//SongInfoPrintOut(song0);
	
		newSongUserInput();
		Song song1 = new Song(songTitle, songArtist, minutesToSeconds(playTime), filePath);
		//SongInfoPrintOut(song1);
		newSongUserInput();
		Song song2 = new Song(songTitle, songArtist, minutesToSeconds(playTime), filePath);
		//SongInfoPrintOut(song2);
		
		//prints out the order of the songs in which they are printed
		System.out.println(promptAverage + averagePlayTime(song0,song1,song2));
		System.out.println("");
		System.out.println(promptClosestPlayTime + closestToFour(song0,song1,song2));
		System.out.println("");
		System.out.println(border);
		System.out.println(categoryTitles);
		System.out.println(border);
		sortedPlayList(song0,song1,song2);
		System.out.println(border);
	}

} 
