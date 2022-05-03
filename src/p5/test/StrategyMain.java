package p5.test;

import java.util.Map;

import p5.Person;
import p5.Template;
import p5.ej4.FilePersister;
import p5.ej4.TimeStamper;
import p5.ej4.UpperCaser;

// add package declarations and imports

public class StrategyMain {

	public static void main(String[] args) {
		Template<Person> simpleLetter = IteratedTemplateMain.createLetterTemplate();
		IteratedTemplateMain.addDataObjects(simpleLetter);

		simpleLetter.withOptions(new TimeStamper<>(),
				new UpperCaser<>(),
				new FilePersister<Person>(p -> p.getName()));

		System.out.println(writeResult(simpleLetter.emit()));
	}

	private static String writeResult(Map<Person, String> emit) {
		String res = "";
		for (Person p : emit.keySet())
			res += emit.get(p) + "\n-----------------------------------------------------\n";
		return res;
	}

}
