package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import model.VinchucaSpecies;

public class Sample {
	
	public VinchucaSpecies specie;
	public Picture picture;
	public Location location;
	public int idCreator;
	public ArrayList<Opinion> opinions;
	public SampleState state;
	private Undefined undefined= new Undefined();
	
	
	public Sample(VinchucaSpecies specie, Picture picture, Location location, int idCreator,
			Opinion opinion, SampleState state) {

		this.specie    = specie;
		this.picture   = picture;
		this.location  = location;
		this.idCreator = idCreator;
		this.opinions  = new ArrayList<Opinion> ();
		opinions.add(opinion);
		this.state     = state;
	}
	

	public Picture getPicture() {
		return picture;
	}



	public Location getLocation() {
		return location;
	}



	public int getIdCreator() {
		return idCreator;
	}



	public ArrayList<Opinion> getOpinions() {
		return opinions;
	}



	public VinchucaSpecies getSpecie() {
		return specie;
	} 
	
	
	public SampleState getState() {
		return state;
	}


	public void getCurrentResult() {
		state.getCurrrentResult(this);
		
	}
	
	public void saveOpinion(Opinion opinion) {
		this.userVerificator(opinion);
		state.saveOpinion(opinion,this);
	}
	
	void userVerificator(Opinion opinion) throws RuntimeException {
		if (opinion.isRepeatUser(this.getOpinions())){
			throw new RuntimeException("El usuario ya opinó en esta muestra");
		}	
	}

	public void addOpinion(Opinion opinion) {
		opinions.add(opinion);
	}

	public void changeSampleState(SampleState sampleState) {
		this.state = sampleState;
		
	}


	public Answer calculateResult(List<OpinionType> opinions) {
	
			if (this.elMasRepetidoSiHay(opinions).get(1) == null) {
				return (this.elMasRepetidoSiHay(opinions).get(0));
			}
			else {
				return (this.throwUndefined()); 
			}
			
	}
	
	public List<OpinionType> elMasRepetidoSiHay(List<OpinionType> opinions) {
		
		int n = opinions.size();
	    int max_count = 0;
	    OpinionType maxfreq = opinions.get(0);
	    OpinionType maxfreqrepeat = null;
	    List<OpinionType> max;
	    
	       
	      //Logic implementation
	      for (int i = 0; i < n; i++){
	         int count = 0;
	         for (int j = 0; j < n; j++){
	            if (opinions.get(i) == opinions.get(j)){
	               count++;
	            }
	         }
	        
	         if (count > max_count){
	            max_count = count;
	            maxfreq = opinions.get(i);
	            maxfreqrepeat = null;
	         } else if (count == max_count && opinions.get(i) != maxfreq) {
	        	 maxfreqrepeat = opinions.get(i);
	         }
	      }
	      max = Arrays.asList(maxfreq,maxfreqrepeat);
	      return max;
	}
	
	
	public Undefined throwUndefined() {
		return undefined;
		
	}
	
	public List<OpinionType> basicOpinions() {
		List<OpinionType> opinionsType = opinions
				.stream()
				.map(o -> o.getType())
				.collect(Collectors.toList());
		return opinionsType;
	}
	
	public List<OpinionType> expertsOpinions(){
		List<OpinionType> opinionsType = opinions
				.stream()
				.filter(o -> o.mustChangeState())
				.map(o -> o.getType())
				.collect(Collectors.toList());
		return opinionsType;
	}
}