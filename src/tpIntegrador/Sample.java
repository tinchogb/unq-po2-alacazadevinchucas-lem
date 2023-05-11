package tpIntegrador;

import java.util.ArrayList;

public class Sample {
	
	public SpecieVinchuca specie;
	public Picture picture;
	public Location location;
	public IdUsuario idCreator;
	public ArrayList<Opinion> opinions;
	public SampleState state;
	
	
	public Sample(SpecieVinchuca specie, Picture picture, Location location, IdUsuario idCreator,
			ArrayList<Opinion> opinions, SampleState state) {

		this.specie    = specie;
		this.picture   = picture;
		this.location  = location;
		this.idCreator = idCreator;
		this.opinions  = opinions;
		this.state     = state;
	}
	

	public Picture getPicture() {
		return picture;
	}



	public Location getLocation() {
		return location;
	}



	public IdUsuario getIdCreator() {
		return idCreator;
	}



	public ArrayList<Opinion> getOpinions() {
		return opinions;
	}



	public SpecieVinchuca getSpecie() {
		return specie;
	} 
	
	public Opinion getCurrentResult() {
		
	}
	
	public void saveOpinion(Opinion opinion) {
		state.saveOpinion(opinion,this);
	}
	
	public void addOpinion(Opinion opinion) {
		opinions.add(opinion);
	}
}
