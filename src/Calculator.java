/**
 * @author S12 Group 3
 * @author BUHION, Deborah Rose P.
 * @author DIZON, Michaela Nicole P.
 * @author LIN, James Kevin S.
 * @author OAFALLAS, Kenneth Neil B.
 */

public class Calculator {
  public Calculator (int cacheAccessTime, int mmAccessTime, int hitCount, int missCount, boolean isLoadThrough, int blockSize){
    
    this.cacheAccessTime = cacheAccessTime;
    this.mmAccessTime = mmAccessTime;
    this.hitCount = hitCount;
    this.missCount = missCount;
    this.isLoadThrough = isLoadThrough;
    this.blockSize = blockSize;
    this.setHitRate(hitCount, missCount);

  }

  public void setHitRate (int hitCount, int missCount) {

    this.hitRate = hitCount / (hitCount+missCount);
  }

  public float getMissPenalty(){
      float missPenalty;

      if (isLoadThrough) {
        missPenalty = this.cacheAccessTime + this.mmAccessTime;
      }

      else {
        missPenalty = this.cacheAccessTime + (this.blockSize * this.mmAccessTime) + this.cacheAccessTime;
      }

      return missPenalty;
  }

  public float getAverageTime(){
    float averageTime = 0;

    averageTime = (this.hitRate * this.cacheAccessTime) + ((1- this.hitRate) * this.getMissPenalty());

    return averageTime;
  }

  public float getTotalTime(){
    float totalTime;
    float hit;
    float miss;
    float cacheProbing;

    if (this.isLoadThrough) {
      miss = this.missCount * this.cacheAccessTime;
    }
    else {
      miss = this.missCount * this.blockSize * this.mmAccessTime * this.cacheAccessTime;
    }

    hit = this.hitCount * this.blockSize * this.cacheAccessTime;
    
    cacheProbing = this.missCount + this.cacheAccessTime;

    totalTime = hit + miss + cacheProbing;

    return totalTime;
  }


  private int cacheAccessTime;
  private int mmAccessTime;
  private int blockSize;
  private int hitCount;
  private int missCount;
  private float hitRate;
  private boolean isLoadThrough;
}
