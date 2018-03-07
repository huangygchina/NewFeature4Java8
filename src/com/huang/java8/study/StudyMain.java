/*
 * Author:hyg
 * Date:2018-03-06
 */
package com.huang.java8.study;

import java.time.Clock;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;;


public class StudyMain {

	public static void main(String[] args) {
		
		
		//允许在接口中有默认方法实现
		studyForInterface();
		
		//Lambda表达式
		studyForLambda();
		
		//函数式接口
		studyForFunctionalInterface();
		
		//方法和构造函数引用
		studyForMethodCall();
		
		//函数式编程
		FPPredicate();
		
		FPFunction();
		
		FPConsumers();
		
		FPComparators();
		
		FPOptionals();
		
		//stream
		StudyStream();
		
		//Parallel Streams
		//studyParallelStream();
		
		//Map
		studyMap();
		
		//TimeAPI
		studyTimeAPI();
		

	}
	
	/**
	 * 允许在接口中有默认方法实现
	 */
	public static void studyForInterface(){
		
		Formula formula = new FormulaImpl();
		double re = formula.sqrt(16);
		double re2 = formula.calculate(16);
		
		System.out.println("result =="+re+";result2 =="+re2);
		
	}
	
	/**
	 * Lambda表达式
	 */
	public static void studyForLambda(){
		List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
		Collections.sort(names, (a, b) -> b.compareTo(a));
		
		for (String str : names){
			
			System.out.println("str==="+str);
		}
		
	}
	
	@FunctionalInterface
	interface Converter<F, T> {
	   T convert(F from);
	}
	
	/**
	 * 函数式接口
	 */
	public static void studyForFunctionalInterface() {
		Converter<String, Integer> converter = (from) -> Integer.valueOf(from);
		Integer converted = converter.convert("123");
		System.out.println(converted); 
		
		
		Converter<String,String> converter1 = String::toUpperCase;
		String str = converter1.convert("abc");
		System.out.println("==============================="+str);
		
		
	}
	

	
	/**
	 * 方法和构造函数引用.
	 */
	public static void studyForMethodCall(){
		//方法引用.
		Converter<String, Integer> converter = Integer::valueOf;
		Integer converted = converter.convert("123");
		System.out.println(converted);   // 123
		
		Somethin something = new Somethin();
		Converter<String, String> converter1 = something::startsWith;
		String converted1 = converter1.convert("Java");
		System.out.println(converted1);    // "J"
		
		//构造函数引用.
		PersonFactory<Person> personFactory = Person::new;
		Person person = personFactory.create("Peter", "Parker");
		System.out.println("firstName = "+person.firstName+" lastName = "+person.lastName);
		
		//访问局部变量.
		final int num = 1;
		Converter<Integer, String> stringConverter =
		        (from) -> String.valueOf(from + num);
		System.out.println("str convert == "+stringConverter.convert(2));
		
	}
	
	/**
	 * 函数式编程-Predicates
	 */
	public static void FPPredicate(){
		//Predicates
		Predicate<String> predicate = (s) -> s.length() > 0;
		boolean re = predicate.test("foo");
		System.out.println("re == "+re);
		
		re = predicate.negate().test("foo");
		System.out.println("re == "+re);
		
		Predicate<Boolean> nonNull = Objects::nonNull;
		Predicate<Boolean> isNull = Objects::isNull;
		
		re = nonNull.test(null);
		System.out.println("re == "+re);
		re = isNull.test(null);
		System.out.println("re == "+re);
		

		Predicate<String> isEmpty = String::isEmpty;
		Predicate<String> isNotEmpty = isEmpty.negate();
		
		re = isEmpty.test("");
		System.out.println("re == "+re);
		re = isNotEmpty.test("");
		System.out.println("re == "+re);
		
	}
	
	/**
	 * 函数式编程-FPFunction
	 */
	public static void FPFunction(){
		Function<String, Integer> toInteger = Integer::valueOf;
		Function<String, String> backToString = toInteger.andThen(String::valueOf);
		
		Integer i = toInteger.apply("123");
		System.out.println("i == "+i);
		
		
		String n = backToString.apply("123");
		System.out.println("n == "+n);
		
	}
	
	/**
	 * 函数式编程-FPSuppliers
	 */
	public static void FPSuppliers(){
		Supplier<Person> personSupplier = Person::new;
		Person p = personSupplier.get();
		p.firstName = "Mike";
		p.lastName = "Meg";

	}
	
