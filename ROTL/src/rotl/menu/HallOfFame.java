package rotl.menu;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Vector;

import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import javafx.util.Pair;

import static java.lang.Math.*;

public class HallOfFame implements MenuOption {

	static final String menuSectionImageSrc = "..\\resources\\images\\BGHallOfFame.jpg";
	
	private static HallOfFame single_instance = null;
	private static Vector<Pair<String, Integer>> history = new Vector<Pair<String, Integer>>();

	private Scanner scanner;

	private HallOfFame() {
	}

	public static HallOfFame getHallOfFame() {
		if (single_instance == null)
			single_instance = new HallOfFame();

		return single_instance;
	}

	public void setHallOfFame() {

		
	}

	private void readHistory() {

		String s = null;
		try {
			scanner = new Scanner(new File("..\\resources\\files\\HallOfFames.txt"));
			while (scanner.hasNextLine()) {
				s = scanner.nextLine();

				Pair<String, Integer> aux = new Pair<String, Integer>(s.substring(0, s.indexOf('#') - 1),
						Integer.parseInt(s.substring(s.indexOf('#') + 1, s.lastIndexOf('#'))));
				history.add(aux);
			}
		} catch (FileNotFoundException e) {
			System.out.println("Couldn't load text ...");
			e.printStackTrace();
		}
	}

	private void processingHistory() {

		Comparator<Pair<String, Integer>> comparator = new PairComparator();
		Collections.sort(history, comparator);
	}

	public void createContent() {

		readHistory();

		processingHistory();

		String content1 = new String("");
		String content2 = new String("");

		for (Integer i = 0; i < min(history.size(), 10); ++i) {

			Pair<String, Integer> aux = history.get(i);
			content1 += i + 1;
			content1 += ".  ";
			content1 += aux.getKey();
			content1 += '\n';

			content2 += aux.getValue();
			content2 += '\n';
		}
	}
}

class PairComparator implements Comparator<Pair<String, Integer>> {
	public int compare(Pair<String, Integer> o1, Pair<String, Integer> o2) {
		return -(o1.getValue().compareTo(o2.getValue()));
	}
}