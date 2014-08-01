import java.time.LocalDate;
public class Model {
    private Security security;
    private Holder holder;

    public Model(Security security, Holder holder) {
        this.security = security;
        this.holder = holder;
    }
    public String getHolderName(){
        return holder.getHolderName();
    }
    public void setSellingDate(LocalDate date){
        holder.setSellingDate(date);
    }
    public void setSellingPremium(double premium){
        holder.setSellingPremium(premium);
    }
    public double getHolderYTM(){
        return holder.getYTM();
    }
    public double getHolderProfit(){
        return holder.getTotalProfit();
    }
}
