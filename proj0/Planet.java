public class Planet{
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	public static final double G = 6.67e-11;

	public Planet(double xP, double yP, double xV, double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Planet(Planet b){
		this(b.xxPos, b.yyPos, b.xxVel, b.yyVel, b.mass, b.imgFileName);
	}

	public double calcDistance(Planet b){
		return Math.sqrt((this.xxPos - b.xxPos) * (this.xxPos - b.xxPos) + (this.yyPos - b.yyPos) * (this.yyPos - b.yyPos));
	}

	public double calcForceExertedBy(Planet b){
		double d = this.calcDistance(b);
		return G * this.mass * b.mass / d / d;
	}

	public double calcForceExertedByX(Planet b){
		double force = this.calcForceExertedBy(b);
		double d = this.calcDistance(b);
		return ((b.xxPos - this.xxPos) / d * force);
	}

	public double calcForceExertedByY(Planet b){
		double force = this.calcForceExertedBy(b);
		double d = this.calcDistance(b);
		return ((b.yyPos - this.yyPos) / d * force);
	}

	public double calcNetForceExertedByX(Planet[] b){
		double force = 0;
		for(int i = 0; i < b.length; i++){
			if (!this.equals(b[i])){
				force = force + calcForceExertedByX(b[i]);
			}
		}
		return force;
	}

	public double calcNetForceExertedByY(Planet[] b){
		double force = 0;
		for(int i = 0; i < b.length; i++){
			if (!this.equals(b[i])){
				force = force + calcForceExertedByY(b[i]);
			}
		}
		return force;
	}

	public void update(double t, double xForce, double yForce){
		double xAcc = xForce / this.mass;
		double yAcc = yForce / this.mass;
		this.xxVel += t * xAcc;
		this.yyVel += t * yAcc;
		this.xxPos += t * this.xxVel;
		this.yyPos += t * this.yyVel;
	}

	public void draw(){
		StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
	}
}