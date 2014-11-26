/*
 * The class that checks whether an input string is properly nested.
 * It has a time complexity of O(N).
 */

import java.lang.IllegalArgumentException; //Illegal Character in String
import java.util.EmptyStackException; //Empty Stack That Can't be Popped
import java.util.Stack; //Stack Class

class StackChecker
{
    public boolean propNested(String S) 
    {
        //Edge Case
		if (S.length()==0) //Empty String
            return true; //Properly Nested
        
		//General Case
        int N = S.length(); //Get Length
        Stack<Character> nest = new Stack<Character>(); //Declare Stack of Characters
        
        for (int i=0; i<N; i++) //Loop Through String Characters
        {
            char tmp = S.charAt(i); //Get Current Character
            
			switch (tmp) //Depending on Character, Peform Action
            {
                case '{':
                case '[':
                case '(':
                    nest.push(tmp); //If One of Preceding Characters, Push Onto Stack
                    break; //Leave Switch
                case '}':
                case ']':
                case ')':
					try //If One of Following Characters
					{
						if (nest.pop()!=precede(tmp)) //Check Most Recent Push Value
							return false; //Improperly Nested If Not Respective Preceding Character
					}
					catch (EmptyStackException e) //If Empty Stack, Can't Pop
						{return false;} //Improperly Nested
                    break; //Leave Switch
                default: //Per Given Assumptions, Won't Happen, but I Included Anyway (I Just Felt Like It)
                    throw new IllegalArgumentException(); //Invalid Character
					//return false; //Could Also Just Say Not Nested Properly
            }       
        }
		
		if (nest.size()!=0) //After All Pushes and Pop, Stack Should be Empty
			return false; //If Not, Improperly Nested
		
		return true; //If Reached Here, Properly Nested
    }
	
	public char precede(char c) //Get Respective Preceding Character
    {
        switch (c) //Check Passed Character
        {
            case '}':
            case ']':
                return (char)(c-2); //General Case, Respective Preceding Character is Two Less on ASCII Table
            case ')': //Special Case
                return '('; //Return Respective Preceding Character, '('
            default: //Shouldn't Happen Per Program, but Placed to Give Program Better Evolution
                return c; //Return Character Passed
        }
    }
}