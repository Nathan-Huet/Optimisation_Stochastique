package bayesian_network_model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class BayesianNetwork {
	private ArrayList<Variable> variables;

	public BayesianNetwork(ArrayList<Variable> variables) {
		this.variables = variables;
	}
	public Double enumerationRequest(ArrayList<State> request, Set<State> evidences) {
		ArrayList<Variable> variablesToInstanciate = new ArrayList<>();
		variablesToInstanciate.addAll(variables);

		ArrayList<Variable> alpha = new ArrayList<>();
		for (Variable variable : variables) {
			alpha.add(variable);
		}
		Set<State> evidences2 = new HashSet<>();
		evidences2.addAll(evidences);

		for (State x : request) {
			for (State y : evidences) {
				if (x.getVariable().equals(y.getVariable())) 
					if (!x.equals(y)) 
						return 0.0;
			}
			evidences.add(x);
		}
		Double alphaValue = enumere(variables, evidences2);
		return enumere(variablesToInstanciate,evidences)/alphaValue;
	}

	public Double enumere(ArrayList<Variable> vars, Set<State> evidences) {
		if (vars.isEmpty()) {
			return 1.0;
		}
		Variable y = vars.get(0);

		Double enumerationResult = 0.0;

		ArrayList<Variable> remnantVariables = new ArrayList<>();
		for (int i = 1; i < vars.size(); i++) {
			remnantVariables.add(vars.get(i));
		}

		State yValueInEvidences = null;
		for (State evidence : evidences) {
			if(evidence.getVariable().equals(y))
				yValueInEvidences = evidence;
		}

		Set<State> parentsY = new HashSet<>();
		for (State candidatParentY : evidences) {
			if (candidatParentY.getVariable().getChildren().contains(y)) {
				parentsY.add(candidatParentY);
			}
		}
		Configuration configParentsY = new Configuration(parentsY);
		if (yValueInEvidences != null) {
			Double probaY = 0.0;
			int indexOfValueInYDomain = yValueInEvidences.getIndexOfValue();
			if (y.isRoot()) {
				probaY = y.getProbability(indexOfValueInYDomain);
			}else {
				probaY = y.getCpt().getProbability(configParentsY).get(indexOfValueInYDomain);
			}
			enumerationResult += probaY * enumere(remnantVariables, evidences);
		}else {			
			int yDomainSize = y.getDomain().size();
			ArrayList<Set<State>> evidencesList = new ArrayList<>();
			for (int i = 0; i < yDomainSize; i++) {
				evidencesList.add(new HashSet<>());
				for (State state : evidences) {
					evidencesList.get(i).add(state);
				}
				State yInstanciation = new State(y, i);
				evidencesList.get(i).add(yInstanciation);
			}
			for (int i = 0; i < evidencesList.size(); i++) {
				if (y.isRoot()) {		
					enumerationResult += y.getProbability(i) * enumere(remnantVariables, evidencesList.get(i));
				}
				else {
					enumerationResult += y.getCpt().getProbability(configParentsY).get(i) * enumere(remnantVariables, evidencesList.get(i));
				}
			}
		}
		return enumerationResult;
	}
}
