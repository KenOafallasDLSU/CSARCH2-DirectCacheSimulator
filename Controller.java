/**
 * @author S12 Group 3
 * @author BUHION, Deborah Rose P.
 * @author DIZON, Michaela Nicole P.
 * @author LIN, James Kevin S.
 * @author OAFALLAS, Kenneth Neil B.
 */

import java.awt.event.*;

public class Controller {

    private View view;
    private Model model;
    private Simulator simulator;
    
    public Controller( View view, Model model) {
      this.view=view;
      this.view.addRunListener(new RunListener());
    }

    public View getView(){
      return this.view;
    }

    class RunListener implements ActionListener{
      public void actionPerformed(ActionEvent e){
        System.out.println(view.flowArea.getText());
        //super.simulator=new Simulator(0,0,view.flowArea.getText(),0,0);
      }
    }
}
    
