package iplace;

import Jama.Matrix;

public class KalmanFilter {

	// http://math.nist.gov/javanumerics/jama/doc/
	// the variance of the positioning error
	private static final double sigmaR = 2;
	// wifi library update every 1 second
	private static final double dt = 1;
	// uncertainty in the systemF dynamics
	private static final double sigmaQ = 0.1 * Math.sqrt(2 / Math.PI);

	private final Matrix GQGTrans;

	private final Matrix F;
	private final Matrix M;
	private final Matrix R;

	private Matrix p;
	private Matrix x;

	public KalmanFilter(double lat0, double lot0) {

		Matrix Q = new Matrix(new double[][] { { sigmaQ * sigmaQ, 0 }, { 0, sigmaQ * sigmaQ } });
		R = new Matrix(new double[][] { { sigmaR * sigmaR, 0 }, { 0, sigmaR * sigmaR } });
		F = new Matrix(new double[][] { { 1, 0, dt, 0 }, { 0, 1, 0, dt }, { 0, 0, 1, 0 }, { 0, 0, 0, 1 } });
		Matrix G = new Matrix(new double[][] { { 0, 0 }, { 0, 0 }, { dt, 0 }, { 0, dt } });
		M = new Matrix(new double[][] { { 1, 0, 0, 0 }, { 0, 1, 0, 0 } });
		p = new Matrix(new double[][] { { sigmaR * sigmaR, 0, 0, 0 }, { 0, sigmaR * sigmaR, 0, 0 },
				{ 0, 0, 15 * 15, 0 }, { 0, 0, 0, 15 * 15 } });

		GQGTrans = G.times(Q).times(G.transpose());
		reset(lat0, lot0);
	}

	public void reset(double lat0, double lot0) {
		x = new Matrix(new double[][] { { lat0 }, { lot0 }, { 0 }, { 0 } });
	}

	public String update(double lat, double lot) {
		// predict
		Matrix x_bar = F.times(x);
		Matrix p_bar = (F.times(p).times(F.transpose())).plus(GQGTrans);
		// Update
		Matrix mpmr = (M.times(p_bar).times(M.transpose()).plus(R)).inverse();
		Matrix k = p_bar.times(M.transpose()).times(mpmr);
		Matrix Y = new Matrix(new double[][] { { lat }, { lot } });
		x = x_bar.plus(k.times(Y.minus(M.times(x_bar))));
		p = (Matrix.identity(4, 4).minus(k.times(M))).times(p_bar);
		String result = Double.toString(x.get(1, 0)) + " " + Double.toString(x.get(0, 0));
		return result;
		// return new LatLng(x.get(0, 0), x.get(1, 0));
	}

	public static class Point {
		public double x;
		public double y;

		public Point(double x, double y) {
			this.x = x;
			this.y = y;
		}
	}
}

class Test {
	public static void main(String[] args) {

		double[][] wifis = new double[10][];

		wifis[0] = new double[] { -0.1752, 1.6722 };
		wifis[1] = new double[] { 0.2784, 3.2999 };
		wifis[2] = new double[] { 1.8177, 3.2585 };
		wifis[3] = new double[] { 3.4643, 3.1955 };
		wifis[4] = new double[] { 3.4026, 5.7257 };
		wifis[5] = new double[] { 5.9359, 5.6577 };
		wifis[6] = new double[] { 6.1601, 8.0462 };
		wifis[7] = new double[] { 8.0159, 7.5123 };
		wifis[8] = new double[] { 8.7819, 10.0279 };
		wifis[9] = new double[] { 9.6048, 8.2946 };

		double[][] kfl = new double[10][];

		kfl[0] = new double[] { -0.1752, 1.6722 };
		kfl[1] = new double[] { 0.2706, 3.2720 };
		kfl[2] = new double[] { 1.6280, 3.5296 };
		kfl[3] = new double[] { 3.2086, 3.5331 };
		kfl[4] = new double[] { 3.8219, 5.0287 };
		kfl[5] = new double[] { 5.4215, 5.7390 };
		kfl[6] = new double[] { 6.3998, 7.2287 };
		kfl[7] = new double[] { 7.7391, 7.8945 };
		kfl[8] = new double[] { 8.8665, 9.2587 };
		kfl[9] = new double[] { 9.8840, 9.5473 };

		KalmanFilter filter = new KalmanFilter(wifis[0][0], wifis[0][1]);

		for (int i = 1; i < wifis.length; i++) {
			double[] wifi = wifis[i];
			KalmanFilter.Point p = new KalmanFilter.Point(0, 0); // = filter.update(wifi[0], wifi[1]);

			System.out.println(String.format("V%d: %4.3f. %4.3f", i, p.x, p.y));
			System.out.println(String.format("V%d: %4.3f. %4.3f", i, kfl[i][0], kfl[i][1]));
			if (Math.abs(p.x - kfl[i][0]) > 0.001 || Math.abs(p.y - kfl[i][1]) > 0.001) {
				System.out.println(String.format("Error"));
			}
		}

		System.out.println("Done");

	}
}
