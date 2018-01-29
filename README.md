# Beauty Service Scheduler

A simple service that can be used by users who want to reserve a visit to beauty shop. Masters can use it like a scheduler and to plan their workday.

## Getting Started

The app could be build and deployed in 2 ways. Using [Intellij IDEA](https://www.jetbrains.com/idea/) or via command line with plugins. 

The source code was written using Google Code style scheme. For further information you can go to [Code Style](https://google.github.io/styleguide/javaguide.html)

### Installing

#### IDEA

To build and deploy web app using IDEA you need to import project from the GitHub repository. 

For deployment you need [Tomcat](https://tomcat.apache.org/download-80.cgi) server version 7 or 8. Just download it and unzip in any folder. 
In IDEA settings set the path to Tomcat up in Application Server menu.

The next step is to set up the build configuration. Create new configuration and choose Tomcat app as default and with artifact. 

After that the app will be deployed and war file created. 

To run you could type in browser: 

```
http://localhost:8080/Beauty
```

And the start page will appear.


#### Command Line

The app can be built and deployed very easily with the tomcat7-maven-plugin. Note: At the time I wrote this there was no tomcat8-maven-plugin; the tomcat-7-maven-plugin works just fine for tomcat 8.

1.Add the plugin to pom.xml

```
<plugin>
  <groupId>org.apache.tomcat.maven</groupId>
  <artifactId>tomcat7-maven-plugin</artifactId>
  <version>${version.tomcat.maven.plugin}</version>
  <configuration>
    <path>/${project.build.finalName}</path>
    <configurationDir>${env.CATALINA_HOME}</configurationDir>
    <additionalConfigFilesDir>${env.CATALINA_HOME}/conf</additionalConfigFilesDir>
    <uriEncoding>${project.build.sourceEncoding}</uriEncoding>
  </configuration>
</plugin>
```

2.Navigate to your project root, where the pom.xml is, and run the following command

```
mvn tomcat7:run
```

## Running the tests

All tests were written using JUnit and Mockito library. Feel free to cover some code.

## Built With

* [Tomcat](https://tomcat.apache.org/download-80.cgi) - Web app server
* [Maven](https://maven.apache.org/) - Dependency Management
* [JQuery](https://jquery.com/) - Web framework
* [MaterialDesignLite](https://getmdl.io/templates/index.html) - Styling library

## Version

Current version:1.0 (just released).

## Authors

* **Kyrylo Svintsov** - *Initial work* - [Github](https://github.com/svintsov)
