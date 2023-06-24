package model;

public class BasicUser implements UserState {

	 @Override // Para poder testear
	    public boolean equals(Object o) {
	        return this.getClass().getName() == o.getClass().getName();
	 }
	
}
