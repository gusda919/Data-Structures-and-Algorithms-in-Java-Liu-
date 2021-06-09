package collision;


public class TestMinPQ {
    public static void main(String[] args) {
	final int minItem = 10000;
	final int maxItem = 99999;
	MinPQ<Integer> h = new MinPQ<Integer>();
	int x;
		
	System.out.println("Begin test... ");
	for (int i = 37; i != 0; i = (i+37) % maxItem) {
	    if (i >= minItem) {
		h.insert(i);
	    }
	}

	for (int i = minItem; i < maxItem; ++i) {
	    x = h.delMin();
	    if (x != i) {
		System.out.println("Oops! " + i);
	    }
	}
		
	h = new MinPQ<Integer>();
	for(int i = 37; i != 0; i = (i + 37) % maxItem) {
	    if(i >= minItem) {
		h.toss(i);
	    }
	}

	for(int i = minItem; i < maxItem; ++i) {
	    x = h.delMin();
	    if(x != i) {
		System.out.println("Oops! Error after toss" + i);
	    }
	}	  
	
	System.out.println("End test... no 'oops' output is good");
    }
}