	/**
	 * 函数式编程-Consumers
	 */
	public static void FPConsumers(){
		Consumer<Person> greeter = (p) -> System.out.println("Hello, " + p.firstName);
		greeter.accept(new Person("Luke", "Skywalker"));
	}


	/**
	 * 函数式编程-Comparators
	 */
	public static void FPComparators(){
		Comparator<Person> comparator = (p1, p2) -> p1.firstName.compareTo(p2.firstName);

		Person p1 = new Person("John", "Doe");
		Person p2 = new Person("Alice", "Wonderland");

		int i = comparator.compare(p1, p2);             // > 0
		System.out.println("i == "+i);
		
		i = comparator.reversed().compare(p1, p2);  // < 0
		System.out.println("i == "+i);
	}
	
	public static void FPOptionals(){
		Optional<String> optional = Optional.of("bam");

		optional.isPresent();           // true
		optional.get();                 // "bam"
		optional.orElse("fallback");    // "bam"

		optional.ifPresent((s) -> System.out.println(s.charAt(0)));     // "b"
		
	}
	
	/**
	 * Stream
	 */
	public static void StudyStream(){
		List<String> stringCollection = new ArrayList<>();
		stringCollection.add("ddd2");
		stringCollection.add("aaa2");
		stringCollection.add("bbb1");
		stringCollection.add("aaa1");
		stringCollection.add("bbb3");
		stringCollection.add("ccc");
		stringCollection.add("bbb2");
		stringCollection.add("ddd1");
		
		//filter
		stringCollection
	    .stream()
	    .filter((s) -> s.startsWith("a"))
	    .forEach(System.out::println);
		
		//sorted
		stringCollection
	    .stream()
	    .sorted()
	    .filter((s) -> s.startsWith("a"))
	    .forEach(System.out::println);
		
		//map
		stringCollection
	    .stream()
	    .map(String::toUpperCase)
	    .sorted((a, b) -> b.compareTo(a))
	    .forEach(System.out::println);
		
		//Match
		boolean anyStartsWithA = stringCollection.stream().anyMatch((s) -> s.startsWith("a"));

		System.out.println(anyStartsWithA); // true

		boolean allStartsWithA = stringCollection.stream().allMatch((s) -> s.startsWith("a"));

		System.out.println(allStartsWithA); // false

		boolean noneStartsWithZ = stringCollection.stream().noneMatch((s) -> s.startsWith("z"));

		System.out.println(noneStartsWithZ); // true
		
		//count
		long startsWithB = stringCollection
				.stream()
				.filter((s) -> s.startsWith("g")).count();

		System.out.println(startsWithB); // 3
		
		//Reduce
		Optional<String> reduced =
			    stringCollection
			        .stream()
			        .sorted()
			        .reduce((s1, s2) -> s1 + "#" + s2);

		reduced.ifPresent(System.out::println);
		
	}
	
	/**
	 * Study parallel stream.
	 */
	static void studyParallelStream(){
		int max = 1000000;
		List<String> values = new ArrayList<>(max);
		for (int i = 0; i < max; i++) {
		    UUID uuid = UUID.randomUUID();
		    values.add(uuid.toString());
		}
		
		long t0 = System.nanoTime();

		long count = values.stream().sorted().count();
		System.out.println(count);

		long t1 = System.nanoTime();

		long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
		System.out.println(String.format("sequential sort took: %d ms", millis));
		
		long t2 = System.nanoTime();

		long count1 = values.parallelStream().sorted().count();
		System.out.println(count1);

		long t3 = System.nanoTime();

		long millis1 = TimeUnit.NANOSECONDS.toMillis(t3 - t2);
		System.out.println(String.format("parallel sort took: %d ms", millis1));
	}
	
	static void studyMap(){
		Map<Integer, String> map = new HashMap<>();

		for (int i = 0; i < 10; i++) {
		    map.putIfAbsent(i, "val" + i);
		}

		map.forEach((id, val) -> System.out.println(val));
		
		map.computeIfPresent(3, (num, val) -> val + num);
		String str = map.get(3);             // val33
		System.out.println("str = " + str);

		map.computeIfPresent(9, (num, val) -> null);
		boolean re = map.containsKey(9);     // false
		System.out.println("re = " + re);

		map.computeIfAbsent(23, num -> "val" + num);
		re = map.containsKey(23);    // true
		str = map.get(23);
		System.out.println("re = " + re + " str = " + str);

		map.computeIfAbsent(3, num -> "bam");
		str = map.get(3);             // val33
		System.out.println("str = " + str);
	
		map.remove(3, "val3");
		str = map.get(3);             // val33
		System.out.println("str = " + str);

		map.remove(3, "val33");
		str = map.get(3);             // null
		System.out.println("str = " + str);
		
		str = map.getOrDefault(42, "sorry ,not found");  // not found
		System.out.println("str = " + str);
		
		map.merge(8, "val9", (value, newValue) -> value.concat(newValue));
		str = map.get(8);             // val9
		System.out.println("str = " + str);

		map.merge(9, "concat", (value, newValue) -> value.concat(newValue));
		str = map.get(9);             // val9concat
		System.out.println("str = " + str);
	}
	
