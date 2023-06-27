package model;

public class SpecialistUser extends User{

	public SpecialistUser() {
		super(new ExpertUser());
	}
	
	@Override
	public boolean isExpert() {
		return true;
	}
	
	@Override
	public void verifyState() {
		
	}

}
