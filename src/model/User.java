package model;

import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;
public class User {

	private UserState state;
	private System system= System.getInstance();
	
	public System getSystem() {
		return system;
	}

	public User(UserState state) {
		this.state = state;
	}
	
	public void verifyState() {
		if (this.enoughOpinionsLastMonth() && this.enoughSamplesLastMonth()) {
			this.state = new ExpertUser();
		} else {
			this.state = new BasicUser();
		}
		
	}

	private boolean enoughSamplesLastMonth() {
		List<Sample> lista = system.getSamples(); 
		List<VinchucaSpecies> opinions = new ArrayList<VinchucaSpecies> ();
		
		lista.stream().filter(o -> o.getUser()==this && o.getCreationDate().isAfter(LocalDate.now().minusDays(30)))
			.forEach(s -> opinions.add(s.getSpecie()));
		return opinions.size()>=10;

	}

	private boolean enoughOpinionsLastMonth() {
		List<Sample> lista = system.getSamples();
		List<Opinion> opinions = new ArrayList<Opinion> ();
		
		lista.stream().forEach(s -> opinions.addAll(s.getOpinions())); //llena opinions con todas las opiniones hechas en el sistema
		List<Opinion> opinionsUser = opinions.stream().filter(o-> o.getUser()==this &&
					o.getDate().isAfter(LocalDate.now().minusDays(30))).toList();//quedan solo las opiniones del usuario de los ultimos 30 días
		
		return opinionsUser.size()>= 20;
		 
	}

	public UserState getState() {
		
		return this.state;
	}



}

