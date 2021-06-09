package avl;
////////////////////////////////////////////////////////////////////////////

////
//AVL TREE NODE                               //
////
////////////////////////////////////////////////////////////////////////////

/**
 * Contains the AVL Tree Node, with rotations and corresponding help functions.
 * 
 * @author Magnus Nielsen (magnus.nielsen@liu.se) Partially based on
 *         C++-laborations by Tommy Olsson and Filip StrÃ¶mbÃ¤ck.
 */
public class AVLTreeNode<T extends Comparable<T>> {

	private int height;
	private int balanceFactor;
	private T element;
	private AVLTreeNode<T> left, right;

	/**
	 * Constructor.
	 *
	 * @param data - Generic parameter containing the data element for the node.
	 */
	public AVLTreeNode(T data) {
		element = data;
	}

	/**
	 * Constructor.
	 *
	 * @param data - Generic parameter containing the data element for the node.
	 * @param l    - AVLTreeNode<T> containing the intended left child for the node
	 *             being created.
	 * @param r    - AVLTreeNode<T> containing the intended right child for the node
	 *             being created.
	 */
	public AVLTreeNode(T data, AVLTreeNode<T> l, AVLTreeNode<T> r) {
		element = data;
		left = l;
		right = r;
	}

	/**
	 * Print this node.
	 *
	 * @indent depth - The current indentation of the output.
	 */
	public void print(int depth) {
		if (right != null) {
			right.print(depth + 2);
			System.out.println(" ".repeat(depth + 1) + "/");
		}

		System.out.println(" ".repeat(depth) + element);

		if (left != null) {
			System.out.println(" ".repeat(depth + 1) + "\\");
			left.print(depth + 2);
		}
	}

////////////////////////////////////////////////////////////////////////////
//                                                                        //
//                          NODE FUNCTIONALITY                            //
//                                                                        //
////////////////////////////////////////////////////////////////////////////

	/**
	 * Adjust the height for a given node n.
	 *
	 * @param n - AVLTreeNode for which to calculate the height.
	 */
	private void calculateHeight(AVLTreeNode<T> n) {
		n.height = 1 + Math.max(nodeHeight(n.left), nodeHeight(n.right));
	}

	/**
	 * Insert element into the tree originating from t. Currently does not balance
	 * the tree. TODO: Implement balancing.
	 *
	 * @param data - Generic element parameter to be inserted.
	 * @return AVLTreeNode containing the appropriate node for the call position,
	 *         after insertion.
	 */
	public AVLTreeNode<T> insert(T data) {
		if (data.compareTo(element) < 0) {
// the key we insert is smaller than the current node
			if (left == null) {
				left = new AVLTreeNode<>(data);
			} else {
				left = left.insert(data);
			}
		} else if (element.compareTo(data) < 0) {
// the key we insert is greater than the current node
			if (right == null) {
				right = new AVLTreeNode<>(data);
			} else {
				right = right.insert(data);
			}
		} else {
			throw new AVLTreeException("Element already exists.");
		}

		calculateHeight(this);

		return balanceTree(this);
	}

	public AVLTreeNode<T> balanceTree(AVLTreeNode<T> n) {

		int leftNodeHeight = (n.left == null) ? -1 : n.left.height;
		int rightNodeHeight = (n.right == null) ? -1 : n.right.height;

		n.balanceFactor = rightNodeHeight - leftNodeHeight;
		System.out.println("current balance:" + n.balanceFactor);

		if (n.balanceFactor == -2) {
			if (n.left.balanceFactor < 0) {
				return singleRotationWithLeftChild(n);
			} else {
				return doubleRotationWithLeftChild(n);
			}

		}

		if (n.balanceFactor == +2) {
			if (n.right.balanceFactor > 0) {
				return singleRotationWithRightChild(n);
			} else {
				return doubleRotationWithRightChild(n);
			}

		}

		return n;
	}

	/**
	 * Returns the height of (sub) tree node.
	 *
	 * @param n - AVLTreeNode for which to get the height.
	 */
	private int nodeHeight(AVLTreeNode<T> n) {
		if (n != null) {
			return n.height;
		}
		return -1;
	}

	/**
	 * Search for a node containing the key. If found: return the node, otherwise
	 * return null.
	 *
	 * @param key - Generic key type for which to search.
	 * @return AVLTreeNode containing the key, if not found null be returned.
	 */
	public AVLTreeNode<T> find(T key) {
		if (key.compareTo(element) < 0) {
			if (left == null) {
				return null;
			}
			return left.find(key);
		} else if (element.compareTo(key) < 0) {
			if (right == null) {
				return null;
			}
			return right.find(key);
		} else {
			return this;
		}
	}

	/**
	 * Looks for the node with the smallest value in the tree (the leftmost node),
	 * and returns that node. If the tree is empty, null will be returned.
	 *
	 * @return AVLTreeNode with the smallest value in the tree, null if the tree is
	 *         empty.
	 */
	public AVLTreeNode<T> findMin() {
		if (left == null) {
			return this;
		} else {
			return left.findMin();
		}
	}

