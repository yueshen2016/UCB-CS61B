class NBody{
	public static double readRadius(String fileName){
		In in = new In(fileName);
		in.readInt();
		double r = in.readDouble();
		return r;
	}

	public static Planet[] readPlanets(String fileName){
		In in = new In(fileName);
		int num_Planet = in.readInt();
		in.readDouble();
		Planet[] b = new Planet[num_Planet];
		for(int i = 0; i < num_Planet; i ++){
			double xP = in.readDouble();
			double yP = in.readDouble();
			double xV = in.readDouble();
			double yV = in.readDouble();
			double m = in.readDouble();
			String img = in.readString();
			b[i] = new Planet(xP, yP, xV, yV, m, img);
		}
		return b;
	}

	public static void main(String[] args){
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		Planet[] b = NBody.readPlanets(filename);
		int num_Planet = b.length;
		double radius = NBody.readRadius(filename);
		double t = 0;

		StdDraw.enableDoubleBuffering();
		StdDraw.setScale(-radius, radius);
		StdDraw.clear();
		StdDraw.picture(0, 0, "images/starfield.jpg");

		for (int i = 0; i < b.length; i++){
			b[i].draw();
		}
		StdDraw.show();

		while (t < T){
			double xForces[] = new double[num_Planet];
			double yForces[] = new double[num_Planet];

			for (int i = 0; i < num_Planet; i++){
				xForces[i] = b[i].calcNetForceExertedByX(b);
				yForces[i] = b[i].calcNetForceExertedByY(b);
			}

			for (int i = 0; i < num_Planet; i++){
				b[i].update(dt, xForces[i], yForces[i]);
			}

			StdDraw.picture(0, 0, "images/starfield.jpg");
			for (int i = 0; i < num_Planet; i++){
				b[i].draw();
			}
			StdDraw.show();
			StdDraw.pause(10);

			t = t + dt;
		}

		StdOut.printf("%d\n", b.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < b.length; i++) {
    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  	  	  b[i].xxPos, b[i].yyPos, b[i].xxVel,
                  	      b[i].yyVel, b[i].mass, b[i].imgFileName);   
}
	}
}