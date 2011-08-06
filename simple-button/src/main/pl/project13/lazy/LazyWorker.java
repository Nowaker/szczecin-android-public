package pl.project13.lazy;

import java.util.List;
import java.util.Random;

import static com.google.common.collect.Lists.newArrayList;

/**
 * This action is super slow, you'd better run it in an {@link android.os.AsyncTask}!
 *
 * @author Konrad Malawski
 */
public class LazyWorker {

  Random rnd = new Random(System.currentTimeMillis());

  public List<String> getData() {
//    sleep(10000);

    return newArrayList("Kraków", "Warszawa", "Poznań", "Szczecin", "Gliwice", "Krynica" ,
                        "Kołobrzeg", "Zakopane", "Katowice", "Przemyśl", "Łódź", "Świerk",
                        "Szczyrk", "Hel", "Wieliczka");
  }

  public Details getDetails(String id){
    sleep(5000);

    return new Details(id, rnd.nextInt()+1000);
  }

  private void sleep(int howLong) {
    try {
      Thread.sleep(howLong);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
