# About this project
This project is a helper which aims at offering a comfortable visualization
of dependent attributes in ACSD.
ACSD-specific files are excluded so they are not shared with the public.

# Structure
This project is composed of following modules
* shared: Common domain objects which are relevant for more than one module
* store: Running this application will parse and store the latest packaged
TSV-files in a neo4j database
* webapp: visualization of stored attributes and their dependencies

More information regarding the single projects can be found
in the project-specific README-files.