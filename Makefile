# filename
JAR_PKG = naivedb.jar

# entry
ENTRY_POINT = org.naivedb.main
SERVER_ENTRY_POINT = org.naivedb.Server
CLIENT_ENTRY_POINT = org.naivedb.Client

# test entry
TEST_ENTRY = org.naivedb.Test.TestRunner

# source base dir
BASE_DIR = src/org/naivedb

# package postfixs
PACKAGES = \
	utils \
	Type \
	Persistence \
	BPlusTree \
	Statement \
	Table \
	Database \
	Test

# build targets (entry function postfix)
mainClass = main
serverClass = Server
clientClass = Client

# needed jar packages
JARS = :lib/junit-4.13-beta-3.jar:lib/hamcrest-core-1.3.jar:lib/commons-cli-1.4.jar:lib/jline-terminal-3.11.0.jar:lib/jline-terminal-jansi-3.11.0.jar:lib/jline-reader-3.11.0.jar

# javac command
JAVAC = javac

JFLAGS = -encoding UTF-8

Default:
	@echo "make build: build project."
	@echo "make clean: clear classes generated."
	@echo "make rebuild: rebuild project."
	@echo "make run: run app."
	@echo "make jar: package project into a executable jar."

build:
	@if [ ! -d out ] ;then mkdir out; fi
	@for name in $(PACKAGES); \
	do \
		echo "compiling $$name";\
		javac -cp out$(JARS) -d out $(JFLAGS) $(BASE_DIR)/$$name/*.java; \
	done
	@javac -cp out$(JARS) -d out $(JFLAGS) $(BASE_DIR)/$(mainClass).java;

server:
	@javac -cp out$(JARS) -d out $(JFLAGS) $(BASE_DIR)/$(serverClass).java;

client:
	@javac -cp out$(JARS) -d out $(JFLAGS) $(BASE_DIR)/$(clientClass).java;

rebuild: clean build

clean:
	rm -frv out/*

run:
	@java -cp out$(JARS) $(ENTRY_POINT)

run_server:
	@java -cp out$(JARS) $(SERVER_ENTRY_POINT)

run_client:
	@java -cp out$(JARS) $(CLIENT_ENTRY_POINT)

test:
	@java -cp out$(JARS) $(TEST_ENTRY)

jar:
	jar cvfe $(JAR_PKG) $(ENTRY_POINT) -C out .
