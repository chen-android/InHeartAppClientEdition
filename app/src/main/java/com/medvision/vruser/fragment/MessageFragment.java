package com.medvision.vruser.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.inheart.inheartapp.R;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.medvision.vruser.beans.Message;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 *
 */
public class MessageFragment extends Fragment {

	@InjectView(R.id.message_rv)
	XRecyclerView mMessageRv;
	@InjectView(R.id.message_no_data_rl)
	RelativeLayout mMessageNoDataRl;

	private MyRecyclerAdapter adapter;

	public static MessageFragment newInstance() {
		return new MessageFragment();
	}

	public MessageFragment() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_message, container, false);
		ButterKnife.inject(this, view);

		LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
		mMessageRv.setLayoutManager(layoutManager);
		adapter = new MyRecyclerAdapter();
		mMessageRv.setAdapter(adapter);
		mMessageRv.setEmptyView(mMessageNoDataRl);
		mMessageRv.setLoadingListener(new XRecyclerView.LoadingListener() {
			@Override
			public void onRefresh() {

			}

			@Override
			public void onLoadMore() {

			}
		});
		return view;
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
			View v = View.inflate(parent.getContext(), R.layout.list_item_message, null);
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
			ImageView mListItemMessageTypeIv;
			TextView mListItemMessageTitleTv;
			TextView mListItemMessageContentTv;
			TextView mListItemMessageTimeTv;

			ItemViewHolder(View itemView) {
				super(itemView);
				mListItemMessageTypeIv = (ImageView) itemView.findViewById(R.id.list_item_message_type_iv);
				mListItemMessageTitleTv = (TextView) itemView.findViewById(R.id.list_item_message_title_tv);
				mListItemMessageContentTv = (TextView) itemView.findViewById(R.id.list_item_message_content_tv);
				mListItemMessageTimeTv = (TextView) itemView.findViewById(R.id.list_item_message_time_tv);
			}
		}
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		ButterKnife.reset(this);
	}
}
