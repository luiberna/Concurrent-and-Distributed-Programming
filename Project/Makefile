JAVA ?= java
JAVAC ?= javac
MAIN ?= iscot.client.actuators.AirConditioning
ARGS ?= localhost 12345 Kitchen

SOURCES := $(shell find src -type f -name '*.java')
OUT_DIR := out

.PHONY: all build run

all: build

# Compile every source file on every invocation.
build:
	mkdir -p $(OUT_DIR)
	$(JAVAC) -encoding UTF-8 -d $(OUT_DIR) $(SOURCES)

# Build must succeed before starting the selected main class.
run: build
	$(JAVA) -cp $(OUT_DIR) $(MAIN) $(ARGS)
