package pl.project13.lazy;

/**
 * @author Konrad Malawski
 */
public class Details {
  private String id;
  private int population;

  public Details(String id, int population) {
    this.id = id;
    this.population = population;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public int getPopulation() {
    return population;
  }

  public void setPopulation(int population) {
    this.population = population;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Details details = (Details) o;

    if (population != details.population) {
      return false;
    }
    if (id != null ? !id.equals(details.id) : details.id != null) {
      return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + population;
    return result;
  }
}
