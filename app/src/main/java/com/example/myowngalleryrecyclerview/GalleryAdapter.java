package com.example.myowngalleryrecyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.GalleryHolder>{
    private Context context;
    private List<PictureModel> pictures = new ArrayList<>();

    public GalleryAdapter(Context context, List<PictureModel> data) {
        this.context = context;
        this.pictures = data;
    }

    @NonNull
    @Override
    public GalleryHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {


        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_item,
                viewGroup, false);

        return new GalleryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GalleryHolder itemViewHolder, int position) {

        Glide.with(context).load(pictures.get(position).getUrl())
                .thumbnail(0.5f)
                .apply(new RequestOptions()
                        .override(400, 400)
                        .diskCacheStrategy(DiskCacheStrategy.ALL))
                .transition(withCrossFade())
                .into((itemViewHolder).itemImageView);
        PictureModel pictureModel = pictures.get(position);
        itemViewHolder.mNameTextView.setText(pictureModel.getTitle());

    }

    @Override
    public int getItemCount() {
        return pictures.size();
    }

    class GalleryHolder extends RecyclerView.ViewHolder {

        private ImageView itemImageView;
        private TextView mNameTextView;

        GalleryHolder(View itemView) {
            super(itemView);

            itemImageView = itemView.findViewById(R.id.item_img);
            mNameTextView = itemView.findViewById(R.id.textViewLarge);
        }
    }
}
