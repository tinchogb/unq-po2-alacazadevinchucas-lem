package model;

public class ExpertUser implements UserState {
	
	 @Override // Para poder testear
	    public boolean equals(Object o) {
	        return this.getClass().getName() == o.getClass().getName();
	 }

	@Override
	public boolean isExpert() {
		return true;
	}


}
