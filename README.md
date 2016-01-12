# FRC-2016

## Cloning this repository

*These instructions assume that you already have git and Eclipse configured.*

1. Clone the repository with 
```bash
    git clone git@github.com:frc604/FRC-2016 --recursive
```
**Make sure to `git clone` with the `--recursive` option to clone the framework submodule.**
If you forgot to do this, change into the project directory and run the following commands after `git clone`:
```bash
    cd FRC-2016
    git submodule init
    git submodule update
```

This will update the framework submodule.
   
2. The folder structure will now look something like this:

`/path/to/repo/*FRC-2016*/(FRC-2016, README.md, etc)`

In Eclipse, create a new workspace inside the first `FRC-2016` folder (the folder surrounded by asterisks). Create a new "Robot Java Project" with the following settings:

    Field|Value
    -----|-----
    Project Name|FRC-2016
    Project Package|com.\_604robotics.robot2016
    Project Type|Iterative Robot

3. Delete the Robot.java file in the project directory.
 
*These steps were labeled optional, but they are not*

4. Change into the submodule directory with
```bash
    cd FRC-2016/src/com/_604robotics/robotnik
```
It should state that the submodule is in a "detached HEAD" state.

5. Create a local branch to track the remote branch with
```bash
    git checkout 2015
```
If this doesn't work, use the command below:
```bash
    git checkout -b 2015 origin/2015
```

6. When the submodule is updated, run `git submodule update --remote` to pull in the changes.

## Troubleshooting

- When deploying code, Eclipse throws an error like "Unable to find Java compiler. Perhaps JAVA_HOME is not set to the JDK."
This can happen for one of two reasons. If deploying works with one workspace but not another, skip to step 2.

  1. `JAVA_HOME` or `PATH` does not contain the JDK path.
    On Windows, go to `Control Panel > System and Security > System` and select `Advanced System Settings`.
    
    Select "Environmental Variables" and look for `JAVA_HOME` and `PATH` variables. Make sure that a path like `C:\Program         Files\Java\jdk1.8.0_65` is on either `JAVA_HOME` or `PATH`. If it is not, add the aforementioned path to `PATH`.
    
    If this still doesn't work, move on to step 2.

  2. Eclipse is not configured to use the JDK.

    In Eclipse, go to `Window > Preferences` and find `Java > Installed JREs` and make sure that `jdk 1.8.0_65` or something       similar is listed on the left. If only `jre 1.8.0._65` is there, click "Add...".

   Select "Standard VM" and enter the path into "JRE home:". Click "Finish" and check for the `jdk 1.8.0_65` entry again.
   
- After cloning the code, the workspace path structure does not show existing paths as packages.
   
   - This will happen if you attempt to add the project to an existing workspace. *Make sure to add the project to a new workspace.*
   - This could also happen if you create the workspace in the wrong place. Please make sure to create a new workspace in the right place, as specified above. *If you choose the wrong folder to create the workspace in, the resulting project will have the wrong layout.*

## Important Notes

- **Do not add any code that isn't already public!** All code added before build season (January 9, 2016) must be available publicly. This is to comply with FRC rules.

  - If you want to experiment with the code now, please use the `pre_build` branch to do so.
  - **Do not merge the pre_build branch into master!** Un-merging is quite hard, and this will create a huge mess.

- Please contact [Ryan](mailto:rlee287@yahoo.com) or [Sang Gi](mailto:squeakadoodle6084@gmail.com) for any other questions.

## TODO:

- [x] Check if "optional" steps are really optional **They are necessary**
- [x] See if `git checkout -b 2015 origin/2015` can be replaced with `git checkout 2015` **Probably**
- [x] Play around with the `pre_build` branch
- [ ] Continue to update troubleshooting instructions as necessary
- [x] See if project can be added to existing workspace instead of new one: **Project must be added to new workspace.**
- [ ] Possibly add instructions as to cloning first and importing the code in another workspace?
