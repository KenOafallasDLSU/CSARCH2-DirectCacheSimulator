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
import java.util.regex.Pattern; 

public class Simulator{
  //input attributes
  private String programFlow;
  private int cacheBlocks;
  private int mmBlocks;
  private int blockSize;
  private boolean isAddress;
  private boolean isCont;

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

    ArrayList<Integer> initParsedFlow = parseProgramFlow();
    ArrayList<Integer> parsedFlow;
    if(isAddress && isCont){
      parsedFlow = new ArrayList<Integer>();
      for(int i=0; i < initParsedFlow.size(); i=i+this.blockSize)
        parsedFlow.add((int)Math.floor(initParsedFlow.get(i)/this.blockSize));
    } else if(isAddress){
      parsedFlow = new ArrayList<Integer>();
      for(int i=0; i < initParsedFlow.size(); i++)
        parsedFlow.add((int)Math.floor(initParsedFlow.get(i)/this.blockSize));
    } else
      parsedFlow = initParsedFlow;

    System.out.println(parsedFlow.toString());

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

      //loop start flag
      if(splitted[0].equalsIgnoreCase("LOOP")) {  
        loopAddStore.add(addressCounter);
        loopCtrStore.add(Integer.parseInt(splitted[2]));
        loopNameStore.add(splitted[1]);
      } 
      //loop end flag
      else if(splitted[0].equalsIgnoreCase("J")) {  
        int index = loopNameStore.size() - 1;
        
        if(splitted[1].equals(loopNameStore.get(index))) {
          ArrayList<Integer> loopSequence = new ArrayList<Integer>();

          //save sequence
          for(int k=loopAddStore.get(index); k <= addressCounter-1; k++){
            loopSequence.add(parsedFlow.get(k));
          }

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
        else 
          break;
      } 
      //range flag, inclusive
      else if(splitted[0].equalsIgnoreCase("RANGE")) {
        int start;
        int end;

        if(splitted[1].matches("^x[0-9a-fA-F]+"))
          start = Integer.parseInt(splitted[1].substring(1), 16);
        else if(splitted[1].matches("[0-9]+"))
          start = Integer.parseInt(splitted[1]);
        else 
          break;

        if(splitted[2].matches("^x[0-9a-fA-F]+"))
          end = Integer.parseInt(splitted[2].substring(1), 16);
        else if(splitted[2].matches("[0-9]+"))
          end = Integer.parseInt(splitted[2]);
        else
          break;

        for(int i = start; i <= end; i++){
          parsedFlow.add(i);
          addressCounter++;
        }
      } 
      //normal input
      else if(!splitted[0].equals("") && (splitted[0].matches("^x[0-9a-fA-F]+") || splitted[0].matches("[0-9]+"))) {
        if(splitted[0].charAt(0) == 'x')
          parsedFlow.add(Integer.parseInt(splitted[0].substring(1), 16));
        else
          parsedFlow.add(Integer.parseInt(splitted[0]));
        addressCounter++;
      }
    }

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
  public void setIsAddress(boolean isAddress) {
    this.isAddress = isAddress;
  }
  public void setIsCont(boolean isCont){
    this.isCont = isCont;
  }

  //getters
  public String getCacheSnapshot() {
    StringBuilder sb = new StringBuilder();
    int addBits = (int)(Math.log(this.mmBlocks*this.blockSize)/Math.log(2));
    int blockBits = (int)(Math.log(this.cacheBlocks)/Math.log(2));
    int wordBits = (int)(Math.log(this.blockSize)/Math.log(2));
    int tagBits = addBits - (blockBits + wordBits); 

    sb.append("Valid Bit  -  TAG  -  MM Address of Data");
    sb.append(System.lineSeparator());

    for(int i = 0; i < this.cacheBlocks; i++){
      boolean valid = this.cacheMemory[i][0] != -1;

      sb.append("Block " + i);
      sb.append(System.lineSeparator());

      for(int k = 0; k < this.blockSize; k++) {
        if(valid){
          String bin = Integer.toBinaryString(this.cacheMemory[i][k]);

          while(bin.length() < addBits){
            bin = "0" + bin;
          }

          //valid bit
          sb.append("1   -   ");
          //tag
          sb.append(bin.substring(0, tagBits));
          sb.append("   -   ");
          //address
          sb.append(bin);
        } else{
          //valid bit
          sb.append("0 ");
        }
        
        if(i < this.cacheBlocks-1 || k < this.blockSize-1)
          sb.append(System.lineSeparator());
      }

      if(i < this.cacheBlocks-1)
        sb.append(System.lineSeparator());
    }

    return sb.toString();
  }
  public int[][] getRawSnapshot(){
    return this.cacheMemory;
  }
  public int getMissCount() {
    return this.missCount;
  }
  public int getHitCount() {
    return this.hitCount;
  }
}