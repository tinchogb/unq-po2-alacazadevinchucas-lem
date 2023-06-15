package model;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

public class SearchSample {

	private System 			system = System.getInstance();
	
	public List<Sample> creationDate(LocalDate date) {
		return this.system.getSamples().stream()
			.filter(s -> s.getCreationDate() == date)
			.toList();
	}
	public List<Sample> lastVerificationDate(LocalDate date) {
		return this.system.getSamples().stream()
		.filter(s -> s.getLastOpinion().getDate() == date)
		.toList();
	}
	public List<Sample> insectType(Answer aType) {
		return this.system.getSamples().stream()
		.filter(s -> s.getState().getCurrentResult(s) == aType)
		.toList();
	}
	public List<Sample> verificationLevel(SampleState level) {
		return this.system.getSamples().stream()
		.filter(s -> s.getState() == level)
		.toList();
	}

	public List<Sample> and(List<Sample> l1, List<Sample> l2) {
		Set<Sample> setSample1 = new HashSet<Sample>();
		Set<Sample> setSample2 = new HashSet<Sample>();
		setSample1.addAll(l1);
		setSample2.addAll(l2);
		Set<Sample> setSample3 = new HashSet<Sample>(setSample1);
		setSample3.retainAll(setSample2);
		return setSample3.stream().toList();
	}

	public List<Sample> or(List<Sample> l1, List<Sample> l2) {
		Set<Sample> setSample1 = new HashSet<Sample>();
		Set<Sample> setSample2 = new HashSet<Sample>();
		setSample1.addAll(l1);
		setSample2.addAll(l2);
		Set<Sample> setSample3 = new HashSet<Sample>(setSample1);
		setSample3.addAll(setSample2);
		return setSample3.stream().toList();
	}

}