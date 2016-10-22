package com.medvision.vrdoctor.fragment;


import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.medvision.vrdoctor.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 *
 */
public class ConsultationFragment extends BaseFragment {


	@InjectView(R.id.consultation_rv)
	XRecyclerView mConsultationRv;

	public ConsultationFragment() {
	}

	public static ConsultationFragment newInstance() {
		return new ConsultationFragment();
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_consultation, container, false);
		ButterKnife.inject(this, view);
		return view;
	}

	private void requestConsultation() {

	}

	@Override
	public boolean onBackPressed() {
		return false;
	}

	private class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
		@Override
		public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			return new ViewHolder(View.inflate(parent.getContext(), R.layout.list_item_consultation, null));
		}

		@Override
		public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {

		}

		@Override
		public int getItemCount() {
			return 0;
		}

		class ViewHolder extends RecyclerView.ViewHolder {
			ImageView iv;
			TextView name;
			TextView tag;
			TextView date;

			public ViewHolder(View itemView) {
				super(itemView);
				iv = (ImageView) itemView.findViewById(R.id.list_item_consultation_iv);
				name = (TextView) itemView.findViewById(R.id.list_item_consultation_name_tv);
				tag = (TextView) itemView.findViewById(R.id.list_item_consultation_tag_tv);
				date = (TextView) itemView.findViewById(R.id.list_item_consultation_date_tv);
			}
		}
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		ButterKnife.reset(this);
	}
}
