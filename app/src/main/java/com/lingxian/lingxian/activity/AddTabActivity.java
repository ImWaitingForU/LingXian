package com.lingxian.lingxian.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.lingxian.lingxian.R;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AddTabActivity extends AppCompatActivity
		implements
			View.OnClickListener {

	public static final int FINISHSETTING = 0x123;
	public static final int RESULTCODE = 10;
	protected static ProgressDialog dialog;
	private TagFlowLayout tfl;
	private LayoutInflater inflater;
	private String[] tags = {"新奇", "文化", "礼物", "宠物", "喜好", "女友", "手办", "御宅"};
	private MyHandler handler = new MyHandler(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_tab);
		inflater = LayoutInflater.from(this);
		initTfl();
		initButton();
	}

	private void initButton() {
		ImageButton ib_back = (ImageButton) findViewById(R.id.bottom)
				.findViewById(R.id.ib_back);
		ib_back.setOnClickListener(this);
		ImageButton ib_ok = (ImageButton) findViewById(R.id.bottom)
				.findViewById(R.id.ib_ok);
		ib_ok.setOnClickListener(this);
	}

	private void initTfl() {
		tfl = (TagFlowLayout) findViewById(R.id.tagFlowLayout);
		tfl.setAdapter(new TagAdapter<String>(tags) {
			@Override
			public View getView(FlowLayout parent, int position, String s) {
				TextView tv = (TextView) inflater.inflate(R.layout.tv_layout,
						tfl, false);
				tv.setText(s);
				return tv;
			}
		});

		final TextView tv = (TextView) findViewById(R.id.header).findViewById(
				R.id.header_tv_title);
		tv.setText("添加分类");

		// 添加多选监听
		tfl.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
			@Override
			public void onSelected(Set<Integer> selectPosSet) {
				if (selectPosSet.size() == 0) {
					tv.setText("添加分类");
				} else {
					tv.setText("选择了:" + selectPosSet.size() + "项");
				}
			}
		});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.ib_back :
				backPressed();
				break;
			case R.id.ib_ok :
				okPressed();
				break;
			default :
				break;
		}
	}

	private void backPressed() {
		new AlertDialog.Builder(this).setMessage("确认不保存直接返回吗？")
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						finish();
					}
				})
				.setNegativeButton("取消", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {

					}
				}).show();
	}

	private void okPressed() {
		dialog = new ProgressDialog(this);
		dialog.setMessage("正在保存");
		dialog.show();
		dialog.setOnDismissListener (new DialogInterface.OnDismissListener () {
			@Override
			public void onDismiss (DialogInterface dialog) {
				Intent intent = new Intent();
				// 获取选中的标签
				Set<Integer> set = tfl.getSelectedList();
				List<String> resultTags = new ArrayList<>();

				if (set != null && set.size() != 0) {
					Log.d("tag", "选择了" + set.toString());
					for (int i : set) {
						resultTags.add(tags[i]);
					}
				}
				intent.putExtra("resultTags", (Serializable) resultTags);
				setResult(RESULTCODE, intent);
				finish();
			}
		});
		Message message = handler.obtainMessage(FINISHSETTING);
		// 假装与服务器通信
		handler.sendMessageDelayed(message, 2000);
	}

	private static class MyHandler extends Handler {
		private WeakReference<Context> reference;

		public MyHandler(Context context) {
			reference = new WeakReference<>(context);
		}

		@Override
		public void handleMessage(Message msg) {
			AddTabActivity activity = (AddTabActivity) reference.get();
			if (activity != null) {
				// 假装与网络通信
				if (msg.what == FINISHSETTING) {
					dialog.setMessage ("保存成功~");
					dialog.dismiss ();
				}
			}
		}
	}
}
