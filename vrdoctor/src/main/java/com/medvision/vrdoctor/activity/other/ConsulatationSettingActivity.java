package com.medvision.vrdoctor.activity.other;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.cs.common.utils.ToastUtil;
import com.cs.networklibrary.http.HttpMethods;
import com.cs.networklibrary.http.HttpResultFunc;
import com.cs.networklibrary.subscribers.ProgressSubscriber;
import com.cs.widget.utils.Navigation;
import com.medvision.vrdoctor.R;
import com.medvision.vrdoctor.beans.requestbody.ConsulationSetReq;
import com.medvision.vrdoctor.network.ConsulationService;
import com.medvision.vrdoctor.utils.SpUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ConsulatationSettingActivity extends AppCompatActivity {

	@InjectView(R.id.consulation_setting_et)
	EditText mConsulationSettingEt;
	@InjectView(R.id.consulation_setting_commit_bt)
	Button mConsulationSettingCommitBt;

	private ConsulationService service;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_consulatation_setting);
		ButterKnife.inject(this);
		Navigation.getInstance(this).setBack().setTitle(getString(R.string.title_consulation));
		service = HttpMethods.getInstance().getClassInstance(ConsulationService.class);
	}


	private void requestConsulationSet() {
		ConsulationSetReq req = new ConsulationSetReq();
		req.setDoctorID(SpUtils.getInstance().getUser().getUid());
		service.requestSetConsulationPrice(req)
				.map(new HttpResultFunc<>())
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new ProgressSubscriber<>(ConsulatationSettingActivity.this, noData -> {
					ToastUtil.showMessage(ConsulatationSettingActivity.this, "设置成功");
				}));
	}

	@OnClick(R.id.consulation_setting_commit_bt)
	public void onClick() {
		requestConsulationSet();
	}
}
