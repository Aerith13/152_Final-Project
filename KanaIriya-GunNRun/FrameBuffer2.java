
import java.awt.Graphics2D;
import java.awt.Image;


public class FrameBuffer2 {

	private Image currentFrame;
	private Image nextFrame;
	

	public FrameBuffer2(Image currentFrame, Image nextFrame) {
		this.currentFrame = currentFrame;
		this.nextFrame = nextFrame;
	}

	public Image getCurrentFrame(){
		return currentFrame;
	}
	
	public Image getNextFrame(){
		return nextFrame;
	} 
	
	public void drawCharacter(GameCharacter c) {
		Graphics2D g = (Graphics2D) nextFrame.getGraphics(); 
		g.drawImage(c.getCurrentFrame(), null, c.getXPos(), c.getYPos());
		g.dispose();
	}
	
	public Image render() {
		// swap
		currentFrame = nextFrame;
		return currentFrame;
	}
	
	public void clear(Image m) {
		nextFrame = m;
	}
}
