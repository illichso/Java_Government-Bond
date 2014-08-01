import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class BondHolder implements Holder {
    private static int holderCount=0;
    private String holderName;
    private Security bond;
    private double buyingPrice;
    private double sellingPrice;
    private double sellingPremium;
    private LocalDate buyingDate;
    private LocalDate sellingDate;
    private long holdingDays;

    public BondHolder(Security security){
        holderCount++;
        holderName="Holder #"+holderCount;
        this.bond = security;
        this.buyingPrice = this.sellingPrice = bond.getParValue();
        this.buyingDate = bond.getSettlementDate();
        this.sellingDate = bond.getMaturityDate();
        sellingPremium = DEFAULT_SELLING_PREMIUM;
        setHoldingDays();
    }
    public BondHolder(Security security, double premium, LocalDate buyingDate)throws IncorrectHolderDataException {
        if(dateCompare(buyingDate,security.getMaturityDate())) {
            holderCount++;
            holderName="Holder #"+holderCount;
            this.bond = security;
            buyingPrice = security.getParValue() * (1 + premium);
            this.sellingPrice = bond.getParValue();
            this.buyingDate = buyingDate;
            this.sellingDate = bond.getMaturityDate();
            sellingPremium = DEFAULT_SELLING_PREMIUM;
            setHoldingDays();
        }else{
            throw  new IncorrectHolderDataException("Transaction data is incorrect!");
        }
    }
    @Override
    public String getHolderName() {
        return holderName;
    }
    @Override
    public double getBuyingPrice(){
        return buyingPrice;
    }
    @Override
    public double getSellingPrice() {
        return sellingPrice;
    }
    @Override
    public LocalDate getBuyingDate() {
        return buyingDate;
    }
    @Override
    public LocalDate getSellingDate() {
        return sellingDate;
    }

    @Override
    public void setHoldingDays() {
        holdingDays = ChronoUnit.DAYS.between(buyingDate, sellingDate);
    }

    @Override
    public boolean dateCompare(LocalDate buyingDate,LocalDate sellingDate) {
        return sellingDate.compareTo(buyingDate)>0;
    }
    @Override
    public long getHoldingDays() {
        return holdingDays;
    }
    @Override
    public double getDailyYield() {
        return bond.getParValue()*bond.getDayCouponRate();
    }
    @Override
    public double getTotalYield() {
        return getHoldingDays()*getDailyYield();
    }
    @Override
    public void setSellingDate(LocalDate sellingDate) {
        this.sellingDate=sellingDate;
        setHoldingDays();
    }
    @Override
    public void setSellingPremium(Double sellingPremium) {
        this.sellingPremium=sellingPremium;
        setSellingPrice();
    }
    @Override
    public void setSellingPrice() {
        sellingPrice=bond.getParValue()*(1+sellingPremium);
    }
    @Override
    public double getTotalIncome() {
        return getTotalYield()+sellingPrice;
    }
    @Override
    public double getTotalProfit() {
        return getTotalIncome()-buyingPrice;
    }
    @Override
    public double getYTM() {
        double result=(getDailyYield()+((bond.getParValue()-buyingPrice)/holdingDays))/
                ((bond.getParValue()+buyingPrice)/2);
        return (Math.pow(1+result,365)-1)*100;
    }
}
