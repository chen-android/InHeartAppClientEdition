package com.medvision.vrdoctor.activity.chat;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.ui.EaseBaseActivity;
import com.hyphenate.easeui.ui.EaseChatFragment;
import com.medvision.vrdoctor.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ChatActivity extends EaseBaseActivity {

	@InjectView(R.id.chat_fl)
	FrameLayout mChatFl;

	private EaseChatFragment chatFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chat);
		ButterKnife.inject(this);
		String userId = getIntent().getStringExtra("userId");
		chatFragment = new EaseChatFragment();
		Bundle b = new Bundle();
		b.putInt(EaseConstant.EXTRA_CHAT_TYPE, EaseConstant.CHATTYPE_CHATROOM);
		b.putString(EaseConstant.EXTRA_USER_ID, userId);
		chatFragment.setArguments(getIntent().getExtras());
		getSupportFragmentManager().beginTransaction().add(R.id.chat_fl, chatFragment).commit();
	}
}
