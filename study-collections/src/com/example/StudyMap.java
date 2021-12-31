package com.example;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 */
public class StudyMap {

	public static void main(String[] args) {
		Map<String,Integer> areaCodes = new HashMap<>();
		areaCodes.put("ankara", 312);
		areaCodes.put("istanbul-anadolu", 216);
		areaCodes.put("istanbul-avrupa", 212);
		System.out.println(areaCodes.get("ankara"));
		for (var entry : areaCodes.entrySet()) {
			System.out.printf("%20s %3d\n",entry.getKey(),entry.getValue());
		}
		for (var city : areaCodes.keySet()) {
			System.out.printf("%20s %3d\n",city,areaCodes.get(city));			
		}
		for (var code : areaCodes.values()) {
			System.out.println(code);
		}
		
	}

}
