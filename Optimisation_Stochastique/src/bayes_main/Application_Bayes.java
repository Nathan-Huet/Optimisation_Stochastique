package bayes_main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import bayes_model.BayesianNetwork;
import bayes_model.ConditionalProbabilityTable;
import bayes_model.Configuration;
import bayes_model.State;
import bayes_model.Variable;

public class Application_Bayes {

	public static void main(String[] args) {
		Application_Bayes app = new Application_Bayes();
		app.launch2();
	}

	public void launchCambriolage() {
		ArrayList<Object> domain = new ArrayList<>();
		domain.add(true);
		domain.add(false);

		Variable cambriolage = new Variable("C", domain);
		Variable seisme = new Variable("S", domain);
		Variable alarme = new Variable("A", domain);
		Variable jeanAppelle = new Variable("J", domain);
		Variable marieAppelle = new Variable("M", domain);
		
		ArrayList<Variable> variables = new ArrayList<>();
		variables.add(cambriolage);
		variables.add(seisme);
		variables.add(alarme);
		variables.add(jeanAppelle);
		variables.add(marieAppelle);
		
		cambriolage.addChild(alarme);
		
		seisme.addChild(alarme);
		
		alarme.addChild(jeanAppelle);
		alarme.addChild(marieAppelle);
		
		//Cambriolage 
		ArrayList<Double> probaCambriolage = new ArrayList<>();
		probaCambriolage.add(0.001);
		probaCambriolage.add(0.999);
		cambriolage.setCpt(new ConditionalProbabilityTable(probaCambriolage));
		
		//Seisme
		ArrayList<Double> probaSeisme = new ArrayList<>();
		probaSeisme.add(0.002);
		probaSeisme.add(0.998);
		seisme.setCpt(new ConditionalProbabilityTable(probaSeisme));
		
		//Alarme
		ArrayList<Configuration> cptConfig = ConditionalProbabilityTable.generateAllConfigurations(alarme.getParents());
		HashMap<Configuration, ArrayList<Double>> relations = new HashMap<>();
		ArrayList<Double> probabilities = new ArrayList<>();
		probabilities.add(0.95);
		probabilities.add(0.05);
		relations.put(cptConfig.get(0), probabilities);
		probabilities = new ArrayList<>();
		probabilities.add(0.29);
		probabilities.add(0.71);
		relations.put(cptConfig.get(1), probabilities);
		probabilities = new ArrayList<>();
		probabilities.add(0.94);
		probabilities.add(0.06);
		relations.put(cptConfig.get(2), probabilities);
		probabilities = new ArrayList<>();
		probabilities.add(0.001);
		probabilities.add(0.999);
		relations.put(cptConfig.get(3), probabilities);
		alarme.setCpt(new ConditionalProbabilityTable(relations));

		//JeanAppelle
		cptConfig = ConditionalProbabilityTable.generateAllConfigurations(jeanAppelle.getParents());
		relations = new HashMap<>();
		probabilities = new ArrayList<>();
		probabilities.add(0.90);
		probabilities.add(0.10);
		relations.put(cptConfig.get(0), probabilities);
		probabilities = new ArrayList<>();
		probabilities.add(0.05);
		probabilities.add(0.95);
		relations.put(cptConfig.get(1), probabilities);
		jeanAppelle.setCpt(new ConditionalProbabilityTable(relations));

		//MarieAppelle
		cptConfig = ConditionalProbabilityTable.generateAllConfigurations(marieAppelle.getParents());
		relations = new HashMap<>();
		probabilities = new ArrayList<>();
		probabilities.add(0.70);
		probabilities.add(0.30);
		relations.put(cptConfig.get(0), probabilities);
		probabilities = new ArrayList<>();
		probabilities.add(0.01);
		probabilities.add(0.99);
		relations.put(cptConfig.get(1), probabilities);
		marieAppelle.setCpt(new ConditionalProbabilityTable(relations));


		BayesianNetwork bn = new BayesianNetwork(variables);

		ArrayList<State> request = new ArrayList<>();
		request.add(new State(cambriolage, 0));
		
		Set<State> evidences = new HashSet<>();
		evidences.add(new State(jeanAppelle, 0));
		evidences.add(new State(marieAppelle, 0));

		System.out.println(request);
		System.out.println(evidences);
		System.out.println();
		Double res = bn.enumerationRequest(request,evidences);
		System.out.println();
		System.out.println(res);
		System.out.println();
		

		request = new ArrayList<>();
		request.add(new State(cambriolage, 1));
		
		evidences = new HashSet<>();
		evidences.add(new State(jeanAppelle, 0));
		evidences.add(new State(marieAppelle, 0));

		System.out.println(request);
		System.out.println(evidences);
		System.out.println();
		Double res2 = bn.enumerationRequest(request,evidences);
		System.out.println();
		System.out.println(res2);
		System.out.println();

		System.out.println(res + res2);
		System.out.println();
		
		
		ArrayList<ArrayList<Double>> probas = new ArrayList<>();
		ArrayList<Double> proba = new ArrayList<>();
		proba.add(0.001);
		proba.add(0.002);
		proba.add(0.95);
		proba.add(0.90);
		proba.add(0.70);
		probas.add(proba);
		
		proba = new ArrayList<>();
		proba.add(0.001);
		proba.add(0.998);
		proba.add(0.94);
		proba.add(0.90);
		proba.add(0.70);
		probas.add(proba);
		
		proba = new ArrayList<>();
		proba.add(0.001);
		proba.add(0.002);
		proba.add(0.05);
		proba.add(0.05);
		proba.add(0.01);
		probas.add(proba);
		
		proba = new ArrayList<>();
		proba.add(0.001);
		proba.add(0.998);
		proba.add(0.06);
		proba.add(0.05);
		proba.add(0.01);
		probas.add(proba);
		
		for (ArrayList<Double> double1 : probas) {
			System.out.print(double1 + " + ");
		}
	}

