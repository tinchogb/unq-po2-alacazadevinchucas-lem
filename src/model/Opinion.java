package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Stream;

 
public class Opinion { 
	
	private User user;
	private LocalDate date;
	private OpinionType type;

	
	public Opinion(User user, OpinionType type) {
		this.user = user;
		this.type = type;
		this.date = LocalDate.now();
	}

	public Object getUser() { 
		return this.user;
	}

	public OpinionType getType() {
		return this.type;
	}
	

	public LocalDate getDate() {
		return date;
	}

	public boolean isRepeatUser(ArrayList<Opinion> opinions) {
		Stream<Object> sOpinions = opinions.stream().map(o -> o.getUser());
		return sOpinions.anyMatch(o-> o == this.getUser());
	}

	public boolean isExpertOpinion() {
		return user.mustChangeState();
	}

}
