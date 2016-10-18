package com.medvision.vrdoctor.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.cs.networklibrary.http.HttpMethods;
import com.cs.networklibrary.http.HttpResultFunc;
import com.cs.networklibrary.subscribers.ProgressSubscriber;
import com.cs.widget.recyclerview.DividerGridItemDecoration;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.medvision.vrdoctor.R;
import com.medvision.vrdoctor.beans.ContentFilter;
import com.medvision.vrdoctor.beans.HomeContent;
import com.medvision.vrdoctor.beans.requestbody.BaseReq;
import com.medvision.vrdoctor.beans.requestbody.HomeContentReq;
import com.medvision.vrdoctor.network.ContentService;
import com.medvision.vrdoctor.utils.SpUtils;
import com.medvision.vrdoctor.view.popupwindow.PopupUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 *
 *
 */
public class ContentFragment extends Fragment {
	@InjectView(R.id.content_search_et)
	EditText mContentSearchEt;
	@InjectView(R.id.content_rv)
	XRecyclerView mContentRv;
	@InjectView(R.id.content_bingzhong_bt)
	Button mContentBingzhongBt;
	@InjectView(R.id.content_neirong_bt)
	Button mContentNeirongBt;
	@InjectView(R.id.content_liaofa_bt)
	Button mContentLiaofaBt;

	private MyContentAdapter mMyContentAdapter;
	private ContentService mContentService;
	private HomeContentReq mHomeContentReq;
	private int currentPage = 1;

	private boolean diseaseLoaded = false;
	private boolean therapyLoaded = false;
	private boolean typeLoaded = false;

	private View diseaseFilterView;

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
		mContentService = HttpMethods.getInstance().getClassInstance(ContentService.class);
		mHomeContentReq = new HomeContentReq();
		mHomeContentReq.setToken(SpUtils.getInstance().getToken());
		GridLayoutManager glm = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
		mContentRv.setLayoutManager(glm);
		mMyContentAdapter = new MyContentAdapter();
		mContentRv.setAdapter(mMyContentAdapter);
		mContentRv.addItemDecoration(new DividerGridItemDecoration(getContext()).setDividerDrawable(getResources().getDrawable(R.drawable.shape_divider_ststem_bg)));
		requestHomeContent();
		return view;
	}

	@OnClick({R.id.content_bingzhong_bt, R.id.content_neirong_bt, R.id.content_liaofa_bt})
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.content_bingzhong_bt:
				showContentDisease(null);
				break;
			case R.id.content_neirong_bt:
				break;
			case R.id.content_liaofa_bt:
				break;
		}
	}

	private void requestHomeContent() {
		mHomeContentReq.setPage(currentPage);
		BaseReq br = new BaseReq(SpUtils.getInstance().getToken());
		mContentService.getFilterType(br)
				.map(new HttpResultFunc<>())
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe();
	}

	private void requestContentDisease() {
		mContentService.getFilterDisease(new BaseReq(SpUtils.getInstance().getToken()))
				.map(new HttpResultFunc<>())
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new ProgressSubscriber<>(getActivity(), contentFilters -> {
					diseaseLoaded = true;
//					showContentDisease();
				}));
	}

	private class MyContentAdapter extends RecyclerView.Adapter<MyContentAdapter.ViewHolder> {
		private List<HomeContent> datas = new ArrayList<>();

		public void setDatas(List<HomeContent> list) {
			this.datas.clear();
			this.datas.addAll(list);
			this.notifyDataSetChanged();
		}

		public void addDatas(List<HomeContent> list) {
			this.datas.addAll(list);
			this.notifyDataSetChanged();
		}
		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			return new ViewHolder(View.inflate(parent.getContext(), R.layout.list_item_content, null));
		}

		@Override
		public void onBindViewHolder(ViewHolder holder, int position) {
			HomeContent content = datas.get(position);
			holder.tv.setText(content.getName());
			Picasso.with(getActivity())
					.load(content.getCoverPic())
					.into(holder.iv);
		}

		@Override
		public int getItemCount() {
			return datas.size();
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

	private void showContentDisease(List<ContentFilter> filters) {
		if (diseaseLoaded) {
			if (diseaseFilterView == null) {
				diseaseFilterView = View.inflate(getActivity(), R.layout.popup_disease_list, null);


			} else {
				new PopupUtils(getActivity()).setContentView(diseaseFilterView).show();
			}
		} else {
			requestContentDisease();
		}
	}

	private class MyFliterAdapter extends RecyclerView.Adapter<MyFliterAdapter.ViewHolder> {

		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			return new ViewHolder(View.inflate(getActivity(), R.layout.list_item_content_filter, null));
		}

		@Override
		public void onBindViewHolder(ViewHolder holder, int position) {

		}

		@Override
		public int getItemCount() {
			return 0;
		}

		class ViewHolder extends RecyclerView.ViewHolder {

			TextView name;
			TextView spell;

			ViewHolder(View itemView) {
				super(itemView);
				name = (TextView) itemView.findViewById(R.id.list_item_content_filter_name_tv);
				spell = (TextView) itemView.findViewById(R.id.list_item_content_filter_spell_tv);
			}
		}
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		ButterKnife.reset(this);
	}
}
