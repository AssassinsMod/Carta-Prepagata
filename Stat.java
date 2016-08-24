import java.util.*;

public class Stat {
	public static void main(String[] args) {
		List<Integer> valori = new ArrayList<Integer>();
		int[] occorrenze = new int[10]; // valori 1~10, indici 0~9

		// parsing
		int tot = 0;
		for (String arg : args) {
			int num = Integer.parseInt(arg);
			if (num < 1 || num > 10) throw new IllegalArgumentException(
				"I numeri devono essere compresi tra 1 e 10");

			// aggiunta alla lista
			valori.add(num);

			// incremento occorrenze
			occorrenze[num - 1]++;

			// update totale
			tot += num;
		}

		Collections.sort(valori);

		// elaborazione
		double media = (double)tot / valori.size();

		int moda = 0, moda_occ = 0;
		for (int i = 0; i < 10; i++) {
			if (occorrenze[i] > moda_occ) {
				moda_occ = occorrenze[i];
				moda = i + 1;
			}
		}

		int mediana = valori.size() % 2 == 0
			// pari -> media dei due valori centrali
			? (int) Math.round((valori.get(valori.size() / 2) +
				valori.get(valori.size() / 2 - 1)) / 2d)
			// dispari -> valore centrale
			: valori.get((valori.size() - 1) / 2);

		// print
		System.out.println(
			"media: " + media + "\n" +
			"moda: " + moda + "\n" +
			"mediana: " + mediana
		);
	}
}
