import junit.framework.TestCase;
import org.junit.Test;

import java.text.DecimalFormat;
import java.time.LocalDate;

public class TestBondHolder extends TestCase {
    private LocalDate buyingDate;
    LocalDate settlementDate;
    double premium;
    DecimalFormat df;
    double parValue;
    int bondYears;
    private Security bond;

    @Test
    public void testHolderBondWithoutBuyingOk(){

        try {
            parValue=100;
            settlementDate=LocalDate.of(1999,12,20);
            bondYears=10;
            df = new DecimalFormat("###,###.000000");
            bond = new Bond(parValue, settlementDate,bondYears);
            Holder  holder = new BondHolder(bond);
            assertEquals("Buying price is wrong",df.format(100.0),df.format(holder.getBuyingPrice()));
            assertEquals("Selling price is wrong",df.format(100.0),df.format(holder.getSellingPrice()));
            assertEquals("Buying date is wrong",LocalDate.of(1999, 12, 20),holder.getBuyingDate());
            assertEquals("Selling date is wrong",LocalDate.of(2009, 12, 20),holder.getSellingDate());
            assertEquals("Holding days are wrong",3653,holder.getHoldingDays());
            assertEquals("Daily yield is wrong",df.format(0.015068),df.format(holder.getDailyYield()));
            assertEquals("Total yield is wrong",df.format(55.045205),df.format(holder.getTotalYield()));
            assertEquals("Total income is wrong",df.format(155.045205),df.format(holder.getTotalIncome()));
            assertEquals("Total profit is wrong",df.format(55.045205),df.format(holder.getTotalProfit()));
            assertEquals("YTM is wrong",df.format(5.653624),df.format(holder.getYTM()));
        } catch (IncorrectBondDataException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testHolderBondWithBuyingOk() {
        try {
            parValue=100;
            settlementDate=LocalDate.of(1999,12,20);
            bondYears=10;
            df = new DecimalFormat("###,###.000000");
            bond = new Bond(parValue, settlementDate,bondYears);
            premium = -0.1;
            buyingDate = LocalDate.of(2002,03,04);
            Holder  holder = new BondHolder(bond,premium,buyingDate);
            assertEquals("Buying price is wrong",df.format(90.0),df.format(holder.getBuyingPrice()));
            assertEquals("Selling price is wrong",df.format(100.0),df.format(holder.getSellingPrice()));
            assertEquals("Buying date is wrong",LocalDate.of(2002, 03, 04),holder.getBuyingDate());
            assertEquals("Selling date is wrong",LocalDate.of(2009, 12, 20),holder.getSellingDate());
            assertEquals("Holding days are wrong",2848,holder.getHoldingDays());
            assertEquals("Daily yield is wrong",df.format(0.015068),df.format(holder.getDailyYield()));
            assertEquals("Total yield is wrong",df.format(42.915068),df.format(holder.getTotalYield()));
            assertEquals("Total income is wrong",df.format(142.915068),df.format(holder.getTotalIncome()));
            assertEquals("Total profit is wrong",df.format(52.915068),df.format(holder.getTotalProfit()));
            assertEquals("YTM is wrong",df.format(7.398743),df.format(holder.getYTM()));
        } catch (IncorrectHolderDataException e) {
            e.printStackTrace();
        } catch (IncorrectBondDataException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testHolderDates(){
        try{
            parValue=100;
            settlementDate=LocalDate.of(1999,12,20);
            bondYears=10;
            df = new DecimalFormat("###,###.000000");
            bond = new Bond(parValue, settlementDate,bondYears);
            premium = -0.1;
            buyingDate = LocalDate.of(2002,03,04);
            Holder  holder = new BondHolder(bond,premium,LocalDate.of(2012,12,20));
            fail("Dates should be incorrect");

    } catch (IncorrectBondDataException e) {
    } catch (IncorrectHolderDataException e) {
        }

    }
    public void tearDown(  ) {
    }
}
