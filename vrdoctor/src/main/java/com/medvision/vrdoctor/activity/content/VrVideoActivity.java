package com.medvision.vrdoctor.activity.content;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.medvision.vrdoctor.R;
import com.medvision.vrdoctor.utils.VrVideoUtils;
import com.utovr.player.UVEventListener;
import com.utovr.player.UVInfoListener;
import com.utovr.player.UVMediaPlayer;
import com.utovr.player.UVMediaType;
import com.utovr.player.UVPlayerCallBack;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class VrVideoActivity extends AppCompatActivity implements UVPlayerCallBack {

	@InjectView(R.id.activity_rlToolbar)
	RelativeLayout mActivityRlToolbar;
	@InjectView(R.id.activity_rlParent)
	RelativeLayout mActivityRlParent;
	@InjectView(R.id.activity_imgBack)
	ImageView mActivityImgBack;
	@InjectView(R.id.activity_imgBuffer)
	ImageView mActivityImgBuffer;
	@InjectView(R.id.activity_rlPlayView)
	RelativeLayout mActivityRlPlayView;
	@InjectView(R.id.vr_video_title_tv)
	TextView mVrVideoTitleTv;
	@InjectView(R.id.vr_video_play_times_tv)
	TextView mVrVideoPlayTimesTv;
	@InjectView(R.id.vr_video_date_tv)
	TextView mVrVideoDateTv;
	@InjectView(R.id.vr_video_name_tv)
	TextView mVrVideoNameTv;
	@InjectView(R.id.vr_video_collect_iv)
	ImageView mVrVideoCollectIv;
	@InjectView(R.id.vr_video_content_tv)
	TextView mVrVideoContentTv;

	private UVMediaPlayer mMediaplayer = null;  // 媒体视频播放器
	private VideoController mCtrl = null;
	//	private String Path = "http://cache.utovr.com/201508270528174780.m3u8";
	private String Path = "";
	private boolean bufferResume = true;
	private boolean needBufferAnim = true;
	protected int CurOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
	private int SmallPlayH = 0;
	private boolean colseDualScreen = false;

	private String contentId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vr_video);
		ButterKnife.inject(this);
		contentId = getIntent().getStringExtra("contentId");
		mMediaplayer = new UVMediaPlayer(VrVideoActivity.this, mActivityRlPlayView);
		//将工具条的显示或隐藏交个SDK管理，也可自己管理
		mMediaplayer.setToolbar(mActivityRlToolbar, null, mActivityImgBack);
		mCtrl = new VideoController(mActivityRlToolbar, playerControl, true);
		changeOrientation(false);
	}

	@Override
	public void onResume() {
		super.onResume();
		if (mMediaplayer != null) {
			mMediaplayer.onResume(VrVideoActivity.this);
		}
	}

	@Override
	public void onPause() {
		super.onPause();
		if (mMediaplayer != null) {
			mMediaplayer.onPause();
		}
	}

	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK) {
			back();
			return true;
		}
		return super.onKeyUp(keyCode, event);
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		changeOrientation(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE);
	}

	@Override
	public void onDestroy() {
		releasePlayer();
		super.onDestroy();
	}

	@OnClick({R.id.activity_imgBack, R.id.vr_video_collect_iv})
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.activity_imgBack:
				back();
				break;
			case R.id.vr_video_collect_iv:
				break;
		}
	}

	private void changeOrientation(boolean isLandscape) {
		if (mActivityRlParent == null) {
			return;
		}
		if (isLandscape) {
			CurOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
			getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
			getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN
					| WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
			RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
					RelativeLayout.LayoutParams.MATCH_PARENT,
					RelativeLayout.LayoutParams.MATCH_PARENT);
			mActivityRlParent.setLayoutParams(lp);
			if (colseDualScreen && mMediaplayer != null) {
				mCtrl.setDualScreenEnabled(true);
			}
			colseDualScreen = false;
			mCtrl.changeOrientation(true, 0);
		} else {
			CurOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
			getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
			getWindow().addFlags(
					WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
			getSmallPlayHeight();
			RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, SmallPlayH);
			mActivityRlParent.setLayoutParams(lp);
			if (mMediaplayer != null && mMediaplayer.isDualScreenEnabled()) {
				mCtrl.setDualScreenEnabled(false);
				colseDualScreen = true;
			}
			mCtrl.changeOrientation(false, 0);
		}
	}

	private void getSmallPlayHeight() {
		if (this.SmallPlayH != 0) {
			return;
		}
		int ScreenW = getWindowManager().getDefaultDisplay().getWidth();
		int ScreenH = getWindowManager().getDefaultDisplay().getHeight();
		if (ScreenW > ScreenH) {
			int temp = ScreenW;
			ScreenW = ScreenH;
			ScreenH = temp;
		}
		SmallPlayH = ScreenW * ScreenW / ScreenH;
	}

	private void back() {
		if (CurOrientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
			releasePlayer();
			finish();
		} else {
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		}
	}

	public void releasePlayer() {
		if (mMediaplayer != null) {
			mMediaplayer.release();
			mMediaplayer = null;
		}
	}

	@Override
	public void createEnv() {
		try {
			// 创建媒体视频播放器
			mMediaplayer.initPlayer();
			mMediaplayer.setListener(mListener);
			mMediaplayer.setInfoListener(mInfoListener);
			//如果是网络MP4，可调用 mCtrl.startCachePro();mCtrl.stopCachePro();
			mMediaplayer.setSource(UVMediaType.UVMEDIA_TYPE_M3U8, Path);
			//mMediaplayer.setSource(UVMediaType.UVMEDIA_TYPE_MP4, "/sdcard/wu.mp4");
		} catch (Exception e) {
			Log.e("utovr", e.getMessage(), e);
		}
	}

	private UVEventListener mListener = new UVEventListener() {
		@Override
		public void onStateChanged(int playbackState) {
			switch (playbackState) {
				case UVMediaPlayer.STATE_PREPARING:
					break;
				case UVMediaPlayer.STATE_BUFFERING:
					if (needBufferAnim && mMediaplayer != null && mMediaplayer.isPlaying()) {
						bufferResume = true;
						VrVideoUtils.setBufferVisibility(mActivityImgBuffer, true);
					}
					break;
				case UVMediaPlayer.STATE_READY:
					// 设置时间和进度条
					mCtrl.setInfo();
					if (bufferResume) {
						bufferResume = false;
						VrVideoUtils.setBufferVisibility(mActivityImgBuffer, false);
					}
					break;
				case UVMediaPlayer.STATE_ENDED:
					//这里是循环播放，可根据需求更改
					mMediaplayer.replay();
					break;
				case UVMediaPlayer.TRACK_DISABLED:
				case UVMediaPlayer.TRACK_DEFAULT:
					break;
			}
		}

		@Override
		public void onError(Exception e, int ErrType) {
			Toast.makeText(VrVideoActivity.this, VrVideoUtils.getErrMsg(ErrType), Toast.LENGTH_SHORT).show();
		}

		@Override
		public void onVideoSizeChanged(int width, int height) {
		}

	};

	private UVInfoListener mInfoListener = new UVInfoListener() {
		@Override
		public void onBandwidthSample(int elapsedMs, long bytes, long bitrateEstimate) {
		}

		@Override
		public void onLoadStarted() {
		}

		@Override
		public void onLoadCompleted() {
			if (bufferResume) {
				bufferResume = false;
				VrVideoUtils.setBufferVisibility(mActivityImgBuffer, false);
			}
			if (mCtrl != null) {
				mCtrl.updateBufferProgress();
			}

		}
	};

	@Override
	public void updateProgress(long l) {
		if (mCtrl != null) {
			mCtrl.updateCurrentPosition();
		}
	}

	private VideoController.PlayerControl playerControl = new VideoController.PlayerControl() {
		@Override
		public void seekTo(long positionMs) {
			if (mMediaplayer != null)
				mMediaplayer.seekTo(positionMs);
		}

		@Override
		public void play() {
			if (mMediaplayer != null && !mMediaplayer.isPlaying()) {
				mMediaplayer.play();
			}
		}

		@Override
		public void pause() {
			if (mMediaplayer != null && mMediaplayer.isPlaying()) {
				mMediaplayer.pause();
			}
		}

		@Override
		public long getDuration() {
			return mMediaplayer != null ? mMediaplayer.getDuration() : 0;
		}

		@Override
		public long getBufferedPosition() {
			return mMediaplayer != null ? mMediaplayer.getBufferedPosition() : 0;
		}

		@Override
		public long getCurrentPosition() {
			return mMediaplayer != null ? mMediaplayer.getCurrentPosition() : 0;
		}

		@Override
		public void setGyroEnabled(boolean val) {
			if (mMediaplayer != null)
				mMediaplayer.setGyroEnabled(val);
		}

		@Override
		public boolean isGyroEnabled() {
			return mMediaplayer != null && mMediaplayer.isGyroEnabled();
		}

		@Override
		public void setDualScreenEnabled(boolean val) {
			if (mMediaplayer != null)
				mMediaplayer.setDualScreenEnabled(val);
		}

		@Override
		public boolean isDualScreenEnabled() {
			return mMediaplayer != null && mMediaplayer.isDualScreenEnabled();
		}

		@Override
		public void toFullScreen() {
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		}

		@Override
		public void toolbarTouch(boolean start) {
			if (mMediaplayer != null) {
				if (start) {
					mMediaplayer.cancelHideToolbar();
				} else {
					mMediaplayer.hideToolbarLater();
				}
			}
		}
	};
}
