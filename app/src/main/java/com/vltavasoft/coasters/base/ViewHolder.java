package com.vltavasoft.coasters.base;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import com.vltavasoft.coasters.R;
import com.vltavasoft.coasters.database.Coaster;

public class ViewHolder extends RecyclerView.ViewHolder {

    private ImageView mImageThumbs;
    private Coaster currentCoaster;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        mImageThumbs = itemView.findViewById(R.id.iv_thumbs);

    }

    public void bind (Coaster coaster) {
        mImageThumbs.setImageURI(Uri.parse(coaster.getImgFrontUrl()));

        currentCoaster = new Coaster(
                coaster.getId(),
                coaster.getName(),
                coaster.getShape(),
                coaster.getCountry(),
                coaster.getCity(),
                coaster.getImgFrontUrl(),
                coaster.getImgBackUrl());
    }

    public void setListener (final CoastersAdapter.OnItemClickListener listener) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                DescriptionFragment descriptionFragment = new DescriptionFragment();
                descriptionFragment.setCoaster(currentCoaster);

                activity.getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fr_start_container, descriptionFragment)
                        .commit();
            }
        });
    }
}
