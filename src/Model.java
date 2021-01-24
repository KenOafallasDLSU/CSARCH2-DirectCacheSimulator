/**
 * @author S12 Group 3
 * @author BUHION, Deborah Rose P.
 * @author DIZON, Michaela Nicole P.
 * @author LIN, James Kevin S.
 * @author OAFALLAS, Kenneth Neil B.
 */

import java.io.File;
import java.util.Arrays;

public class Model {
  private Simulator sim;
  private Calculator calculator;

  //Simulator and Calculator attributes
  private float cacheAccessTime;
  private float mmAccessTime;

  //Simulator attributes
  private String programFlow;
  private int cacheBlocks;
  private int mmBlocks;
  private int blockSize;

  private String cacheSnapshot;
  private boolean isAddress;

  //Calculator attributes
  private int hitCount;
  private int missCount; 

  public Model(){
    this.sim = new Simulator();
  }

  /**
   * Runs all needed functions for simulation and sets the output attribues
   * 
   * @precondition - programFlow, cacheBlocks, mmBlocks, blockSize 
   *                 are already set in Model instance
   */
  public void runSimulationSequence() {
    this.sim.setProgramFlow(this.programFlow);
    this.sim.setCacheBlocks(this.cacheBlocks);
    this.sim.setMMBlocks(this.mmBlocks);
    this.sim.setBlockSize(this.blockSize);

    this.sim.runSimulation();

    this.hitCount = this.sim.getHitCount();
    this.missCount = this.sim.getMissCount();
    this.cacheSnapshot = this.sim.getCacheSnapshot();
  }

  public void setCacheAccessTime(float cacheAccessTime){
    this.cacheAccessTime=cacheAccessTime;
  }

  public void setMMAccessTime(float mmAccessTime){
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

  public void setIsAddress(boolean isAddress){
    this.isAddress=isAddress;
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

  public int getMMBlocks(){
    return this.mmBlocks;
  }

  public int getBlockSize(){
    return this.blockSize;
  }

  public String getCacheSnapshot() {
    return this.cacheSnapshot;
  }

  public boolean getIsAddress(){
    return this.isAddress;
  }
  
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
