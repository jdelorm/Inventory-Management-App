Title: Inventory Management Application

Purpose: To build a JavaFX-based inventory system that allows users to manage parts and products using a clean GUI and data models

Author: John DeLorme

Contact: jdelorm@wgu.edu

Version: 1.6

Date: 3/26/2023

IDE: IntelliJ 2022.2.1 Community Edition

Java: 8 (build 1.8.0_361-b09)

JDK: 17.0.4.1

How to get program to run:

    1. Install latest edition of IntelliJ Community Edition
    2. Install Java 8
    3. Install JDK 17
    4. In IntelliJ click "Get from version control"
    5. Paste this URL: [https://github.com/jdelorm/WGUPS-Routing-Program](https://github.com/jdelorm/Inventory-Management-App)
    6. Choose where to save the project and click "Clone"
    7. Once the project loads, project should prompt you to download dependencies
    8. Find main.java and open it.
    8. Click "RUN" or the green arrow in the IDE and follow instructions in console

Troubleshooting: If having problems running the program ensure that you

Import Maven Dependencies

    IntelliJ IDEA should automatically detect the pom.xml file and prompt you to import the Maven project.

    If you don't see the prompt, manually trigger the Maven import:

        Open the Maven window (View -> Tool Windows -> Maven).

        Click the Refresh button (the circular arrow icon) to load the dependencies.

Set the JDK Version

    Ensure that your project is using JDK 17

    Go to File -> Project Structure:

        Under Project Settings -> Project, make sure Project SDK is set to JDK 17.

        Under Modules, ensure that the language level is also set to 17.
