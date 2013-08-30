package main;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.lang.String;

public class Main {
	public static void main(String[] args) throws IOException{
		Map<Character, Character> m = new HashMap<Character, Character>();
		FileInputStream in = null, map = null;
		FileOutputStream out = null;
		BufferedReader readIn = null, readMap = null;
		
		try{
			in = new FileInputStream("cipher.txt");
			map = new FileInputStream("forwardRotationByOne.txt");
			out = new FileOutputStream("plain.txt");
			readIn = new BufferedReader(new InputStreamReader(in));
			readMap = new BufferedReader(new InputStreamReader(map));
			char[] substitution = new char[26];
			char[] alphabet = new char[26];
			String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
			int a, i=0;
			
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
			while(readIn.read() != -1){
				String s = readIn.readLine();
				String output = "";
				for(char ch : s.toCharArray()){
					if(m.containsKey(ch)){
						output = output.concat(m.get(ch)+"");
						//System.out.print(m.get(ch) + " - " + ch);
					}					
				}	System.out.println(output);			
				out.write(output);
			} while((a = in.read()) != -1){
				out.write(a);			
			}
		} finally{
			if(in != null){
				in.close();
			} if(map != null){
				map.close();
			} if(out != null){
				out.close();
			}
		}
	}
}
