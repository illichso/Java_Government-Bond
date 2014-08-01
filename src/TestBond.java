import junit.framework.TestCase;
import org.junit.Test;

import java.text.DecimalFormat;
import java.time.LocalDate;

public class TestBond extends TestCase {
    double parValue;
    LocalDate settlementDate;
    int bondYears;
    double yearCouponRate;
    DecimalFormat df;

    public TestBond() {
        parValue = 1000;
        settlementDate = LocalDate.of(1999,12,20);
        bondYears = 10;
        yearCouponRate=0.1;
        df = new DecimalFormat("###,###.000000");
    }

    @Test
    public void testCheckBondDataWithoutYearCouponRateOk(){
        try {
            Security bond = new Bond(parValue, settlementDate,bondYears);
            assertEquals("Par Value is wrong ", 1000.0, bond.getParValue());
            assertEquals("Days to Maturity are wrong", 10, bond.getBondDays());
            assertEquals("Year coupon rate is wrong",0.055,bond.getYearCouponRate());
            assertEquals("Day coupon rate is wrong", df.format(0.000151), df.format(bond.getDayCouponRate()));
            assertEquals("Yearly coupon payment is wrong",0.055,bond.getYearCouponRate());
            assertEquals("Yearly coupon payment is wrong", df.format(0.004583), df.format(bond.getMonthCouponRate()));
            assertEquals("Settlement date is wrong",LocalDate.of(1999,12,20),bond.getSettlementDate());
            assertEquals("Maturity date is wrong",LocalDate.of(2009,12,20),bond.getMaturityDate());
        } catch (IncorrectBondDataException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testCheckBondDataWithoutYearCouponRateWithOldData(){
        try {
            settlementDate = LocalDate.of(1961,07,01);
            bondYears = 15;
            Security bond = new Bond(parValue, settlementDate,bondYears);
            assertEquals("Par Value is wrong ", 1000.0, bond.getParValue());
            assertEquals("Settlement date is wrong",LocalDate.of(1961,07,01),bond.getSettlementDate());//1
            assertEquals("Maturity date is wrong",LocalDate.of(1976,07,01),bond.getMaturityDate());//2
            assertEquals("Days to Maturity are wrong", 15, bond.getBondDays());
            assertEquals("Year coupon rate is wrong",0.115,bond.getYearCouponRate());
            assertEquals("Day coupon rate is wrong", df.format(0.000315), df.format(bond.getDayCouponRate()));
            assertEquals("Yearly coupon payment is wrong",0.115,bond.getYearCouponRate());
            assertEquals("Yearly coupon payment is wrong", df.format(0.009583), df.format(bond.getMonthCouponRate()));

        } catch (IncorrectBondDataException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testCheckBondDataWithoutYearCouponRateNegativeBondYears(){
        try {
            Security bond = new Bond(-100, settlementDate,bondYears);
            fail("Should be IncorrectBondDataException");
        } catch (IncorrectBondDataException e) {
        }
    }
    @Test
    public void testCheckBondDataWithoutYearCouponRateNegativeParValue(){
        try {
            Security bond = new Bond(parValue, settlementDate,-1);
            fail("Should be IncorrectBondDataException");
        } catch (IncorrectBondDataException e) {
        }
    }
    @Test
    public void testCheckBondDataWithYearCouponRateOk(){
        try {
            Security bond = new Bond(parValue, settlementDate,bondYears,yearCouponRate);
            assertEquals("Par Value is wrong ", 1000.0, bond.getParValue());
            assertEquals("Days to Maturity are wrong", 10, bond.getBondDays());
            assertEquals("Year coupon rate is wrong",0.1,bond.getYearCouponRate());
            assertEquals("Day coupon rate is wrong", df.format(0.000274), df.format(bond.getDayCouponRate()));
            assertEquals("Yearly coupon payment is wrong",0.1,bond.getYearCouponRate());
            assertEquals("Yearly coupon payment is wrong", df.format(0.008333), df.format(bond.getMonthCouponRate()));
            assertEquals("Settlement date is wrong",LocalDate.of(1999,12,20),bond.getSettlementDate());
            assertEquals("Maturity date is wrong",LocalDate.of(2009,12,20),bond.getMaturityDate());
        } catch (IncorrectBondDataException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testCheckBondDataWithYearCouponRateNegativeBondYears(){
        try {
            Security bond = new Bond(parValue, settlementDate,-1,yearCouponRate);
            fail("Should be IncorrectBondDataException");
        } catch (IncorrectBondDataException e) {
        }
    }
    @Test
    public void testCheckBondDataWithYearCouponRateNegativeParValue(){
        try {
            Security bond = new Bond(-100, settlementDate,bondYears,yearCouponRate);
            fail("Should be IncorrectBondDataException");
        } catch (IncorrectBondDataException e) {
        }
    }
    @Test
    public void testCheckBondDataWithYearCouponRateNegativeYearCouponRate(){
        try {
            Security bond = new Bond(parValue, settlementDate,bondYears,-5);
            fail("Should be IncorrectBondDataException");
        } catch (IncorrectBondDataException e) {
        }
    }
    public void tearDown(  ) {
    }
}
