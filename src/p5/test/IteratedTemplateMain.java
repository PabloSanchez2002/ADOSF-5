package p5.test;

import p5.Person;

// add package declarations and imports

public class IteratedTemplateMain {

	public static void main(String[] args) {
		Template<Person> simpleLetter = createLetterTemplate();
		addDataObjects(simpleLetter);

		Map<Person, String> result = simpleLetter.emit();

		for (Person p : result.keySet())
			System.out.println(result.get(p));

	}

	public static Template<Person> createLetterTemplate() {
		Template<Person> simpleLetter = TemplateMain.createLetterTemplate();
		simpleLetter.addWhen(p -> p.getAge() >= 65, "Contact us if thinking about retirement...")
				.addWhen(p -> p.getMascots().size() > 0, "... and greetings to your mascots:")
				.addForEach(p -> p.getMascots(),
						"  Ey ##, you are a nice ##!",
						m -> m.getMascotName(),
						m -> m.getMascotType());
		return simpleLetter;
	}

	public static void addDataObjects(Template<Person> simpleLetter) {
		Person peter = new Person("Peter", LocalDate.of(1957, 4, 1)),
				mary = new Person("Mary", LocalDate.of(2001, 1, 1));

		peter.addMascots(new Mascot("cat", "Felix"),
				new Mascot("canary", "Chirps"));
		simpleLetter.addObjects(peter, mary);
	}
}
