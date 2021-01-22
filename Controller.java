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
    
    public Controller( View view, Model model) {
      this.view=view;
      this.model=model;
      this.view.addRunListener(new RunListener());
    }

    public View getView(){
      return this.view;
    }

    class RunListener implements ActionListener{
      public void actionPerformed(ActionEvent e){
        //System.out.println(view.flowArea.getText());
        //super.simulator=new Simulator(0,0,view.flowArea.getText(),0,0);
        //Controller.this.simulator.setProgramFlow(view.flowArea.getText());
        //Controller.this.simulator.printWords();

        model.setprogramFlow(view.flowArea.getText());
        model.setCacheAccessTime(Float.parseFloat(view.cat.getText()));
        model.setMMAccessTime(Float.parseFloat(view.mat.getText()));
        
        model.setMMBlocks(Integer.parseInt(view.mmInput.getText()));
        model.setBlockSize(Integer.parseInt(view.blockSize.getText()));

        if(view.cmWord.isSelected()){
          int cBlocks = Integer.parseInt(view.cmInput.getText())/Integer.parseInt(view.blockSize.getText());
          model.setCacheBlocks(cBlocks);

          
        }else{
          model.setCacheBlocks(Integer.parseInt(view.cmInput.getText()));
        }

        if(view.mmWord.isSelected()){
  //        System.out.println("mm word");
          int mBlocks = Integer.parseInt(view.mmInput.getText())/Integer.parseInt(view.blockSize.getText());
          model.setMMBlocks(mBlocks);
        }else{
          model.setMMBlocks(Integer.parseInt(view.mmInput.getText()));
        }

        
        System.out.println("Cache access: "+model.getCacheAccessTime());
        System.out.println("MM access: "+model.getMMAccessTime());
        System.out.println("Cache Blocks: "+model.getCacheBlocks());
        System.out.println("MM Blocks: "+model.getMMBlocks());
        System.out.println("Size of a Block: "+model.getBlockSize());
        
      }
    }

    class CacheBlockListener implements ActionListener{
      public void actionPerformed(ActionEvent e){

      }
    }
}
    
