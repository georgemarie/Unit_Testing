package test;
import JFree.DiscountManager;
import JFree.IDiscountCalculator;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.*;

public class DiscountManagerTest {

    Mockery mockingContext;
    IDiscountCalculator mockedDependency;

    @Before
    public void setup(){
        mockingContext = new Mockery();
        mockedDependency = mockingContext.mock(IDiscountCalculator.class);
    }

    @Test
    public void testCalculatePriceWhenDiscountsSeasonIsFalse() throws Exception {
        // Arrange
        boolean isDiscountsSeason = false;
        double originalPrice = 100.0;
        double expectedPrice = 100.0;
        mockingContext.checking(new Expectations(){});
        DiscountManager discountManager = new DiscountManager(isDiscountsSeason, mockedDependency);

        // Act
        double actualPrice = discountManager.calculatePriceAfterDiscount(originalPrice);

        // Assert
        mockingContext.assertIsSatisfied();
        assertEquals(expectedPrice, actualPrice, 0.01);
    }

    @Test
    public void testCalculatePriceWhenDiscountsSeasonIsTrueAndSpecialWeekIsTrue() throws Exception {
        // Arrange
        boolean isDiscountsSeason = true;
        double originalPrice = 100.0;
        double expectedPrice = 80.0;
        mockingContext.checking(new Expectations() {{
            oneOf(mockedDependency).isTheSpecialWeek();
            will(returnValue(true));
        }});
        DiscountManager discountManager = new DiscountManager(isDiscountsSeason, mockedDependency);

        // Act
        double actualPrice = discountManager.calculatePriceAfterDiscount(originalPrice);

        // Assert
        mockingContext.assertIsSatisfied();
        assertEquals(expectedPrice, actualPrice, 0.01);
    }

    @Test
    public void testCalculatePriceWhenDiscountsSeasonIsTrueAndSpecialWeekIsFalseEvenWeek() throws Exception {
        // Arrange
        boolean isDiscountsSeason = true;
        double originalPrice = 100.0;
        double expectedPrice = 100.0 - (100.0 * 0.07); // 7% discount for even weeks

        Mockery mockingContext = new Mockery();
        IDiscountCalculator mockedDependency = mockingContext.mock(IDiscountCalculator.class);
        mockingContext.checking(new Expectations() {{
            oneOf(mockedDependency).isTheSpecialWeek();
            will(returnValue(false));
            oneOf(mockedDependency).getDiscountPercentage();
            will(returnValue(7));
        }});
        DiscountManager discountManager = new DiscountManager(isDiscountsSeason, mockedDependency);

        // Act
        double actualPrice = discountManager.calculatePriceAfterDiscount(originalPrice);

        // Assert
        mockingContext.assertIsSatisfied();
        assertEquals(expectedPrice, actualPrice, 0.01);
    }

    @Test
    public void testCalculatePriceWhenDiscountsSeasonIsTrueAndSpecialWeekIsFalseOddWeek() throws Exception {
        // Arrange
        boolean isDiscountsSeason = true;
        double originalPrice = 100.0;
        double expectedPrice = 100.0 - (100.0 * 0.05); // 5% discount for odd weeks

        Mockery mockingContext = new Mockery();
        IDiscountCalculator mockedDependency = mockingContext.mock(IDiscountCalculator.class);
        mockingContext.checking(new Expectations() {{
            oneOf(mockedDependency).isTheSpecialWeek();
            will(returnValue(false));
            oneOf(mockedDependency).getDiscountPercentage();
            will(returnValue(5));
        }});
        DiscountManager discountManager = new DiscountManager(isDiscountsSeason, mockedDependency);

        // Act
        double actualPrice = discountManager.calculatePriceAfterDiscount(originalPrice);

        // Assert
        mockingContext.assertIsSatisfied();
        assertEquals(expectedPrice, actualPrice, 0.01);
    }
}
