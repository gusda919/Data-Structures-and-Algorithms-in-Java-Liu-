package wordChain;
// Queue är en kö med metoderna Put, Get, IsEmpty och Empty.


class Queue {
    private ListNode front = null, back = null;

    public void put(Object element) {
	if (isEmpty()) {
	    back = front = new ListNode();
	} else {
	    back = back.next = new ListNode();
	}
	back.element = element;
    }

    public Object get() throws Exception {
	if (isEmpty()) {
	    throw new Exception();
	}
	Object element = front.element;
	front = front.next;
	return element;
    }

    public boolean isEmpty() {
	return front == null;
    }

    public void empty() {
	front = back = null;
    }
    
    class ListNode {
	Object element;
	ListNode next = null;
    }
}
