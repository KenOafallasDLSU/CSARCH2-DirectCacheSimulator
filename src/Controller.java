/**
 * @author S12 Group 3
 * @author BUHION, Deborah Rose P.
 * @author DIZON, Michaela Nicole P.
 * @author LIN, James Kevin S.
 * @author OAFALLAS, Kenneth Neil B.
 */

import java.awt.event.*;
import java.io.File;
import java.io.IOException; 
import java.io.FileWriter; 
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import javax.swing.JFileChooser;
import static javax.swing.JOptionPane.showMessageDialog;
public class Controller {

    private View view;
    private Model model;
    
    public Controller( View view, Model model) {
      this.view=view;
      this.model=model;
      this.view.addRunListener(new RunListener());
      this.view.addResetListener(new ResetListener());
      this.view.addExportListener(new ExportListener());
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

        try{
          model.setCacheAccessTime(Float.parseFloat(view.cat.getText()));
          model.setMMAccessTime(Float.parseFloat(view.mat.getText()));
        }catch(NumberFormatException exception){
          
          showMessageDialog(null, "Number in cache access time and memory access time please");
          System.out.println("Number in cache access time and memory access time please");
        }
        catch(Exception except){
          showMessageDialog(null, "Unknown error occured");
          except.printStackTrace();
        }

        try{
          model.setBlockSize(Integer.parseInt(view.blockSize.getText()));
        }catch(NumberFormatException exception){
          showMessageDialog(null, "int in block size please");
          System.out.println("int in block size please");
        }
        catch(Exception except){
          showMessageDialog(null, "Unknown error occured");
          except.printStackTrace();
        }
        
        

        try{
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
        }
        catch(NumberFormatException except){
          showMessageDialog(null, "Number in cache size and memory size please");
          System.out.println("Number in cache size and memory size please");
        }
        catch(ArithmeticException except){
          showMessageDialog(null, "Make sure the block size is not zero or blank please");
          System.out.println("Make sure the block size is not zero or blank please");
        }
        catch(Exception except){
          showMessageDialog(null, "Unknown error occured");
          except.printStackTrace();
        }
        


        model.setIsAddress(view.addressInput.isSelected());
        model.setIsCont(view.isCont.isSelected());
        model.setIsLoadThrough(view.isLoadThrough.isSelected());

        System.out.println("Cache access: "+model.getCacheAccessTime());
        System.out.println("MM access: "+model.getMMAccessTime());
        System.out.println("Cache Blocks: "+model.getCacheBlocks());
        System.out.println("MM Blocks: "+model.getMMBlocks());
        System.out.println("Size of a Block: "+model.getBlockSize());
        model.runSimulationSequence();
        System.out.println("snapshot: "+model.getCacheSnapshot());
        System.out.println("is address: "+model.getIsAddress());
        System.out.println("is cont: "+model.getIsCont());
        System.out.println("is load through: "+model.getIsLoadThrough());
        

        try{
          model.setCalculator();
        }catch(ArithmeticException except){
          showMessageDialog(null, "Make sure the block size is not zero or blank please");
          System.out.println("Make sure the block size is not zero or blank please");
        }
        catch(Exception except){
          showMessageDialog(null, "Unknown error occured in calculating");
          except.printStackTrace();
        }
        


        System.out.println("Miss count: "+model.getMissCount());
        System.out.println("Hit count: "+model.getHitCount());

        System.out.println("Miss penalty: "+model.getMissPenalty());
        System.out.println("Avergae time: "+model.getAverageTime());
        System.out.println("Total time: "+model.getTotalTime());

        String putInflowArea=
          "Miss count: "+model.getMissCount()+"\n"+
          "Hit count: "+model.getHitCount()+"\n"+
          "Miss penalty: "+model.getMissPenalty()+" ns\n"+
          "Average time: "+model.getAverageTime()+" ns\n"+
          "Total time: "+model.getTotalTime()+" ns\n"+"\n"+
          model.getCacheSnapshot();
        view.flowArea.setText(putInflowArea);





        view.outputScreen();

      }
    }

    class ResetListener implements ActionListener{
      public void actionPerformed(ActionEvent e){
       
        view.cat.setText("");
        view.mat.setText("");
        view.flowArea.setText("");
        view.mmInput.setText("");
        view.blockSize.setText("");
        view.cmInput.setText("");
        view.inputScreen();

      }
    }

    class ExportListener implements ActionListener{
      public void actionPerformed(ActionEvent e){
       
        try {
          SimpleDateFormat sdf = new SimpleDateFormat("MM_dd_yyyy_h_mm_ss_a");
          Timestamp timestamp = new Timestamp(System.currentTimeMillis());
          System.out.println(sdf.format(timestamp));
          

          JFileChooser f = new JFileChooser();
          f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 
          int result=f.showSaveDialog(null);
          
          if(result == JFileChooser.APPROVE_OPTION){
            System.out.println("my file is: "+f.getSelectedFile());

            String filename=f.getSelectedFile()+"\\"+"Result_Log_"+sdf.format(timestamp)+".txt";
            System.out.println("file is: "+filename);

            File myObj = new File(filename);
            if (myObj.createNewFile()) {
              System.out.println("File created: " + myObj.getName());
            } else {
              System.out.println("File already exists.");
            }
            FileWriter myWriter = new FileWriter(filename);
            String toBeWritten=
              "Miss count: "+model.getMissCount()+System.lineSeparator()+
              "Hit count: "+model.getHitCount()+System.lineSeparator()+
              "Miss penalty: "+model.getMissPenalty()+" ns"+System.lineSeparator()+
              "Average time: "+model.getAverageTime()+" ns"+System.lineSeparator()+
              "Total time: "+model.getTotalTime()+" ns"+System.lineSeparator()+System.lineSeparator()+
              model.getCacheSnapshot();
            myWriter.write(toBeWritten);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
          }
             
        } catch (IOException error) {
          showMessageDialog(null, "Unknown error occured in file exporting");
          System.out.println("An error occurred.");
          error.printStackTrace();
        }

      }
    }


}
    
