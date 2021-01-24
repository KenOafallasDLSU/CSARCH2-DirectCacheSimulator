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
import java.util.Scanner;

public class Simulator{
  //input attributes
  private String programFlow;
  private int cacheBlocks;
  private int mmBlocks;
  private int blockSize;

  //output attributes
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
    this.cacheMemory = initCacheMemory();
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
   * Initializes addresses in cache to -1 guard value, indicating empty
   */
  private int[][] initCacheMemory() {
    int[][] filledCache = new int[this.cacheBlocks][this.blockSize];

    for(int i=0; i < this.cacheBlocks; i++){
      for(int k=0; k < this.blockSize; k++){
        filledCache[i][k] = -1;
      }
    }

    return filledCache;
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
    ArrayList<Integer> loopAddStore = new ArrayList<Integer>();
    ArrayList<Integer> loopCtrStore = new ArrayList<Integer>();
    ArrayList<String> loopNameStore = new ArrayList<String>();

    Scanner sc = new Scanner(this.programFlow);
    int addressCounter = 0;
    while(sc.hasNextLine()){
      String line = sc.nextLine().trim();
      String[] splitted = line.split(" ");
      //System.out.println(Arrays.toString(splitted));

      if(splitted[0].equalsIgnoreCase("LOOP")) {
        loopAddStore.add(addressCounter);
        loopCtrStore.add(Integer.parseInt(splitted[2]));
        loopNameStore.add(splitted[1]);
      } else if(splitted[0].equalsIgnoreCase("J")) {
        int index = loopNameStore.size() - 1;
        
        if(splitted[1].equals(loopNameStore.get(index))) {
          ArrayList<Integer> loopSequence = new ArrayList<Integer>();

          //save sequence
          for(int k=loopAddStore.get(index); k <= addressCounter-1; k++){
            loopSequence.add(parsedFlow.get(k));
          }
          //System.out.println("Stored loop: " + loopSequence.toString());

          //add loop
          for(int i=0; i < loopCtrStore.get(index)-1; i++){
            for(int k=0; k < loopSequence.size(); k++){
              parsedFlow.add(loopSequence.get(k));
              addressCounter++;
            }
          }

          loopAddStore.remove(index);
          loopCtrStore.remove(index);
          loopNameStore.remove(index);
        }
      } else{
        parsedFlow.add(Integer.parseInt(splitted[0]));
        addressCounter++;
      }
    }

    // System.out.println("Name: " + loopNameStore.toString());
    // System.out.println("Add: " + loopAddStore.toString());
    // System.out.println("Ctr: " + loopCtrStore.toString());
    // System.out.println("Flow: " + parsedFlow.toString());
    // System.out.println("Count: " + addressCounter);
    sc.close();
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
  public int[][] getCacheSnapshot() {
    return this.cacheMemory;
  }
  public int getMissCount() {
    return this.missCount;
  }
  public int getHitCount() {
    return this.hitCount;
  }
}