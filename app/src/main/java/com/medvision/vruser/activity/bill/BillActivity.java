package com.medvision.vruser.activity.bill;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.inheart.inheartapp.R;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.medvision.vruser.beans.Message;

import java.util.ArrayList;
import java.util.List;

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

	private class MyRecyclerAdapter extends RecyclerView.Adapter {

		private List<Message> mMessages;

		public MyRecyclerAdapter() {
			mMessages = new ArrayList<>();
		}

		public void clearData() {
			this.mMessages.clear();
		}

		public void addData(List<Message> messages) {
			if (!messages.isEmpty()) {
				mMessages.addAll(messages);
			}
		}

		public List<Message> getData() {
			return this.mMessages;
		}

		@Override
		public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			View v = View.inflate(parent.getContext(), R.layout.list_item_bill, null);
			v.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
			return new ItemViewHolder(v);
		}

		@Override
		public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
			ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
			Message message = mMessages.get(position);


		}

		@Override
		public int getItemCount() {
			return mMessages.size();
		}

		class ItemViewHolder extends RecyclerView.ViewHolder {
			ImageView mListItemBillTypeIv;
			TextView mListItemBillTitleTv;
			TextView mListItemBillDateTv;
			TextView mListItemBillAmountTv;

			ItemViewHolder(View itemView) {
				super(itemView);
				mListItemBillTypeIv = (ImageView) itemView.findViewById(R.id.list_item_bill_iv);
				mListItemBillTitleTv = (TextView) itemView.findViewById(R.id.list_item_bill_name_tv);
				mListItemBillDateTv = (TextView) itemView.findViewById(R.id.list_item_bill_date_tv);
				mListItemBillAmountTv = (TextView) itemView.findViewById(R.id.list_item_bill_amount_tv);
			}
		}
	}
}
