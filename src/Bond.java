import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class Bond implements Security{
    private double parValue;
    private LocalDate settlementDate;
    private LocalDate maturityDate;
    private long bondYears;
    private long bondDays;
    private double yearCouponRate;
    private double monthCouponRate;
    private double dayCouponRate;

    public Bond(double parValue,LocalDate settlementDate,int bondYears) throws IncorrectBondDataException {
        if(checkBondData(parValue,bondYears)){
            this.parValue = parValue;
            this.settlementDate = settlementDate;
            this.bondYears = bondYears;
            setMaturityDate();
            setBondDays();
            setYearCouponRate();
            setMonthCouponRate();
            setDayCouponRate();
        }else {
            throw  new IncorrectBondDataException("Bond data is incorrect!");
        }
    }
    public Bond(double parValue,LocalDate settlementDate,int bondYears, double yearCouponRate) throws IncorrectBondDataException {
        if(checkBondData(parValue,bondYears)&& yearCouponRate >=0){
            this.parValue = parValue;
            this.settlementDate = settlementDate;
            this.bondYears = bondYears;
            setMaturityDate();
            setBondDays();
            this.yearCouponRate = yearCouponRate;
            setMonthCouponRate();
            setDayCouponRate();
        }else {
            throw  new IncorrectBondDataException("Bond data is incorrect!");
        }
    }
    @Override
    public boolean checkBondData(double parValue, int bondYears){
        return parValue>0 && bondYears>0;
    }
    @Override
    public double getParValue() {
        return parValue;
    }
    @Override
    public LocalDate getSettlementDate(){
        return settlementDate;
    }
    @Override
    public void setMaturityDate() {
        maturityDate = settlementDate.plusYears(bondYears);
    }
    @Override
    public LocalDate getMaturityDate() {
        return maturityDate;
    }
    @Override
    public long getBondYears() {
        return bondYears;
    }
    @Override
    public void setBondDays() {
        if(maturityDate==null){
            setMaturityDate();
        }
        bondDays=ChronoUnit.DAYS.between(settlementDate, maturityDate);
    }
    @Override
    public long getBondDays() {
        return bondDays/DEFAULT_CALENDAR_DAYS_IN_YEAR;
    }
    @Override
    public void setYearCouponRate() {
        boolean found=false;
        BufferedReader inputStream = null;
        try{ inputStream = new BufferedReader (new FileReader("Gilts.csv"));
            String bufStr="";
            LocalDate interestDate;
            LocalDate firstFileDate=settlementDate;
            boolean foundFirstDate=false;
            while((bufStr=inputStream.readLine())!=null){
                try {
                    String[] strParts = bufStr.split(",");
                    interestDate = LocalDate.parse(strParts[0], DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH));
                    if(!foundFirstDate){
                        firstFileDate=interestDate;
                        foundFirstDate=true;
                    }
                    if(settlementDate.equals(interestDate) || settlementDate.isBefore(interestDate) ){
                        if(settlementDate.isBefore(firstFileDate)){
                            System.out.println("Bond settlement date is earlier than DB bond dates. " +
                                    "The first bond date was taken from the file for calculations with interest rate " + strParts[1] + "%.");
                        }
                        yearCouponRate=Double.parseDouble(strParts[1])/100;
                        found=true;
                        break;
                    }
                }
                catch (DateTimeParseException e){
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally { if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        }
        if(!found){
            System.out.println("Interest Rate was not found in DB. Default value of " +DEFAULT_BOND_YEAR_COUPON_RATE +"% was applied.");
            this.yearCouponRate = DEFAULT_BOND_YEAR_COUPON_RATE;
        }
    }
    @Override
    public double getYearCouponRate() {
        return yearCouponRate;
    }
    @Override
    public void setMonthCouponRate() {
        if(yearCouponRate==0){
            setYearCouponRate();
        }
        monthCouponRate=yearCouponRate/12;
    }
    @Override
    public double getMonthCouponRate() {
        return monthCouponRate;
    }
    @Override
    public void setDayCouponRate() {
        if(yearCouponRate==0){
            setYearCouponRate();
        }
            dayCouponRate=yearCouponRate/DEFAULT_CALENDAR_DAYS_IN_YEAR;
    }
    @Override
    public double getDayCouponRate() {
        return dayCouponRate;
    }
}
