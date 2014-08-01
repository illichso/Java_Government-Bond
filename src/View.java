import java.text.DecimalFormat;
public class View {
    public View() {
    }
    public void printFinResults(String holderName, String text, double YTM, double profit){
        DecimalFormat df = new DecimalFormat("###,###.00");
        System.out.println("Financial results for " + holderName+ " ("+text+")");
        System.out.println("   YTM is: " + df.format(YTM)
                +"%; total profit is: "+df.format(profit)+" GBP.");
    }
}
