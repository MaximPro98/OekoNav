package com.example.oekonav.ui.profile;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.oekonav.FileHelper;
import com.example.oekonav.R;
import com.esafirm.imagepicker.features.ImagePicker;
import com.esafirm.imagepicker.features.ReturnMode;
import com.esafirm.imagepicker.model.Image;
import com.parse.GetDataCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import java.io.File;
import java.net.URI;
import java.util.List;

public class ProfileFragment extends Fragment {
    private ImageView userImage;
    private ProfileViewModel profileViewModel;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        profileViewModel =
                ViewModelProviders.of(this).get(ProfileViewModel.class);
        View root = inflater.inflate(R.layout.fragment_profile, container, false);

        userImage = root.findViewById(R.id.img_profile_view);
        try {
            ParseFile userimg = ParseUser.getCurrentUser().fetchIfNeeded().getParseFile("ProfilePicture");
            if(userimg != null){
                userimg.getDataInBackground(new GetDataCallback() {
                    @Override
                    public void done(byte[] data, ParseException e) {
                        userImage.setImageBitmap(BitmapFactory.decodeByteArray(data,0,data.length));
                    }
                });
            }

        }catch (ParseException e){

        }

        TextView userTagline = root.findViewById(R.id.textView_ProfileDesc);
        TextView username = root.findViewById(R.id.textView_ProfileName);
        TextView score = root.findViewById(R.id.textView_ScoreValue);

        username.setText(ParseUser.getCurrentUser().getUsername());
        score.setText(ParseUser.getCurrentUser().get("Score").toString());
         String tagline = "";
         try {
             tagline = ParseUser.getCurrentUser().get("Tagline").toString();
         } catch (Exception e) {
             e.printStackTrace();
         }
        if (tagline != null) {

            userTagline.setText("Hey I'm New");
        }else{
            userTagline.setText("Hey I'm New");

        }
        userImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ImagePicker.create(ProfileFragment.this)
                        .returnMode(ReturnMode.ALL) // set whether pick action or camera action should return immediate result or not. Only works in single mode for image picker
                        .folderMode(true) // set folder mode (false by default)
                        .single()
                        .toolbarFolderTitle("Folder") // folder selection title
                        .toolbarImageTitle("Tap to select")
                        .toolbarDoneButtonText("DONE") // done button text
                        .start(0);
            }
        });


        return root;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        List<Image> images = ImagePicker.getImages(data);
        if (images != null && !images.isEmpty()) {
            Uri fileURI =  Uri.fromFile(new File(images.get(0).getPath()));
            userImage.setImageBitmap(BitmapFactory.decodeFile(images.get(0).getPath()));
            byte[] fileBytes = FileHelper.getByteArrayFromFile(null, fileURI);
            fileBytes = FileHelper.reduceImageForUpload(fileBytes);
            ParseFile userimg = new ParseFile("profile_" + ParseUser.getCurrentUser().getObjectId() + ".png",fileBytes);
            userimg.saveInBackground(new SaveCallback() {
                public void done(ParseException e) {
                    // If successful add file to user and signUpInBackground
                    if(null == e) {
                        ParseUser.getCurrentUser().put("ProfilePicture", userimg);
                        ParseUser.getCurrentUser().saveEventually();
                    }
                }
            });

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}