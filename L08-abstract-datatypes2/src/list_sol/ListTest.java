package list_sol;

import static org.junit.Assert.*;
import list.List;

import org.junit.Test;

public class ListTest {

	private interface ListMaker {
		public List<Integer> make();
	}
	
	private static void exampleFromLectureNotes(ListMaker m) {
		List<Integer> l = m.make();
		// l ~= empty
		l.add(1);
		// l ~= 1
		l.add(2);
		// l ~= 1, 2
		l.remove(1);
		// l ~= 2
		int n = l.get(0);
		// n = 2
		assertEquals(n, 2);
	}
	
	private static void test(ListMaker m) {
		// Start out simple: empty list
		
		List<Integer> l = m.make(), sl;
		assertEquals(l.size(), 0);
		
		// Now let's add some elements.
		
		l = m.make();
		l.add(3);
		l.add(1);
		l.add(2);
		assertEquals(l.size(), 3);
		assertEquals((int)l.get(0), 3);
		assertEquals((int)l.get(1), 1);
		assertEquals((int)l.get(2), 2);
		
		// Try removing elements.
		
		l.remove(3);
		assertEquals(l.size(), 2);
		assertEquals((int)l.get(0), 1);
		assertEquals((int)l.get(1), 2);
		
		l = m.make();
		l.add(3);
		l.add(1);
		l.add(2);
		l.remove(1);
		assertEquals(l.size(), 2);
		assertEquals((int)l.get(0), 3);
		assertEquals((int)l.get(1), 2);
		
		l = m.make();
		l.add(3);
		l.add(1);
		l.add(2);
		l.remove(2);
		assertEquals(l.size(), 2);
		assertEquals((int)l.get(0), 3);
		assertEquals((int)l.get(1), 1);
		
		// subList (read-only)
		
		l = m.make();
		l.add(3);
		l.add(1);
		l.add(2);
		
		sl = l.subList(0, 0);
		assertEquals(sl.size(), 0);
		
		sl = l.subList(0, 3);
		assertEquals(sl.size(), 3);
		assertEquals((int)sl.get(0), 3);
		assertEquals((int)sl.get(1), 1);
		assertEquals((int)sl.get(2), 2);
		
		sl = l.subList(1, 3);
		assertEquals(sl.size(), 2);
		assertEquals((int)sl.get(0), 1);
		assertEquals((int)sl.get(1), 2);
		
		sl = l.subList(0, 2);
		assertEquals(sl.size(), 2);
		assertEquals((int)sl.get(0), 3);
		assertEquals((int)sl.get(1), 1);
		
		sl = l.subList(2, 3);
		assertEquals(sl.size(), 1);
		assertEquals((int)sl.get(0), 2);
		
		// Mutating sublists
		
		sl.set(0, 8);
		assertEquals(sl.size(), 1);
		assertEquals((int)sl.get(0), 8);
		assertEquals(l.size(), 3);
		assertEquals((int)l.get(0), 3);
		assertEquals((int)l.get(1), 1);
		assertEquals((int)l.get(2), 8);
		
		l = m.make();
		l.add(3);
		l.add(1);
		l.add(2);
		sl = l.subList(1, 3);
		sl.set(0, 20);
		sl.set(1, 30);
		assertEquals(l.size(), 3);
		assertEquals((int)l.get(0), 3);
		assertEquals((int)l.get(1), 20);
		assertEquals((int)l.get(2), 30);
	}
	
	@Test
	public void testLinkedList() {
		ListMaker m = new ListMaker() {
			public List<Integer> make() {
				return new LinkedList<Integer>();
			}
		};
		
		exampleFromLectureNotes(m);
		test(m);
	}
	
	@Test
	public void testArrayList() {
		ListMaker m = new ListMaker() {
			public List<Integer> make() {
				return new ArrayList<Integer>();
			}
		};
		
		exampleFromLectureNotes(m);
		test(m);
	}

}
