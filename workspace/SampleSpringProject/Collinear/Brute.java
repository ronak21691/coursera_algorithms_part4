public class Brute {

	private static boolean isCollinear(Point p, Point q, Point r, Point s) {
		double slope = p.slopeTo(q);
		return (p.slopeTo(r) == slope) && (p.slopeTo(s) == slope);
	}

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

		for (int i = 0; i < N - 3; i++)
			for (int j = i + 1; j < N - 2; j++)
				for (int k = j + 1; k < N - 1; k++)
					for (int l = k + 1; l < N; l++)
						if (isCollinear(points[i], points[j], points[k],
								points[l])) {
							StdOut.print(points[i] + " -> ");
							StdOut.print(points[j] + " -> ");
							StdOut.print(points[k] + " -> ");
							StdOut.print(points[l] + "\n");

							points[i].drawTo(points[l]);
						}
	}

}
