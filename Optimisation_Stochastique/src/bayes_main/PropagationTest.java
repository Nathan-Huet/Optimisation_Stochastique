package bayes_main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import bayesian_network_model.*;


public class PropagationTest {
	public static void main(String[] args) {
		PropagationTest app = new PropagationTest();
		app.launch3();
	}

	private void launch1() {
		ArrayList<Object> domain = new ArrayList<>();
		domain.add(true);
		domain.add(false);
		Variable p1 = new Variable("P1", domain );
		domain = new ArrayList<>();
		domain.add(true);
		domain.add(false);
		Variable p2 = new Variable("P2", domain );
		domain = new ArrayList<>();
		domain.add(true);
		domain.add(false);
		Variable p3 = new Variable("P3", domain );
		p1.addChild(p3);
		p2.addChild(p3);

		//P1
		ArrayList<Double> probaP1 = new ArrayList<>();
		probaP1.add(0.4);
		probaP1.add(0.6);
		p1.setCpt(new ConditionalProbabilityTable(probaP1));

		//P2
		ArrayList<Double> probaP2 = new ArrayList<>();
		probaP2.add(0.7);
		probaP2.add(0.3);
		p2.setCpt(new ConditionalProbabilityTable(probaP2));

		//P3
		ArrayList<Configuration> cptConfig = Configuration.generateAllConfigurations(p3.getParents());
		HashMap<Configuration, ArrayList<Double>> relations = new HashMap<>();
		ArrayList<Double> probabilities = new ArrayList<>();
		probabilities.add(0.1);
		probabilities.add(0.9);
		relations.put(cptConfig.get(0), probabilities);
		probabilities = new ArrayList<>();
		probabilities.add(0.8);
		probabilities.add(0.2);
		relations.put(cptConfig.get(1), probabilities);
		probabilities = new ArrayList<>();
		probabilities.add(0.3);
		probabilities.add(0.7);
		relations.put(cptConfig.get(2), probabilities);
		probabilities = new ArrayList<>();
		probabilities.add(0.1);
		probabilities.add(0.9);
		relations.put(cptConfig.get(3), probabilities);
		p3.setCpt(new ConditionalProbabilityTable(relations));

		System.out.println(p3.getCpt());

		//PROPAGATION TABLE
		p1.setPropagationTable(new PropagationTable(p1));
		p2.setPropagationTable(new PropagationTable(p2));
		p3.setPropagationTable(new PropagationTable(p3));

		p3.getPropagationTable().initialisation();
		System.out.println(p1.getPropagationTable());
		System.out.println(p2.getPropagationTable());
		System.out.println(p3.getPropagationTable());		
	}

	private void launch2() {
		ArrayList<Object> domain = new ArrayList<>();
		domain.add(true);
		domain.add(false);
		Variable a = new Variable("A", domain );
		domain = new ArrayList<>();
		domain.add(true);
		domain.add(false);
		Variable b = new Variable("B", domain );
		domain = new ArrayList<>();
		domain.add(true);
		domain.add(false);
		Variable c = new Variable("C", domain );
		domain = new ArrayList<>();
		domain.add(true);
		domain.add(false);
		Variable d = new Variable("D", domain );

		a.addChild(b);
		a.addChild(d);
		b.addChild(c);

		//A
		ArrayList<Double> probaA = new ArrayList<>();
		probaA.add(0.1);
		probaA.add(0.9);
		a.setCpt(new ConditionalProbabilityTable(probaA));

		//B
		ArrayList<Configuration> cptConfig = Configuration.generateAllConfigurations(b.getParents());
		HashMap<Configuration, ArrayList<Double>> relations = new HashMap<>();
		ArrayList<Double> probabilities = new ArrayList<>();
		probabilities.add(0.7);
		probabilities.add(0.3);
		relations.put(cptConfig.get(0), probabilities);
		probabilities = new ArrayList<>();
		probabilities.add(0.2);
		probabilities.add(0.8);
		relations.put(cptConfig.get(1), probabilities);
		b.setCpt(new ConditionalProbabilityTable(relations));

		//C
		cptConfig = Configuration.generateAllConfigurations(c.getParents());
		relations = new HashMap<>();
		probabilities = new ArrayList<>();
		probabilities.add(0.4);
		probabilities.add(0.6);
		relations.put(cptConfig.get(0), probabilities);
		probabilities = new ArrayList<>();
		probabilities.add(0.001);
		probabilities.add(0.999);
		relations.put(cptConfig.get(1), probabilities);
		c.setCpt(new ConditionalProbabilityTable(relations));

		//D
		cptConfig = Configuration.generateAllConfigurations(d.getParents());
		relations = new HashMap<>();
		probabilities = new ArrayList<>();
		probabilities.add(0.8);
		probabilities.add(0.2);
		relations.put(cptConfig.get(0), probabilities);
		probabilities = new ArrayList<>();
		probabilities.add(0.4);
		probabilities.add(0.6);
		relations.put(cptConfig.get(1), probabilities);
		d.setCpt(new ConditionalProbabilityTable(relations));


		//PROPAGATION TABLE
		a.setPropagationTable(new PropagationTable(a));
		b.setPropagationTable(new PropagationTable(b));
		c.setPropagationTable(new PropagationTable(c));
		d.setPropagationTable(new PropagationTable(d));

		c.getPropagationTable().initialisation();
		d.getPropagationTable().initialisation();
		System.out.println(a.getPropagationTable());
		System.out.println(b.getPropagationTable());
		System.out.println(c.getPropagationTable());
		System.out.println(d.getPropagationTable());
	}

