package com.example.asus.wook3.ui.satin.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.asus.wook3.R;
import com.example.asus.wook3.bean.SatinBean;
import com.example.asus.wook3.inter.OnItemClickListener;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by asus on 2018/5/26.
 */

public class XrvListAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<SatinBean.DataBean> list;
    private LayoutInflater inflater;
    private OnItemClickListener onItemClickListener;



    public XrvListAdapter(Context context, List<SatinBean.DataBean> list) {
        this.context = context;
        this.list = list;
        inflater= LayoutInflater.from(context);
    }
    public void setOnListItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemViewType(int position) {
        String type = list.get(position).getType();
        if (type.equals("41")) {
            return 41;
        }
        return 1;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType==41){
            View view = inflater.inflate(R.layout.item2, parent, false);
            XlvViewHolder2 xlvViewHolder2 = new XlvViewHolder2(view);
            return xlvViewHolder2;
        }else {
            View view = inflater.inflate(R.layout.item, parent, false);
            XlvViewHolder xlvViewHolder = new XlvViewHolder(view);
            return xlvViewHolder;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        int itemViewType = getItemViewType(position);
        if (itemViewType==1) {
            XlvViewHolder xlvViewHolder = (XlvViewHolder) holder;
            final SatinBean.DataBean dataBean = list.get(position);
            // Glide.with(context).load(imgUrl).into(xlvViewHolder.iv);
            xlvViewHolder.iv.setImageURI(dataBean.getProfile_image());
            xlvViewHolder.tvTitle.setText(dataBean.getText());
            xlvViewHolder.tvPrice.setText("发布时间"+ dataBean.getCreated_at()+"");

            //设置条目监听
            xlvViewHolder.ll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(position);
                    }
                }
            });
        }else if (itemViewType==41) {
            XlvViewHolder2 xlvViewHolder2 = (XlvViewHolder2) holder;
            final SatinBean.DataBean dataBean = list.get(position);
            // Glide.with(context).load(imgUrl).into(xlvViewHolder.iv);
            xlvViewHolder2.iv.setImageURI(dataBean.getProfile_image());
            xlvViewHolder2.tvTitle.setText(dataBean.getText());
            xlvViewHolder2.tvPrice.setText("发布时间"+ dataBean.getCreated_at()+"");
            xlvViewHolder2.video.setVideoURI(Uri.parse( dataBean.getVideouri()));

             //开始播放视频
            xlvViewHolder2.video.start();
            //设置条目监听
            xlvViewHolder2.ll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(position);
                    }
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class XlvViewHolder extends RecyclerView.ViewHolder {

        private final LinearLayout ll;
        private final SimpleDraweeView iv;
        private final TextView tvTitle;
        private final TextView tvPrice;

        public XlvViewHolder(View itemView) {
            super(itemView);
            ll = itemView.findViewById(R.id.ll);
            iv = itemView.findViewById(R.id.iv);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvPrice = itemView.findViewById(R.id.tvPrice);
        }
    }
    class XlvViewHolder2 extends RecyclerView.ViewHolder {
        private final VideoView video;
        private final LinearLayout ll;
        private final SimpleDraweeView iv;
        private final TextView tvTitle;
        private final TextView tvPrice;

        public XlvViewHolder2(View itemView) {
            super(itemView);
            video = itemView.findViewById(R.id.video);
            ll = itemView.findViewById(R.id.ll);
            iv = itemView.findViewById(R.id.iv);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvPrice = itemView.findViewById(R.id.tvPrice);
        }
    }
}
