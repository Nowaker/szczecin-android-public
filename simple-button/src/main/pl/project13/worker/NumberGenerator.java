package pl.project13.worker;

import com.google.inject.ImplementedBy;

/**
 * @author Konrad Malawski
 */
@ImplementedBy(RandomNumberGenerator.class)
public interface NumberGenerator {
  int getRandomNumber();
}
