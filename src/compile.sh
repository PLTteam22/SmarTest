jflex stl.flex>dump
rm dump
./yacc.linux -J stl.y
javac Parser.java>dump
rm dump
java Parser $1>STL.java
javac STL.java
