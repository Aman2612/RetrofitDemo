package hawkeyestudios.reteofitdemo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Aman on 21/01/18.
 */

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.myViewHolder> {

    List<GithubRepo> users;

    public RVAdapter(List<GithubRepo> users) {
        this.users = users;
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View myView = LayoutInflater.from(parent.getContext()).inflate(R.layout.myview,parent,false);
        myViewHolder holder = new myViewHolder(myView);
        return holder;
    }

    @Override
    public void onBindViewHolder(myViewHolder holder, int position) {

        holder.textView.setText(users.get(position).getName().toString());

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder
    {
        TextView textView;

        public myViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.text);
        }
    }

}
