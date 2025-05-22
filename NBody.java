public class NBody {


    /** Returns a double corresponding to the radius of the universe in the given 'file'. */
    @Deprecated
    public static double readRadius(String file) {
        In in = new In(file);
        // int numberOfBodies = in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    /** Returns an array of Bodies corresponding to the bodies in the given 'file'. */
    public static Body[] readBodies(String file) {
        In in = new In(file);
        int numberOfBodies = in.readInt();
        // double radius = in.readDouble();
        Body[] arrayOfBodies = new Body[numberOfBodies];
        for (int i = 0; i < numberOfBodies; i += 1) {
            Body thisNewBody = new Body(in.readDouble(), in.readDouble(), in.readDouble(),
                    in.readDouble(), in.readDouble(), in.readString());
            arrayOfBodies[i] = thisNewBody;
        }
        return arrayOfBodies;
    }

    /**
     * Simulates the motion of celestial bodies in a 2D universe using Newtonian gravity.
     * It:
     * - Parses the total time `T`, time step `dt`, and data file name from command-line arguments
     * - Reads the radius of the universe and an array of `Body` objects from the input file
     * - Sets up the drawing canvas and draws the initial background and planets
     */
    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Body[] bodies = readBodies(filename);
        StdDraw.setScale(-radius, radius);
        StdDraw.picture(0, 0, "images/starfield.jpg");
        for (int i = 0; i < bodies.length; i += 1) {
            bodies[i].draw();
        }
        StdDraw.enableDoubleBuffering();
        double time = 0;

        /**
        * - In a time loop:
        *     - Calculates the net forces on each body
        *     - Updates their positions and velocities
        *     - Redraws the background and the updated positions of all bodies
        * - Prints out the final state of each body (position, velocity, mass, and image file name)
        */
        for (; time < T; time += dt) {
            double[] xForces = new double[bodies.length];
            double[] yForces = new double[bodies.length];
            for (int i = 0; i < bodies.length; i += 1) {
                xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
                yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
            }
            for (int i = 0; i < bodies.length; i += 1) {
                bodies[i].update(dt, xForces[i], yForces[i]);
            }
            StdDraw.picture(0, 0, "images/starfield.jpg");
            for (int i = 0; i < bodies.length; i += 1) {
                bodies[i].draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
        }
        StdOut.printf("%d\n", bodies.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < bodies.length; i += 1) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
                    bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);
        }
    }
}