import java.time.LocalDate;

public interface Holder {
    public final static int DEFAULT_SELLING_PREMIUM =0;
    public String getHolderName();
    public double getBuyingPrice();
    public double getSellingPrice();
    public void setSellingPrice();

    public LocalDate getBuyingDate();
    public LocalDate getSellingDate();
    public void setHoldingDays();
    public long getHoldingDays();
    public boolean dateCompare(LocalDate buyingDate,LocalDate sellingDate);
    public void setSellingDate(LocalDate sellingDate);

    public double getDailyYield();
    public double getTotalYield();

    public void setSellingPremium(Double sellingPremium);

    public double getTotalIncome();
    public double getTotalProfit();
    public double getYTM();
}
