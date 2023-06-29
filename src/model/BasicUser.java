package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class BasicUser implements UserState {

	 @Override // Para poder testear
	    public boolean equals(Object o) {
	        return this.getClass().getName() == o.getClass().getName();
	 }

	@Override
	public boolean isExpert() {
		return false;
	}
	

	
}
