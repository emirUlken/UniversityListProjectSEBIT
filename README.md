# UniversityListProjectSEBIT

This is an internship project for SEBİT AŞ. It lists university department list for the ÖSYM guide posted online.

In order to make it work, follow these steps:

1.) Start this Spring Boot project as a server. The server runs on http://localhost:8080 Make the API calls to this URI.<br />
2.) Go to <PYTHON LINK>. Run that python script AFTER the server has started. It will scrape all the data from the excel worksheet and make a POST request to the server with all department info in it.<br />
3.) Go to <REACT LINK>. Start the web client with "npm start" after steps 1 and 2. It will open up a localhost on port http://localhost:3000
  
TODO LIST:

- Fix DELETE operation on entities. It currently doesn't operate properly due to foreign key constraints.  
- Fix homepage when there is no data to be displayed.
- Add sorting functionality to filter out results on department list.


Emirhan Ülken
