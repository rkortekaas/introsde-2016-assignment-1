# introsde-2016-assignment-1
Assignment 1 for Introduction to Service Design

# Application purpose
The purpose of this application is to provide the user with health information about people present in people.xml. Users can access firstname, lastname, birthdate, weight, height and bmi. Also, users can convert java person objects to xml and json format.

# Tasks the code performs
1) Print all persons in people.xml with detail
2) Print healthprofile of person with id=5
3) Print persons with weight > 90kg
4) Marshall java person objects into xml -> output.xml
5) Unmarshall output.xml into java objects and print
6) Marshall java objects to json format -> output.json

# How to run code
Run the code by changing to the project directory and run ant command: "ant execute.evaluation". This will install necessary builds and other dependencies (ivy, libs, jaxb) and run the HealthProfile, JAXBMarshalling and JAXBUnMarshalling. The result is a printed list of previously mentioned tasks the code performs.