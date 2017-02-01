package com.venky.core.collections;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


public class SequenceMap<K,V> implements Map<K, V> {
	private HashMap<K, V> inner = new HashMap<K, V>();
	private SequenceSet<K> keys = new SequenceSet<K>();

	public int indexOf(K key){
		return keys.indexOf(key);
	}
	
	public V getValueAt(int i) {
		return get(keys.get(i));
	}
	
	public SequenceMap<K,V> reverse(){ 
		SequenceMap<K, V> ret = new SequenceMap<>();
		for (int i = keys.size() - 1; i >=0 ; i --){
			ret.put(keys.get(i), getValueAt(i));
		}
		return ret;
	}
	
	
	@Override
	public int size() {
		return inner.size();
	}

	@Override
	public boolean isEmpty() {
		return inner.isEmpty();
	}

	@Override
	public boolean containsKey(Object key) {
		return inner.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		return inner.containsValue(value);
	}

	@Override
	public V get(Object key) {
		return inner.get(key);
	}

	@Override
	public V put(K key, V value) {
		V old = inner.put(key, value);
		keys.add(key);
		return old;
	}

	@Override
	public V remove(Object key) {
		V v = inner.remove(key);
		keys.remove(key);
		return v;
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		for (K k : m.keySet()){
			put(k,m.get(k));
		}
	}

	@Override
	public void clear() {
		inner.clear();
		keys.clear();
	}

	@Override
	public Set<K> keySet() {
		return Collections.unmodifiableSet(keys);
	}

	@Override
	public Collection<V> values() {
		return Collections.unmodifiableCollection(inner.values());
	}

	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet() {
		return Collections.unmodifiableSet(inner.entrySet());
	}
	
	
	@Override
	public String toString(){ 
        if (size() == 0)
            return "{}";

        StringBuilder sb = new StringBuilder();
        sb.append('{');
        for (int i = 0 ; i < keys.size() ; i ++) {
            K key = keys.get(i);
            V value = inner.get(key);
            if (i > 0) {
            	sb.append(',').append(' ');
            }
            sb.append(key   == this ? "(this Map)" : key);
            sb.append('=');
            sb.append(value == this ? "(this Map)" : value);
        }
        return sb.append('}').toString();
	}
}
