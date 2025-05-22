# N-Body
N-Body Simulation
This is a Python project that simulates the motion of multiple objects in space under the influence of gravity. It’s based on Newton’s laws of motion and gravitational force. The main goal is to update the positions and velocities of all objects over time and visualize their paths.

The project uses numpy for math and matplotlib for basic plotting. It’s a simple way to get an idea of how gravity works between multiple bodies.

What it does
Each object has a mass, position, and velocity. At every step of the simulation, the program calculates the gravitational force between every pair of objects, then updates their velocity and position based on that force. The result is a basic orbit-like movement that looks kind of like what planets or stars might do in space.

How to run it
You’ll need Python 3 installed, along with numpy and matplotlib. You can install them using pip:

nginx
Copy
Edit
pip install numpy matplotlib
Then just run the Python script:

nginx
Copy
Edit
python nbody_simulation.py
You can tweak the number of bodies, their initial positions, masses, and velocities directly in the code to see how different setups behave.

Ideas for improvement
Add collision detection so bodies can bounce off or merge

Add colors or trails to make the animation more interesting

Improve accuracy with better numerical integration (like Runge-Kutta)

Extend to 3D instead of just 2D

This was mostly a learning project to better understand physics simulation and working with arrays in Python. Still a work in progress, but fun to play with.
