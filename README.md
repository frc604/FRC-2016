# FRC-2016

## Cloning this repository

*These instructions assume that you already have git and Eclipse configured.*

1. Clone the repository with `git clone git@github.com:frc604/FRC-2016`
   
2. The folder structure will now look something like this:

`/path/to/repo/*FRC-2016*/(FRC-2016, README.md, etc)`

In Eclipse, create a new workspace inside the first `FRC-2016` folder (the folder surrounded by asterisks). Create a new "Robot Java Project" with the following settings:

    Field|Value
    -----|-----
    Project Name|FRC-2016
    Project Package|com.\_604robotics.robot2016
    Project Type|Iterative Robot

3. Delete the Robot.java file in the project directory. 

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
   - If you are using Eclipse for C/C++ developers, packages may not show up either. This is simply cosmetic and should not affect development.
   - This could also happen if you create the workspace in the wrong place. Please make sure to create a new workspace in the right place, as specified above. *If you choose the wrong folder to create the workspace in, the resulting project will have the wrong layout.*

## Important Notes

- **Do not add any code that isn't already public!** All code added before build season (January 9, 2016) must be available publicly. This is to comply with FRC rules.

- Please contact [Ryan](mailto:rlee287@yahoo.com) or [Sang Gi](mailto:squeakadoodle6084@gmail.com) for any other questions.

## TODO:

- [ ] Continue to update troubleshooting instructions as necessary
- [x] See if project can be added to existing workspace instead of new one: **Project must be added to new workspace.**
- [ ] Possibly add instructions as to cloning first and importing the code in another workspace?
