package model;

public class Undefined implements Answer{

	 @Override // Para poder testear
	    public boolean equals(Object o) {
	        return this.getClass().getName() == o.getClass().getName();
	 }
}
