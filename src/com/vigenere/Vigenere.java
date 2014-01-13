/**
 * @author İlker Korkut
 */
package com.vigenere;

import java.util.*;

public class Vigenere {

	// Turkish Alphabet
	public static String ALPHABET = "abcçdefgğhıijklmnoöprsştuüvyz";
	//Turkish constant IC(index of coincidence) frequency
	public static double turkishFrequency = 0.059;
	//Turkish Random text IC(index of coincidence) frequeny
	public static double trRandomFreq = 0.0344;
	
	/**
	 * Encrypts plain text with a key with Vigenere Algorithm
	 * @param plainText Plain text will be encrypt
	 * @param key Key will be use for encrypt the text
	 * @return Encrypted Text
	 */
	public String encrypt(String plainText,String key){
		
		key = repeatedKeyWithTextLength(plainText,key);
		String encryptedText = "";

	    for (int i=0; i<plainText.length(); i++){
	    	encryptedText += ALPHABET.charAt( ( ALPHABET.indexOf(key.charAt(i)) + ALPHABET.indexOf(plainText.charAt(i)) ) %29);
	    }

	    System.out.println("Encrypted Text:	"+encryptedText);
		
		return encryptedText;
	}
	/**
	 * Decrypts Vigenere encrypted text
	 * @param encText Cipher text for decrypt
	 * @param key Key for decrypting
	 * @return
	 */
	public String decrypt(String encText, String key){
		
		key = repeatedKeyWithTextLength(encText,key);
		String decryptedText = "";
		
		for (int i=0; i<encText.length(); i++){
			decryptedText += ALPHABET.charAt( ( ( ALPHABET.indexOf(encText.charAt(i)) - ALPHABET.indexOf(key.charAt(i)) ) +29 ) %29);
        }   
        
        System.out.println("decipher:	"+decryptedText);
		return decryptedText;
	}
	
	/**
	 * Repeats the key due to text length
	 * @param text
	 * @param key
	 * @return
	 */
	public String repeatedKeyWithTextLength(String text, String key){
		int keyLength = key.length();
        int textLength = text.length();
	    String tempKey =key;
	    int keyMod = textLength % keyLength;
        
	    for(int i=1; i<textLength/keyLength; i++){
            key += tempKey;
        }
	    
	    key += key.substring(0,keyMod);
	    return key;
	}
	/**
	 * 	<br>Text: 		<br>PLAIN TEXT EXAMPLE<br>
	 * 	KEYKE YKEY KEYKEYK : Repeated Key
	 * It splits the words with key and finds the which character has which chars in the text.
	 * 
	 * @param text Anytext
	 * @param keyLength Length of the key
	 * @return
	 */
	public List<String> splitTextWithKeyLength(String text, int keyLength){
		List<String> splittedText = new ArrayList<String>();

		StringBuilder[] sb = new StringBuilder[keyLength];
		
		for (int i = 0; i < keyLength; i++){
			sb[i] = new StringBuilder();
		}

		for (int i = 0; i < text.length(); i++){
			sb[i % keyLength].append(text.charAt(i));
		}
		
		for(StringBuilder s : sb){
			splittedText.add(s.toString());
		}

		return splittedText;
	}
	/**
	 * 
	 * @param text Encrypted text for analyse frequency
	 * @return
	 */
	public Map<String,Double> frequencyAnalyse(String text){
		int textLength = text.length();
		
		Map<String,Double> frequencies = new HashMap<String, Double>();
		
		
		for(int i=0; i<textLength; i++){
		    if(frequencies.containsKey(Character.toString(text.charAt(i)))){
				double val = frequencies.get(Character.toString(text.charAt(i)));
				frequencies.put(Character.toString(text.charAt(i)), ++val);
			}else{
				frequencies.put(Character.toString(text.charAt(i)), 1.0);
			}
		    
		}
		System.out.println("Frekanslar");
		for (Map.Entry<String, Double> entry : frequencies.entrySet()){
			frequencies.put(entry.getKey(), entry.getValue()/textLength);
		    System.out.println(entry.getKey() + "/" + entry.getValue());
		}
		
		return frequencies;
	}
	/**
	 * Turkish Letters Frequency IC(index of coincidence)
	 */
	public Map<String,Double> turkishLetterFrequency(){
		Map<String,Double> turkishLetterFrequency = new HashMap<String,Double>();
		turkishLetterFrequency.put("a", 11.68);
		turkishLetterFrequency.put("b", 2.95);
		turkishLetterFrequency.put("c", 0.97);
		turkishLetterFrequency.put("ç", 1.26);
		turkishLetterFrequency.put("d", 4.87);
		turkishLetterFrequency.put("e", 9.01);
		turkishLetterFrequency.put("f", 0.44);
		turkishLetterFrequency.put("g", 1.34);
		turkishLetterFrequency.put("ğ", 1.13);
		turkishLetterFrequency.put("h", 1.14);
		turkishLetterFrequency.put("ı", 8.27);
		turkishLetterFrequency.put("i", 5.20);
		turkishLetterFrequency.put("j", 0.01);
		turkishLetterFrequency.put("k", 4.71);
		turkishLetterFrequency.put("l", 5.75);
		turkishLetterFrequency.put("m", 3.74);
		turkishLetterFrequency.put("n", 7.23);
		turkishLetterFrequency.put("o", 2.45);
		turkishLetterFrequency.put("ö", 0.87);
		turkishLetterFrequency.put("p", 0.79);
		turkishLetterFrequency.put("r", 6.95);
		turkishLetterFrequency.put("s", 2.95);
		turkishLetterFrequency.put("ş", 1.94);
		turkishLetterFrequency.put("t", 3.09);
		turkishLetterFrequency.put("u", 3.43);
		turkishLetterFrequency.put("ü", 1.99);
		turkishLetterFrequency.put("v", 0.98);
		turkishLetterFrequency.put("y", 3.37);
		turkishLetterFrequency.put("z", 1.50);
		return turkishLetterFrequency;
	}
}
