package bayesian_network_model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class PropagationTable {
	private Variable variable;
	private ArrayList<State> states;
	private HashMap<State, Double> pi_valeurs;
	private HashMap<State, Double> lambda_valeurs;
	private HashMap<State, Double> probabilities;
	private Set<State> w_states = new HashSet<>();
	private int numberOfLambdaMessageReceived = 0;

	public PropagationTable(Variable variable) {
		this.variable = variable;
		this.pi_valeurs = new HashMap<>();
		this.lambda_valeurs = new HashMap<>();
		this.probabilities = new HashMap<>();

		this.states = new ArrayList<>();
		ArrayList<Object> domain = variable.getDomain();
		for (int i = 0; i < domain.size(); i++) {
			State state = new State(variable, i);
			states.add(state);
		}

	}

	public HashMap<State, Double> getLambdaValeurs() {
		return lambda_valeurs;
	}

	public HashMap<State, Double> getPiValeurs() {
		return pi_valeurs;
	}

	public HashMap<State, Double> getProbabilities() {
		return probabilities;
	}

	public void setWStates(Set<State> givenStates) {
		this.w_states = givenStates;
		for (State state : givenStates) {
			if (state.getVariable().equals(this.variable)) {				
				for (State state2 : states) {
					this.lambda_valeurs.put(state2, 0.0);
					this.probabilities.put(state2, 0.0);
				}
				this.lambda_valeurs.put(state, 1.0);
				this.probabilities.put(state, 1.0);
			}
		}
	}

	public void initialisation() {
		for (State state : states) {
			lambda_valeurs.put(state, 1.0);
		}

		ArrayList<Variable> parents = variable.getParents();
		if (parents.isEmpty()) {
			for (State state : states) {
				pi_valeurs.put(state, variable.getProbability(state.getIndexOfValue()));
				probabilities.put(state, variable.getProbability(state.getIndexOfValue()));
			}
		}
		for (Variable parent : parents) {
			parent.getPropagationTable().initialisation();
		}
		if (!parents.isEmpty()) {
			ArrayList<Configuration> configurations = Configuration.generateAllConfigurations(parents);
			for (int i = 0; i < states.size(); i++) {
				Double pi = 0.0;
				//System.out.println(states.get(i));
				for (Configuration configuration : configurations) {
					Double tmp = variable.getCpt().getProbability(configuration).get(i);
					//System.out.print(tmp);
					Set<State> parents_states = configuration.getStates();
					for (State parent_state : parents_states) {
						//System.out.print(" * " +parent_state.getVariable().getPropagationTable().getPiValeurs().get(parent_state));
						tmp *= parent_state.getVariable().getPropagationTable().getPiValeurs().get(parent_state);
					}
					//System.out.println("+");
					pi += tmp;
				}
				pi_valeurs.put(states.get(i), pi);
				probabilities.put(states.get(i), pi);
			}
		}


	}

	@Override
	public String toString() {
		String string = "";
		string += variable.getName() + "\t\t";
		string += "PI\t";
		string += "LAMBDA\t";
		string += "P\n";
		for (State state : states) {
			string += state + "\t";
			string += pi_valeurs.get(state) + "\t";
			string += lambda_valeurs.get(state) + "\t";
			string += probabilities.get(state) + "\n";

		}

		return string ;
	}

	public void updateLAMBDA(Variable child) {
		State childIisInW_States = null;
		for (State w_state : w_states) {
			if (child.equals(w_state.getVariable())) {
				childIisInW_States = w_state;
			}
		}

		ArrayList<Configuration> allConfigurations = Configuration.generateAllConfigurations(child.getParents());
		for (State state : states) {
			ArrayList<Configuration> configurations = Configuration.getConfigurationsWithState(allConfigurations, state);
			Double lambda = 0.0;
			for (Configuration configuration : configurations) {
				if (childIisInW_States != null) {
					lambda += child.getCpt().getProbability(configuration).get(childIisInW_States.getIndexOfValue());
				}else {
					for (int j = 0; j < child.getDomain().size(); j++) {
						lambda += child.getCpt().getProbability(configuration).get(j) * child.getPropagationTable().getLambdaValeurs().get(new State(child, j));
					}

				}


			}

			lambda_valeurs.put(state, lambda_valeurs.get(state)*lambda);

		}

		updateProba();
	}

	public double computeLambda(Variable child, State parent) {
		ArrayList<Configuration> allConfigurations = Configuration.generateAllConfigurations(child.getParents());
		ArrayList<Configuration> configurations = Configuration.getConfigurationsWithState(allConfigurations, parent);
		double lambda = 0.0;
		for (Configuration configuration : configurations) {
			for (int j = 0; j < child.getDomain().size(); j++) {
				lambda += child.getCpt().getProbability(configuration).get(j) * child.getPropagationTable().getLambdaValeurs().get(new State(child, j));
			}

		}
		return lambda;
	}

	public void updatePI(Variable parent) {

		double lamdaToParent = 0;
		ArrayList<State> parentStates =parent.getPropagationTable().states;
		double pi = 0.0;
		ArrayList<Object> domain = variable.getDomain();
		for (int i = 0; i < domain.size(); i++) {
			double probaOfVariableGivenParent = 0.0;
			for (State parentState : parentStates) {
				double proba = parent.getPropagationTable().getProbabilities().get(parentState);
				double pi_message = proba/computeLambda(variable, parentState);

				ArrayList<Configuration> allConfigurations = Configuration.generateAllConfigurations(variable.getParents());
				ArrayList<Configuration> configurations = Configuration.getConfigurationsWithState(allConfigurations, parentState);

				for (Configuration configuration : configurations) {
					probaOfVariableGivenParent += variable.getCpt().getProbability(configuration).get(i) * pi_message;

				}

			}

			pi_valeurs.put(new State(variable, i), probaOfVariableGivenParent);
		}

		updateProba();
	}

	public void updateProba() {
		double tmp = 0.0;
		for (State state : states) {
			tmp += pi_valeurs.get(state) * lambda_valeurs.get(state);
		}
		double alpha = 1.0/tmp;
		for (State state : states) {
			probabilities.put(state, alpha * pi_valeurs.get(state) * lambda_valeurs.get(state));
		}
	}


	public void updateOfLambda() {
		if (! (numberOfLambdaMessageReceived >= variable.getChildren().size())) {
			numberOfLambdaMessageReceived++;
		}else {
			ArrayList<Variable> parents = variable.getParents();
			for (Variable parent : parents ) {
				parent.getPropagationTable().updateLAMBDA(variable);
				parent.getPropagationTable().updateOfLambda();
			}
			numberOfLambdaMessageReceived = 0;
		}
	}

	public void updateOfPi() {
		ArrayList<Variable> children = variable.getChildren();
		for (Variable child : children) {
			child.getPropagationTable().updatePI(variable);
			child.getPropagationTable().updateOfPi();
		}
	}

	/*public void update() {
		updateOfLambda();
		updateOfPi();
	}*/
}
