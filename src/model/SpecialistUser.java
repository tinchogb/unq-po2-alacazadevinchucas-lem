package model;

public class SpecialistUser extends User{

	public SpecialistUser() {
		super(new ExpertUser());
	}
	@Override
	public Sample createSample(VinchucaSpecies specie, String picture, Location location,
			Opinion opinion) {
		return new Sample (specie, picture,location,this,
				opinion, new VerifiedPartialSample(),System.getInstance());
	}
	@Override
	public void review(Sample sample, OpinionType type, UserState state) {
		Opinion opinion = new Opinion(this,type , true);
		sample.saveOpinion(opinion);
	}
	
	@Override
	public void verifyState() {
		
	}

}