	/**
	 * Looks for the node with the largest value in the tree (the rightmost node),
	 * and returns that node. If the tree is empty, null will be returned.
	 *
	 * @return AVLTreeNode with the largest value in the tree, null if the tree is
	 *         empty.
	 */

	public AVLTreeNode<T> findMax() {
		if (right == null) {
			return this;
		} else {
			return right.findMax();
		}
	}

	/**
	 * Clear the given tree completely.
	 *
	 * @return null (to be used for clearing the root node.
	 */
	public AVLTreeNode<T> clear() {
		if (left != null) {
			left = left.clear();
		}
		if (right != null) {
			right = right.clear();
		}
		return null;
	}

	/**
	 * Removes the node key from the tree if it exists. Currently removes in a lazy
	 * fashion (does not balance the tree). TODO: Implement balancing.
	 *
	 * @param key - Generic parameter with the key to be deleted.
	 * @return - AVLTreeNode<T>, the correct node for the source position in the
	 *         tree.
	 */
	public AVLTreeNode<T> remove(T key) {
		if (key.compareTo(element) < 0) { // key is smaller than the current node
			if (left == null) {
// key not found in tree
				return this;
			}
			left = left.remove(key);
		} else if (element.compareTo(key) < 0) { // key is greater than the current node
			if (right == null) {
// key not found in tree
				return this;
			}
			right = right.remove(key);
		} else { // node found
			if (left != null && right != null) {
// The node has two children, so we replace it with the next node inorder.
				AVLTreeNode<T> tmp = right.findMin();
				element = tmp.element;
				right = right.remove(tmp.element);
			} else {
// The node has, at most, one child.
				if (left == null) {
					return right;
				} else {
					return left;
				}
			}
		}
		return this;
	}

	public int getBalanceFactor() {
		return balanceFactor;
	}

	public void setBalanceFactor(int balanceFactor) {
		this.balanceFactor = balanceFactor;
	}

	/**
	 * Getter for element.
	 *
	 * @return the element in said node.
	 */
	public T getElement() {
		return element;
	}

	/**
	 * Getter for the left hand tree node.
	 *
	 * @return the left hand tree node.
	 */
	public AVLTreeNode<T> getLeft() {
		return left;
	}

	/**
	 * Getter for the right hand tree node.
	 *
	 * @return the right hand tree node.
	 */
	public AVLTreeNode<T> getRight() {
		return right;
	}

	/**
	 * Getter for height in the current node.
	 *
	 * @return the height for the current node.
	 */
	public int getHeight() {
		return height;
	}

////////////////////////////////////////////////////////////////////////////
//                                                                       //
//                              ROTATIONS                                 //
//                                                                        //
////////////////////////////////////////////////////////////////////////////

	/**
	 * Single rotation, left to right, using pivot as pivot.
	 *
	 * @param pivot - AVLTreeNode used as pivot for the rotation.
	 * @return AVLTreeNode with the new node for the pivot location in the tree.
	 */
	private AVLTreeNode<T> singleRotationWithLeftChild(AVLTreeNode<T> pivot) {
		AVLTreeNode<T> temp = pivot.left;

		pivot.left = temp.right;
		temp.right = pivot;

		calculateHeight(pivot);
		calculateHeight(temp);
		return temp;
	}

	/**
	 * Single rotation, right to left, using pivot as pivot.
	 *
	 * @param pivot - AVLTreeNode used as pivot for the rotation.
	 * @return AVLTreeNode with the new node for the pivot location in the tree.
	 */
	private AVLTreeNode<T> singleRotationWithRightChild(AVLTreeNode<T> pivot) {
		AVLTreeNode<T> temp = pivot.right;

		pivot.right = temp.left;
		temp.left = pivot;

		calculateHeight(pivot);
		calculateHeight(temp);
		return temp;
	}

	/**
	 * Double rotation, left to right, using pivot as pivot.
	 *
	 * @param pivot - AVLTreeNode used as pivot for the rotation.
	 * @return AVLTreeNode with the new node for the pivot location in the tree.
	 */
	private AVLTreeNode<T> doubleRotationWithLeftChild(AVLTreeNode<T> pivot) {
		pivot.left = singleRotationWithRightChild(pivot.left);
		return singleRotationWithLeftChild(pivot);
	}

	/**
	 * Double rotation, right to left, using pivot as pivot.
	 *
	 * @param pivot - AVLTreeNode used as pivot for the rotation.
	 * @return AVLTreeNode with the new node for the pivot location in the tre.
	 */
	private AVLTreeNode<T> doubleRotationWithRightChild(AVLTreeNode<T> pivot) {
		pivot.right = singleRotationWithLeftChild(pivot.right);
		return singleRotationWithRightChild(pivot);
	}

}
