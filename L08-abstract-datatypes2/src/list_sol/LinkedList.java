package list_sol;

import list.List;

/** LinkedList represents a mutable list of elements using a linked list. */
public class LinkedList<E> implements List<E> {
    private Node first, last;
    private class Node {
        E elt;
        Node next;
    }
    
    // Rep invariant:
    //     last is null or is reachable from first by following next links
    // Abstraction function:
    //     represents the sequence first.elt, first.next.elt, ..., first.next*.elt,
    //     up to but not including last
    
    public LinkedList() {
    	first = last = null;
    }
    
    public E get(int i) throws IndexOutOfBoundsException {
    	Node n;
    	
    	for (n = first; n != last && i > 0; n = n.next)
    		--i;
    	
    	if (i == 0 && n != last)
    		return n.elt;
    	
    	throw new IndexOutOfBoundsException();
    }

    public int size() {
    	int size = 0;
    	Node n;
    	
    	for (n = first; n != last; n = n.next)
    		++size;
    	
    	return size;
    }
    
    public void add(E e) {
    	Node n;
    	
    	if (first == null) {
    		Node nd = new Node();
    		first = nd;
    		nd.elt = e;
    		return;
    	}
    	
    	for (n = first; n != last; n = n.next)
    		if (n.next == last) {
    			Node nd = new Node();
    			n.next = nd;
    			nd.elt = e;
    			nd.next = last;
    			return;
    		}
    	
    	throw new AssertionError("rep invariant violation!");
    }
    
    public boolean remove(E e) {
    	Node prev = null;
    	
    	for (Node n = first; n != last; n = n.next) {
    		if (n.elt.equals(e)) {
    			if (prev == null)
    				first = n.next;
    			else
    				prev.next = n.next;
    			
    			if (last == n)
    				last = prev;
    			
    			return true;
    		}
    		
    		prev = n;
    	}
    	
    	return false;
    }
    
    public void set(int i, E e) {
    	Node n;
    	
    	for (n = first; n != last && i > 0; n = n.next)
    		--i;
    	
    	if (i == 0 && n != last) {
    		n.elt = e;
    		return;
    	}
    	
    	throw new IndexOutOfBoundsException();
    }

    public List<E> subList (int from, int to) {
    	LinkedList<E> l = new LinkedList<E>();
    	
    	to -= from;
    	
    	for (l.first = first; from > 0; l.first = l.first.next)
    		--from;
    	
    	for (l.last = l.first; to > 0; l.last = l.last.next)
    		--to;
    	
    	return l;
    }
}
