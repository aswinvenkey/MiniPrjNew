package com.example.aswinvenkat.miniprj;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import com.ipaulpro.afilechooser.utils.FileUtils;
import android.widget.Button;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by aswin venkat on 3/23/2015.
 */
public class FileChooserExampleActivity extends Activity {

    private static final String TAG = "FileChooserExampleActivity";
    public static String path;
    private static final int REQUEST_CODE = 6384; // onActivityResult request
    // code

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create a simple button to start the file chooser process
        Button button = new Button(this);
        button.setText(R.string.choose_file);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Display the file chooser dialog
                showChooser();
            }
        });

        setContentView(button);
    }

    private void showChooser() {
        // Use the GET_CONTENT intent from the utility class
        Intent target = FileUtils.createGetContentIntent();
        // Create the chooser Intent
        Intent intent = Intent.createChooser(
                target, getString(R.string.chooser_title));
        try {
            startActivityForResult(intent, REQUEST_CODE);
        } catch (ActivityNotFoundException e) {
            // The reason for the existence of aFileChooser
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CODE:
                // If the file selection was successful
                if (resultCode == RESULT_OK) {
                    if (data != null) {
                        // Get the URI of the selected file
                        final Uri uri = data.getData();
                        Log.i(TAG, "Uri = " + uri.toString());
                        try {
                            // Get the file path from the URI
                            path = FileUtils.getPath(this, uri);
                            Toast.makeText(getApplicationContext(),path,Toast.LENGTH_SHORT).show();
                            String url = "http://192.168.1.3/media/UploadToServer.php";
                            File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(),
                                    path);
                            try {
                                HttpClient httpclient = new DefaultHttpClient();

                                HttpPost httppost = new HttpPost(url);

                                InputStreamEntity reqEntity = new InputStreamEntity(
                                        new FileInputStream(file), -1);
                                reqEntity.setContentType("binary/octet-stream");
                                reqEntity.setChunked(true); // Send in multiple parts if needed
                                httppost.setEntity(reqEntity);
                                HttpResponse response = httpclient.execute(httppost);
                                //Do something with response...

                            } catch (Exception e) {
                                // show error
                                e.printStackTrace();
                                Log.e("ERROR","ERROR",e);
                                Toast.makeText(getApplicationContext(),"ErrorMan",Toast.LENGTH_SHORT).show();
                            }


                        } catch (Exception e) {
                            Log.e("FileSelectorTestActivity", "File select error", e);
                        }
                    }
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


}