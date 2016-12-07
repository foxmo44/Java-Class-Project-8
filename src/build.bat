del *.class
javac *.java
jar -cfv Project08Fox.jar *.java *.class
jar -ufmv Project08Fox.jar manifest.txt

