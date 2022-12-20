package viterbi_forward_main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import viterbi_forward_model.HiddenMarkovModel;
import viterbi_forward_model.Observation;
import viterbi_forward_model.State;

public class Application {
	public static void main(String[] args) {
		HashMap<State, HashMap<State, Double>> transitionMatrix = new HashMap<>();
		HashMap<State, HashMap<Observation, Double>> observationMatrix = new HashMap<>();
		HashMap<State, Double> initialLaw = new HashMap<>();
		
		//states
		State s1 = new State("1");
		State s2 = new State("2");
		State s3 = new State("3");
		ArrayList<State> states = new ArrayList<>();
		states.add(s1);
		states.add(s2);
		states.add(s3);
		
		//observations
		Observation oa = new Observation("a");
		Observation ob = new Observation("b");
		ArrayList<Observation> observations = new ArrayList<>();
		observations.add(oa);
		observations.add(ob);
		
		//transitionMatrix init
		HashMap<State, Double> transitionVector = new HashMap<>();
		transitionVector.put(s1, 0.45);
		transitionVector.put(s2, 0.35);
		transitionVector.put(s3, 0.20);
		transitionMatrix.put(s1, transitionVector);
		
		transitionVector = new HashMap<>();
		transitionVector.put(s1, 0.10);
		transitionVector.put(s2, 0.50);
		transitionVector.put(s3, 0.40);
		transitionMatrix.put(s2, transitionVector);
		
		transitionVector = new HashMap<>();
		transitionVector.put(s1, 0.15);
		transitionVector.put(s2, 0.25);
		transitionVector.put(s3, 0.60);
		transitionMatrix.put(s3, transitionVector);
		
		//observationMatrix init
		HashMap<Observation, Double> observationVector = new HashMap<>();
		observationVector.put(oa, 1.0);
		observationVector.put(ob, 0.0);
		observationMatrix.put(s1, observationVector);
		
		observationVector = new HashMap<>();
		observationVector.put(oa, 0.5);
		observationVector.put(ob, 0.5);
		observationMatrix.put(s2, observationVector);
		
		
		observationVector = new HashMap<>();
		observationVector.put(oa, 0.0);
		observationVector.put(ob, 1.0);		
		observationMatrix.put(s3, observationVector);
		
		//initialLaw init
		initialLaw.put(s1, 0.5);
		initialLaw.put(s2, 0.3);
		initialLaw.put(s3, 0.2);		
		
		HiddenMarkovModel hmm = new HiddenMarkovModel(transitionMatrix, observationMatrix, initialLaw, states, observations);
		
		Application app = new Application();
		//app.launchAlphaForward(hmm);
		app.launchViterbi(hmm);
		
		//app.afficher(hmm);
	}
	
	public void launchAlphaForward(HiddenMarkovModel hmm) {
		List<Observation> sequenceObservation = new ArrayList<>();
		List<Observation> obs = hmm.observations;
		sequenceObservation.add(obs.get(0));
		sequenceObservation.add(obs.get(1));
		sequenceObservation.add(obs.get(1));
		
		System.out.print("proba of ");
		for (Observation observation : sequenceObservation) {
			System.out.print(observation);
		}		
		System.out.print(" : ");
		
		double probaOfSequence = hmm.alphaForward(sequenceObservation);
		System.out.println(probaOfSequence);
	}
	
	public void launchAlpha(HiddenMarkovModel hmm) {
		List<Observation> sequenceObservation = new ArrayList<>();
		List<Observation> obs = hmm.observations;
		sequenceObservation.add(obs.get(0));
		sequenceObservation.add(obs.get(1));
		sequenceObservation.add(obs.get(1));
		
		//alpha time = 2 , state = 1
		double probaOfSequence = hmm.alpha(hmm.states.get(0),2,sequenceObservation);
		System.out.println(probaOfSequence);
	}
	
	public void launchViterbi(HiddenMarkovModel hmm) {
		List<Observation> sequenceObservation = new ArrayList<>();
		List<Observation> obs = hmm.observations;
		sequenceObservation.add(obs.get(0));
		sequenceObservation.add(obs.get(1));
		sequenceObservation.add(obs.get(1));
		
		System.out.print("max proba of sequence ");
		for (Observation observation : sequenceObservation) {
			System.out.print(observation);
		}		
		System.out.print(" : ");
		
		double probaOfSequence = hmm.viterbi(sequenceObservation);
		System.out.println(probaOfSequence);
		System.out.println("With state sequence : " + hmm.getSequenceMax(sequenceObservation));
	}
	
	public void afficher(HiddenMarkovModel hmm) {
		for (State state : hmm.states) {
			for (State state2 : hmm.states) {
				System.out.print(hmm.transitionMatrix.get(state).get(state2) + "\t");
			}
			System.out.println();
		}
		System.out.println();
		for (State state : hmm.states) {
			for (Observation obs : hmm.observations) {
				System.out.print(hmm.observationMatrix.get(state).get(obs) + "\t");
			}
			System.out.println();
		}
		System.out.println();
		for (State state : hmm.states) {
			System.out.println(hmm.initialLaw.get(state));
		}
	}
}
