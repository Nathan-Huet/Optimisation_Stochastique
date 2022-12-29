package bayesian_network_model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Configuration {
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

	public static ArrayList<Configuration> generateAllConfigurations(ArrayList<Variable> givenVariables) {
		int sizeValues = 1;
		ArrayList<Configuration> allConfigs = new ArrayList<>();
		for (Variable variable : givenVariables) {
			sizeValues *= (variable.getDomain().size());
		}

		for(int i = 0; i < sizeValues; i++) {
			int j = 1;
			Set<State> list = new HashSet<>();
			for(Variable variable : givenVariables) {
				int length = variable.getDomain().size();
				State state = new State(variable, (i/j)%length);
				list.add(state);
				j *= variable.getDomain().size();
			}
			Configuration conf = new Configuration(list);
			allConfigs.add(conf);
		}
		return allConfigs;
	}

	public static ArrayList<Configuration> getConfigurationsWithState(ArrayList<Configuration> allConfigurations, State state ) {
		ArrayList<Configuration> result = new ArrayList<>();
		for (Configuration configuration : allConfigurations) {
			if (configuration.getStates().contains(state)) {
				result.add(configuration);
			}
		}
		return result;
	}
}
