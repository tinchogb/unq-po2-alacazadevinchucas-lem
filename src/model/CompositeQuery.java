package model;

import java.util.List;


public abstract class CompositeQuery implements IQuery {
	
	protected IQuery query1;
	protected IQuery query2;

	public abstract List<Sample> filter();
}