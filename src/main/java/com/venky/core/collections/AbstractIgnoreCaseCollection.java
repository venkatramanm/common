package com.venky.core.collections;

import java.util.Collection;
import java.util.Iterator;

import com.venky.core.util.ObjectUtil;

public abstract class AbstractIgnoreCaseCollection{

	private UpperCaseStringCache ucasehelper = UpperCaseStringCache.instance();
	protected String ucase(Object other){
		return ucasehelper.get(String.valueOf(other)); //Optimization.
	}

	public boolean removeAll(Collection<?> c) {
		boolean modified = false;
		Iterator<String> it = iterator();
		while (it.hasNext()){
			if (isElementPresentInCollection(it.next(), c)){
				it.remove();
				modified = true;
			}
		}
		return modified;
	}
	
	private static boolean isElementPresentInCollection(String element, Collection<?> c){
		boolean present = false;
		Iterator<?> other = c.iterator(); 
		while (other.hasNext()){
			String otherNext = String.valueOf(other.next()); 
			if (ObjectUtil.equals(UpperCaseStringCache.instance().get(otherNext), UpperCaseStringCache.instance().get(element))){
				present = true;
				break;
			}
		}
		return present;
	}

	public boolean retainAll(Collection<?> c) {
		boolean modified = false;
		Iterator<String> it = iterator();
		while (it.hasNext()){
			String myNext = it.next();
			if (!isElementPresentInCollection(myNext, c)){
				it.remove();
				modified = true;
			}
		}
		return modified;
	}

    public boolean containsAll(Collection<?> c) {
        for (Object e : c)
            if (!contains(e))
                return false;
        return true;
    }
    public boolean addAll(Collection<? extends String> c){
    	boolean ret = false;
    	for (String e:c){
    		ret = add(e);
    	}
    	return ret;
    }
	public abstract Iterator<String> iterator();
	public abstract boolean contains(Object o);
	public abstract boolean add(String o);
}
