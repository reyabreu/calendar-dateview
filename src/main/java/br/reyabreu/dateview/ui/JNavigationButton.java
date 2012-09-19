package br.reyabreu.dateview.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

/**
 * A button with a simply drawn navigation arrow and no text.
 *
 * @author rizzor
 *
 */
public class JNavigationButton extends JButton {

	static class JArrowIcon implements Icon {

		private int width;

		private int height;

		protected int direction;

		protected int[] xCoord = new int[3];

		protected int[] yCoord = new int[3];

		public JArrowIcon(int width, int height, int direction) {
			setDimension(width, height);
			this.direction = direction;
		}

		public int getIconHeight() {
			return height;
		}

		public int getIconWidth() {
			return width;
		}

		public void paintIcon(Component c, Graphics g, int x, int y) {
			switch (direction) {
			case SwingConstants.LEFT:
				setLeftArrow(x, y);
				break;
			case SwingConstants.RIGHT:
				setRightArrow(x, y);
				break;
			default:
				// Right Arrow is default
				setRightArrow(x, y);
				break;
			}
			g.setColor(Color.BLACK);
			g.fillPolygon(xCoord, yCoord, 3);

		}

		private void setDimension(int width, int height) {
			this.width = width;
			this.height = height;
		}

		private void setLeftArrow(int x, int y) {
			xCoord[0] = x;
			yCoord[0] = y + (height / 2);

			xCoord[1] = x + width;
			yCoord[1] = y - 1;

			xCoord[2] = x + width;
			yCoord[2] = y + height;
		}

		private void setRightArrow(int x, int y) {
			xCoord[0] = x + width;
			yCoord[0] = y + (height / 2);

			xCoord[1] = x;
			yCoord[1] = y - 1;

			xCoord[2] = x;
			yCoord[2] = y + height;
		}

	}

	/**
	 *
	 */
	private static final long serialVersionUID = 8795653830044746264L;

	/**
	 * By using Swing Constants, arrow direction can be defined (LEFT/RIGHT)
	 *
	 * @param iconWidth
	 * @param iconHeight
	 * @param direction
	 */
	public JNavigationButton(int iconWidth, int iconHeight, int direction) {
		super("");
		this.setFocusable(false);
		this.setIcon(createIcon(iconWidth, iconHeight, direction));
	}

	private Icon createIcon(int iconWidth, int iconHeight, int direction) {
		return new JArrowIcon(iconWidth, iconHeight, direction);
	}

}
