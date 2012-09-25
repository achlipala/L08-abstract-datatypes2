package list_sol;

import list.List;

/** ArrayList represents a mutable list of elements using an array. */
public class ArrayList<E> implements List<E> {
    private E[] elts;
    private int first, last;

    
    // Rep invariant:
    //     elts != null
    //     0 <= first <= last <= elts.length
    // Abstraction function:
    //     represents the length-n sequence elts[first],...,elts[last-1]
    
    public ArrayList() {
    	elts = (E[])new Object[0];
    	first = last = 0;
    }
    
    public E get(int i) throws IndexOutOfBoundsException {
    	if (i < 0 || i >= size())
    		throw new IndexOutOfBoundsException();

    	return elts[first + i];
    }

    public int size() {
    	return last - first;
    }
    
    public void add(E e) {
    	if (last >= elts.length) {
    		E[] newElts = (E[])new Object[size () > 0 ? size() * 2 : 1];
    		System.arraycopy(elts, first, newElts, 0, size());
    		last -= first;
    		first = 0;
    		elts = newElts;
    	}
    	
    	elts[last++] = e;
    }
    
    public boolean remove(E e) {
    	for (int i = first; i < last; ++i)
    		if (elts[i].equals(e)) {
    			System.arraycopy(elts, i+1, elts, i, last-i-1);
    			--last;
    			return true;
    		}
    	
    	return false;
    }

    public void set(int i, E e) {
    	if (i < 0 || i >= size())
    		throw new IndexOutOfBoundsException();

    	elts[first + i] = e;
    }
    
    public List<E> subList (int from, int to) {
    	ArrayList<E> l = new ArrayList<E>();
    	l.elts = elts;
    	l.first = first + from;
    	l.last = l.first + (to - from);
    	return l;
    }

}
