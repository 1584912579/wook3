package com.example.asus.wook3.ui.satin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;

import com.example.asus.wook3.R;
import com.example.asus.wook3.bean.SatinBean;
import com.example.asus.wook3.component.DaggerHttpComponent;
import com.example.asus.wook3.inter.OnItemClickListener;
import com.example.asus.wook3.module.HttpModule;
import com.example.asus.wook3.ui.base.BaseActivity;
import com.example.asus.wook3.ui.satin.adapter.XrvListAdapter;
import com.example.asus.wook3.ui.satin.contract.Satincontract;
import com.example.asus.wook3.ui.satin.presenter.SatinPresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import dagger.internal.DaggerCollections;

public class MainActivity extends BaseActivity<SatinPresenter> implements Satincontract.View {

    /**
     * 跳转
     */
    private Button mBtn;
    private XRecyclerView mXrv;
    private XrvListAdapter xrvListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        initView();
        mPresenter.getPresenter("1","1");
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ShowActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void inject() {
        DaggerHttpComponent.builder()
                .httpModule(new HttpModule())
                .build()
                .inject(this);
    }

    @Override
    public void getSatinSuccess(List<SatinBean.DataBean> list) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mXrv.setLayoutManager(linearLayoutManager);
        xrvListAdapter = new XrvListAdapter(this, list);
        mXrv.setAdapter(xrvListAdapter);
        xrvListAdapter.setOnListItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }

            @Override
            public void onLongItemClick(int position) {

            }
        });

    }

    private void initView() {
        mBtn = (Button) findViewById(R.id.btn);
        mXrv = (XRecyclerView) findViewById(R.id.xrv);
    }
}
