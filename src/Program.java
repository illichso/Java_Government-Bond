import java.time.LocalDate;

public class Program {
    public static void main(String[] args) {
        try {
            LocalDate date = LocalDate.of(1988, 12, 25);
            double bondParValue = 300000;
            int bondYears = 30;
            Security bond = new Bond(bondParValue,date,bondYears);
            Holder holder1 = new BondHolder(bond);
            Model model1 = new Model(bond,holder1);
            View view = new View();
            Controller controller = new Controller(model1,view);
            controller.updateView("if the bond will not be sold");

            LocalDate sellingDate = LocalDate.of(2002,04,12);
            controller.setSellingDate(sellingDate);
            double premium = -0.13;
            controller.setSellingPremium(premium);
            controller.updateView("after selling the bond");

            Holder holder2 = new BondHolder(bond,premium,sellingDate);
            Model model2 = new Model(bond,holder2);
            Controller controller1 = new Controller(model2,view);
            controller1.updateView("after purchasing the bond");

        } catch (IncorrectBondDataException e) {
            e.printStackTrace();
        } catch (IncorrectHolderDataException e) {
            e.printStackTrace();
        }
    }
}
