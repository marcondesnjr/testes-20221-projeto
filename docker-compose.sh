 #!/bin/sh

mvn clean install
sudo docker compose up --build -d