package com.example.ethionewapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ethionewapp.retrofit.NewsData;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsAdapater extends RecyclerView.Adapter<NewsAdapater.NewsAdapterViewHolder> {
    private  Context context;
    private List<NewsData> arrayListNewsItem;

    public NewsAdapater(Context context, List<NewsData> arrayListNewsItem) {
        this.context = context;
        this.arrayListNewsItem = arrayListNewsItem;
    }

    @NonNull
    @Override
    public NewsAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.news_item_layout, viewGroup, false);
        return new NewsAdapterViewHolder(view, context, arrayListNewsItem);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapterViewHolder newsAdapterViewHolder, int i) {
        NewsData newsItem = arrayListNewsItem.get(i);
        String image = newsItem.getImage();
        String title = newsItem.getTitle();
        String date = newsItem.getTitle_date();
        String site_link = newsItem.getLogo();
        String href = newsItem.getHref();
        String logo_link = newsItem.getLogo_link();
        String id = newsItem.getId();

        newsAdapterViewHolder.title.setText(title);
        newsAdapterViewHolder.date.setText(date);
        newsAdapterViewHolder.link.setText(site_link);
        Picasso.get().load(image).fit().centerInside().into(newsAdapterViewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return arrayListNewsItem.size();
    }

    public class NewsAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView imageView;
        private TextView title;
        private TextView date;
        private TextView link;
        Context context;
        List<NewsData> data;

        public NewsAdapterViewHolder(@NonNull View itemView, Context context, List<NewsData> data) {
            super(itemView);
            imageView = itemView.findViewById(R.id.rv_layout_image_view);
            title = itemView.findViewById(R.id.rv_title);
            date = itemView.findViewById(R.id.rv_date);
            link = itemView.findViewById(R.id.rv_link);
            itemView.setOnClickListener(this);
            this.context = context;
            this.data = data;
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, WebViewActivity.class);
            intent.putExtra("URL_NAME", data.get(getAdapterPosition()).getHref());
            context.startActivity(intent);
        }
    }
}
