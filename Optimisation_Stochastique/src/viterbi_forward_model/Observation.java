package viterbi_forward_model;

public class Observation {
	public String name;

	public Observation(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
	
	
}
