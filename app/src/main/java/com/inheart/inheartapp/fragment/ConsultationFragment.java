package com.inheart.inheartapp.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.cs.widget.recyclerview.supprot.BladeView;
import com.inheart.inheartapp.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 *
 */
public class ConsultationFragment extends Fragment {
	@InjectView(R.id.consultation_type_disease_rb)
	RadioButton mConsultationTypeDiseaseRb;
	@InjectView(R.id.consultation_type_expert_rb)
	RadioButton mConsultationTypeExpertRb;
	@InjectView(R.id.consultation_type_rg)
	RadioGroup mConsultationTypeRg;
	@InjectView(R.id.consultation_disease_rv)
	RecyclerView mConsultationDiseaseRv;
	@InjectView(R.id.consultation_disease_bv)
	BladeView mConsultationDiseaseBv;
	@InjectView(R.id.consultation_disease_rl)
	RelativeLayout mConsultationDiseaseRl;


	public ConsultationFragment() {
	}

	public static ConsultationFragment newInstance() {
		ConsultationFragment fragment = new ConsultationFragment();
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_consultation, container, false);
		ButterKnife.inject(this, view);

		return view;
	}

	private class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			return null;
		}

		@Override
		public void onBindViewHolder(ViewHolder holder, int position) {

		}

		@Override
		public int getItemCount() {
			return 0;
		}

		class ViewHolder extends RecyclerView.ViewHolder {

			public ViewHolder(View itemView) {
				super(itemView);
			}
		}
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		ButterKnife.reset(this);
	}
}
