package com.qiYang.util;

import java.util.ArrayList;
import java.util.List;

public class Separator {
	public static final String SYMBOL = "@";
	
	//以指定符号分割字符串，并转成整型数组输出
	public static List<Integer> combiList(String str,String symbol){
		String[] arr = str.split(symbol);
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < arr.length; i++) {
			list.add(Integer.parseInt(arr[i]));
		}
		
		return list;
	}
	//把数字字符串已某种符号拼接成字符串
	public static String combiStr(List<Integer> str,String symbol){
		String str1 = "";
		boolean f = true;
		for (Integer i : str) {
			if(f){
				str1 += i+"";
				f = false;
			}else
				str1 += symbol+i;
		}
		return str1;
	}
	//把数字字符串已某种符号拼接成字符串
	public static String combiStr(int[] str,String symbol){
		String str1 = "";
		boolean f = true;
		for (Integer i : str) {
			if(f){
				str1 += i+"";
				f = false;
			}else
				str1 += symbol+i;
		}
		return str1;
	}
}
