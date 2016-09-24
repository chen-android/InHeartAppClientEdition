package com.medvision.vruser.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cs.widget.recyclerview.supprot.BladeView;
import com.inheart.inheartapp.R;
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


	private ArrayList<Disease> diseases = new ArrayList<>();
	private MyDiseaseAdapter myDiseaseAdapter;
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
		myDiseaseAdapter = new MyDiseaseAdapter();
		layoutManager = new LinearLayoutManager(getActivity());
		layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
		mConsultationDiseaseRv.setLayoutManager(layoutManager);
		mConsultationDiseaseRv.setAdapter(myDiseaseAdapter);
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
	}

//	@OnCheckedChanged(R.id.consultation_type_rg)
//	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//		if (buttonView == mConsultationTypeExpertRb) {
//			Toast.makeText(buttonView.getContext(),"haha",Toast.LENGTH_SHORT).show();
//		}
//
//	}

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

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		ButterKnife.reset(this);
	}
}
