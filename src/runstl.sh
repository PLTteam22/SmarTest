#jflex stl.flex
#./yacc.linux -J stl.y
#javac Parser.java>dump
#rm dump
java Parser $1
