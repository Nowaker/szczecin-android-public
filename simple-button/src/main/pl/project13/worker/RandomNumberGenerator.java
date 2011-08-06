package pl.project13.worker;

import java.util.Random;

/**
 * @author Konrad Malawski
 */
public class RandomNumberGenerator implements NumberGenerator {

  Random rnd = new Random();

  public int getRandomNumber(){
    return rnd.nextInt();
  }
}
