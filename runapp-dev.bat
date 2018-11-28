java -Xms256m -Xmx512m -XX:MaxDirectMemorySize=1G -XX:+UseG1GC -XX:MaxGCPauseMillis=200 -Dspring.profiles.active=dev -jar .\target\portvaluation-0.0.1-SNAPSHOT.jar
