package bayes_model;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Set;

public class Configuration {
	//private ArrayList<State> states;
	private Set<State> states;
	
	public Configuration(Set<State> states) {
		this.states = states;
	}
	
	public Set<State> getStates() {
		return states;
	}

	@Override
	public String toString() {
		return states + "";
	}

	@Override
	public int hashCode() {
		return Objects.hash(states);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Configuration other = (Configuration) obj;
		return Objects.equals(states, other.states);
	}
	
	
	
}
