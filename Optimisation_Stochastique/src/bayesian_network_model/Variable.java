package bayesian_network_model;

import java.util.ArrayList;
import java.util.Objects;

public class Variable {
	private String name = "";
	private ArrayList<Object> domain = new ArrayList<>();
	private ArrayList<Variable> parents = new ArrayList<>();
	private ArrayList<Variable> children = new ArrayList<>();
	private ConditionalProbabilityTable cpt;
	private PropagationTable propagationTable;
	
	public Variable(String name, ArrayList<Object> domain) {
		super();
		this.name = name;
		this.domain = domain;
	}
	
	public String getName() {
		return name;
	}
	public ArrayList<Object> getDomain() {
		return domain;
	}
	public ArrayList<Variable> getParents() {
		return parents;
	}
	public ArrayList<Variable> getChildren() {
		return children;
	}
	public ConditionalProbabilityTable getCpt() {
		return cpt;
	}
	public void addChild(Variable child) {
		this.children.add(child);
		child.addParent(this);
	}
	private void addParent(Variable parent) {
		this.parents.add(parent);
	}
	public void setCpt(ConditionalProbabilityTable cpt) {
		this.cpt = cpt;
	}
	public boolean isRoot() {
		return parents.isEmpty();
	}
	public Double getProbability(int i) {
		return cpt.getProbability(i);
	}

	@Override
	public String toString() {
		return "" + name + ": " + domain + "";
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Variable other = (Variable) obj;
		return Objects.equals(name, other.name);
	}

	public PropagationTable getPropagationTable() {
		return propagationTable;
	}
	
	public void setPropagationTable(PropagationTable pt) {
		this.propagationTable = pt;
	}
}
