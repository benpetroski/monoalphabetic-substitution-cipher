package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException{
		Map<Character, Character> m = new HashMap<Character, Character>();
		FileInputStream in = null, map = null;
		BufferedWriter write = null;
		BufferedReader readIn = null, readMap = null;
		
		try{
			Scanner input = new Scanner(System.in);
			
			char[] substitution = new char[26];
			char[] alphabet = new char[26];
			String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
			int a, i=0;
			
			System.out.println("Would you like to encrypt or decrypt (0/1): ");
			String choice = input.next();
			
			if(choice.equals("0")){
				in = new FileInputStream("plain.txt");
				map = new FileInputStream("forwardRotationByTwo.txt");
				File erase = new File("cipher.txt");	erase.delete();
				write = new BufferedWriter(new FileWriter("cipher.txt", true));
				readIn = new BufferedReader(new InputStreamReader(in));
				readMap = new BufferedReader(new InputStreamReader(map));
				
				for(char c : letters.toCharArray()){
					if(c!='=' && c!='\n' && c!=' '){
						alphabet[i]=c;
						i+=1;
					}
				} i=0;
				while(readMap.read() != -1){
					String s = readMap.readLine();
					for(char ch : s.toCharArray()){
						if(ch!='=' && ch!='\n' && ch!=' '){
							substitution[i]=ch;
							i+=1;
						}
					}
				} for(i=0; i<alphabet.length; i++){
					//System.out.println(alphabet[i] + " " + substitution[i]);
					m.put(substitution[i], alphabet[i]);				
				} 
				System.out.println(m);
				String output;
				String s = readIn.readLine();
				while(s != null){					
					output = "";
					for(char ch : s.toCharArray()){
						if(m.containsKey(ch)){
							output = output.concat(m.get(ch)+"");
							//System.out.print(m.get(ch) + " - " + ch);
						}					
					}	System.out.println(output);	
					output = output.concat("\n");
					write.write(output);
					s = readIn.readLine();
				}
			} else if(choice.equals("1")){
					
				in = new FileInputStream("cipher.txt");
				map = new FileInputStream("forwardRotationByTwo.txt");
				File erase = new File("plain.txt");	erase.delete();
				write = new BufferedWriter(new FileWriter("plain.txt", true));
				readIn = new BufferedReader(new InputStreamReader(in));
				readMap = new BufferedReader(new InputStreamReader(map));
				
				for(char c : letters.toCharArray()){
					if(c!='=' && c!='\n' && c!=' '){
						alphabet[i]=c;
						i+=1;
					}
				} i=0;
				while(readMap.read() != -1){
					String s = readMap.readLine();
					for(char ch : s.toCharArray()){
						if(ch!='=' && ch!='\n' && ch!=' '){
							substitution[i]=ch;
							i+=1;
						}
					}
				} for(i=0; i<alphabet.length; i++){
					//System.out.println(alphabet[i] + " " + substitution[i]);
					m.put(alphabet[i], substitution[i]);				
				} 
				System.out.println(m);
				String output;
				String s = readIn.readLine();
				while(s != null){					
					output = "";
					for(char ch : s.toCharArray()){
						if(m.containsKey(ch)){
							output = output.concat(m.get(ch)+"");
							//System.out.print(m.get(ch) + " - " + ch);
						}					
					}	System.out.println(output);	
					output = output.concat("\n");
					write.write(output);
					s = readIn.readLine();
				}
			}
		} finally{
			if(in != null){
				in.close();
			} if(map != null){
				map.close();
			} if(write != null){
				write.close();
			}
		}
	}
}
