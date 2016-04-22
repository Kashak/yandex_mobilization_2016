package ru.mail.belyakovim.yandex_mobilization_2016;

import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import ru.mail.belyakovim.yandex_mobilization_2016.databinding.MusicianItemBinding;

/**
 * Created by kashak on 4/21/16.
 */
public class MusicianAdapter extends RecyclerView.Adapter<MusicianAdapter.MusicianItemViewHolder> {

    private MusiciansList  musiciansList;
    private  MainActivity  mainActivity;

    public MusicianAdapter(MainActivity mainActivity, MusiciansList musiciansList) {
        this.musiciansList = musiciansList;
        this.mainActivity = mainActivity;
    }

    @Override
    public MusicianItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        MusicianItemBinding binding = MusicianItemBinding.inflate(inflater, parent, false);
        return new MusicianItemViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(MusicianItemViewHolder holder, int position) {
        Musician musician = musiciansList.getMusiciansListItem(position);
        holder.binding.setMusician(musician);
        holder.binding.setHandler(new ClickHandler());
    }

    @Override
    public int getItemCount() {
        return musiciansList.size();
    }

    public class MusicianItemViewHolder extends RecyclerView.ViewHolder {

        MusicianItemBinding binding;

        public MusicianItemViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }

    public class ClickHandler implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(mainActivity, MusicianProfileActivity.class);

            MusicianItemBinding binding = DataBindingUtil.getBinding(v);
            int id = binding.getMusician().getId();

            intent.putExtra(MobilizationApplication.MUSICIAN_ID, id);

            mainActivity.startActivity(intent);
        }
    }

    @BindingAdapter("bind:imageUrl")
    public static void loadImage(ImageView imageView, String v) {
        Picasso.with(imageView.getContext()).load(v).into(imageView);
    }
}
