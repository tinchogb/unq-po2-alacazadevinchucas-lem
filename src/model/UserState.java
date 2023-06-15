package model;

public interface UserState {
	

	public Sample createSample(VinchucaSpecies specie, String picture, Location location, User user,
			Opinion opinion);

	public void review(Sample sample, OpinionType type, UserState state, User user);


}
