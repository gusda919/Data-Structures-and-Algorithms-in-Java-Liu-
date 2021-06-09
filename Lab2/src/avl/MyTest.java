package avl;



public class MyTest {

	  public static void main(final String[] args) {
	        AVLTree<Integer> tree = new AVLTree();
	     
	        
	  
		   
	        
	        tree.insert(5);
	        System.out.println("tree height " + tree.getRoot().getHeight());
	        System.out.println("balance after rotation " + tree.getRoot().getBalanceFactor());
	        tree.insert(3);
	        System.out.println("tree height " + tree.getRoot().getHeight());
	        System.out.println("balance after rotation " + tree.getRoot().getBalanceFactor());
	        tree.insert(1);
	        System.out.println("tree height " + tree.getRoot().getHeight());
	        System.out.println("balance after rotation " + tree.getRoot().getBalanceFactor());
	       
	   
	        
	        tree.insert(7);
	        System.out.println("tree height " + tree.getRoot().getHeight());
	        System.out.println("balance after rotation " + tree.getRoot().getBalanceFactor());
	        tree.insert(8);
	        System.out.println("tree height " + tree.getRoot().getHeight());
	        System.out.println("balance after rotation " + tree.getRoot().getBalanceFactor());
	        tree.insert(9);
	        System.out.println("tree height " + tree.getRoot().getHeight());
	        System.out.println("balance after rotation " + tree.getRoot().getBalanceFactor());
	       
	 
	        
	        
	        
	        
	        
	        
	
	  }
	  
	  
}
