package avl;

import org.w3c.dom.Node;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.util.Scanner;
import java.io.*;

public class AVLTest extends JPanel implements ActionListener {
	public static final long serialVersionUID = 3L;
	/* The binary tree */
	private AVLTree tree = null;

	/* The node location of the tree */
	private HashMap<AVLTreeNode, Rectangle> nodeLocations = null;

	/* The sizes of the subtrees */
	private HashMap<AVLTreeNode, Dimension> subtreeSizes = null;

	/* Do we need to calculate locations */
	static private boolean dirty = true;

	/* Space between nodes */
	private int parent2child = 20, child2child = 30;

	/* Helpers */
	private Dimension empty = new Dimension(0, 0);

	private FontMetrics fm = null;

	public AVLTest(AVLTree tree) {
		this.tree = tree;
		nodeLocations = new HashMap<AVLTreeNode, Rectangle>();
		subtreeSizes = new HashMap<AVLTreeNode, Dimension>();
	}

	public void actionPerformed(ActionEvent e) {
		repaint();
	}

	// calculate node locations
	private void calculateLocations() {
		nodeLocations.clear();
		subtreeSizes.clear();
		AVLTreeNode root = tree.getRoot();
		if (root != null) {
			calculateSubtreeSize(root);
			calculateLocation(root, Integer.MAX_VALUE, Integer.MAX_VALUE, 0);
		}
	}

	// calculate the size of a subtree rooted at n
	private Dimension calculateSubtreeSize(AVLTreeNode n) {
		if (n == null)
			return new Dimension(0, 0);
		Dimension ld = calculateSubtreeSize(n.getLeft());
		Dimension rd = calculateSubtreeSize(n.getRight());
		int h = fm.getHeight() + parent2child + Math.max(ld.height, rd.height);
		int w = ld.width + child2child + rd.width;
		Dimension d = new Dimension(w, h);
		subtreeSizes.put(n, d);
		return d;
	}

	// calculate the location of the nodes in the subtree rooted at n
	private void calculateLocation(AVLTreeNode n, int left, int right, int top) {
		if (n == null)
			return;
		Dimension ld = (Dimension) subtreeSizes.get(n.getLeft());
		if (ld == null)
			ld = empty;
		Dimension rd = (Dimension) subtreeSizes.get(n.getRight());
		if (rd == null)
			rd = empty;
		int center = 0;
		if (right != Integer.MAX_VALUE)
			center = right - rd.width - child2child / 2;
		else if (left != Integer.MAX_VALUE)
			center = left + ld.width + child2child / 2;
		int width = fm.stringWidth("" + n.getElement());
		Rectangle r = new Rectangle(center - width / 2 - 3, top, width + 6, fm.getHeight());
		nodeLocations.put(n, r);
		calculateLocation(n.getLeft(), Integer.MAX_VALUE, center - child2child / 2,
				top + fm.getHeight() + parent2child);
		calculateLocation(n.getRight(), center + child2child / 2, Integer.MAX_VALUE,
				top + fm.getHeight() + parent2child);
	}

	// draw the tree using the pre-calculated locations
	private void drawTree(Graphics2D g, AVLTreeNode n, int px, int py, int yoffs) {
		if (n == null)
			return;
		Rectangle r = (Rectangle) nodeLocations.get(n);
		g.draw(r);
		g.drawString("" + n.getElement(), r.x + 3, r.y + yoffs);
		if (px != Integer.MAX_VALUE)
			g.drawLine(px, py, r.x + r.width / 2, r.y);
		drawTree(g, n.getLeft(), r.x + r.width / 2, r.y + r.height, yoffs);
		drawTree(g, n.getRight(), r.x + r.width / 2, r.y + r.height, yoffs);
	}

	public void paint(Graphics g) {
		super.paint(g);
		fm = g.getFontMetrics();
		// if node locations not calculated
		if (dirty) {
			calculateLocations();
			dirty = false;
		}
		Graphics2D g2d = (Graphics2D) g;
		g2d.translate(getWidth() / 2, parent2child);
		drawTree(g2d, tree.getRoot(), Integer.MAX_VALUE, Integer.MAX_VALUE, fm.getLeading() + fm.getAscent());
		fm = null;
	}

