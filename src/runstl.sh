jflex stl.flex>dump
rm dump
./yacc.linux -J stl.y
javac Parser.java
java Parser $1
