import java.time.LocalDate;

public interface Security{
    public final static int DEFAULT_CALENDAR_DAYS_IN_YEAR =365;
    public final static double DEFAULT_BOND_YEAR_COUPON_RATE = 0.1;

    public double getParValue();
    public LocalDate getSettlementDate();
    public void setMaturityDate();
    public LocalDate getMaturityDate();
    public long getBondYears();
    public void setBondDays();
    public long getBondDays();
    public void setYearCouponRate();
    public double getYearCouponRate();
    public void setMonthCouponRate();
    public double getMonthCouponRate();
    public void setDayCouponRate();
    public double getDayCouponRate();
    public boolean checkBondData(double parValue, int years);
}
