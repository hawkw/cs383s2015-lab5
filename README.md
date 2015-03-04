# cs383s2015-lab5
Lab 5 for CS383 at Allegheny College

## How to Use:
 1. `./sbt assembly` will build the jarfile. 
   - You do not need sbt or Scala installed, the shell script will take care of that
   - You DO need to export the variable `$EV3_HOME` in your runtime configuration (`.zshrc`, `.bashrc` and so forth). This should be the path to wherever LeJOS EV3 is installed on your system.
 2. Connect the brick and run `deploy.sh` to send the jar file to the robot.