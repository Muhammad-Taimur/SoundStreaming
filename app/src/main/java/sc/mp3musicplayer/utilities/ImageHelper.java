package sc.mp3musicplayer.utilities;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import sc.mp3musicplayer.R;
import sc.mp3musicplayer.models.STrack;

import static sc.mp3musicplayer.utilities.FilesHelper.createDirectory;
import sc.mp3musicplayer.constants.Constants.Folder;

/**
 * Created by regulosarmiento on 01/08/16.
 */
public class ImageHelper {

    /**
     * Download an image.
     * @param STrack {@link STrack}
     * @param context The view.
     */
    public static void downloadImage(final STrack STrack, final WeakReference<Context> context){
        File mImagePath = new File(createDirectory(Folder.IMAGES) + STrack.getJPG());

        new Thread(){ // To make sure it's not doing too much work on the main Thread.
            @Override
            public void run() {
                super.run();
                try {

                    BitmapFactory.Options bmOptions;
                    bmOptions = new BitmapFactory.Options();
                    bmOptions.inSampleSize = 1;
                    Bitmap image = decodeImage(STrack.getArtwork_url(), bmOptions);
                    if(image == null){
                        image = BitmapFactory.decodeResource(context.get().getResources(), R.mipmap.ic_launcher);
                    }

                    FileOutputStream oImage = new FileOutputStream(mImagePath);
                    image.compress(Bitmap.CompressFormat.JPEG, 100, oImage);
                    oImage.flush();
                    oImage.close();
                } catch (FileNotFoundException e) {
                    Log.e("Error: ", e.getMessage());

                } catch (IOException e) {
                    Log.e("Error: ", e.getMessage());
                }
            }
        }.run();
    }

    /**
     * Decodes image stream
     * @param URL The url where the image is downloaded.
     * @param options {@link Options}
     * @return {@link Bitmap}
     */
    public static Bitmap decodeImage(final String URL, final Options options){
       final Bitmap bitmap;
       final InputStream in;
        try {
            in = openHttpConnection(URL);
            bitmap = BitmapFactory.decodeStream(in, null, options);
            in.close();
        } catch (IOException e1) {
            return null;
        } catch (NullPointerException e2){
            return null;
        } catch(OutOfMemoryError e3){//Prevent OutOfMemoryError when image loading
            return null;
        }
        return bitmap;
    }

    /**
     * Gets an image from a url
     * @param strURL The url where the image is downloaded.
     * @return {@link InputStream}
     * @throws IOException
     */
    private static InputStream openHttpConnection(final String strURL) throws IOException{
        InputStream inputStream = null;
        URL url = new URL(strURL);
        URLConnection conn = url.openConnection();

        try{
            HttpURLConnection httpConn = (HttpURLConnection)conn;
            httpConn.setRequestMethod("GET");
            httpConn.connect();

            if (httpConn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                inputStream = httpConn.getInputStream();
            }
        }
        catch (Exception ignored) {}
        return inputStream;
    }
}
