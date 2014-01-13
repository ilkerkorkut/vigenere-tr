package com.vigenere;

import java.util.*;
import java.util.Map.Entry;

public class Main {

	public static void main(String[] args){
		
		/**
		 * Condition can take "encrypt","decrypt","findKey" for Vigenere
		 */
		String condition = "findKey";
		
		// Key for encrypt plain text
		String key = "test";
		
		Vigenere vigenere = new Vigenere();
		
		if(condition.equals("encrypt")){
			
			String plainText = "bubirplaintekst";
			
			vigenere.encrypt(plainText, key);
			
		}else if(condition.equals("decrypt")){
			
			String encText = "uaşeluftesmzgvm";
			
			vigenere.decrypt(encText, key);
			
		}else if(condition.equals("findKey")){
			String encText = "uaşeluftesmzgvm";
			
			int keyLength = 4; // assume, we know key length
			
			Map<String,Double> frequencies = new HashMap<String,Double>();
			List<String> splittedTextList = vigenere.splitTextWithKeyLength(encText, keyLength);
			
			String foundKey = "";
			for(String s : splittedTextList){
				System.out.println(s);
				frequencies = vigenere.frequencyAnalyse(s);
				
				
				double maximum = Collections.max(frequencies.values());
				String maximumCharacter = "";
				List<String> maxChars  = new ArrayList<String>();
				int count = 0;
				for(Entry<String,Double> entry : frequencies.entrySet()) {
				    if(entry.getValue() == maximum){
				    	maximumCharacter = entry.getKey();
				    	maxChars.add(entry.getKey());
				    	count++;
				    }
				}
				char keyChar = '*';
				if(count>1){
					String mLetters = "";
					for(String str : maxChars){
						mLetters += str+" ";
						int charIndex = Vigenere.ALPHABET.indexOf(str);
						keyChar = Vigenere.ALPHABET.charAt(charIndex);
					}
					System.out.println("En çok kullanılan harfler: "+mLetters);
				}
				else{
					int charIndex = Vigenere.ALPHABET.indexOf(maximumCharacter);
					keyChar = Vigenere.ALPHABET.charAt(charIndex);
					System.out.println("En çok kullanılan harf: "+maximumCharacter);
				}
				/**
				 * Finding key section will be evolved with Turkish Alphabet.
				 */
				foundKey += keyChar;
				
				System.out.println("###################################");
			}
			System.out.println("Tahmini Anahtar : "+foundKey);
			
			// Decrypt with estimated key
			vigenere.decrypt(encText, foundKey);
			
		}else if(condition.equals("customCipher")){
			String plainText = "bubirplaintekst";
			
			CustomCipher cp = new CustomCipher();
			
			System.out.println("Şifrelenmiş text:	"+ cp.encryptAndDecrypt(plainText));
			System.out.println("Deşifrelenmiş text:	"+ cp.encryptAndDecrypt(cp.encryptAndDecrypt(plainText)));
		}
		
	}
}
