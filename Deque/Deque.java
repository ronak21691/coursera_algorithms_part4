import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
	private Node first;
	private Node last;
	private int N;

	private class Node {
		private Item item;
		private Node pre;
		private Node next;
	}

	public Deque() {
		first = null;
		last = null;
		N = 0;
	}

	public boolean isEmpty() {
		return N == 0;
	}

	public int size() {
		return N;
	}

	public void addFirst(Item item) {
		if (item == null)
			throw new java.lang.NullPointerException();
		Node oldFirst = first;
		first = new Node();
		first.item = item;
		first.next = oldFirst;
		first.pre = null;
		if (isEmpty()) {
			last = first;
		} else {
			oldFirst.pre = first;
		}
		N++;
	}

	public void addLast(Item item) {
		if (item == null)
			throw new java.lang.NullPointerException();
		Node oldLast = last;
		last = new Node();
		last.item = item;
		last.next = null;
		last.pre = oldLast;
		if (isEmpty()) {
			first = last;
		} else {
			oldLast.next = last;
		}
		N++;
	}

	public Item removeFirst() {
		if (isEmpty())
			throw new java.util.NoSuchElementException();
		Item x = first.item;
		first = first.next;
		N--;
		if (isEmpty())
			last = null;
		else
			first.pre = null;
		return x;
	}

	public Item removeLast() {
		if (isEmpty())
			throw new java.util.NoSuchElementException();
		Item x = last.item;
		last = last.pre;
		N--;
		if (isEmpty())
			first = null;
		else
			last.next = null;
		return x;
	}

	@Override
	public Iterator<Item> iterator() {
		return new ListIterator();
	}

	private class ListIterator implements Iterator<Item> {

		private Node current = first;

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return current != null;
		}

		@Override
		public Item next() {
			// TODO Auto-generated method stub
			if (!hasNext())
				throw new java.util.NoSuchElementException();
			Item item = current.item;
			current = current.next;
			return item;
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			throw new java.lang.UnsupportedOperationException();
		}
	}

	public static void main(String[] args) {
		Deque<String> deque = new Deque<String>();
		while (!StdIn.isEmpty()) {
			String s = StdIn.readString();
			if (!s.equals("-")) {
				StdOut.println("1->deque.size()=" + deque.size());
				deque.addFirst(s);
				StdOut.println("2->deque.size()=" + deque.size());
			} else if (!deque.isEmpty()) {
				StdOut.println(deque.removeFirst() + " ");
				StdOut.println("3->deque.size()=" + deque.size());
			}
		}
		StdOut.println("(" + deque.size() + " left on the deque)");
	}
}
