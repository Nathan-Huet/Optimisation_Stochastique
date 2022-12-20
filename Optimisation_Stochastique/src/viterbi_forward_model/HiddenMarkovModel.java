package viterbi_forward_model;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class HiddenMarkovModel {
	public HashMap<State, HashMap<State, Double>> transitionMatrix;
	public HashMap<State, HashMap<Observation, Double>> observationMatrix;
	public HashMap<State, Double> initialLaw;
	public List<State> states;
	public List<Observation> observations;
	
	private Stack<State> sequenceMax;
	private State bestState;
	
	public HiddenMarkovModel(HashMap<State, HashMap<State, Double>> transitionMatrix,
			HashMap<State, HashMap<Observation, Double>> observationMatrix, HashMap<State, Double> initialLaw, List<State> states, List<Observation> observations) {
		this.transitionMatrix = transitionMatrix;
		this.observationMatrix = observationMatrix;
		this.initialLaw = initialLaw;
		this.states = states;
		this.observations = observations;
	}

	public double alphaForward(List<Observation> sequenceObservation) {
		double sum = 0.0;
		int n = sequenceObservation.size();
		if (n >= 1) {
			//sum(delta(e,n))
			for (State e : states) {
				sum += alpha(e,n, sequenceObservation);
			}
		}
		return sum;
	}

	public double alpha(State e, int n, List<Observation> sequenceObservation) {
		if (n <= 1) {
			//pi(e) * B(e,SO[0])
			return initialLaw.get(e) * observationMatrix.get(e).get(sequenceObservation.get(0));
		}
		//sum(A(e,eBis) * delta(eBis, n-1)) *  B(e,SO[n-1])
		double sum = 0;
		for (State eBis : states) {			
			sum += transitionMatrix.get(eBis).get(e)*alpha(eBis, n-1, sequenceObservation);
		}
		sum *= observationMatrix.get(e).get(sequenceObservation.get(n-1));
		return sum;
	}

	public double viterbi(List<Observation> sequenceObservation) {
		double best = 0.0;
		sequenceMax = new Stack<>();
		int n = sequenceObservation.size();
		if (n >= 1) {
			for (State e : states) {
				//max(delta(e,n))
				best = Math.max(best,delta(e,n, sequenceObservation));
				sequenceMax.add(bestState);
			}
		}
		return best;
	}
	
	public double delta(State e, int n, List<Observation> sequenceObservation) {		
		if (n <= 1) {
			//pi(e) * B(e,SO[0])
			return initialLaw.get(e) * observationMatrix.get(e).get(sequenceObservation.get(0));
		}
		//max(A(e,eBis) * delta(eBis, n-1)) *  B(e,SO[n-1])
		double max = -1;
		for (State eBis : states) {
			double tmp = transitionMatrix.get(eBis).get(e)*delta(eBis, n-1, sequenceObservation);
			if (tmp > max) {
				max = tmp;
				bestState = e;
			}
		}
		max *= observationMatrix.get(e).get(sequenceObservation.get(n-1));
		return max;
	}
	
	public List<State> getSequenceMax(List<Observation> sequenceObservation) {
		viterbi(sequenceObservation);
		return sequenceMax;
	}
}
