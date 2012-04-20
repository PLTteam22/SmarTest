import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;
import java.util.Random;


public class BuiltInFunction {
	
	//load method
		StlSetNode load(String connection_string, String category){
			
			return null;
		}
	
	//askQuestion method
		int askQuestion(StlSetNode s){
			boolean isFull = false;
			float points = 0;
			while(true){	
			
				int size = s.getQuestionArrayList().size();
				//generate random number for picking question from set randomly
				int item = new Random().nextInt(size);
								
				if(size>0){
				
					//check if all questions have been asked once
					if(size == s.getQuestionIsAskedHM().size()){
						isFull = true;
					}
							
					for(int i=0;i<s.getQuestionArrayList().size();i++)
					{
					    if (i == item){
					    	//if all questions have  been asked once pick randomly
					    	if(isFull){
					    		//print question
					    		//wait to read the input from user
					    		//set the points obtained to points variable
					    		break;
					    	}
					    	else{
					    		//check if the question is in the hashmap or not and add to the hashmap if not exists
					    		if(!( s.getQuestionIsAskedHM().containsKey(s.getQuestionArrayList().get(i)))){
					    			s.getQuestionIsAskedHM().put((QuestionLiteralNode)s.getQuestionArrayList().get(i), true);
					    			//print question
						    		//wait to read the input from user
						    		//set the points obtained to points variable
					    			break;
					    		}					    		
					    		
					    	}					    	
					    	
					    }				       
					}
				}
			}	
			return 0;
		}
	//readLine
		String readline(){
			return null;
		}
	//print
		void print(String s){
			System.out.println(s);
		}
	//printVar for int 
		void printVar(int x){
			System.out.println(x);
		}
					
	//printVar for char
		void printVar(char x){
			System.out.println(x);
		}

	//printVar for float
		void printVar(float x){
			System.out.println(x);
		}
		
	//printVar for set and questions
		void printVar(Object x){
			System.out.println(x);
		}
	//length of a set
		int len (StlSetNode s){
			return s.getQuestionArrayList().size();
		}

}
