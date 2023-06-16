package model;

public class SpecialistUser extends User{

	public SpecialistUser() {
		super(new ExpertUser());
	}
	
	@Override
	public void verifyState() {
		
	}

}