	private void launch3() {
		ArrayList<Object> domain = new ArrayList<>();
		domain.add(true);
		domain.add(false);
		Variable a = new Variable("A", domain );
		domain = new ArrayList<>();
		domain.add(true);
		domain.add(false);
		Variable b = new Variable("B", domain );
		domain = new ArrayList<>();
		domain.add(true);
		domain.add(false);
		Variable c = new Variable("C", domain );
		domain = new ArrayList<>();
		domain.add(true);
		domain.add(false);
		Variable d = new Variable("D", domain );
		domain = new ArrayList<>();
		domain.add(true);
		domain.add(false);
		Variable e = new Variable("E", domain );
		domain = new ArrayList<>();
		domain.add(true);
		domain.add(false);
		Variable f = new Variable("F", domain );
		domain = new ArrayList<>();
		domain.add(true);
		domain.add(false);
		Variable g = new Variable("G", domain );
		domain = new ArrayList<>();
		domain.add(true);
		domain.add(false);
		Variable h = new Variable("H", domain );
		domain = new ArrayList<>();
		domain.add(true);
		domain.add(false);
		Variable i = new Variable("I", domain );

		//Children
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
		ArrayList<Configuration> cptConfig = Configuration.generateAllConfigurations(b.getParents());
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
		cptConfig = Configuration.generateAllConfigurations(c.getParents());
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
		cptConfig = Configuration.generateAllConfigurations(d.getParents());
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
		cptConfig = Configuration.generateAllConfigurations(e.getParents());
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
		cptConfig = Configuration.generateAllConfigurations(f.getParents());
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
		cptConfig = Configuration.generateAllConfigurations(g.getParents());
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
		cptConfig = Configuration.generateAllConfigurations(h.getParents());
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
		cptConfig = Configuration.generateAllConfigurations(i.getParents());
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

		//PROPAGATION TABLE
		a.setPropagationTable(new PropagationTable(a));
		b.setPropagationTable(new PropagationTable(b));
		c.setPropagationTable(new PropagationTable(c));
		d.setPropagationTable(new PropagationTable(d));
		e.setPropagationTable(new PropagationTable(e));
		f.setPropagationTable(new PropagationTable(f));
		g.setPropagationTable(new PropagationTable(g));
		h.setPropagationTable(new PropagationTable(h));
		i.setPropagationTable(new PropagationTable(i));
		
		d.getPropagationTable().initialisation();
		e.getPropagationTable().initialisation();
		g.getPropagationTable().initialisation();
		h.getPropagationTable().initialisation();
		i.getPropagationTable().initialisation();
		
		
		
		HashSet<State> givenStates = new HashSet<>();
		givenStates.add(new State(e, 0));
		givenStates.add(new State(g, 0));
		givenStates.add(new State(h, 0));
		
		a.getPropagationTable().setWStates(givenStates);
		b.getPropagationTable().setWStates(givenStates);
		c.getPropagationTable().setWStates(givenStates);
		d.getPropagationTable().setWStates(givenStates);
		e.getPropagationTable().setWStates(givenStates);
		f.getPropagationTable().setWStates(givenStates);
		g.getPropagationTable().setWStates(givenStates);
		h.getPropagationTable().setWStates(givenStates);
		i.getPropagationTable().setWStates(givenStates);
		
		a.getPropagationTable().updateOfLambda();
		b.getPropagationTable().updateOfLambda();
		c.getPropagationTable().updateOfLambda();
		d.getPropagationTable().updateOfLambda();
		e.getPropagationTable().updateOfLambda();
		f.getPropagationTable().updateOfLambda();
		g.getPropagationTable().updateOfLambda();
		h.getPropagationTable().updateOfLambda();
		i.getPropagationTable().updateOfLambda();
		
		a.getPropagationTable().updateOfPi();
		b.getPropagationTable().updateOfPi();
		c.getPropagationTable().updateOfPi();
		d.getPropagationTable().updateOfPi();
		e.getPropagationTable().updateOfPi();
		f.getPropagationTable().updateOfPi();
		g.getPropagationTable().updateOfPi();
		h.getPropagationTable().updateOfPi();
		i.getPropagationTable().updateOfPi();


		System.out.println(a.getPropagationTable());
		System.out.println(b.getPropagationTable());
		System.out.println(c.getPropagationTable());
		System.out.println(d.getPropagationTable());
		System.out.println(e.getPropagationTable());
		System.out.println(f.getPropagationTable());
		System.out.println(g.getPropagationTable());
		System.out.println(h.getPropagationTable());
		System.out.println(i.getPropagationTable());
	}
}
