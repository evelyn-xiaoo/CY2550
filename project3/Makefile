JFLAGS = -h -w -c -n -s
JC = --help --words --caps --numbers --symbols

.SUFFIXES: .txt .class .java

.java.class:
        $(JC) $(JFLAGS) $*/project3/xkcdpwgen.java

CLASSES = 
        Main.java 

default: classes

classes: $(CLASSES:.java=.class)

clean:
        $(RM) *.class
