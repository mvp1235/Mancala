import java.awt.Graphics2D;

/**
 * DrawableShape interface
 * @author Team Lucky Number 7
 */
public interface DrawableShape
{
	void draw(Graphics2D g2, int x, int y);
	int getHeight();
	int getWidth();
}
