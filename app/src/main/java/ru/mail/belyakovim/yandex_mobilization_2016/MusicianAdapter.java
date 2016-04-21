package ru.mail.belyakovim.yandex_mobilization_2016;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.mail.belyakovim.yandex_mobilization_2016.databinding.MusicianItemBinding;

/**
 * Created by kashak on 4/21/16.
 */
public class MusicianAdapter extends RecyclerView.Adapter<MusicianAdapter.MusicianItemViewHolder> {

    private MusiciansList musiciansList;

    public MusicianAdapter(MusiciansList musiciansList) {
        this.musiciansList = musiciansList;
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
}
