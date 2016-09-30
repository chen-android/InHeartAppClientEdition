package com.medvision.vrdoctor.activity.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.cs.widget.utils.Navigation;
import com.medvision.vrdoctor.R;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;

public class DoctorVerify1Activity extends AppCompatActivity {

	private static final int REQUEST_IMAGE = 1;
	@InjectView(R.id.doctor_verify_iv)
	ImageView mDoctorVerifyIv;
	@InjectView(R.id.doctor_verify_next_bt)
	Button mDoctorVerifyNextBt;
	@InjectView(R.id.activity_doctor_verify)
	LinearLayout mActivityDoctorVerify;
	@InjectView(R.id.doctor_verify_name_et)
	EditText mDoctorVerifyNameEt;
	@InjectView(R.id.doctor_verify_id_et)
	EditText mDoctorVerifyIdEt;
	private ArrayList<String> paths = new ArrayList<>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_doctor_verify1);
		ButterKnife.inject(this);
		Navigation.getInstance(this).setBack().setTitle(getString(R.string.title_doctor_verify));

	}

	@OnClick({R.id.doctor_verify_iv, R.id.doctor_verify_next_bt})
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.doctor_verify_iv:
				imgSelect();
				break;
			case R.id.doctor_verify_next_bt:
				break;
		}
	}

	private void imgSelect() {
		Intent intent = new Intent(this, MultiImageSelectorActivity.class);
		intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA, true);
		// max select image amount
		// select mode (MultiImageSelectorActivity.MODE_SINGLE OR MultiImageSelectorActivity.MODE_MULTI)
		intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE, MultiImageSelectorActivity.MODE_SINGLE);
		// default select images (support array list)
		intent.putStringArrayListExtra(MultiImageSelectorActivity.EXTRA_DEFAULT_SELECTED_LIST, paths);
		startActivityForResult(intent, REQUEST_IMAGE);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == REQUEST_IMAGE) {
			if (resultCode == RESULT_OK) {
				// Get the result list of select image paths
				List<String> path = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
				paths.clear();
				paths.addAll(path);
				Picasso.with(this).load(new File(path.get(0))).into(mDoctorVerifyIv);
			}
		}
	}
}
