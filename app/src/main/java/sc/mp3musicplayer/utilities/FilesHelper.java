package sc.mp3musicplayer.utilities;

import android.os.Environment;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

import java.io.File;
import java.util.List;

import sc.mp3musicplayer.constants.Constants;
import sc.mp3musicplayer.constants.Constants.Folder;
import sc.mp3musicplayer.models.DTrack;
import sc.mp3musicplayer.models.STrack;

/**
 * Created by regulosarmiento on 27/07/16.
 */
public class FilesHelper {

    /**
     * Gets the pathname of tracks
     * @return An array of String.
     */
    private static String[] getTracksPath(){
        final String[] mTracks = new File(getDirectoryTracks()).list();
        if(mTracks == null){
            return new String[0];
        }
        return mTracks;
    }

    /**
     * Gets the pathname of images
     * @return An array of String.
     */
    public static String[] getImagesPath(){
        final String[] mImages = new File(getDirectoryImages()).list();
        if(mImages == null){
            return new String[0];
        }
        return mImages;
    }

    /**
     * Deletes a track file.
     * @param trackName The pathname of the track that will be deleted.
     */
    public static void deleteTrack(final String trackName){
        final File mTrack = new File(getDirectoryTracks(), trackName);
        if(mTrack.exists()){
            Preconditions.checkArgument(mTrack.delete(), "Image has been deleted!");
        }
    }

    /**
     * Deletes an image file.
     * @param imageName The pathname of the image that will be deleted.
     */
    public static void deleteImage(final String imageName){
        final File mImage = new File(getDirectoryImages(), imageName);
        if(mImage.exists()){
            Preconditions.checkArgument(mImage.delete(), "Image has been deleted!");
        }
    }

    /**
     * Gets the directory where Tracks have been stored.
     * @return A path
     */
    public static String getDirectoryTracks(){
        return Preconditions.checkNotNull(getDirectoryDownload() + Constants.DIR_TRACKS, "Directory Tracks cannot be null!");
    }

    /**
     * Gets the directory where Tracks' images have been stored.
     * @return A path
     */
    public static String getDirectoryImages(){
        return Preconditions.checkNotNull(getDirectoryDownload() + Constants.DIR_IMAGES, "Directory Images cannot be null!");
    }

    /**
     * Gets the directory download
     * @return A file.
     */
    private static File getDirectoryDownload(){
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
    }

    /**
     * Creates a directory to store either Tracks or Images.
     * @param folder {@link Folder}.
     * @return A path.
     */
    public static String createDirectory(final Folder folder){
        final String mPath;
        final File mDirectory;

        if(folder == Folder.TRACKS){
             mPath = getDirectoryDownload() + Constants.DIR_TRACKS;
          } else {
             mPath = getDirectoryDownload() + Constants.DIR_IMAGES;
        }

        mDirectory = new File(mPath);

        if(!mDirectory.exists()){
             mDirectory.mkdirs();
          } else {
             return mPath;
        }

        return mPath;
    }

    /**
     * Gets those tracks that have already been downloaded.
     * @return list of tracks
     */
    public static List<DTrack> getListOfTracks(){
        final List<DTrack> mLocalSTracks = Lists.newArrayList();
        final String[] mTracks = getTracksPath();

        for (String mTrack : mTracks) {
            File mImage = new File(getDirectoryImages() + "/" + mTrack.replace(".mp3", ".jpg"));
            mLocalSTracks.add(
                new DTrack(
                    mTrack.replace(".mp3", ""),  // Title
                    mImage.exists() ? mImage.getAbsolutePath() : null, // Pathname image
                    getDirectoryTracks() + "/" + mTrack  // Pathname track(mp3)
                )
            );
        }
        return mLocalSTracks;
    }

    /**
     * Checks if a tracks exists or not.
     * @param trackName STrack's name to be checked.
     * @return True if the track exists or false otherwise.
     */
    public static boolean isExists(final String trackName){
        for(DTrack track : getListOfTracks()){
            if(track.getTitle().equals(trackName)){
                return true;
            }
        }
        return false;
    }

}
