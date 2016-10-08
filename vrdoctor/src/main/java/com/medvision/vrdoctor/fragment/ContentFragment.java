package com.medvision.vrdoctor.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.cs.widget.recyclerview.DividerGridItemDecoration;
import com.medvision.vrdoctor.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 *
 *
 */
public class ContentFragment extends Fragment {
	@InjectView(R.id.content_search_et)
	EditText mContentSearchEt;
	@InjectView(R.id.content_bingzhong_rb)
	RadioButton mContentBingzhongRb;
	@InjectView(R.id.content_neirong_rb)
	RadioButton mContentNeirongRb;
	@InjectView(R.id.content_liaofa_rb)
	RadioButton mContentLiaofaRb;
	@InjectView(R.id.content_tab_rg)
	RadioGroup mContentTabRg;
	@InjectView(R.id.content_rv)
	RecyclerView mContentRv;

	private MyContentAdapter adapter;

	public static ContentFragment newInstance() {
		ContentFragment fragment = new ContentFragment();
		return fragment;
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
		GridLayoutManager glm = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
		mContentRv.setLayoutManager(glm);
		adapter = new MyContentAdapter();
		mContentRv.setAdapter(adapter);
		mContentRv.addItemDecoration(new DividerGridItemDecoration(getContext()).setDividerDrawable(getResources().getDrawable(R.drawable.shape_divider_ststem_bg)));
		return view;
	}

	private class MyContentAdapter extends RecyclerView.Adapter<MyContentAdapter.ViewHolder> {
		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			return new ViewHolder(View.inflate(parent.getContext(), R.layout.list_item_content, null));
		}

		@Override
		public void onBindViewHolder(ViewHolder holder, int position) {

		}

		@Override
		public int getItemCount() {
			return 20;
		}

		class ViewHolder extends RecyclerView.ViewHolder {
			ImageView iv;
			TextView tv;

			public ViewHolder(View itemView) {
				super(itemView);
				iv = (ImageView) itemView.findViewById(R.id.list_item_content_iv);
				tv = (TextView) itemView.findViewById(R.id.list_item_content_tv);
			}
		}
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		ButterKnife.reset(this);
	}
}
