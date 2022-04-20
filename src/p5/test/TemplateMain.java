package p5.test;

import p5.Person;

// add package declarations and imports

public class TemplateMain {

	public static void main(String[] args) {
		Template<Person> simpleLetter = createLetterTemplate();
		addDataObjects(simpleLetter);

		Map<Person, String> result = simpleLetter.emit();

		for (Person p : result.keySet())
			System.out.println(result.get(p));

		System.out.println(simpleLetter.emit(new Person("Jude", LocalDate.of(2018, 5, 5))));
	}

	public static void addDataObjects(Template<Person> simpleLetter) {
		simpleLetter.addObjects(new Person("Peter", LocalDate.of(1974, 4, 1)),
				new Person("Peter", LocalDate.of(2005, 10, 12)),
				new Person("Paul", LocalDate.of(2014, 6, 19)),
				new Person("Mary", LocalDate.of(2001, 1, 1)))
				.withSortingCriteria((Person p1, Person p2) -> p1.getAge() - p2.getAge());
	}

	public static Template<Person> createLetterTemplate() {
		Template<Person> simpleLetter = new Template<>();
		simpleLetter.add("Dear ##,\nHow are you today?",
				p -> p.getName())
				.add("Since you were born on ##, you are ## years old.",
						p -> p.getBirthDate(),
						p -> p.getAge());
		return simpleLetter;
	}
}
