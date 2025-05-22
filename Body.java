public class Body {

    /** The 6 instance variables */
    public double xxPos; /** The body's current x position. */
    public double yyPos; /** The body's current y position. */
    public double xxVel; /** The body's current velocity in the x direction. */
    public double yyVel; /** The body's current velocity in the y direction. */
    public double mass; /** The body's mass. */
    public String imgFileName; /** The name of the file that corresponds to the image that depicts the body */
    private static final double Gravity = 6.67e-11; /** Gravity constant */

    /**
     * Constructor for a new Body object with all variables.
     */
    public Body(double xP, double yP, double xV, double yV, double m, String img) {
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = img;
    }

    /**
     * Constructor for a new Body object that copies the given Body object.
     */
    public Body(Body p) {
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }

    /**
     * Returns the distance of the supplied Body 'b' and the body that calls this method.
     */
    public double calcDistance(Body b) {
        double dx = b.xxPos - this.xxPos;
        double dy = b.yyPos - this.yyPos;
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * Returns a double describing the force exerted on this body by the given body 'b'.
     */
    public double calcForceExertedBy(Body b) {
        double numerator = Gravity * b.mass * this.mass;
        return numerator / (this.calcDistance(b) * this.calcDistance(b));
    }

    /**
     * Returns a double describing the force exerted in the x direction on this body by the given body 'b'.
     */
    public double calcForceExertedByX(Body b) {
        double dx = b.xxPos - this.xxPos;
        double numerator = b.calcForceExertedBy(this) * dx;
        return numerator / b.calcDistance(this);
    }

    /**
     * Returns a double describing the force exerted in the y direction on this body by the given body 'b'.
     */
    public double calcForceExertedByY(Body b) {
        double dy = b.yyPos - this.yyPos;
        double numerator = b.calcForceExertedBy(this) * dy;
        return numerator / b.calcDistance(this);
    }

    /**
     * Returns the net force exerted by all bodies in the supplied array 'a' upon the current body.
     * (x-direction wise)
     */
    public double calcNetForceExertedByX(Body[] a) {
        double total = 0;
        for (int i = 0; i < a.length; i += 1) {
            if (this.equals(a[i])) {
                continue;
            } else {
                total += this.calcForceExertedByX(a[i]);
            }
        }
        return total;
    }

    /**
     * Returns the net force exerted by all bodies in the supplied array 'a' upon the current body.
     * (y-direction wise)
     */
    public double calcNetForceExertedByY(Body[] a) {
        double total = 0;
        for (int i = 0; i < a.length; i += 1) {
            if (this.equals(a[i])) {
                continue;
            } else {
                total += this.calcForceExertedByY(a[i]);
            }
        }
        return total;
    }

    /**
     * Adjusts the current velocity and position of this body if an x-force 'fX' and a y-force 'fY' were
     * applied for 'dt' seconds.
     */
    public void update(double dt, double fX, double fY) {
        double ax = fX / this.mass;
        double ay = fY / this.mass;
        double newXVelocity = this.xxVel + dt * ax;
        double newYVelocity = this.yyVel + dt * ay;
        double newXPosition = this.xxPos + dt * newXVelocity;
        double newYPosition = this.yyPos + dt * newYVelocity;
        this.xxPos = newXPosition;
        this.yyPos = newYPosition;
        this.xxVel = newXVelocity;
        this.yyVel = newYVelocity;
    }

    /** Uses StdDraw API to draw the Body's image at the Body's position. */
    public void draw() {
        StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
    }
}