package model;

public class ExpertUser implements UserState {

	
	@Override
	public Sample createSample(VinchucaSpecies specie, String picture, Location location,User user,
			Opinion opinion) {
		return new Sample (specie, picture,location,user,
				opinion, new VerifiedPartialSample(),System.getInstance());
		
	}

	@Override
	public void review(Sample sample, OpinionType type, UserState state,User user) {
		Opinion opinion = new Opinion(user,type , true);
		sample.saveOpinion(opinion);	
	}
	

}