	static void studyTimeAPI(){
		//Clock
		Clock clock = Clock.systemDefaultZone();
		long millis = clock.millis();
		
		System.out.println("millis = " + millis);

		Instant instant = clock.instant();
		Date legacyDate = Date.from(instant);   // legacy java.util.Date
		System.out.println("legacyDate = " + legacyDate);
		
		//Timezones
		System.out.println(ZoneId.getAvailableZoneIds());
		// prints all available timezone ids

		ZoneId zone1 = ZoneId.of("Europe/Berlin");
		ZoneId zone2 = ZoneId.of("Brazil/East");
		System.out.println(zone1.getRules());
		System.out.println(zone2.getRules());

		// ZoneRules[currentStandardOffset=+01:00]
		// ZoneRules[currentStandardOffset=-03:00]
		
		//LocalTime
		LocalTime now1 = LocalTime.now(zone1);
		LocalTime now2 = LocalTime.now(zone2);

		System.out.println(now1.isBefore(now2));  // false

		long hoursBetween = ChronoUnit.HOURS.between(now2, now1);
		long minutesBetween = ChronoUnit.MINUTES.between(now1, now2);

		System.out.println(hoursBetween);       // -3
		System.out.println(minutesBetween);     // -239
		
		
		LocalTime late = LocalTime.of(23, 59, 59);
		System.out.println(late);       // 23:59:59

		
		DateTimeFormatter germanFormatter =
		    DateTimeFormatter
		        .ofLocalizedTime(FormatStyle.SHORT)
		        .withLocale(Locale.GERMAN);

		LocalTime leetTime = LocalTime.parse("13:37", germanFormatter);
		System.out.println(leetTime);   // 13:37
		
		//LocalDate
		LocalDate today = LocalDate.now();
		LocalDate tomorrow = today.plus(1, ChronoUnit.DAYS);
		LocalDate yesterday = tomorrow.minusDays(2);
		

		LocalDate independenceDay = LocalDate.of(2014, Month.JULY, 4);
		DayOfWeek dayOfWeek = independenceDay.getDayOfWeek();
		System.out.println(dayOfWeek);
		
		
		System.out.println(yesterday);
		dayOfWeek = yesterday.getDayOfWeek(); 
		System.out.println(dayOfWeek);
		
		// FRIDAY<span style="font-family: Georgia, 'Times New Roman', 'Bitstream Charter', Times, serif; font-size: 13px; line-height: 19px;">Parsing a LocalDate from a string is just as simple as parsing a LocalTime:</span>
		
		germanFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).withLocale(Locale.GERMAN);

		LocalDate xmas = LocalDate.parse("24.12.2014", germanFormatter);
		System.out.println(xmas); // 2014-12-24
		
		//LocalDateTime
		LocalDateTime sylvester = LocalDateTime.of(2014, Month.DECEMBER, 31, 23, 59, 59);

		dayOfWeek = sylvester.getDayOfWeek();
		System.out.println(dayOfWeek);      // WEDNESDAY

		Month month = sylvester.getMonth();
		System.out.println(month);          // DECEMBER

		long minuteOfDay = sylvester.getLong(ChronoField.MINUTE_OF_DAY);
		System.out.println(minuteOfDay);    // 1439
		
		instant = sylvester
		        .atZone(ZoneId.systemDefault())
		        .toInstant();

		legacyDate = Date.from(instant);
		System.out.println(legacyDate);     // Wed Dec 31 23:59:59 CET 2014
		
		DateTimeFormatter formatter =
			    DateTimeFormatter
			        .ofPattern("MMM dd, yyyy - HH:mm");

			LocalDateTime parsed = LocalDateTime.parse("Nov 03, 2014 - 07:13", formatter);
			String string = formatter.format(parsed);
			System.out.println(string);     // Nov 03, 2014 - 07:13
		
	}
		
}
