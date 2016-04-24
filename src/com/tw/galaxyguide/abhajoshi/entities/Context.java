package com.tw.galaxyguide.abhajoshi.entities;

import java.util.HashMap;
import java.util.Map;

/**
 * The context of the input processing.
 * 
 * @author abha
 *
 */
public class Context {
	private Map<String, Integer> map = null;

	public Context() {
		map = new HashMap<>();
	}

	/**
	 * 
	 * @param name
	 * @return
	 */
	public boolean exists(String name) {
		return map.containsKey(name);
	}

	/**
	 * 
	 * @param token
	 * @param num
	 */
	public void addToContext(IToken token, Integer num) {
		map.put(token.getName(), num);
	}

	/**
	 * 
	 * @param name
	 * @return
	 */
	public Integer getDecimalValue(String name) {
		return map.get(name);
	}
}
