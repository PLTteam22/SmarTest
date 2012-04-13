jflex stl.flex
./yacc.linux -J stl.y
javac Parser.java
java Parser $1
