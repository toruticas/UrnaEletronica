CC=javac

all: movefiles

movefiles: generateJar
	mv *.class bin/

generateJar: compile
	jar cfm UrnaEletronica.jar MANIFEST.mf *.class

compile:
	$(CC) src/*.java
	mv src/*.class .
