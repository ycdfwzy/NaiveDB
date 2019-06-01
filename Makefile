# entry
ENTRY_POINT = org.naivedb.main
SERVER_ENTRY_POINT = org.naivedb.Server
CLIENT_ENTRY_POINT = org.naivedb.Client

# jar path
JAR_PATH = target/NaiveDB-1.0-jar-with-dependencies.jar

Default:
	@echo "make build: compile project."
	@echo "make clean: clear classes generated."
	@echo "make rebuild: rebuild project."
	@echo "make run: run app (6 ways)."
	@echo "make jar: package project into a executable jar."

build:
	@mvn compile

clean:
	@mvn clean

rebuild: clean build

run_deb:
	@mvn -q exec:java -Dexec.mainClass="$(ENTRY_POINT)"

run_deb_server:
	@mvn -q exec:java -Dexec.mainClass="$(SERVER_ENTRY_POINT)"

run_deb_client:
	@mvn -q exec:java -Dexec.mainClass="$(CLIENT_ENTRY_POINT)"

jar:
	@mvn package

run_rel:
	@java -classpath target/NaiveDB-1.0-jar-with-dependencies.jar $(ENTRY_POINT)

run_rel_server:
	@java -classpath target/NaiveDB-1.0-jar-with-dependencies.jar $(SERVER_ENTRY_POINT)

run_rel_client:
	@java -classpath target/NaiveDB-1.0-jar-with-dependencies.jar $(CLIENT_ENTRY_POINT)

test:
	@mvn -q test

