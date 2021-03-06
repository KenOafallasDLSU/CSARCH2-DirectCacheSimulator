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
        boolean validOutput = true;

        model.setprogramFlow(view.flowArea.getText());

        try{
          if(Float.parseFloat(view.cat.getText())>0){
            
            model.setCacheAccessTime(Float.parseFloat(view.cat.getText()));
          }else{
            model.setCacheAccessTime(1);
            throw new Exception("Cache access time must be positive number please");
          }

          if(Float.parseFloat(view.mat.getText())>0){
            model.setMMAccessTime(Float.parseFloat(view.mat.getText()));
          }else{
            model.setCacheAccessTime(1);
            model.setMMAccessTime(1);
            throw new Exception("MM access time must be positive number please");
          }
            
        }catch(NumberFormatException exception){
          showMessageDialog(null, "Number in cache access time and memory access time please");
          System.out.println("Number in cache access time and memory access time please");
          validOutput = false;
        }
        catch(Exception except){
          showMessageDialog(null, "Error occured: "+except.getMessage());
          validOutput = false;
          //except.printStackTrace();
        }

        try{
          if(Integer.parseInt(view.blockSize.getText())>0){
            model.setBlockSize(Integer.parseInt(view.blockSize.getText()));
          }else{
            model.setBlockSize(1);
            throw new Exception("Block size must be positive number please");
          }

        }catch(NumberFormatException exception){
          showMessageDialog(null, "int in block size please");
          System.out.println("int in block size please");
          validOutput = false;
        }
        catch(Exception except){
          showMessageDialog(null, "Error occured: "+except.getMessage());
          validOutput = false;
      //    except.printStackTrace();
        }
        
        try{
          if(Integer.parseInt(view.cmInput.getText())>0){
            if(view.cmWord.isSelected()){
              int cBlocks = Integer.parseInt(view.cmInput.getText())/Integer.parseInt(view.blockSize.getText());
              model.setCacheBlocks(cBlocks);
            }else{
              model.setCacheBlocks(Integer.parseInt(view.cmInput.getText()));
            }
          }else{
            model.setCacheBlocks(1);
            model.setMMBlocks(1);
            throw new Exception("Cache size should be positive please");
          }

          if(Integer.parseInt(view.mmInput.getText())>0){
            if(view.mmWord.isSelected()){
              //System.out.println("mm word");
              int mBlocks = Integer.parseInt(view.mmInput.getText())/Integer.parseInt(view.blockSize.getText());
              model.setMMBlocks(mBlocks);
            }else{
              model.setMMBlocks(Integer.parseInt(view.mmInput.getText()));
            }
          }else{
            model.setMMBlocks(1);
            throw new Exception("MM size should be positive please");
          }

        }
        catch(NumberFormatException except){
          showMessageDialog(null, "Number in cache size and memory size please");
          System.out.println("Number in cache size and memory size please");
          validOutput = false;
        }
        catch(ArithmeticException except){
          showMessageDialog(null, "Make sure the block size is not zero or blank please");
          System.out.println("Make sure the block size is not zero or blank please");
          validOutput = false;
        }
        catch(Exception except){
          showMessageDialog(null, "Unknown error occured: "+except.getMessage());
          validOutput = false;
        //  except.printStackTrace();
        }

        model.setIsAddress(view.addressInput.isSelected());
        model.setIsCont(view.isCont.isSelected());
        model.setIsLoadThrough(view.isLoadThrough.isSelected());
        
        try{
          model.runSimulationSequence();
          model.setCalculator();
        }catch(ArithmeticException except){
          showMessageDialog(null, "Make sure the block size is not zero or blank please");
          System.out.println("Make sure the block size is not zero or blank please");
          validOutput = false;
        }
        catch(Exception except){
          showMessageDialog(null, "Error occured in calculating: "+except.getMessage());
          except.printStackTrace();
          validOutput = false;
        }

        if(validOutput) {
          String putInflowArea=
          "Miss count: "+model.getMissCount()+"\n"+
          "Hit count: "+model.getHitCount()+"\n"+
          "Miss penalty: "+model.getMissPenalty()+" ns\n"+
          "Average memory access time: "+model.getAverageTime()+" ns\n"+
          "Total memory access time: "+model.getTotalTime()+" ns\n"+"\n"+
          model.getCacheSnapshot();
          view.flowArea.setText(putInflowArea);
          view.outputScreen();

          validOutput = true;
        } else{
          view.cat.setText("");
          view.mat.setText("");
          view.flowArea.setText("");
          view.mmInput.setText("");
          view.blockSize.setText("");
          view.cmInput.setText("");

          validOutput = true;
        }
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
              "Average memory access time: "+model.getAverageTime()+" ns"+System.lineSeparator()+
              "Total memory access time: "+model.getTotalTime()+" ns"+System.lineSeparator()+System.lineSeparator()+
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
    
