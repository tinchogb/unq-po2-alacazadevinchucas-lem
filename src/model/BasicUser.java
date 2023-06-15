package model;

public class BasicUser implements UserState {

	
	@Override
	public Sample createSample(VinchucaSpecies specie, String picture, Location location, User user,
			Opinion opinion) {
		return new Sample (specie, picture,location,user,opinion, new UnverifiedSample(),System.getInstance());
		
	}

	@Override
	public void review(Sample sample, OpinionType type, UserState state,User user) {
		Opinion opinion = new Opinion(user,type , false);
		sample.saveOpinion(opinion);
	}
	
}
