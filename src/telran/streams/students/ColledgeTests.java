package telran.streams.students;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.stream.StreamSupport;

import org.junit.jupiter.api.Test;

class ColledgeTests {
private static final String NAME1 = null;
private static final String NAME2 = null;
private static final String NAME3 = null;
private static final int HOURS1 = 100;
private static final int HOURS2 = 100;
private static final int HOURS3 = 150;
private static final int[] MARKS1 = {60,70,80};
private static final int[] MARKS2 = {60,60,60};
Student st1 = new Student(NAME1, HOURS1,MARKS1);
Student st2 = new Student(NAME2, HOURS2,MARKS2);
Student st3 = new Student(NAME3, HOURS3,MARKS2);
Colledge colledge = new Colledge(new Student[] {st1,st2,st3});

	@Test
	void sortTest() {
		Student[] expected = {st1,st3,st2}; 
		
		assertArrayEquals(expected, sortStudents(colledge));
	}
	@Test
	void summaryStatistcsHoursTest() {
		IntSummaryStatistics iss =getHoursStatistics(colledge);
		assertEquals(100, iss.getMin());
		assertEquals(150, iss.getMax());
		assertEquals(350, iss.getSum());
	}
	@Test
	void summaryStatistcsMarksTest() {
		IntSummaryStatistics iss =geMarksStatistics(colledge);
		assertEquals(60, iss.getMin());
		assertEquals(80, iss.getMax());
		
	}
	private static IntSummaryStatistics geMarksStatistics(Colledge col) {
		// returns summary statistic for marks all colledge's students
		return Arrays.stream(col.students).flatMapToInt(student -> Arrays.stream(student.marks())).summaryStatistics();
	}
	private static IntSummaryStatistics getHoursStatistics(Colledge col) {
		//returns IntSummaryStatistics of hours all colledges's students
		return Arrays.stream(col.students)
                .mapToInt(Student::hours)
                .summaryStatistics();
	}
	private static Student[] sortStudents(Colledge col) {
		
		//consider getting stream from Iterable
		//returns array of students sorted in descending order of the average marks
		//in the case average marks are equaled there will be compare hours(more hours)
		//one code line
		return  StreamSupport.stream(col.spliterator(), false)
				.sorted(Comparator.comparingDouble((Student s) -> Arrays.stream(s.marks())
						.average().orElseThrow()
						).thenComparingInt(s -> s.hours()).reversed()).toArray(Student[]::new);
	}
	
}
