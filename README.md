# UniversityListProjectSEBIT

This is an internship project for SEBİT AŞ. It lists university department list for the ÖSYM guide posted online.

In order to make it work, follow these steps:<br /><br />

Step 1.) Start this Spring Boot project as a server. The server runs on http://localhost:8080 Make the API calls to this URI.<br /><br />
Step 2.) Go to https://github.com/emirUlken/Excel_Scrape_With_Python_OSYM_2 Run that python script AFTER the server has started. It will scrape all the data from the excel worksheet and make a POST request to the server with all department info in it.<br /><br />
Step 3.) Go to https://github.com/emirUlken/Web_Client_OSYM_3 Start the web client with "npm start" after steps 1 and 2. It will open up a localhost on port http://localhost:3000 <br /><br />
  
TODO LIST:

- Fix DELETE operation on entities. It currently doesn't operate properly due to foreign key constraints.  
- Fix homepage when there is no data to be displayed.
- Add sorting functionality to filter out results on department list.


Emirhan Ülken
