package jp.co.cosmoroot.android.samples;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class NotifySample extends Activity {

	private Context m_context = null;

	// 通知領域
	private NotificationManager m_notificationManager =null;
	private Notification m_notification = null;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        m_context = this;

		// 通知領域への表示
		m_notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

		m_notification = new Notification(
				R.drawable.icon,
				"ロギングを開始しました。",
				System.currentTimeMillis());

		final TextView textView = (TextView)findViewById(R.id.TextView01);

        // 通知の表示
        Button button1 = (Button)findViewById(R.id.Button01);
        button1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				textView.setText("通知を開始しました");
				
				// intentの設定
				PendingIntent contentIntent =
						PendingIntent.getActivity(m_context, 0, getIntent(), 0);

				m_notification.setLatestEventInfo(
						getApplicationContext(),
						"アプリ名",
						"通知情報の説明文",
						contentIntent);

				m_notification.flags = Notification.FLAG_ONGOING_EVENT; // 「実行中」に表示する
				m_notificationManager.notify(R.string.app_name, m_notification);
			}
		});


        // 通知の消去
        Button button2 = (Button)findViewById(R.id.Button02);
        button2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				textView.setText("通知を停止しました");

				// 通知を消す。
				m_notificationManager.cancelAll();
			}
		});

    }

	/* (非 Javadoc)
	 * @see android.app.Activity#onDestroy()
	 */
	@Override
	protected void onDestroy() {
		super.onDestroy();

		// 通知の消去
		m_notificationManager.cancelAll();
	}
}