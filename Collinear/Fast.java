import java.util.Arrays;

public class Fast {

	public static void main(String[] args) {

		StdDraw.setXscale(0, 32768);
		StdDraw.setYscale(0, 32768);

		In in = new In(args[0]);
		int N = in.readInt();
		Point[] points = new Point[N];
		for (int i = 0; i < N; i++) {
			int x = in.readInt();
			int y = in.readInt();
			points[i] = new Point(x, y);
			points[i].draw();
		}
		Arrays.sort(points);
		StringBuilder print = new StringBuilder();

		// fast-find [O(N²logN)] lines, and display them
		Point[] slopeOrder;
		for (int i = 0; i < N - 3; i++) {
			Point p = points[i];

			slopeOrder = Arrays.copyOfRange(points, i + 1, N); // points.clone();
			Arrays.sort(slopeOrder, p.SLOPE_ORDER);

			int size = 1;
			for (int j = 0; j < slopeOrder.length - 2; j += size, size = 1) {
				double slope = p.slopeTo(slopeOrder[j]);

				while (j + size < slopeOrder.length
						&& p.slopeTo(slopeOrder[j + size]) == slope)
					size++;

				if (size < 3 || alreadyDone(points, i, p, slope))
					continue;

				print.append(p + " -> ");
				for (int k = 0; k < size - 1; k++)
					print.append(slopeOrder[j + k] + " -> ");
				print.append(slopeOrder[j + size - 1] + "\n");

				p.drawTo(slopeOrder[j + size - 1]);
			}
		}

		// print all lines found and display them to screen at once
		StdOut.print(print);
		StdDraw.show(0);
	}

	// search backwards to find a point with a given slope to p
	private static boolean alreadyDone(Point[] a, int N, Point p, double slp) {
		for (int i = N; i >= 0; i--)
			if (p.slopeTo(a[i]) == slp)
				return true;
		return false;
	}

}
