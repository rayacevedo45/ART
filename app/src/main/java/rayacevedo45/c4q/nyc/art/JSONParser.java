package rayacevedo45.c4q.nyc.art;

import android.content.Context;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by c4q-Abass on 6/23/15.
 */

//JSON Reader and Writer class
public class JSONParser {
    private Context mContext;
    private String mFileName;

    public JSONParser(Context c, String f) {
        mContext = c;
        mFileName = f;
    }

    public JSONParser(){

    }

    public String getStockQuotesJSON() throws  JSONException, IOException{
        DefaultHttpClient httpClient = new DefaultHttpClient(new BasicHttpParams());
        HttpPost httppost;
        httppost = new HttpPost(Stock.APIurl);
        httppost.setHeader("Content-type", "application/json");
        InputStream inputStream = null;
        String stockResults = null;

            HttpResponse response = httpClient.execute(httppost);
            HttpEntity entity = response.getEntity();
            inputStream = entity.getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();

            String line = null;
            while((line = reader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }
            stockResults = stringBuilder.toString();

        JSONObject jsonObject = new JSONObject(stockResults);
        JSONObject queryJSONObject = jsonObject.getJSONObject("query");
        JSONObject resultsJSONObject = queryJSONObject.getJSONObject("results");
        JSONObject quoteJSONObject = resultsJSONObject.getJSONObject("quote");
        String stockSymbol = quoteJSONObject.getString("symbol");

        JSONArray queryArray = quoteJSONObject.names();
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < queryArray.length(); i++) {
            list.add(queryArray.getString(i));
        }


        return stockSymbol;
    }

    public JSONObject parse(String webPage) {
        try {
            Log.d("$$$", webPage);
            URL url = new URL(webPage);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            String Json = readStream(connection.getInputStream());
            Log.d("|||", Json);
            JSONObject jsonObject = new JSONObject(Json);
            return jsonObject;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String readStream(InputStream in) throws IOException {
        char[] buffer = new char[1024 * 4];
        InputStreamReader reader = new InputStreamReader(in, "UTF8");
        StringWriter writer = new StringWriter();
        int n;
        while ((n = reader.read(buffer)) != -1) {
            writer.write(buffer, 0, n);
        }
        return writer.toString();
    }

    public void saveNotes(ArrayList<Note> notes)
                throws JSONException, IOException {
            // Build an array in JSON
            JSONArray array = new JSONArray();
            for (Note c : notes)
                array.put(c.toJSON());

            // Write the file to disk
            Writer writer = null;
            try {
                OutputStream out = mContext.openFileOutput(mFileName, Context.MODE_PRIVATE);
                writer = new OutputStreamWriter(out);
                writer.write(array.toString());
            } finally {
                if (writer != null)
                    writer.close();
            }

    }


    public ArrayList<Note> loadNotes() throws IOException, JSONException {
            ArrayList<Note> notes = new ArrayList<Note>();
            BufferedReader reader = null;
            try {
                // Open and read the file into a StringBuilder
                InputStream in = mContext.openFileInput(mFileName);
                reader = new BufferedReader(new InputStreamReader(in));
                StringBuilder jsonString = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) {
                    // Line breaks are omitted and irrelevant
                    jsonString.append(line);
                }
                // Parse the JSON using JSONTokener
                JSONArray array = (JSONArray) new JSONTokener(jsonString.toString()).nextValue();
                //Build the array of notes from JSONObjects
                for (int i = 0; i < array.length(); i++) {
                    notes.add(new Note(array.getJSONObject(i)));
                }
            } catch (FileNotFoundException e) {
                // Ignore this one; it happens when starting fresh
            } finally {
                if (reader != null)
                    reader.close();
            }
            return notes;
    }
}


