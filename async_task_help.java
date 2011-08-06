package pl.project13.activity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;
import com.google.inject.Inject;
import pl.project13.R;
import pl.project13.lazy.Details;
import pl.project13.lazy.LazyWorker;
import roboguice.activity.RoboListActivity;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @author Konrad Malawski
 */
public class TasksActivity extends RoboListActivity {

  @Inject
  LazyWorker lazyWorker;

  Handler handler = new Handler();

  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

//    Toast.makeText(this, "Hello, from " + this.getClass().getSimpleName(), Toast.LENGTH_LONG).show();

    setContentView(R.layout.list);

//    loadData();

    asyncLoadData();
  }

  private void asyncLoadData() {
    List<String> datas = Collections.emptyList();
    try {
      datas = new AsyncTask<Void, Integer, List<String>>() {

        @Override
        protected List<String> doInBackground(Void... voids) {
          return lazyWorker.getData();
        }
      }.execute().get();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }

    final ProgressDialog progressDialog = new ProgressDialog(TasksActivity.this);
    progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
    progressDialog.setMessage("Loading details...");
    progressDialog.setCancelable(false);

    try {
      final List<String> finalDatas = datas;
      progressDialog.setMax(datas.size());

      new AsyncTask<String, Integer, Void>() {
        @Override
        protected void onPreExecute() {
//          handler.post(new Runnable() {
//            public void run() {
              progressDialog.show();
//            }
//          });
        }

        @Override
        protected void onPostExecute(Void aVoid) {
//          handler.post(new Runnable() {
//            public void run() {
          progressDialog.dismiss();
//            }
//          });
        }

        @Override
        protected Void doInBackground(String... strings) {
          int i = 1;
          for (final String data : finalDatas) {
            final Details details = lazyWorker.getDetails(data);

            handler.post(new Runnable() {
              public void run() {
                Toast.makeText(TasksActivity.this, "processed: " + details.getId(), Toast.LENGTH_SHORT)
                     .show();
              }
            });

            publishProgress(i++);
          }

          return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
          progressDialog.setProgress(values[0]);
        }
      }.execute(datas.toArray(new String[0]));
    } finally {
      progressDialog.hide();
    }
  }

  private void loadData() {
    List<String> data = lazyWorker.getData();
  }
} 
