import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
	private Item[] randomQueue;
	private int N = 0;

	public RandomizedQueue() {
		randomQueue = (Item[]) new Object[2];
	}

	public boolean isEmpty() {
		return N == 0;
	}

	public int size() {
		return N;
	}

	private void resize(int capacity) {
		Item[] temp = (Item[]) new Object[capacity];
		for (int i = 0; i < N; i++) {
			temp[i] = randomQueue[i];
		}
		randomQueue = temp;
	}

	public void enqueue(Item item) {
		if (item == null)
			throw new java.lang.NullPointerException();
		if (N == randomQueue.length)
			resize(2 * N);
		randomQueue[N++] = item;
	}

	public Item dequeue() {
		if (isEmpty())
			throw new java.util.NoSuchElementException();
		int index = (int) (Math.random() * N);
		Item item = randomQueue[index];
		if (index != N - 1)
			randomQueue[index] = randomQueue[N - 1];
		randomQueue[N - 1] = null;
		N--;
		if (N > 0 && N == randomQueue.length / 4)
			resize(randomQueue.length / 2);
		return item;
	}

	public Item sample() {
		if (isEmpty())
			throw new java.util.NoSuchElementException();
		return randomQueue[(int) (Math.random() * N)];
	}

	@Override
	public Iterator<Item> iterator() {
		// TODO Auto-generated method stub
		return new ListIterator();
	}

	private class ListIterator implements Iterator<Item> {
		private int index = 0;
		private Item[] r;

		public ListIterator() {
			r = (Item[]) new Object[N];
			for (int i = 0; i < N; i++)
				r[i] = randomQueue[i];
			StdRandom.shuffle(r);
		}

		public boolean hasNext() {
			return index < N;
		}

		public void remove() {
			throw new java.lang.UnsupportedOperationException();
		}

		public Item next() {
			if (!hasNext())
				throw new java.util.NoSuchElementException();
			Item item = randomQueue[index++];
			return item;
		}
	}

	public static void main(String[] args) {
		RandomizedQueue<String> q = new RandomizedQueue<String>();

		while (!StdIn.isEmpty()) {
			String item = StdIn.readString();

			if (item.startsWith("exit"))
				break;

			if (item.startsWith("-")) {
				String out = q.dequeue();
				StdOut.printf("pop: %s\n", out);
			} else if (item.startsWith("+"))
				q.enqueue(item.substring(1));

			StdOut.printf("%d [ ", q.size());
			for (String s : q)
				StdOut.printf("%s ", s);
			StdOut.println("]");
		}

		for (String i : q)
			for (String j : q)
				StdOut.printf("(%s, %s) ", i, j);
		StdOut.println();
	}
}