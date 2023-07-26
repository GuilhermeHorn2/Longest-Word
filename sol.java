package misc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;


public class main_misc {
	
	
	public static final int MAX = 1_000_000;
	
	
	
	public static void main(String[] args) {
		
	
		List<String> bank = new ArrayList<>(Arrays.asList("cat","banana","dog","nana","walk","walker","dogwalker"));
		
		System.out.println(longest_word(bank));
		
	
		
		
	}
	
	private static void swap(List<List<Integer>> vec,int a,int b) {
		
		//O(1),because size of the element is constant(2)
		
		List<Integer> tmp = new ArrayList<>();
		tmp.addAll(vec.get(a));
		vec.set(a, vec.get(b));
		vec.set(b, tmp);
		
		
		
	}
	
	private static int partition(List<List<Integer>> vec,int strt,int end){
		
		int pivot = vec.get((strt+end)/2).get(0);
		
		while(strt <= end){
			
			while(vec.get(strt).get(0) < pivot){
				strt++;
			}
			while(vec.get(end).get(0) > pivot){
				end--;
			}
			if(strt <= end){
				swap(vec,strt,end);
				strt++;
				end--;
			}
		}
		return strt;
	}
	
	private static void quick_sort(List<List<Integer>> vec,int strt,int end) {
		
		int part = partition(vec,strt,end);
		
		if(strt < part-1) {
			quick_sort(vec,strt,part-1);
		}
		if(end > part){
			quick_sort(vec,part,end);
		}
	}
	
	private static List<String> sort_by_length(List<String> bank){
		
		List<List<Integer>> vec = new ArrayList<>();
		
		for(int i = 0;i < bank.size();i++) {
			List<Integer> tmp = new ArrayList<>(Arrays.asList(bank.get(i).length(),i));//length,index
			vec.add(tmp);
		}
		
		quick_sort(vec,0,vec.size()-1);
		
		List<String> sorted = new ArrayList<>();
		
		for(int i = 0;i < vec.size();i++){
			int idx = vec.get(i).get(1);
			sorted.add(bank.get(idx));
		}
		
		return sorted;
	}
	
	
	private static boolean can_build(List<String> bank,String word,int strt,int end){
		
		if(word.equals("")){
			return true;
		}
		
		for(int i = strt;i <= end;i++){
			
			String a = bank.get(i);
			
			if(word.startsWith(a)){
				
				String b = word.replace(a, "");
				if(can_build(bank,b,strt,end)){
					return true;
				}
				
			}
		}
		return false;
		
	}
	
	private static String longest_word(List<String> bank){
		
		List<String> bank_sorted = sort_by_length(bank);
		
		int l = bank_sorted.size();
		
		String longest_word = "";
		
		for(int i = 0;i < bank_sorted.size();i++){
			
			String word = bank_sorted.get(i); 
			
			//check if word can be built from other words
			
			if(can_build(bank_sorted,word,0,i-1)){
				longest_word =  word;
			}
			
			
			
		}
		
		return longest_word;
	}
	
}
