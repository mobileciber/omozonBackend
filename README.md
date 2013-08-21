# README zum hybridmobile-Backend (Mobile Computing) #

* Das Backend verwendet die [H2 Database Engine](http://www.h2database.com/html/main.html).
* Zur Entwicklung wird H2 als Stand-Alone-Prozess benutzt, "in Produktion" als In-Memory-Datenbank.
* Zum Starten der Datenbank `./start-db.sh` bzw. `./start-db.cmd` verwenden.
* Bauen des Projekts inkl. Integrationstests: `mvn clean verify -Pintegration`. Dabei werden H2 als Stand-Alone-Prozess und Jetty gestartet und nach Durchf�hren der Tests wieder beendet.
* Bauen des Projekts "f�rs Deployment": `mvn clean verify -Pproduction`.

bauen des Backends:
mvn clean verify
Starten des Backends:
mvn clean package jetty:run
Test (RESTClient/Basic Auth: freddy:krueger):
http://localhost:8484/hybridmobile-backend/api/customers?username=freddy

http://localhost:8484/hybridmobile-backend/api/customers
{"username":"michael", "passwd":"myers", "city":"Haddonfield Illinois","email":"michael@halloween.com", "name":"Michael Myers","street":"Smith’s Grove Sanitarium 1","zip":"22222"}
http://localhost:8484/hybridmobile-backend/api/customers?username=michael
