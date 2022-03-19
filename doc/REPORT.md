# Software Developed

## Contributions 
Amanjot Singh   %  
Daniel Khadivi  %  
Kayla Manahan   %  

# Objectives
Our aim was to make the code reusable by strategically applying design patterns. 

# Solution Description & Benefits
Our system implements a variety of patterns to assist with making the code more extensible. 

## Composite Pattern 
The [User](/src/main/java/ca/unb/usermanagement/model/User.java) class implements the Composite Pattern because it was the most effective way to create the role-based access heirarchy. 

The [User Roles](/src/main/java/ca/unb/usermanagement/model/EUserRole.java) in order from least to most access: 
* ROLE_USER
* ROLE_MODERATOR
* ROLE_ADMIN
* ROLE_SUPERUSER

## Singleton Pattern 
The [SuperUser](/src/main/java/ca/unb/usermanagement/model/SuperUser.java) and [UserRegistry](/src/main/java/ca/unb/usermanagement/model/UserRegistry.java) classes implement the singleton pattern because we want to ensure only one instance of each exist. The SuperUser should have all access rights to the system, but we needed to ensure only one SuperUser existed per UserRegistry. 

The benefit of implementing the Singleton pattern in these classes is having strciter control over the global access to the Object instance. 

## Strategy Pattern 
The [ReportGenerator](/src/main/java/ca/unb/usermanagement/service/report/ReportGenerator.java) class implements the Strategy Pattern which allows for flexibility in the [Type of Report](/src/main/java/ca/unb/usermanagement/service/report/ReportType.java) that is generated. 

Currently the system supports the following types of reports:
* PDF
* XLSX

## Factory Method Pattern
The [Response](/src/main/java/ca/unb/usermanagement/payload/response/Response.java) class implements the Factory method because it can not anticipate which Response Type it will need to create. 

By using the factory method pattern:  
* The subclasses decide which class to instantiate.
* Localized knowledge of delegate helper subclass. 

# UML Diagram 

<img src="User-Management-UML.png" />

# Using Our Code

## Where to start
The [Help](../HELP.md) file is the best place to begin. It contains the localhost webpages necessary for reviewing our project.

## Basic Scenerio