/**
 * @author S12 Group 3
 * @author BUHION, Deborah Rose P.
 * @author DIZON, Michaela Nicole P.
 * @author LIN, James Kevin S.
 * @author OAFALLAS, Kenneth Neil B.
 */

public class Driver {
    public static void main(String[] args) {

     //   Simulator simulator = new Simulator(0,0,0);
        View view = new View();
        Model model=new Model();
        Simulator simulator=new Simulator();
        Controller controller = new Controller(view, model, simulator);
        controller.getView().inputScreen();
    }
}
