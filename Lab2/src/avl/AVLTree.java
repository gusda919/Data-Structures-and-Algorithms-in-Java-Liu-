package avl;

import java.util.Objects;

////////////////////////////////////////////////////////////////////////////
////
//AVL TREE                                 //
////
////////////////////////////////////////////////////////////////////////////

/**
 * Implementation of an AVL Tree.
 *
 * @author Magnus Nielsen (magnus.nielsen@liu.se) Partially based on existing
 *         C++-laborations by Tommy Olsson and Filip StrÃ¶mbÃ¤ck.
 */
public class AVLTree<T extends Comparable<T>> {

	private AVLTreeNode<T> root;

	/**
	 * Default constructor.
	 */
	public AVLTree() {
		root = null;
	}

	public void insert(T data) {
		if (root == null) {
			root = new AVLTreeNode<>(data);
		} else {
			try {
				root = root.insert(data);
			} catch (AVLTreeException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	/**
	 * Remove the node which contains the key.
	 *
	 * @param key - Generic parameter with the key value which to remove from the
	 *            tree.
	 */
	public void remove(T key) {
		if (root != null) {
			root = root.remove(key);
		}
	}

	/**
	 * Check if the tree contains a node with the key.
	 *
	 * @param key - Generic key value for which to search.
	 * @return boolean, true if the key exists in the tree, false otherwise.
	 */
	public boolean member(T key) {
		return root.find(key) != null;
	}

	/**
	 * Look up the key in the tree, returns the element if it exists.
	 *
	 * @param key - Generic value for which to search.
	 * @return the element containing (or equalling) the key.
	 */
	public T find(T key) {
		if (root == null) {
			throw new AVLTreeException("Value not found in the tree!");
		}
		AVLTreeNode<T> tmp = root.find(key);
		if (tmp == null) {
			throw new AVLTreeException("Value not found in the tree!");
		}
		return tmp.getElement();
	}

	/**
	 * Find the smallest element in the tree.
	 *
	 * @return the element containing the smallest value in the tree.
	 */
	public T findMin() {
		if (root == null) {
			throw new AVLTreeException("Cannot find a smallest element in an empty tree!");
		}
		return root.findMin().getElement();
	}

	/**
	 * Find the largest element in the tree.
	 *
	 * @return the element containing the largest value in the tree.
	 */
	public T findMax() {
		if (root == null) {
			throw new AVLTreeException("Cannot find a largest element in an empty tree!");
		}
		return root.findMax().getElement();
	}

	/**
	 * Print the tree to standard output.
	 */
	public void print() {
		System.out.println("");
		if (root == null) {
			System.out.println("TrÃ¤det Ã¤r tomt!");
		} else {
			root.print(0);
		}
	}

	/**
	 * Check if the tree is empty.
	 *
	 * @return boolean, true if empty and false otherwise.
	 */
	public boolean empty() {
		return root == null;
	}

	/**
	 * Clear the tree.
	 */
	public void clear() {
		root = root.clear();
	}

	/**
	 * Swap trees x and y with each other.
	 *
	 * @param x - first tree to swap.
	 * @param y - second tree to swap.
	 */
	public void swap(AVLTree<T> x, AVLTree<T> y) {
		x.swap(y);
	}

	/**
	 * Swap this tree with another.
	 *
	 * @param t - the tree to swap with.
	 */
	private void swap(AVLTree<T> t) {
		AVLTreeNode<T> tmp = t.root;
		t.root = root;
		root = tmp;
	}

	/**
	 * Getter for the root of the tree.
	 *
	 * @return the root of the tree.
	 */
	public AVLTreeNode getRoot() {
		return root;
	}

	/**
	 * Find the height of the node with a given key.
	 *
	 * @param key - Generic parameter containing the key.
	 * @return int representing the height of the node containing the key.
	 */
	public int getNodeHeight(T key) {
		return Objects.requireNonNull(root.find(key)).getHeight();
	}
}
