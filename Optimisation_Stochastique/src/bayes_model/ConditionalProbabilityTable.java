package bayes_model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class ConditionalProbabilityTable {
	private HashMap<Configuration,ArrayList<Double>> relations;
	private ArrayList<Double> probabilities;
	
	public ConditionalProbabilityTable(HashMap<Configuration, ArrayList<Double>> relations) {
		this.relations = relations;
	}
	
	public ConditionalProbabilityTable(ArrayList<Double> probabilities) {
		this.probabilities = probabilities;
	}
	
	public HashMap<Configuration, ArrayList<Double>> getRelations() {
		return relations;
	}
	
	public Double getProbability(int i) {
		return probabilities.get(i);
	}
	
	public ArrayList<Double> getProbability(Configuration i) {
		return relations.get(i);
	}
	
	public boolean isRootCpt() {
		return relations == null;
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

	@Override
	public String toString() {
		return "relations=" + relations + "\n probabilities=" + probabilities + "";
	}
	
	
}
