package tpIntegrador;

import java.util.ArrayList;
import java.util.stream.Stream;

public class Sample {
	
	public SpecieVinchuca specie;
	public Picture picture;
	public Location location;
	public IdUsuario idCreator;
	public ArrayList<Opinion> opinions = new ArrayList<Opinion> ();
	public SampleState state;
	
	
	public Sample(SpecieVinchuca specie, Picture picture, Location location, IdUsuario idCreator,
			Opinion opinion, SampleState state) {

		this.specie    = specie;
		this.picture   = picture;
		this.location  = location;
		this.idCreator = idCreator;
		opinions.add(opinion);
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
	
	
	public SampleState getState() {
		return state;
	}


	public Opinion getCurrentResult() {
		state.getCurrrentResult(this);
		// si hay respuesta de experto da esa, si no la más repetida entre los básicos
		//undefine si da empate entre más de una opción
		return null;
	}
	
	public void saveOpinion(Opinion opinion) {
		// 3 verificaciones para hacer. que el usuario no haya ya opinado
		//que si es parcial no puedan opinar los básicos
		//que si es verificada no pueda opinar nadie
		this.userVerificator(opinion);
		state.saveOpinion(opinion,this);
	}
	
	private void userVerificator(Opinion opinion) {
		this.verifyRepeatedUser(opinion);
		
	}

	private void verifyRepeatedUser(Opinion opinion) {
		Stream<Opinion> sOpinions = opinions.stream();
		if (sOpinions.map(o -> o.getUser()).anyMatch(o ->o.equals(opinion.getUser()) )){
			throw new RuntimeException("El usuario ya opinó en esta muestra");
		}
	}


	public void addOpinion(Opinion opinion) {
		opinions.add(opinion);
	}
	
	public void changeSampleState() {
		
	}
}
