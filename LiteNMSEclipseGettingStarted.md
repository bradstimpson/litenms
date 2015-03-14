# Introduction #

In order to develop LiteNMS you will need the following pieces of Software:

  * Eclipse 3.3 (Europa)
  * Maven Plugin for Eclipse
  * Subclipse for Subversion access
  * (Optional) A Linux shell is ideal for development of LiteNMS as it will simplify the entire build process.  Moreover, most if not all of the build scripts supplied with LiteNMS are made for Linux/BSD.

## Eclipse 3.3 Europa ##

Eclipse is a powerful Integrated Development Environment (IDE) for developing applications.  Eclipse is a very versatile IDE based on the concept of adding bundles (plugins) in run-time based on OSGi.  You can dowload at:  http://www.eclipse.org

There are many different versions available, for example, J2EE edition, C++ edition, etc...  Since LiteNMS is written in Java it is recommended that the J2EE edition is downloaded.  This can be found here:  [Eclipse 3.3 J2EE (linux)](http://www.eclipse.org/downloads/download.php?file=/technology/epp/downloads/release/20071103/eclipse-jee-europa-fall2-linux-gtk.tar.gz)

## Maven 2 Plugin ##

The build system of LiteNMS uses Maven.  Maven is an advanced project build tool, which can be considered as the Object Oriented version of Ant.  Maven is great at managing complex dependencies which is ideal for LiteNMS as it uses many 3rd party libraries.

To assist with development you can add a Maven 2 plugin to Eclipse.  This will allow you to run Maven goals (compile options) on the project directly from within Eclipse with no need for a command-line.  However, you should keep in mind that building the project from within Eclipse is magnitudes slower then from the command-line in Linux.

It is preferred that you install the Maven 2 plugin by using the Eclipse update site.  Eclipse has the ability to add plugins via a simple utility.  To install the Maven 2 plugin, perform the following steps:

  * **STEP 1:**  Open Eclipse and navigate to the Help -> Software Updates -> Find and Install...
  * **STEP 2:**  When the Window appears, click the radio button that says 'Search for new features to Install', and click Next.
  * **STEP 3:**  Click the 'New Remote Site...' button and in the window that appears add for the name "Maven 2 Plugin'  and the url "http://m2eclipse.codehaus.org/".
  * **STEP 4:**  Click Ok and then click Next.  You should be asked a series of questions, Agree and continue with downloading/installation.

## Subclipse Eclipse Subversion Plugin ##

The Subclipse plugin is used to access code stored within an SVN repository.  SVN is a very powerful source code revisioning system that allows for complex code merges, code branching and code reversion to older versions.

Installing the Subclipse plugin for Eclipse can be accomplished by following the same procedure for the Maven 2 plugin.  Instead, the name will be 'Subclipse 1.2.x (Eclipse 3.2+)' and the url will be 'http://subclipse.tigris.org/update_1.2.x'.  For a further detailed set of instructions you can refer to [Subclipse Installation](http://subclipse.tigris.org/install.html).

NOTE:  When installing Subclipse 1.2.x you should deselect 'Integrations (Optional)'.  This feature attempts to add Buckminster support to Subclipse (Buckminster is a set of frameworks and tools for automating build, assemble & deploy (BA&D) development processes in complex or distributed component-based development.).   We do not require this feature to build LiteNMS.

## Linux/BSD ##

Ideally, to build/develop for LiteNMS, you should use a Linux/BSD operating system.  Linux/BSD provides a suite of tools out-of-the-box that are indispensable as a developer.  However, if would prefer to use a Windows variant for your development, it is not impossilbe.  First of all, you will need to download Cygwin.  Cygwin is a Linux emulator for Windows which uses a simple dynamic link library (dll) or shared library to access the underlying Windows operating system.  Cygwin can provide all the tools you will need to develop LiteNMS.  You can download Cygwin from: http://cygwin.com/

NOTE:  If you use Cygwin, it will be in your best interest to store your Eclipse Workspace in a directory with no spaces (e.g.  Program\_Files as opposed to Program Files).  Spaces in directory names will cause you many headaches.  Also, since Cygwin is not a real Linux environment, you will be required to find a java compiler for Cygwin.  Please refer to: [site#1](http://www.cs.drexel.edu/~kschmidt/Ref/cygwinSetup.html).  It may also be possible to use the Windows Sun Java SDK from within Cygwin; unfortunately, that is beyond the scope of this wiki.

# First Steps (Work in Progress) #

So now you should have your development environment setup with the required plugins.  You will need to perform the following steps:

  * Check the code out of the SVN
  * Run the setup.sh script, which will setup your environment.  It will create the needed directories as well as install some LiteNMS dependencies into your local Maven repository.
  * Perform your first 'in Eclipse build' (Alternatively, you can build from the command-line if desired).
  * Add the Maven dependencies to the build path.
  * Update your source folders.

Each step is covered in detail below:

## Check out from SVN ##
  * First, you will want to open the SVN repository exploring perspective.  You can accomplish this by going to Window -> Open Perspective -> Other.  In the Popup window, select 'SVN Repository Exploring'.
  * Now, add the google SVN to the subclipse SVN list.  This can be accomplished by clicking the little repository button at the top of the browse pane.

![http://litenms.googlecode.com/files/Subclipse1.jpg](http://litenms.googlecode.com/files/Subclipse1.jpg)

A new window should appear.  Enter 'https://litenms.googlecode.com/svn/trunk' and click Finish.

![http://litenms.googlecode.com/files/Subclipse2.jpg](http://litenms.googlecode.com/files/Subclipse2.jpg)

  * An Error window will pop up, stating that the security certificate is not valid.  Click the button Accept Permanently.  A message should appear stating that an error occured validating the site.  Tell it to add anyways.

  * Now, you will need to navigate to the trunk of the repository to download the source code.  If you click on the newly added SVN repository, pending will appear and a popup will appear asking you to enter your username and password, like so,

![http://litenms.googlecode.com/files/Subclipse3.jpg](http://litenms.googlecode.com/files/Subclipse3.jpg)

```
ENTER YOUR USERNAME (your google.com username)
AND YOUR PASSWORD FROM THIS SITE (http://code.google.com/hosting/settings)
==> Click save password to make your life easier.
```


  * Right click on the project and go to 'Checkout'. A window will appear. Make sure the 'Check out as a project configured using the New Project Wizard'.  Click Finish.

![http://litenms.googlecode.com/files/Subclipse4.jpg](http://litenms.googlecode.com/files/Subclipse4.jpg)

  * After a few seconds a window will appear asking you if you really want to checkout the root of the project.  Click 'Yes'.  The new project 'Select a Wizard' window should appear.

![http://litenms.googlecode.com/files/Subclipse5.jpg](http://litenms.googlecode.com/files/Subclipse5.jpg)

  * Select Java Project and click 'Next.  In the Java Project Window that appears, enter the name of the project 'litenms' and click Finish.
  * Voila!  After some downloading, you should have the project in your workspace.

## Execute the setup.sh script ##

  1. Execute the setup.sh script by navigating to the root directory litenms/ and typing ./setup.sh .  This will install the dependencies in the dependencies folder, create your maven repository and copy settings.xml to this repository.

## Enable the Maven Plugin ##

  1. In Eclipse, navigate to the litenms project.  Right click at the root of the project, go to Maven -> Enable.  This will now proceed to build the project.  If you encounter problems, please see below.

## Update Source Folders ##

  1. Now, following the correct build, right click on the root of the project and go to Maven -> Update Source Folders.

# Encountered Problems #

Here are some of the most common problems I have encountered.

1.  **_Maven plugin does not work_**:  Most of the time this is because the plugin can not find the Maven local repository, usually installed under {User HOME}/.m2/repository.  If you create this directory and then execute export MAVEN\_HOME = "{USER\_HOME}/.m2/"  it should correct the issue, where {USER\_HOME} equals your home directory.  To make my life easier, I usually add the following lines to my .bashrc file so that they are set automatically on system startup:

```
export JAVA_OPTS="-Xms512m -Xmx1024m"
export JAVA_HOME=/usr/lib/jvm/java-1.5.0-sun
export MAVEN_HOME=/home/bstimpson/.m2
export MAVEN_OPTS=-Xmx1024m
```

**NOTE:**  You may want to adjust some of the memory values in the above options to match the amount of system memory available (i.e. if you have 512Mb ram, set -Xms40m -Xmx 256).

2.  **_Subclipse plugin won't install_**:  This could be caused by a couple of things.  1)  You are not running Eclipse 3.2 or newer or 2) you selected to install all the options.  If you selected to install all the options this will cause a dependency error.  If you open the tree under Subclipse 1.2.x (Eclipse 3.2+), you will see 'Integrations (Optional)'.  If you deselect this, the dependency error should be removed.


# Plugins Summary #

Here is a brief summary of all the plugins I use in Eclipse:
```
Name:  Maven 2 Plugin
Url: http://m2eclipse.codehaus.org/

Name: Subclipse 1.2.x (Eclipse 3.2+)
Url: http://subclipse.tigris.org/update_1.2.x

Name: Spring IDE 1.3.1
Url: http://springide.org/updatesite/

Name: Groovy IDE
Url: http://dist.codehaus.org/groovy/distributions/update/

Name: Jboss Tools
Url: http://download.jboss.org/jbosstools/updates/stable

Name:  Jboss Rules IDE
Url: http://downloads.jboss.com/drools/updatesite3.3/
```