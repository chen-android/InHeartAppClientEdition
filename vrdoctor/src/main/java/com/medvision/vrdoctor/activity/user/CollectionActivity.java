package com.medvision.vrdoctor.activity.user;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.cs.widget.utils.Navigation;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.medvision.vrdoctor.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class CollectionActivity extends AppCompatActivity {

	@InjectView(R.id.collection_rv)
	XRecyclerView mCollectionRv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_collection);
		ButterKnife.inject(this);
		Navigation.getInstance(this).setTitle("收藏").setBack();

	}
}