	public void launch2() {
		ArrayList<Object> domain = new ArrayList<>();
		domain.add(true);
		domain.add(false);

		Variable a = new Variable("A", domain);
		Variable b = new Variable("B", domain);
		Variable c = new Variable("C", domain);
		Variable d = new Variable("D", domain);
		Variable e = new Variable("E", domain);
		Variable f = new Variable("F", domain);
		Variable g = new Variable("G", domain);
		Variable h = new Variable("H", domain);
		Variable i = new Variable("I", domain);

		ArrayList<Variable> variables = new ArrayList<>();
		variables.add(a);
		variables.add(b);
		variables.add(c);
		variables.add(d);
		variables.add(e);
		variables.add(f);
		variables.add(g);
		variables.add(h);
		variables.add(i);

		a.addChild(b);
		a.addChild(c);

		b.addChild(d);
		b.addChild(e);

		c.addChild(f);
		c.addChild(g);

		f.addChild(h);
		f.addChild(i);

		//A 
		ArrayList<Double> probaA = new ArrayList<>();
		probaA.add(0.4);
		probaA.add(0.6);
		a.setCpt(new ConditionalProbabilityTable(probaA));

		//B
		ArrayList<Configuration> cptConfig = ConditionalProbabilityTable.generateAllConfigurations(b.getParents());
		HashMap<Configuration, ArrayList<Double>> relations = new HashMap<>();
		ArrayList<Double> probabilities = new ArrayList<>();
		probabilities.add(0.2);
		probabilities.add(0.8);
		relations.put(cptConfig.get(0), probabilities);
		probabilities = new ArrayList<>();
		probabilities.add(0.3);
		probabilities.add(0.7);
		relations.put(cptConfig.get(1), probabilities);
		b.setCpt(new ConditionalProbabilityTable(relations));

		//C
		cptConfig = ConditionalProbabilityTable.generateAllConfigurations(c.getParents());
		relations = new HashMap<>();
		probabilities = new ArrayList<>();
		probabilities.add(0.1);
		probabilities.add(0.9);
		relations.put(cptConfig.get(0), probabilities);
		probabilities = new ArrayList<>();
		probabilities.add(0.25);
		probabilities.add(0.75);
		relations.put(cptConfig.get(1), probabilities);
		c.setCpt(new ConditionalProbabilityTable(relations));

		//D
		cptConfig = ConditionalProbabilityTable.generateAllConfigurations(d.getParents());
		relations = new HashMap<>();
		probabilities = new ArrayList<>();
		probabilities.add(0.5);
		probabilities.add(0.5);
		relations.put(cptConfig.get(0), probabilities);
		probabilities = new ArrayList<>();
		probabilities.add(0.35);
		probabilities.add(0.65);
		relations.put(cptConfig.get(1), probabilities);
		d.setCpt(new ConditionalProbabilityTable(relations));

		//E
		cptConfig = ConditionalProbabilityTable.generateAllConfigurations(e.getParents());
		relations = new HashMap<>();
		probabilities = new ArrayList<>();
		probabilities.add(0.15);
		probabilities.add(0.85);
		relations.put(cptConfig.get(0), probabilities);
		probabilities = new ArrayList<>();
		probabilities.add(0.45);
		probabilities.add(0.55);
		relations.put(cptConfig.get(1), probabilities);
		e.setCpt(new ConditionalProbabilityTable(relations));

		//F
		cptConfig = ConditionalProbabilityTable.generateAllConfigurations(f.getParents());
		relations = new HashMap<>();
		probabilities = new ArrayList<>();
		probabilities.add(0.3);
		probabilities.add(0.7);
		relations.put(cptConfig.get(0), probabilities);
		probabilities = new ArrayList<>();
		probabilities.add(0.6);
		probabilities.add(0.4);
		relations.put(cptConfig.get(1), probabilities);
		f.setCpt(new ConditionalProbabilityTable(relations));

		//G
		cptConfig = ConditionalProbabilityTable.generateAllConfigurations(g.getParents());
		relations = new HashMap<>();
		probabilities = new ArrayList<>();
		probabilities.add(0.25);
		probabilities.add(0.75);
		relations.put(cptConfig.get(0), probabilities);
		probabilities = new ArrayList<>();
		probabilities.add(0.1);
		probabilities.add(0.9);
		relations.put(cptConfig.get(1), probabilities);
		g.setCpt(new ConditionalProbabilityTable(relations));

		//H
		cptConfig = ConditionalProbabilityTable.generateAllConfigurations(h.getParents());
		relations = new HashMap<>();
		probabilities = new ArrayList<>();
		probabilities.add(0.65);
		probabilities.add(0.35);
		relations.put(cptConfig.get(0), probabilities);
		probabilities = new ArrayList<>();
		probabilities.add(0.2);
		probabilities.add(0.8);
		relations.put(cptConfig.get(1), probabilities);
		h.setCpt(new ConditionalProbabilityTable(relations));

		//I
		cptConfig = ConditionalProbabilityTable.generateAllConfigurations(i.getParents());
		relations = new HashMap<>();
		probabilities = new ArrayList<>();
		probabilities.add(0.25);
		probabilities.add(0.75);
		relations.put(cptConfig.get(0), probabilities);
		probabilities = new ArrayList<>();
		probabilities.add(0.5);
		probabilities.add(0.5);
		relations.put(cptConfig.get(1), probabilities);
		i.setCpt(new ConditionalProbabilityTable(relations));



		BayesianNetwork bn = new BayesianNetwork(variables);

		ArrayList<State> request = new ArrayList<>();
		request.add(new State(d, 0));
		
		Set<State> evidences = new HashSet<>();
		evidences.add(new State(e, 0));
		evidences.add(new State(g, 0));
		evidences.add(new State(h, 0));
		//evidences.add(new State(p2, 0));

		System.out.println(request);
		System.out.println(evidences);
		System.out.println();
		Double res = bn.enumerationRequest(request,evidences);
		System.out.println(res);
	}

