package com.inheart.inheartapp.activity.bill;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.inheart.inheartapp.R;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class BillActivity extends AppCompatActivity {

	@InjectView(R.id.bill_rv)
	XRecyclerView mBillRv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bill);
		ButterKnife.inject(this);


	}
}
