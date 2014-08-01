import java.time.LocalDate;

public class Controller {
    private Model model;
    private View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    public void setSellingDate(LocalDate date){
        model.setSellingDate(date);
    }
    public void setSellingPremium(double premium){
        model.setSellingPremium(premium);
    }
    public void updateView(String text){
        view.printFinResults(model.getHolderName(),text,model.getHolderYTM(),model.getHolderProfit());
    }
}
