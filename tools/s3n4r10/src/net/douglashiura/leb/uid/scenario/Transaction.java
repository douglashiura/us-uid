package net.douglashiura.leb.uid.scenario;

public class Transaction {

	private Interaction aTarget;
	private String id;

	public Transaction(String id, Interaction aSource, Interaction aTarget) {
		this.id = id;
		this.aTarget = aTarget;
	}

	public Interaction getTarget() {
		return aTarget;
	}

	public String getId() {
		return id;
	}

}
