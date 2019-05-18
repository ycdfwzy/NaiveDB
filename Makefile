# filename
JAR_PKG = naivedb.jar

# entry
ENTRY_POINT = main

# test entry
TEST_ENTRY = Test.TestRunner

# source base dir
BASE_DIR = src

PACKAGES = \
	utils \
	BPlusTree \
	Database \
	Table \
	Test

JARS = :lib/junit-4.13-beta-3.jar:lib/hamcrest-core-1.3.jar

mainFunc = main

JAVAC = javac

JFLAGS = -encoding UTF-8

Default:
	@echo "make build: build project."
	@echo "make clean: clear classes generated."
	@echo "make rebuild: rebuild project."
	@echo "make run: run app."
	@echo "make jar: package project into a executable jar."

build:
	@for name in $(PACKAGES); \
	do \
		echo "compiling $$name";\
		javac -cp out$(JARS) -d out $(JFLAGS) $(BASE_DIR)/$$name/*.java; \
	done
	@javac -cp out$(JARS) -d out $(JFLAGS) $(BASE_DIR)/$(mainFunc).java;

rebuild: clean build

clean:
	rm -frv out/*

run:
	@java -cp out$(JARS) $(ENTRY_POINT)

test:
	@java -cp out$(JARS) $(TEST_ENTRY)

jar:
	jar cvfe $(JAR_PKG) $(ENTRY_POINT) -C out .
