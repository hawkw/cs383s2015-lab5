# cs383s2015-lab5
Lab 5 for CS383 at Allegheny College

## How to Use:
 1. `./sbt assembly` will build the jarfile.
   - You do not need sbt or Scala installed, the shell script will take care of that
   - You DO need to export the variable `$EV3_HOME` in your runtime configuration (`.zshrc`, `.bashrc` and so forth). This should be the path to wherever LeJOS EV3 is installed on your system.
   - Because of issues related to managing multi-project SBT builds, it is necessary to edit `project/Build.scala` if you want to change whether you are building the evasion program (`RandomNav.scala`) or the tracking program (`Search.scala`).
 2. Connect the brick and run `deploy.sh` to send the jar file to the robot.
   - When it asks you for a password just press return.
   - Once again, the file name of the jar is hardcoded in the shell script and will need to be changed if you want to deploy the other program. Eventually this will be fixed.

## Implementation Details:
The tracking and evasion strategies we implemented are described in the
following sections.

### Evasion Strategy:
We kept this strategy as simple as possible. Our program `RandomNav` uses an
infinite stream of random degree and distance values to command the LeJOS `DifferentialPilot`
object to move the robot in a random walk. That is, the robot rotates for a
random degree value, then travels for a random distance. It does this until any
of the robot's buttons are pressed, at which point it exits the main function.

### Tracking Strategy:
Obviously, tracking is the more sophisticated task of the two. To accomplish the
difficult task of tracking a moving target using only one distance sensor, we devised a
way of extrapolating a future position of the target using the currently visible
location of the target and its last seen location. This meant that our robot
can turn to face this future position and thus track the target with an
acceptable degree of reliability.

Note that we assume our target robot uses a constant speed for its evasion
technique. Therefore, the only events that will cause our robot will lose track
of its target are the target robot changing its heading or stopping.
Whenever our robot loses track of its target, it enters a "panic mode", in which it
attempts to relocate the target by rotating in progressively widening sweeps
from left to right and right to left. Once it has relocated the target, it
resumes its method of predicting the target's future positions.

## Project Takeaways
The most challenging portion of this project was almost certainly the
implementation of the tracking strategy. The limitation of only one ultrasonic
sensor presented a significant obstacle that required more complex tracking
techniques to overcome. These techniques employed trigonometric functions that
allowed us to calculate the trajectory of the target using limited information.
Another challenge we faced was the use of Scala to write our programs. Despite
the divergence from our previous use of Java and Eclipse to write and build our
programs, we were able to successfully write, build, and upload Scala programs
that ran on our Mindstorms robot.
