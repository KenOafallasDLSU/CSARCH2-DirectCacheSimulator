/**
 * Simulator handles cache movement simulations and miss/hit counting
 * 
 * @author S12 Group 3
 * @author BUHION, Deborah Rose P.
 * @author DIZON, Michaela Nicole P.
 * @author LIN, James Kevin S.
 * @author OAFALLAS, Kenneth Neil B.
 */

import java.util.ArrayList;

public class Simulator{
  //input
  private String programFlow;
  private int cacheBlocks;
  private int mmBlocks;
  private int blockSize;

  //output
  private int[][] cacheMemory;
  private int[][] mainMemory;
  private int missCount;
  private int hitCount;

  public Simulator() {
    this.programFlow = "";
    this.cacheBlocks = 0;
    this.mmBlocks = 0;
    this.blockSize = 0;

    this.cacheMemory = new int[0][0];
    this.mainMemory = new int[0][0];
    this.missCount = 0;
    this.hitCount = 0;
  }

  /**
   * runSimulation sets the output values based on the current data in input values
   * 
   * @precondition - input values must be set beforehand
   */
  public void runSimulation() {
    this.cacheMemory = new int[this.cacheBlocks][this.blockSize];
    this.mainMemory = initMainMemory();
    this.missCount = 0;
    this.hitCount = 0;

    ArrayList<Integer> parsedFlow = parseProgramFlow();

    for(int i=0; i < parsedFlow.size(); i++){
      boolean isHit = true;
      int mainBlkAddress = parsedFlow.get(i);
      int cacheBlkAddress = mainBlkAddress % cacheBlocks;
      
      for(int k=0; k < blockSize; k++) {
        if(this.cacheMemory[cacheBlkAddress][k] != this.mainMemory[mainBlkAddress][k])
        {
          isHit = false;
          break;
        }
      }

      if(isHit) {
        this.hitCount++;
      } else{
        this.missCount++;
        this.cacheMemory[cacheBlkAddress] = this.mainMemory[mainBlkAddress];
      }
    }
  }

  /**
   * Initializes addresses in main memory
   */
  private int[][] initMainMemory() {
    int[][] filledMemory = new int[this.mmBlocks][this.blockSize];
    int mmCtr = 0;

    for(int i=0; i < this.mmBlocks; i++){
      for(int k=0; k < this.blockSize; k++){
        filledMemory[i][k] = mmCtr;
        mmCtr++;
      }
    }

    return filledMemory;
  }

  /**
   * Parses program flow into sequence of ints representing MM addresses
   */
  private ArrayList<Integer> parseProgramFlow() {
    ArrayList<Integer> parsedFlow = new ArrayList<Integer>();

    //logic here
    parsedFlow.add(23) //sample data
    parsedFlow.add(12)
    parsedFlow.add(9)
    parsedFlow.add(9)

    return parsedFlow;
  }


  //setters
  public void setProgramFlow(String programFlow) {
    this.programFlow = programFlow;
  }
  public void setCacheBlocks(int cacheBlocks) {
    this.cacheBlocks = cacheBlocks;
  }
  public void setMMBlocks(int mmBlocks) {
    this.mmBlocks = mmBlocks;
  }
  public void setBlockSize(int blockSize) {
    this.blockSize = blockSize;
  }

  //getters
  public String[][] getCacheSnapshot() {
    return this.cacheMemory;
  }
  public int getMissCount() {
    return this.missCount;
  }
  public int getHitCount() {
    return this.hitCount;
  }
}