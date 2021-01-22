/**
 * @author S12 Group 3
 * @author BUHION, Deborah Rose P.
 * @author DIZON, Michaela Nicole P.
 * @author LIN, James Kevin S.
 * @author OAFALLAS, Kenneth Neil B.
 */

 /*
simulation is a module with the functions

constructor Simulator(cacheAccessTime, mmAccessTime, programFlow, cacheBlocks, mmBlocks, blockSize)
array getCacheSnapsot()
int getMissCount()
int getHitCount()
*/

import java.io.File;

public class Simulator{
  private float cacheAccessTime;
  private float mmAccessTime;
  private String programFlow;
  private int cacheBlocks;
  private int mmBlocks;
  private int blockSize;
  
  public Simulator(float cacheAccessTime, float mmAccessTime, String programFlow, int cacheBlocks, int mmBlocks, int blockSize) {
    this.cacheAccessTime=cacheAccessTime;
    this.mmAccessTime=mmAccessTime;
    //this.programFlow=Arrays.copyOf(programFlow, programFlow.length);
    this.programFlow= new String(programFlow);
    this.cacheBlocks=cacheBlocks;
    this.mmBlocks=mmBlocks;
    this.blockSize=blockSize;
  }

  public void printWords(String[] words){
    System.out.println(words[0]);
  }


}
