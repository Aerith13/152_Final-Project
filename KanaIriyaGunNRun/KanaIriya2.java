
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class KanaIriya2 extends GameCharacter {

	// Asset Constant 
	private final static int NROWS = 2;
    private final static int NCOLS = 5;
    private final static int SWIDTH = 102;
    private final static int SHEIGHT = 102;
    private final static int SPEED = 80;
	
    private boolean isRunning;
    private boolean isShooting;
 
    // audio
    private Clip clip;
    private AudioInputStream gun;
    
    
	// Constructor
	public KanaIriya2() {
		super(200, 200, "assets//bullet.png", NROWS, NCOLS, SWIDTH, SHEIGHT, SPEED);
		this.isRunning = false;
		this.isShooting = false;

		try {
            //load sound
            gun =  AudioSystem.getAudioInputStream( 
                    new File("assets//burst.wav").getAbsoluteFile());
            clip = AudioSystem.getClip(); 
            clip.open(gun);
            clip.setFramePosition(0);
            
            
		} catch(IOException ex) {
			System.out.println(ex.getMessage());
        	System.exit(1);
        } catch (UnsupportedAudioFileException ex) {
            System.out.println(ex.getMessage());
        	System.exit(1);
        } catch (LineUnavailableException ex) {
        	System.out.println(ex.getMessage());
        	System.exit(1);
        }
	}

	public void update() {
		long ctime = System.currentTimeMillis();
		 
		if((ctime - this.getLastUpdate()) >= this.getSpeed()) {
			this.setLastUpdate(ctime);
			
			if(isRunning) {
				// check if frame is in range
				// if not set it to starting frame
				if(this.getIndex() >= 0 && this.getIndex() <= 4) 
					this.setIndex(this.getIndex()+1);
				else this.setIndex(0);
				
				if(this.getIndex() > 4) this.setIndex(0);
				
			}else if(isShooting) {
				
				// check if frame is in range
				// if not set it to starting frame
				if(this.getIndex() >= 5 && this.getIndex() <= 9) 
					this.setIndex(this.getIndex()+1);
				else this.setIndex(5);
				
				if(this.getIndex() > 9) this.setIndex(5);
			}else this.setIndex(0); // set frame 0 if idle
			
			
			// Play the audio on specific frames
			switch(this.getIndex()) {
			case 2:
			case 7:
				clip.setFramePosition(0);
				clip.start();
				break;
			case 0:
			case 5:
				clip.stop();
				break;
			}
		}
		
	}
	
	
	//
	//  Control Functions
	//
	public void running() {
		this.isRunning = true;
	}
	
	public void stopRunning() {
		this.isRunning = false;
	}
	
	public void shoot() {
		this.isShooting = true;
	}
	
	public void stopShooting() {
		this.isShooting = false;
	}

	
	
}
