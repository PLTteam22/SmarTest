#jflex stl.flex
#./yacc.linux -J stl.y
#javac Parser.java>dump
#rm dump
java -cp smartest.jar smartest.Parser $1
