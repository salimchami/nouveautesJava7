package com.softeam.presentations.nouveautesjava7;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class CalculateTask extends RecursiveTask<Double> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final int MAX_VALUES = 1_000;

	private final int start;
	private final int stop;

	/**
	 * Constructeur
	 */
	private CalculateTask(int start, int stop) {
		this.start = start;
		this.stop = stop;
	}

	/**
	 * Méthode interne de calcul entre deux index.
	 */
	private static double calculate(int start, int stop) {
		double result = 0.0;
		for (int i = start; i < stop; i++)
			result += Math.cos(i) + Math.sin(i);
		return result;
	}

	/**
	 * Traitement de la tâche
	 */
	@Override
	protected Double compute() {
		final int count = this.stop - this.start;
		// S'il y a peu d'éléments à calculer :
		if (count < MAX_VALUES) {
			// On effectue le calcul et on renvoie le résultat :
			return calculate(this.start, this.stop);
		}

		// Sinon on coupe la tâche en deux :
		final int middle = count / 2;

		// On crée une première tâche qu'on va exécuter en arrière-plan (via
		// fork()) :
		CalculateTask task1 = new CalculateTask(this.start, this.start + middle);
		task1.fork();

		// On crée une seconde tâche qu'on va exécuter directement (via
		// compute()),
		// ce qui nous permet de récupérer son résultat :
		CalculateTask task2 = new CalculateTask(this.start + middle, this.stop);
		double value2 = task2.compute();

		// On attend la fin de la première tâche pour récupérer son résultat :
		double value1 = task1.join();

		// Et on retourne
		return value1 + value2;
	}

	/**
	 * Exécution de la tâche via un ForkJoinPool.
	 */
	public static double calculate(int max) {
		ForkJoinPool pool = new ForkJoinPool();
		return pool.invoke(new CalculateTask(0, max));
	}

}