	static void printMenu() {
		final String newline = System.getProperty("line.separator");

		String strMenu = "+--- Binary trees ---" + newline;
		strMenu += "r : Reset tree" + newline;
		strMenu += "i : Insert key" + newline;
		strMenu += "f : Find key" + newline;
		strMenu += "d : Delete key" + newline;
		strMenu += "h : Height of key" + newline;
		strMenu += "q : Quit program" + newline;
		strMenu += "x : show this text" + newline;
		System.out.print(strMenu);
	}

	static char getChar(Scanner in) {
		return in.next().charAt(0);
	}

	static int getInt(Scanner in) {
		return in.nextInt();
	}

	static String getString(Scanner in) {
		return in.nextLine();
	}

	/**
	 * Fetch the entire contents of a text file, and return it in a String.
	 */
	static public String getContents(File aFile) {

		StringBuilder contents = new StringBuilder();

		try {
			// use buffering, reading one line at a time

			BufferedReader input = new BufferedReader(new FileReader(aFile));
			try {
				String line = null; // not declared within while loop

				while ((line = input.readLine()) != null) {
					contents.append(line);
					contents.append(System.getProperty("line.separator"));
				}
			} finally {
				input.close();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return contents.toString();
	}

	public static void main(String[] args) {
		boolean textMode = false;
		if (args.length == 1 && args[0].equals("--text-mode")) {
			textMode = true;
		}

		AVLTree<Integer> tree = new AVLTree<>();

		JFrame f = new JFrame("AVL");
		f.getContentPane().add(new AVLTest(tree));
		// create and add an event handler for window closing event
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		f.setBounds(50, 50, 600, 400);
		if (!textMode) {
			f.setVisible(true);
		}

		char c;
		int k;
		String v;
		Scanner in = new Scanner(System.in);
		printMenu();
		for (;;) {
			System.out.print("lab > ");
			System.out.flush();
			c = getChar(in);

			switch (c) {
			case 'r':
			case '6':
				tree = new AVLTree<>();
				f.dispose();
				f = new JFrame("AVL");
				f.getContentPane().add(new AVLTest(tree));
				// create and add an event handler for window closing event
				f.addWindowListener(new WindowAdapter() {
					public void windowClosing(WindowEvent e) {
						System.exit(0);
					}
				});
				f.setBounds(50, 50, 600, 400);
				if (!textMode) {
					f.setVisible(true);
				}
				break;
			case 'h':
				System.out.println("Height of key (int): ");
				System.out.flush();
				k = getInt(in);
				System.out.println("The node: " + k + " has the height: " + tree.getNodeHeight(k));
				break;
			case 'i':
			case '1':
				System.out.print("Insert key (int): ");
				System.out.flush();
				k = getInt(in);
				tree.insert(k);
				System.out.print("Inserted key=" + k + "\n");
				System.out.flush();
				dirty = true;
				f.repaint();
				break;
			case 'd':
			case '2':
				System.out.print("Delete key (int): ");
				System.out.flush();
				k = getInt(in);
				tree.remove(k);
				dirty = true;
				f.repaint();
				break;
			case 'f':
				System.out.print("Find key (int): ");
				System.out.flush();
				k = getInt(in);
				Integer str = tree.find(k);
				if (str != null)
					System.out.print("Found key:" + k + "\n");
				else
					System.out.print("Key:" + k + " not found\n");
				break;
			case 'p':
			case '8':
				tree.print();
				break;
			case 'q':
			case '0':
				System.out.println("Program terminated.");
				System.exit(0);
				break;
			case 'x':
				printMenu();
				break;
			default:
				System.out.print("**** Sorry, No menu item named '");
				System.out.print(c + "'\n");
				System.out.flush();
			}
		}
	}
}
