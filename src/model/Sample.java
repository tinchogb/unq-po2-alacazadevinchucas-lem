package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class Sample {
	
	private VinchucaSpecies specie;
	private String picture;
	private Location location;
	private User user;
	private ArrayList<Opinion> opinions;
	private SampleState state;
	private Undefined undefined= new Undefined();
	private System system;
	private LocalDate creationDate;
	private Opinion lastOpinion;
	
	
	public Sample(VinchucaSpecies specie, String picture, Location location, User user,
			Opinion opinion, SampleState state, System system) {

		this.specie    = specie;
		this.picture   = picture;
		this.location  = location;
		this.user      = user;
		this.opinions  = new ArrayList<Opinion> ();
		opinions.add(opinion);
		this.state     = state;
		this.system  = system;
		this.creationDate = LocalDate.now();
		this.lastOpinion  = opinion;
	}
	
	public LocalDate getCreationDate() {
		return this.creationDate;
	}
	
	public Opinion getLastOpinion() {
		return this.lastOpinion;
	}
	
	public String getPicture() {
		return picture;
	}



	public Location getLocation() {
		return location;
	}



	public User getUser() {
		return user;
	}
	
	public System getSystem() {
		return system;
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


	public Answer getCurrentResult() {
		return state.getCurrentResult(this);
		
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
		this.lastOpinion  = opinion;
	}

	public void changeSampleState(SampleState sampleState) {
		this.state = sampleState;
		if (this.state.isValidated()) {
			this.system.Notify(this);
		}
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
				.filter(o -> o.getIsExpertOpinion())
				.map(o -> o.getType())
				.collect(Collectors.toList());
		return opinionsType;
	}
	
	public boolean isValidated() {
		return this.state.isValidated();
	}

}
