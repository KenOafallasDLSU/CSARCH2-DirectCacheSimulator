/**
 * @author S12 Group 3
 * @author BUHION, Deborah Rose P.
 * @author DIZON, Michaela Nicole P.
 * @author LIN, James Kevin S.
 * @author OAFALLAS, Kenneth Neil B.
 */

public class Calculator {
  public Calculator (int cacheAccessTime, int mmAccessTime, int hitCount, int missCount){
    
    this.cacheAccessTime = cacheAccessTime;
    this.mmAccessTime = mmAccessTime;
    this.hitCount = hitCount;
    this.missCount = missCount;

  }

  public float getMissPenalty(){
      float missPenalty = 0;

      return missPenalty;
  }

  public float getAverageTime(){
    float averageTime = 0;

    return averageTime;
  }

  public float getTotalTime(){
    float totalTime = 0;

    return totalTime;
  }


  private int cacheAccessTime;
  private int mmAccessTime;
  private int hitCount;
  private int missCount;
}
