package ru.mail.belyakovim.yandex_mobilization_2016;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private               RecyclerView  mRecyclerView;
    private       RecyclerView.Adapter  mAdapter;
    private RecyclerView.LayoutManager  mLayoutManager;
    private              MusiciansList  mMusiciansList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(
                new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));

        mMusiciansList = ((MobilizationApplication) getApplication()).getMusiciansList();

        mAdapter = new MusicianAdapter(this, mMusiciansList);
        mRecyclerView.setAdapter(mAdapter);

        MusiciansListTask musiciansListTask = new MusiciansListTask();
        musiciansListTask.execute();
    }

    protected boolean isOnline() {
        String cs = Context.CONNECTIVITY_SERVICE;
        ConnectivityManager cm = (ConnectivityManager)
                getSystemService(cs);
        return cm.getActiveNetworkInfo() != null;
    }

    public class MusiciansListTask extends AsyncTask<Void, Void, Void> {

        private final String cache_filename = "raw_data_json.cache";

        protected ProgressDialog dialog;

        @Override
        protected Void doInBackground(Void... params) {
            String rawJSONData = null;
            // Проверяем наличие интернета
            if (isOnline()) {
                rawJSONData = downloadDataFromServer(MobilizationApplication.getServerDataPath());
            } else {
                rawJSONData = getDataFromCache();
            }
            if (rawJSONData != null) {
                fillMusiciansList(rawJSONData);
                putDataToCache(rawJSONData);
            } else {
                dialog.dismiss();
                Toast.makeText(MainActivity.this,
                        getResources().getString(R.string.internet_required) , Toast.LENGTH_SHORT).show();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(MainActivity.this);
            dialog.setTitle(getResources().getString(R.string.loading));
            dialog.show();
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            dialog.dismiss();
            mAdapter.notifyDataSetChanged();
            super.onPostExecute(aVoid);
        }

        private void fillMusiciansList(String rawJSONData) {
            try {
                JSONArray musiciansArray = new JSONArray(rawJSONData);

                for (int i = 0; i < musiciansArray.length(); i++) {
                    Musician musician = MusicianBuilder.buildFromJSON(musiciansArray.getJSONObject(i));
                    if (musician != null) {
                        mMusiciansList.add(musician);
                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        private void putDataToCache(String rawJSONData) {
            FileOutputStream fos = null;
            try {
                if (!getCacheDir().exists()) {
                    if (!getCacheDir().mkdirs()) {
                        return;
                    }
                }
                fos = openFileOutput(cache_filename, Context.MODE_PRIVATE);
                fos.write(rawJSONData.getBytes());
                fos.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private String getDataFromCache() {
            FileInputStream fio = null;
            String rawJSONData = null;
            try {
                fio = openFileInput(cache_filename);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fio));
                StringBuilder stringBuilder = new StringBuilder();

                String buff;
                while (null != (buff = bufferedReader.readLine())) {
                    stringBuilder.append(buff);
                }

                return stringBuilder.toString();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        private String downloadDataFromServer(String serverDataPath) {
            URL url;
            HttpURLConnection urlConnection = null;
            String result = null;
            try {
                url = new URL(serverDataPath);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                BufferedReader responseReader = new BufferedReader(new InputStreamReader(in));
                StringBuilder stringBuilder = new StringBuilder();

                String buff;
                while (null != (buff = responseReader.readLine())) {
                    stringBuilder.append(buff);
                }

                result = stringBuilder.toString();

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }
            return result;
        }
    }
}
