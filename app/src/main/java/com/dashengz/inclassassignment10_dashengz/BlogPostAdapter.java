package com.dashengz.inclassassignment10_dashengz;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Jonathan on 4/9/17.
 */

public class BlogPostAdapter extends RecyclerView.Adapter<BlogPostAdapter.ViewHolder> {
    private ArrayList<BlogPost> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTitle;
        public TextView mTime;
        public TextView mBody;
        public ViewHolder(View item) {
            super(item);
            mTitle = (TextView) item.findViewById(R.id.title);
            mTime = (TextView) item.findViewById(R.id.time);
            mBody = (TextView) item.findViewById(R.id.body);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public BlogPostAdapter(ArrayList<BlogPost> dataset) {
        mDataset = dataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public BlogPostAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        LinearLayout frame = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.blogpost_list_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        return new ViewHolder(frame);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mTitle.setText(mDataset.get(position).getTitle());
        holder.mTime.setText(mDataset.get(position).toReadableTime());
        holder.mBody.setText(mDataset.get(position).getBody());

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
