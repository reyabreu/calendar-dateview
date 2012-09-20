calendar-dateview
=================

This is a project that showcases a calendar view of the selected month. The calendar view highlights the current date and allows month and year selection. The user can navigate monthwise by clicking on the forward and previous buttons, or alternatively, by clicking on the month label and selecting the desired month. The year selection is provided by a spinner component. The demo app includes sl4j console logging for the view actions.

The provided JDateView class implements the actual calendar view and was written following MVC principles.
	
This project was developed on Netbeans 7.1.2 between Sep-17 and Sep-19, 2012. It is a Maven 3.0.4 standalone Java SE project using Swing. It also uses JUnit 3.8.1 for unit testing and sl4j for logging. Additionally depends on Maven plugins for packaging, execution and javadoc generation.

A Maven goal has been added:

	exec:java is provided for Demo Application execution within Maven.

Alternatively after a Maven build, an executable jar is available on target directory, allowing for command line execution:

	>java -jar br.reyabreu.dateview-1.0-SNAPSHOT
	
	(provided the jars dependencies for org.slf4j.Logger and org.slf4j.LoggerFactory are in the classpath)
	
For any inquiries and/or suggestions please contact:
	
	Reynaldo J. Abreu Rizzo
	reyabreu@gmail.com

This code is provided as is.