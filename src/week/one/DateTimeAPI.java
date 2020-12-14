package week.one;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class DateTimeAPI {

	public static void main(String[] args) {
		// January 1st 1980, noon
		// year, month, day, hour, minute, second, nanosecond
		LocalDateTime ldt = LocalDateTime.of(1980, 1, 1, 12, 0, 0, 0);
		System.out.println("I would use " + java.time.LocalDateTime.class.getName());
		
		LocalDate cur = LocalDate.now();
		LocalDate prevThur = getPreviousThursday(cur);
		System.out.println("Previous Thursday of " + cur + " is " + prevThur );
		
		prevThur = getPreviousThursday(ldt);
		System.out.println("Previous Thursday of " + ldt.toLocalDate() + " is " + prevThur );
	}
	
	/**
	 * Return the LocalDate of the previous Thursday
	 * @param localDate
	 * @return LocalDate of the previous Thursday
	 */
	private static LocalDate getPreviousThursday(LocalDate localDate) {
		LocalDate prevThur = null;
		switch(localDate.getDayOfWeek()) {
		case FRIDAY:
			prevThur = localDate.minusDays(1L);
			break;
		case SATURDAY:
			prevThur = localDate.minusDays(2L);
			break;
		case SUNDAY:
			prevThur = localDate.minusDays(3L);
			break;
		case MONDAY:
			prevThur = localDate.minusDays(4L);
			break;
		case TUESDAY:
			prevThur = localDate.minusDays(5L);
			break;
		case WEDNESDAY:
			prevThur = localDate.minusDays(6L);
			break;
		default:
			prevThur = localDate.minusDays(7L);
		}
		return prevThur;
	}
	
	/**
	 * Return the LocalDate of the previous Thursday
	 * @param localDate
	 * @return LocalDate of the previous Thursday
	 */
	private static LocalDate getPreviousThursday(LocalDateTime localDateTime) {
		return getPreviousThursday(localDateTime.toLocalDate());
	}

}
