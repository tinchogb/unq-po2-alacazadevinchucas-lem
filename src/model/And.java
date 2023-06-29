package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

public class And extends 	CompositeQuery{

	
	
	public And(IQuery query1, IQuery query2) {
		super();
		this.query1 = query1;
		this.query2 = query2;
	}

	@Override
	public List<Sample> filter() {
		
		Set<Sample> setSample1 = new HashSet<Sample>();
		Set<Sample> setSample2 = new HashSet<Sample>();
		setSample1.addAll(query1.filter());
		setSample2.addAll(query2.filter());
		Set<Sample> setSample3 = new HashSet<Sample>(setSample1);
		setSample3.retainAll(setSample2);
		return setSample3.stream().toList();

	}

}
