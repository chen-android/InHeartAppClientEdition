package com.medvision.vrdoctor.activity.user;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cs.networklibrary.http.HttpMethods;
import com.cs.networklibrary.http.HttpResultFunc;
import com.cs.networklibrary.subscribers.ProgressSubscriber;
import com.cs.widget.utils.Navigation;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.medvision.vrdoctor.R;
import com.medvision.vrdoctor.beans.Collection;
import com.medvision.vrdoctor.beans.requestbody.CollectionListReq;
import com.medvision.vrdoctor.network.UserService;
import com.medvision.vrdoctor.utils.Constant;
import com.medvision.vrdoctor.utils.SpUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 个人收藏界面
 */
public class CollectionActivity extends AppCompatActivity {

	@InjectView(R.id.collection_rv)
	XRecyclerView mCollectionRv;

	private UserService mUserService;
	private CollectionListReq mCollectionListReq;
	private int currentPage = 1;
	private MyAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_collection);
		ButterKnife.inject(this);
		Navigation.getInstance(this).setTitle(getString(R.string.title_collection)).setBack();
		mUserService = HttpMethods.getInstance().getClassInstance(UserService.class);
		mAdapter = new MyAdapter();
		mCollectionRv.setAdapter(mAdapter);
		mCollectionListReq = new CollectionListReq();
		mCollectionListReq.setToken(SpUtils.getInstance().getToken());
		mCollectionListReq.setPaging(currentPage);
		requestCollectionList(mCollectionListReq, Constant.REQUEST_REFRESH);
		mCollectionRv.setLoadingListener(new XRecyclerView.LoadingListener() {
			@Override
			public void onRefresh() {
				currentPage = 1;
				mCollectionListReq.setPaging(currentPage);
				requestCollectionList(mCollectionListReq, Constant.REQUEST_REFRESH);
			}

			@Override
			public void onLoadMore() {
				currentPage++;
				mCollectionListReq.setPaging(currentPage);
				requestCollectionList(mCollectionListReq, Constant.REQUEST_MORE);
			}
		});
	}

	private void requestCollectionList(CollectionListReq collectionListReq, int loadType) {
		mUserService.requestCollectionList(collectionListReq)
				.map(new HttpResultFunc<>())
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new ProgressSubscriber<>(this, collections -> setViewData(collections, loadType)));
	}

	private void setViewData(List<Collection> collections, int loadType) {
		if (loadType == Constant.REQUEST_MORE) {
			mAdapter.addData(collections);
		} else if (loadType == Constant.REQUEST_REFRESH) {
			mAdapter.setData(collections);
		}
	}

	private class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
		private List<Collection> data;

		public MyAdapter() {
			data = new ArrayList<>();
		}

		public void setData(List<Collection> list) {
			data.clear();
			data.addAll(list);
			this.notifyDataSetChanged();
		}

		public void addData(List<Collection> list) {
			data.addAll(list);
			this.notifyDataSetChanged();
		}

		@Override
		public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			return new MyAdapter.ViewHolder(View.inflate(parent.getContext(), R.layout.list_item_collection, null));
		}

		@Override
		public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {
			Collection collection = data.get(position);
			holder.title.setText(collection.getName());
			holder.date.setText(collection.getCreatedAt());
			Picasso.with(holder.itemView.getContext())
					.load(collection.getCoverPic())
					.into(holder.iv);
		}

		@Override
		public int getItemCount() {
			return data.size();
		}

		class ViewHolder extends RecyclerView.ViewHolder {
			ImageView iv;
			TextView title;
			TextView date;

			public ViewHolder(View itemView) {
				super(itemView);
				iv = (ImageView) itemView.findViewById(R.id.list_item_collection_iv);
				title = (TextView) itemView.findViewById(R.id.list_item_collection_title_tv);
				date = (TextView) itemView.findViewById(R.id.list_item_collection_date_tv);
			}
		}
	}
}
