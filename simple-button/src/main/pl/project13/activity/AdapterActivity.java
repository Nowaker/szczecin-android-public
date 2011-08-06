package pl.project13.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.google.inject.Inject;
import pl.project13.R;
import pl.project13.worker.CountriesResource;
import roboguice.activity.RoboListActivity;

/**
 * @author Konrad Malawski
 */
public class AdapterActivity extends RoboListActivity {

  @Inject
  CountriesResource countriesResource;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    ListView lv = getListView(); // from ListActivity

    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_row, R.id.text1, getCountries());
    setListAdapter(adapter);

    lv.setTextFilterEnabled(true);

    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      public void onItemClick(AdapterView<?> parent, View view,
                              int position, long id) {
        ListView listView = (ListView) view;
        TextView tv = (TextView) listView.findViewById(R.id.text1);

        Toast.makeText(getApplicationContext(),
                       tv.getText(),
                       Toast.LENGTH_SHORT)
             .show();
      }
    });
  }

  public String[] getCountries() {
    return countriesResource.getCountries();
  }
}
