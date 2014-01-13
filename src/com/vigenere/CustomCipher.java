package com.vigenere;

import java.util.*;

public class CustomCipher {
	
	private static int changeIndex = 2;
	
	public String encryptAndDecrypt(String text){

		List<Integer> oddIndexes = new ArrayList<Integer>();
		List<Integer> evenIndexes = new ArrayList<Integer>();
		
		List<Character> oddChars = new ArrayList<Character>();
		List<Character> evenChars = new ArrayList<Character>();
		
		StringBuilder encText = new StringBuilder(text);
		
		for(int i=0; i<text.length(); i++){
			if( (i%changeIndex) != 0){
				oddIndexes.add(i);
				oddChars.add(text.charAt(i));
			}else if( (i%changeIndex) == 0){
				evenIndexes.add(i);
				evenChars.add(text.charAt(i));
			}
		}
		
		int index = 0;
		for(int o: oddIndexes){
			encText.setCharAt(o, evenChars.get(index%evenChars.size()));
			index++;
		}
		for(int e: evenIndexes){
			encText.setCharAt(e, oddChars.get(index%oddChars.size()));
			index++;
		}

		return encText.toString(); 
	}
}
