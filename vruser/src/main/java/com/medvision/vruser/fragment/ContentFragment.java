package com.medvision.vruser.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.medvision.vruser.R;
import com.medvision.vruser.beans.Banner;
import com.medvision.vruser.utils.BannerViewHolder;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 *
 */
public class ContentFragment extends Fragment {

	@InjectView(R.id.content_rv)
	XRecyclerView mContentRv;

	private ConvenientBanner<Banner> convenientBanner;
	private TextView searchTv;

	private MyContentAdapter adapter;

	public ContentFragment() {
		// Required empty public constructor
	}

	public static ContentFragment newInstance(String param1, String param2) {
		return new ContentFragment();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_content, container, false);
		ButterKnife.inject(this, view);
		LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
		mContentRv.setLayoutManager(layoutManager);
		adapter = new MyContentAdapter();
		mContentRv.setAdapter(adapter);
		mContentRv.addHeaderView(initHeaderView());

		return view;
	}

	private View initHeaderView() {
		View v = View.inflate(getContext(), R.layout.layout_item_fragment_content, null);
		convenientBanner = (ConvenientBanner<Banner>) v.findViewById(R.id.content_cb);
		searchTv = (TextView) v.findViewById(R.id.content_search_tv);
		ArrayList<Banner> banners = new ArrayList<>();
		banners.add(new Banner());
		convenientBanner.setPages(BannerViewHolder::new, banners);
		return v;
	}

	private class MyContentAdapter extends RecyclerView.Adapter<MyContentAdapter.MyContentViewHolder> {

		@Override
		public MyContentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			View v = View.inflate(parent.getContext(), R.layout.list_item_content, null);
			v.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
			return new MyContentViewHolder(v);
		}

		@Override
		public void onBindViewHolder(MyContentViewHolder holder, int position) {

		}

		@Override
		public int getItemCount() {
			return 10;
		}

		class MyContentViewHolder extends RecyclerView.ViewHolder {
			private ImageView iv;
			private TextView titleTv;
			private TextView dateTv;

			public MyContentViewHolder(View itemView) {
				super(itemView);
				iv = (ImageView) itemView.findViewById(R.id.list_item_content_iv);
				titleTv = (TextView) itemView.findViewById(R.id.list_item_content_title_tv);
				dateTv = (TextView) itemView.findViewById(R.id.list_item_content_date_tv);
			}
		}
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		ButterKnife.reset(this);
	}
}
