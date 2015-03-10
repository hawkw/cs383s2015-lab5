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
