package kg.geektech.valyuta;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import kg.geektech.valyuta.databinding.ListRvMainBinding;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    private ListRvMainBinding binding;
    private List<MainModel> list;

    public void setList(List<MainModel> list) {
        this.list = new ArrayList<>();
        this.list.addAll(list);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ListRvMainBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ListRvMainBinding binding;

        public ViewHolder(@NonNull ListRvMainBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void onBind(MainModel mainModel) {
            binding.tvValyuta.setText(mainModel.getValyuta());
            binding.tvKurs.setText(mainModel.getKurs());
        }
    }
}
