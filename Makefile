# filename
JAR_PKG = naivedb.jar

# entry
ENTRY_POINT = main

# source base dir
BASE_DIR = src

PACKAGES = \
	utils \
	BPlusTree

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
		javac -cp out -d out $(JFLAGS) $(BASE_DIR)/$$name/*.java; \
	done
	@javac -cp out -d out $(JFLAGS) $(BASE_DIR)/$(mainFunc).java;

rebuild: clean build

clean:
	rm -frv out/*

run:
	@java -cp out $(ENTRY_POINT)

jar:
	jar cvfe $(JAR_PKG) $(ENTRY_POINT) -C out .
