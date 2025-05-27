package test;

import JFree.DiscountCalculator;
import org.jfree.data.time.Week;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

public class DiscountCalculatorTest {

    @Test
    public void testIsTheSpecialWeekWhenFalse() throws Exception {
        // Arrange
        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.MARCH, 22);
        Date date = calendar.getTime();
        Week week = new Week(date);
        DiscountCalculator calculator = new DiscountCalculator(week);

        // Act
        boolean result = calculator.isTheSpecialWeek();

        // Assert
        assertFalse(result);
    }

    @Test
    public void testIsTheSpecialWeekWhenTrue() throws Exception {
        // Arrange
        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.JUNE, 23);
        Date date = calendar.getTime();
        Week week = new Week(date);
        DiscountCalculator calculator = new DiscountCalculator(week);

        // Act
        boolean result = calculator.isTheSpecialWeek();

        // Assert
        assertTrue(result);
    }

    @Test
    public void testGetDiscountPercentageEvenWeek() throws Exception {
        // Arrange
        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.AUGUST, 20);
        Date date = calendar.getTime();
        Week week = new Week(date);
        DiscountCalculator calculator = new DiscountCalculator(week);

        // Act
        int discount = calculator.getDiscountPercentage();

        // Assert
        assertEquals(7, discount);
    }

    @Test
    public void testGetDiscountPercentageOddWeek() throws Exception {
        // Arrange
        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.JANUARY, 15);
        Date date = calendar.getTime();
        Week week = new Week(date);
        DiscountCalculator calculator = new DiscountCalculator(week);

        // Act
        int discount = calculator.getDiscountPercentage();

        // Assert
        assertEquals(5, discount);
    }
}
