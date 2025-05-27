package test;

import org.jfree.data.time.TimePeriodFormatException;
import org.jfree.data.time.Week;
import org.jfree.data.time.Year;
import org.junit.Test;

import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.*;

import static org.junit.Assert.*;

public class WeekTest {
    Week week;

    private void arrange() {
        week = new Week();
    }
    @Test
    public void testWeekDefaultConstructor() {
        arrange();

        LocalDate currentDate = LocalDate.now();
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        int currentWeekNumber = currentDate.get(weekFields.weekOfWeekBasedYear());

        assertEquals(2025, week.getYear().getYear());
        assertEquals(currentWeekNumber, week.getWeek());
    }

    @Test
    public void testWeekConstructorWithWeekAndYear() {
        Week week = new Week(5, 2025);

        assertEquals(2025, week.getYear().getYear());
        assertEquals(5, week.getWeek());
    }

    @Test
    public void testWeekConstructorWithWeekAndYearWithFirstWeek() {
        Week week = new Week(1, 2025);

        assertEquals(1, week.getWeek());
        assertEquals(2025, week.getYear().getYear());
    }

    @Test
    public void testWeekConstructorWithWeekAndYearWithLastWeek() {
        Week week = new Week(53, 2025);

        assertEquals(53, week.getWeek());
        assertEquals(2025, week.getYear().getYear());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWeekConstructorWithWeekAndYearWithZeroWeek() {
        new Week(0, 2025);
        // Should throw an exception, but it doesn't
        // the if condition in the constructor doesn't correctly check if the week number is valid
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWeekConstructorWithWeekAndYearWithWeekAbove53() {
        Week week = new Week(54, 2025);
        // Should throw an exception, but it doesn't
        // the if condition in the constructor doesn't correctly check if the week number is valid
    }

    @Test
    public void testWeekConstructorWithWeekAndYearObject() {
        Year year = new Year(2025);
        Week week = new Week(10, year);

        assertEquals(10, week.getWeek());
        assertEquals(2025, week.getYear().getYear());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWeekConstructorWithWeekAndYearObjectZeroWeek() {
        Year year = new Year(2025);
        new Week(0, year);
        // Should throw an exception, but it doesn't
        // Same logical error in Week(int week, int year)
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWeekConstructorWithWeekAndYearObjectAbove53() {
        Year year = new Year(2025);
        new Week(54, year);
        // Should throw an exception, but it doesn't
        // Same logical error in Week(int week, int year)
    }

    @Test
    public void testWeekConstructorWithDateObject() {
        Calendar calendar = Calendar.getInstance();
        Week week = new Week(calendar.getTime());

        assertEquals(calendar.get(Calendar.YEAR), week.getYear().getYear());
        assertEquals(calendar.get(Calendar.WEEK_OF_YEAR), week.getWeek());
    }

    @Test
    public void testWeekConstructorWithSpecificDateObject() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2030, Calendar.MAY, 17);
        Week week = new Week(calendar.getTime());

        assertEquals(2030, week.getYear().getYear());
        assertEquals(20, week.getWeek());
    }

    @Test
    public void testWeekConstructorWithDateObjectAndTimeZoneUTC() {
        TimeZone timezone = TimeZone.getTimeZone("UTC");
        Calendar calendar = Calendar.getInstance(timezone);

        Week week = new Week(calendar.getTime());

        assertEquals(calendar.get(Calendar.YEAR), week.getYear().getYear());
        assertEquals(calendar.get(Calendar.WEEK_OF_YEAR), week.getWeek());
    }

    @Test
    public void testWeekConstructorWithDateObjectAndTimeZoneMoscow() {
        TimeZone timezone = TimeZone.getTimeZone("Europe/Moscow");
        Calendar calendar = Calendar.getInstance(timezone);

        Week week = new Week(calendar.getTime());

        assertEquals(calendar.get(Calendar.YEAR), week.getYear().getYear());
        assertEquals(calendar.get(Calendar.WEEK_OF_YEAR), week.getWeek());
    }

    @Test
    public void testWeekConstructorWithSpecificDateAndTimeZoneMoscow() {
        TimeZone timezone = TimeZone.getTimeZone("Europe/Moscow");
        Calendar calendar = Calendar.getInstance(timezone);
        calendar.set(2030, Calendar.MAY, 17);

        Week week = new Week(calendar.getTime());

        assertEquals(2030, week.getYear().getYear());
        assertEquals(20, week.getWeek());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWeekConstructorWithDateTimeZoneLocaleWithNullDate() {
        new Week(null, TimeZone.getTimeZone("UTC"), Locale.US);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWeekConstructorWithDateTimeZoneLocaleWithNullTimeZone() {
        new Week(new Date(), null, Locale.US);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWeekConstructorWithDateTimeZoneLocaleWithNullLocale() {
        new Week(new Date(), TimeZone.getTimeZone("UTC"), null);
    }

    @Test
    public void testWeekConstructorWithDateTimeZoneLocaleUS() {
        TimeZone timeZone = TimeZone.getTimeZone("UTC");
        Locale locale = Locale.US;
        Calendar calendar = Calendar.getInstance(timeZone, locale);
        calendar.set(2025, Calendar.MAY, 17);

        Week week = new Week(calendar.getTime(), timeZone, locale);

        assertEquals(2025, week.getYear().getYear());
        assertEquals(20, week.getWeek());
    }

    @Test
    public void testWeekConstructorWithDateTimeZoneLocaleCanada() {
        TimeZone timeZone = TimeZone.getTimeZone("America/Toronto");
        Locale locale = Locale.CANADA;

        Calendar calendar = Calendar.getInstance(timeZone, locale);
        calendar.set(2025, Calendar.JULY, 10);

        Week week = new Week(calendar.getTime(), timeZone, locale);

        assertEquals(2025, week.getYear().getYear());
        assertEquals(calendar.get(Calendar.WEEK_OF_YEAR), week.getWeek());
    }

    @Test
    public void testGetYear() {
        Week week = new Week(15, 2025);
        assertEquals(2025, week.getYear().getYear());
    }

    @Test
    public void testGetYearValue() {
        Week week = new Week(15, 2025);
        assertEquals(2025, week.getYearValue());
    }

    @Test
    public void testGetWeek() {
        Week week = new Week(42, 2025);
        assertEquals(42, week.getWeek());
    }

    @Test
    public void testGetFirstMillisecond() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.MAY, 17);
        Week week = new Week(20, 2025);
        week.peg(calendar);

        assertEquals(week.getFirstMillisecond(calendar), week.getFirstMillisecond());
    }

    @Test
    public void testGetLastMillisecond() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.APRIL, 13);
        Week week = new Week(15, 2025);
        week.peg(calendar);

        assertEquals(week.getLastMillisecond(calendar), week.getLastMillisecond());
    }

    @Test
    public void testPegUpdatesFirstAndLastMillisecond() {
        Calendar calendar = Calendar.getInstance();
        Week week = new Week(20, 2025);
        week.peg(calendar);

        assertEquals(week.getFirstMillisecond(calendar), week.getFirstMillisecond());
        assertEquals(week.getLastMillisecond(calendar), week.getLastMillisecond());
    }

    @Test
    public void testPreviousMethod() throws Exception {
        Week week = new Week(1, 1900);
        Method previousMethod = Week.class.getDeclaredMethod("previous");
        previousMethod.setAccessible(true);

        Week result = (Week) previousMethod.invoke(week);
        assertNull(result);
    }

    @Test
    public void testNextMethod() throws Exception {
        Week week = new Week(52, 9999);
        Method nextMethod = Week.class.getDeclaredMethod("next");
        nextMethod.setAccessible(true);

        Week result = (Week) nextMethod.invoke(week);
        assertNull(result);
    }

    @Test
    public void testLeapYearWeekCalculation() {
        Week leapWeek = new Week(new GregorianCalendar(2020, Calendar.FEBRUARY, 29).getTime());
        assertEquals(2020, leapWeek.getYearValue());
        assertTrue(leapWeek.getWeek() > 0 && leapWeek.getWeek() <= 53);
    }

    @Test
    public void testWeekTransitionBetweenYears() {
        Week week = new Week(new GregorianCalendar(2023, Calendar.DECEMBER, 31).getTime());
        assertTrue(week.getYearValue() == 2023 || week.getYearValue() == 2024);
    }

    @Test
    public void testToString() {
        Week week = new Week(20, 2025);
        assertEquals("Week 20, 2025", week.toString());
    }

    @Test
    public void testEqualsSameObject() {
        Week week = new Week(15, 2025);
        assertTrue(week.equals(week));
    }

    @Test
    public void testEqualsDifferentObjectsSameValues() {
        Week week1 = new Week(15, 2025);
        Week week2 = new Week(15, 2025);
        assertTrue(week1.equals(week2));
    }

    @Test
    public void testEqualsDifferentWeeks() {
        Week week1 = new Week(15, 2025);
        Week week2 = new Week(16, 2025);
        assertFalse(week1.equals(week2));
    }

    @Test
    public void testEqualsDifferentYears() {
        Week week1 = new Week(15, 2025);
        Week week2 = new Week(15, 2026);
        assertFalse(week1.equals(week2));
    }

    @Test
    public void testEqualsWithNull() {
        Week week = new Week(15, 2025);
        assertFalse(week.equals(null));
    }

    @Test
    public void testEqualsWithDifferentType() {
        Week week = new Week(15, 2025);
        assertFalse(week.equals("Some String"));
    }

    @Test
    public void testHashCodeConsistency() {
        Week week = new Week(20, 2025);
        int initialHashCode = week.hashCode();
        assertEquals(initialHashCode, week.hashCode());
    }

    @Test
    public void testHashCodeDifferentWeeks() {
        Week week1 = new Week(10, 2025);
        Week week2 = new Week(11, 2025);
        assertNotEquals(week1.hashCode(), week2.hashCode());
    }

    @Test
    public void testHashCodeDifferentYears() {
        Week week1 = new Week(10, 2025);
        Week week2 = new Week(10, 2026);
        assertNotEquals(week1.hashCode(), week2.hashCode());
    }

    @Test
    public void testCompareToEqualWeeks() {
        Week week1 = new Week(15, 2025);
        Week week2 = new Week(15, 2025);
        assertEquals(0, week1.compareTo(week2));
    }

    @Test
    public void testCompareToEarlierWeek() {
        Week week1 = new Week(14, 2025);
        Week week2 = new Week(15, 2025);
        assertTrue(week1.compareTo(week2) < 0);
    }

    @Test
    public void testCompareToLaterWeek() {
        Week week1 = new Week(16, 2025);
        Week week2 = new Week(15, 2025);
        assertTrue(week1.compareTo(week2) > 0);
    }

    @Test
    public void testCompareToDifferentYears() {
        Week week1 = new Week(10, 2025);
        Week week2 = new Week(10, 2026);
        assertTrue(week1.compareTo(week2) < 0);
    }

    @Test(expected = TimePeriodFormatException.class)
    public void testParseWeekInvalidFormat() {
        Week.parseWeek("Invalid format");
    }
    

    @Test
    public void testParseWeekValidFormat() {
        Week week = Week.parseWeek("2025-10");
        assertEquals(10, week.getWeek());
        assertEquals(2025, week.getYear().getYear());
    }

    @Test(expected = TimePeriodFormatException.class)
    public void testParseWeekInvalidWeekNumber() {
        Week.parseWeek("2025-54"); // Week 54 is invalid
    }

    @Test
    public void testFindSeparatorHyphen() throws Exception {
        Method method = Week.class.getDeclaredMethod("findSeparator", String.class);
        method.setAccessible(true);
        int result = (int) method.invoke(null, "2025-10");
        assertEquals(4, result);
    }

    @Test
    public void testFindSeparatorComma() throws Exception {
        Method method = Week.class.getDeclaredMethod("findSeparator", String.class);
        method.setAccessible(true);
        int result = (int) method.invoke(null, "2025,10");
        assertEquals(4, result);
    }

    @Test
    public void testFindSeparatorNone() throws Exception {
        Method method = Week.class.getDeclaredMethod("findSeparator", String.class);
        method.setAccessible(true);
        int result = (int) method.invoke(null, "202510");
        assertEquals(-1, result);
    }

    @Test
    public void testEvaluateAsYearValid() throws Exception {
        Method method = Week.class.getDeclaredMethod("evaluateAsYear", String.class);
        method.setAccessible(true);
        Year result = (Year) method.invoke(null, "2025");
        assertNotNull(result);
        assertEquals(2025, result.getYear());
    }

    @Test
    public void testEvaluateAsYearInvalid() throws Exception {
        Method method = Week.class.getDeclaredMethod("evaluateAsYear", String.class);
        method.setAccessible(true);
        Year result = (Year) method.invoke(null, "yearmonth");
        assertNull(result);
    }

    @Test
    public void testStringToWeekValid() throws Exception {
        Method method = Week.class.getDeclaredMethod("stringToWeek", String.class);
        method.setAccessible(true);
        int result = (int) method.invoke(null, "10");
        assertEquals(10, result);
    }

    @Test
    public void testStringToWeekWithWPrefix() throws Exception {
        Method method = Week.class.getDeclaredMethod("stringToWeek", String.class);
        method.setAccessible(true);
        int result = (int) method.invoke(null, "W10");
        assertEquals(10, result);
    }

    @Test
    public void testStringToWeekInvalid() throws Exception {
        Method method = Week.class.getDeclaredMethod("stringToWeek", String.class);
        method.setAccessible(true);
        int result = (int) method.invoke(null, "M22");
        assertEquals(-1, result);
    }

    @Test
    public void testStringToWeekOutOfRange() throws Exception {
        Method method = Week.class.getDeclaredMethod("stringToWeek", String.class);
        method.setAccessible(true);
        int result = (int) method.invoke(null, "54");
        assertEquals(-1, result);
    }
}
