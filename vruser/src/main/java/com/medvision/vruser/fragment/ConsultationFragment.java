package com.medvision.vruser.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cs.widget.recyclerview.supprot.BladeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.medvision.vruser.R;
import com.medvision.vruser.activity.consultation.ExpertDetailActivity;
import com.medvision.vruser.beans.Disease;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 *
 */
public class ConsultationFragment extends Fragment {
	private static final String TAG = ConsultationFragment.class.getSimpleName();
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
	@InjectView(R.id.consultation_type_place_rb)
	RadioButton mConsultationTypePlaceRb;
	@InjectView(R.id.consultation_expert_rv)
	XRecyclerView mConsultationExpertRv;
	@InjectView(R.id.consultation_expert_rl)
	RelativeLayout mConsultationExpertRl;


	private ArrayList<Disease> diseases = new ArrayList<>();
	private MyDiseaseAdapter myDiseaseAdapter;
	private MyExpertAdapter myExpertAdapter;
	private LinearLayoutManager layoutManager;
	private int index;

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
		initDisease();
		initExpert();
		mConsultationTypeRg.setOnCheckedChangeListener((radioGroup, i) -> {
			switch (i) {
				case R.id.consultation_type_expert_rb:
					mConsultationDiseaseRl.setVisibility(View.GONE);
					mConsultationExpertRl.setVisibility(View.VISIBLE);
					break;
				case R.id.consultation_type_disease_rb:
					mConsultationDiseaseRl.setVisibility(View.VISIBLE);
					mConsultationExpertRl.setVisibility(View.GONE);
					break;
			}
		});
		return view;
	}

	private void initDisease() {
		diseases.add(new Disease("感冒", "G"));
		diseases.add(new Disease("癌症", "A"));
		diseases.add(new Disease("产前xxxxxxx", "C"));
		diseases.add(new Disease("产前xxxxxxx", "C"));
		diseases.add(new Disease("产前xxxxxxx", "C"));
		diseases.add(new Disease("产前xxxxxxx", "C"));
		diseases.add(new Disease("产前xxxxxxx", "C"));
		diseases.add(new Disease("产前xxxxxxx", "C"));
		diseases.add(new Disease("产前xxxxxxx", "C"));
		diseases.add(new Disease("地位", "D"));
		diseases.add(new Disease("癫痫", "D"));
		diseases.add(new Disease("儿童xxxxx", "R"));
		diseases.add(new Disease("儿童xxxxx", "R"));
		diseases.add(new Disease("儿童xxxxx", "R"));
		diseases.add(new Disease("儿童xxxxx", "R"));
		diseases.add(new Disease("儿童xxxxx", "R"));
		diseases.add(new Disease("非器质性xxxx", "F"));
		diseases.add(new Disease("非器质性xxxx", "F"));
		diseases.add(new Disease("非器质性xxxx", "F"));
		diseases.add(new Disease("非器质性xxxx", "F"));

		Collections.sort(diseases, (o1, o2) -> o1.getFirstSpell().compareTo(o2.getFirstSpell()));

		ArrayList<String> spellListForBladeView = new ArrayList<>();
		ArrayList<String> allSpellList = new ArrayList<>();
		String A = "";
		String B = "";
		for (int i = 0; i < diseases.size(); i++) {
			B = diseases.get(i).getFirstSpell();
			allSpellList.add(B);
			if (!A.equals(B)) {
				spellListForBladeView.add(B);
				A = B;
			}

		}
		mConsultationDiseaseBv.setList(spellListForBladeView.toArray(new String[spellListForBladeView.size()]));
		mConsultationDiseaseBv.setOnItemClickListener(s -> layoutManager.scrollToPositionWithOffset(allSpellList.indexOf(s), 0));

		myDiseaseAdapter = new MyDiseaseAdapter();
		layoutManager = new LinearLayoutManager(getActivity());
		layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
		mConsultationDiseaseRv.setLayoutManager(layoutManager);
		mConsultationDiseaseRv.setAdapter(myDiseaseAdapter);

	}

	private void initExpert() {
		myExpertAdapter = new MyExpertAdapter();
		layoutManager = new LinearLayoutManager(getActivity());
		layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
		mConsultationExpertRv.setLayoutManager(layoutManager);
		mConsultationExpertRv.setAdapter(myExpertAdapter);
	}

	private class MyDiseaseAdapter extends RecyclerView.Adapter<MyDiseaseAdapter.ViewHolder> {

		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			View v = View.inflate(parent.getContext(), R.layout.list_item_disease, null);
			v.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
			return new ViewHolder(v);
		}

		@Override
		public void onBindViewHolder(ViewHolder holder, int position) {
			Disease d = diseases.get(position);
			if (position == 0 || !d.getFirstSpell().equals(diseases.get(position - 1).getFirstSpell())) {
				holder.spell.setVisibility(View.VISIBLE);
			} else {
				holder.spell.setVisibility(View.GONE);
			}
			holder.name.setText(d.getDiseaseName());
			holder.spell.setText(d.getFirstSpell());
		}

		@Override
		public int getItemCount() {
			return diseases.size();
		}

		class ViewHolder extends RecyclerView.ViewHolder {
			TextView name;
			TextView spell;

			ViewHolder(View itemView) {
				super(itemView);
				name = (TextView) itemView.findViewById(R.id.item_disease_name_tv);
				spell = (TextView) itemView.findViewById(R.id.item_disease_spell_tv);
			}
		}
	}

	private class MyExpertAdapter extends RecyclerView.Adapter<MyExpertAdapter.ViewHolder> {

		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			View v = View.inflate(parent.getContext(), R.layout.list_item_expert, null);
			v.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
			return new ViewHolder(v);
		}

		@Override
		public void onBindViewHolder(ViewHolder holder, int position) {
			holder.itemView.setOnClickListener(v -> startActivity(new Intent(getContext(), ExpertDetailActivity.class)));
		}

		@Override
		public int getItemCount() {
			return 5;
		}

		class ViewHolder extends RecyclerView.ViewHolder {
			ImageView iv;
			TextView name;
			TextView title;
			TextView askTimes;
			TextView location;
			TextView tip;

			ViewHolder(View itemView) {
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
