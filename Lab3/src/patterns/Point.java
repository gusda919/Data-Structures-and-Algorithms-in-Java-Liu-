package patterns;

import javax.swing.*;
import java.awt.*;

/**
 * Class representing one point in the two dimensional plane.
 * @author Magnus Nielsen
 *
 * Largely based on existing C++-laborations by Tommy Olsson and Filip Strömbäck.
 */

public class Point extends JComponent {
    private static final int MAXCOORD = 32767;
    private int x,y;
   


    public Point(int xPos, int yPos) {
        x = xPos;
        y = yPos;
        
    }

    /**
     * Calculate the slope between this point and p.
     *
     * @param p - The additional point to use in the calculation.
     * @return double - If the points are the same, negative infinity is returned,
     * If the line between the points is horizontal positive zero is returned,
     * If the line between the points is vertical positive infinity is returned.
     */

    public double slopeTo(Point p) {
        if (x == p.x && y == p.y)
            return Double.NEGATIVE_INFINITY;
        else if (y == p.y) // horizontal line segment
            return 0.0;
        else if (x == p.x) // vertical line segment
            return Double.POSITIVE_INFINITY;
        else
            return ((double) p.y - (double) y) / ((double) p.x - (double) x);
    }

    /**
     * Paint the point at its location in the plane.
     *
     * @param g - Graphics object.
     * @param frameWidth - int containing the width of the frame in pixels.
     * @param frameHeight - int containing the height of the frame in pixels.
     */
    public void paintComponent(Graphics g, int frameWidth, int frameHeight) {
        int xScaled = x / ((MAXCOORD + 1) / frameWidth);
        int yScaled = (MAXCOORD - y) / ((MAXCOORD + 1) / frameHeight);
        g.fillOval(xScaled, yScaled,2, 2);
    }

    /**
     * Draws a line from this Point to Point p.
     *
     * @param p - The Point to connect to this one with a line.
     * @param g - Graphics object.
     */
    public void lineTo(Point p, Graphics g, int frameWidth, int frameHeight) {
        int x1Scaled = x / ((MAXCOORD + 1) / frameWidth);
        int y1Scaled = (MAXCOORD - y) / ((MAXCOORD + 1) / frameHeight);
        int x2Scaled = p.x / ((MAXCOORD + 1) / frameWidth);
        int y2Scaled = (MAXCOORD - p.y) / ((MAXCOORD + 1) / frameHeight);

        g.setColor(Color.blue);
        g.drawLine(x1Scaled, y1Scaled, x2Scaled, y2Scaled);
        g.setColor(Color.black);
    }

    /**
     * Comparison - Is this Point lexicographically lesser than p?
     *
     * @param p - The other Point to compare with.
     * @return - boolean, true if this Point is the smaller of the two. False otherwise.
     */
    public boolean lessThan(Point p) {
        if (x == p.x) {
            return y < p.y;
        }
        return x < p.x;
    }

    /**
     * Comparison - Is this Point lexicographically greater than p?
     *
     * @param p - The other Point to compare with.
     * @return - boolean, true if this Point is the greater of the two. False otherwise.
     */
    public boolean greaterThan(Point p) {
        return p.lessThan(this);
    }
}
