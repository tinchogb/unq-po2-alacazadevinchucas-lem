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
		state.getCurrrentResult(this);
		// si hay respuesta de experto da esa, si no la más repetida entre los básicos
		//undefine si da empate entre más de una opción
	}
	
	public void saveOpinion(Opinion opinion) {
		state.saveOpinion(opinion,this);
	}
	
	public void addOpinion(Opinion opinion) {
		opinions.add(opinion);
	}
}
