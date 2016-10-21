package com.medvision.vrdoctor.fragment;


import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
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
import com.cs.widget.recyclerview.RecyclerViewDivider;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.medvision.vrdoctor.R;
import com.medvision.vrdoctor.beans.FilterDisease;
import com.medvision.vrdoctor.beans.FilterTherapy;
import com.medvision.vrdoctor.beans.FilterType;
import com.medvision.vrdoctor.beans.HomeContent;
import com.medvision.vrdoctor.beans.requestbody.BaseReq;
import com.medvision.vrdoctor.beans.requestbody.HomeContentReq;
import com.medvision.vrdoctor.network.ContentService;
import com.medvision.vrdoctor.utils.Constant;
import com.medvision.vrdoctor.utils.SpUtils;
import com.medvision.vrdoctor.view.popupwindow.PopupUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 *
 *
 */
public class ContentFragment extends BaseFragment {
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

	private List<FilterType> mFilterTypes = new ArrayList<>();
	private List<FilterDisease> mFilterDiseases = new ArrayList<>();
	private List<FilterTherapy> mFilterTherapies = new ArrayList<>();

	private View diseaseFilterView;
	private PopupUtils mPopupUtils;

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
		requestContentFilter();
		requestContent(Constant.REQUEST_REFRESH);
		return view;
	}

	@OnClick({R.id.content_bingzhong_bt, R.id.content_neirong_bt, R.id.content_liaofa_bt})
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.content_bingzhong_bt:
				showContentDisease();
				break;
			case R.id.content_neirong_bt:
				break;
			case R.id.content_liaofa_bt:
				break;
		}
	}

	private void requestContentFilter() {
		mHomeContentReq.setPage(currentPage);
		BaseReq br = new BaseReq(SpUtils.getInstance().getToken());
		mContentService.getFilterType(br)
				.map(new HttpResultFunc<>())
				.flatMap(filterTypes -> {
					mFilterTypes.addAll(filterTypes);
					return mContentService.getFilterDisease(br);
				})
				.map(new HttpResultFunc<>())
				.flatMap(filterDiseases -> {
					mFilterDiseases.addAll(filterDiseases);
					return mContentService.getFilterTherapy(br);
				})
				.map(new HttpResultFunc<>())
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Subscriber<List<FilterTherapy>>() {
					@Override
					public void onCompleted() {
						initFilterView();
					}

					@Override
					public void onError(Throwable e) {

					}

					@Override
					public void onNext(List<FilterTherapy> filterTherapies) {
						mFilterTherapies.addAll(filterTherapies);
					}
				});
	}

	private void requestContent(int requestType) {
		mContentService.getSearchContent(mHomeContentReq)
				.map(new HttpResultFunc<>())
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new ProgressSubscriber<>(getActivity(), homeContents -> {
					if (requestType == Constant.REQUEST_REFRESH) {
						mMyContentAdapter.setDatas(homeContents);
					} else if (requestType == Constant.REQUEST_MORE) {
						mMyContentAdapter.addDatas(homeContents);
					}
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

	private void initFilterView() {
		mPopupUtils = new PopupUtils(getActivity());
		diseaseFilterView = View.inflate(getContext(), R.layout.popup_disease_list, null);
		RecyclerView rv = (RecyclerView) diseaseFilterView.findViewById(R.id.popup_disease_rv);
		LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
		rv.setLayoutManager(linearLayoutManager);
		rv.addItemDecoration(new RecyclerViewDivider(getContext(), LinearLayoutManager.VERTICAL));
		MyFilterAdapter diseaseAdapter = new MyFilterAdapter();
		List<FilterDisease.DiseaseItem> items = new ArrayList<>();
		for (FilterDisease filterDisease : mFilterDiseases) {
			for (FilterDisease.DiseaseItem diseaseItem : filterDisease.getArray()) {
				diseaseItem.setLetter(filterDisease.getLetter());
				items.add(diseaseItem);
			}
		}
		diseaseAdapter.setDiseaseItems(items);
		rv.setAdapter(diseaseAdapter);
	}

	private void showContentDisease() {
		if (mPopupUtils != null) {
			mPopupUtils.setContentView(diseaseFilterView).show();
		}
	}

	private class MyFilterAdapter extends RecyclerView.Adapter<MyFilterAdapter.ViewHolder> {

		private List<FilterDisease.DiseaseItem> mDiseaseItems;

		public MyFilterAdapter() {
			mDiseaseItems = new ArrayList<>();
		}

		public void setDiseaseItems(List<FilterDisease.DiseaseItem> items) {
			this.mDiseaseItems.clear();
			this.mDiseaseItems.addAll(items);
		}

		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			View view = View.inflate(parent.getContext(), R.layout.list_item_content_filter, null);
			view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
			return new ViewHolder(view);
		}

		@Override
		public void onBindViewHolder(ViewHolder holder, int position) {
			FilterDisease.DiseaseItem item = mDiseaseItems.get(position);
			if (position == 0) {
				holder.spell.setText(item.getLetter());
			} else {
				if (mDiseaseItems.get(position - 1).getLetter().equals(item.getLetter())) {
					holder.spell.setVisibility(View.GONE);
				} else {
					holder.spell.setText(item.getLetter());
				}
			}
			holder.name.setText(item.getName());
		}

		@Override
		public int getItemCount() {
			return mDiseaseItems.size();
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
	public boolean onBackPressed() {
		if (mPopupUtils.isShowing()) {
			mPopupUtils.dismissPopup();
			return true;
		}
		return false;
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		ButterKnife.reset(this);
	}
}
