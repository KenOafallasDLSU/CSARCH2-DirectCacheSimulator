/**
 * @author S12 Group 3
 * @author BUHION, Deborah Rose P.
 * @author DIZON, Michaela Nicole P.
 * @author LIN, James Kevin S.
 * @author OAFALLAS, Kenneth Neil B.
 */

import java.io.File;

public class Model {
  private Simulator simulator;
  private Calculator calculator;

  //Simulator and Calculator attributes
  private float cacheAccessTime;
  private float mmAccessTime;

  //Simulator attributes
  private String programFlow;
  private int cacheBlocks;
  private int mmBlocks;
  private int blockSize;

  //Calculator attributes
  private int hitCount;
  private int missCount; 

  public Model(){
  }

  public void setCacheAccessTime(int cacheAccessTime){
    this.cacheAccessTime=cacheAccessTime;
  }

  public void setmmAccessTime(int mmAccessTime){
    this.mmAccessTime=mmAccessTime;
  }

  public void setprogramFlow(String programFlow){
    this.programFlow=programFlow;
  }

  public void setCacheBlocks(int cacheBlocks){
    this.cacheBlocks=cacheBlocks;
  }

  public void setMMBlocks(int mmBlocks){
    this.mmBlocks=mmBlocks;
  }

  public void setBlockSize(int blockSize){
    this.blockSize=blockSize;
  }

  public float getCacheAccessTime(){
    return this.cacheAccessTime;
  }

  public float getMMAccessTime(){
    return this.mmAccessTime;
  }

  public String getProgramFlow(){
    return this.programFlow;
  }

  public int getCacheBlocks(){
    return this.cacheBlocks;
  }

  public int getBlockSize(){
    return this.blockSize;
  }
  /*
  constructor Calculator(cacheAccessTime, mmAccessTime, hitCount, missCount)
float getMissPenalty()
float getAverageTime()
float getTotalTime()
  */
  
  public void setHitCount(int hitCount){
    this.hitCount=hitCount;
  }

  public void setMissCount(int missCount){
    this.missCount=missCount;
  }

  public int getHitCount(){
    return this.hitCount;
  }

  public int getMissCount(){
    return this.missCount;
  }

}