	public void launch() {
		ArrayList<Object> domain = new ArrayList<>();
		domain.add(true);
		domain.add(false);


		Variable p1 = new Variable("p1", domain);
		Variable p2 = new Variable("p2", domain);
		Variable p3 = new Variable("p3", domain);

		p1.addChild(p3);
		p2.addChild(p3);

		ArrayList<Double> probaP1 = new ArrayList<>();
		probaP1.add(0.4);
		probaP1.add(0.6);
		p1.setCpt(new ConditionalProbabilityTable(probaP1));

		ArrayList<Double> probaP2 = new ArrayList<>();
		probaP2.add(0.7);
		probaP2.add(0.3);
		p2.setCpt(new ConditionalProbabilityTable(probaP2));

		ArrayList<Configuration> cptP3Config = ConditionalProbabilityTable.generateAllConfigurations(p3.getParents());
		HashMap<Configuration, ArrayList<Double>> relationsP3 = new HashMap<>();
		ArrayList<Double> probabilities = new ArrayList<>();
		probabilities.add(0.1);
		probabilities.add(0.9);
		relationsP3.put(cptP3Config.get(0), probabilities);
		probabilities = new ArrayList<>();
		probabilities.add(0.8);
		probabilities.add(0.2);
		relationsP3.put(cptP3Config.get(1), probabilities);
		probabilities = new ArrayList<>();
		probabilities.add(0.3);
		probabilities.add(0.7);
		relationsP3.put(cptP3Config.get(2), probabilities);
		probabilities = new ArrayList<>();
		probabilities.add(0.1);
		probabilities.add(0.9);
		relationsP3.put(cptP3Config.get(3), probabilities);
		p3.setCpt(new ConditionalProbabilityTable(relationsP3));

		ArrayList<Variable> variables = new ArrayList<>();
		variables.add(p1);
		variables.add(p2);
		variables.add(p3);



		BayesianNetwork bn = new BayesianNetwork(variables);

		ArrayList<State> request = new ArrayList<>();
		request.add(new State(p1, 1));
		Set<State> evidences = new HashSet<>();
		evidences.add(new State(p2, 0));
		//evidences.add(new State(p2, 0));

		System.out.println(request);
		System.out.println(evidences);
		System.out.println();
		Double res = bn.enumerationRequest(request,evidences);
		System.out.println(res);

	}
}
