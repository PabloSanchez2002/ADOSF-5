package p5.test;

import java.time.LocalDate;
import java.util.Map;

import p5.Person;
import p5.Template;

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
				new Person("Mary", LocalDate.of(2001, 1, 1)));
		simpleLetter.withSortingCriteria((Person p1, Person p2) -> p2.getAge() - p1.getAge());
	}

	public static Template<Person> createLetterTemplate() {
		Template<Person> simpleLetter = new Template<>();
		simpleLetter.add("Dear ##,\nHow are you today?", p -> p.getName());
		simpleLetter.add("Since you were born on ##, you are ## years old.", p -> p.getBirthDate(), p -> p.getAge());
		return simpleLetter;
	}
}
