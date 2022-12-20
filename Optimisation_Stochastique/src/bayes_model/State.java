package bayes_model;

import java.util.Objects;

public class State {
	private Variable variable;
	private int indexOfValue;
	
	public State(Variable variable, int indexOfValue) {
		if (variable.getDomain().size() <= indexOfValue || indexOfValue < 0) {
			throw new ArrayIndexOutOfBoundsException("L'index doit etre compris entre 0 et " + variable.getDomain().size());
		}
		this.variable = variable;
		this.indexOfValue = indexOfValue;
	}

	public Variable getVariable() {
		return variable;
	}

	public int getIndexOfValue() {
		return indexOfValue;
	}

	@Override
	public String toString() {
		return variable.getName() + " : " + variable.getDomain().get(indexOfValue);
	}

	@Override
	public int hashCode() {
		return Objects.hash(indexOfValue, variable);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		State other = (State) obj;
		return indexOfValue == other.indexOfValue && Objects.equals(variable, other.variable);
	}
	
	
}
