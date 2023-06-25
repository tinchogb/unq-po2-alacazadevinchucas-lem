package model;

import java.time.LocalDate;
import java.util.List;

public class CreationDate implements IQuery {

	private LocalDate		date;
	private List<Sample>	samples;

	public CreationDate(LocalDate aDate, List<Sample> aSamples) {
		this.date 	 = aDate;
		this.samples = aSamples;
	}

	@Override
	public List<Sample> filter() {
		return this.samples.stream()
		.filter(s -> s.getCreationDate().equals(this.date))
		.toList();
	}
}
