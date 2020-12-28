package com.android.testdemo.base;

import android.app.Application;

import com.android.testdemo.utils.LogUtil;
import com.tencent.smtt.sdk.QbSdk;

/**
 * 在 mainfast 文件中注册
 * <application
 *   android:name="com.example.textexception.AppContext"
 *   android:allowBackup="true"
 *   .....
 *   ></application>
 * 
 * @author pei
 */
public class AppContext extends Application{

	private static AppContext instance;

	public static synchronized AppContext getInstance() {
		return instance;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;
//		//bugly调试,第三个参数为SDK调试模式开关,建议在测试阶段建议设置成true，发布时设置为false
//		CrashReport.initCrashReport(getApplicationContext(), Constents.BUGLY_APP_ID, BuildConfig.DEBUG);

		X5Init();
	}

	public void X5Init() {
		//搜集本地tbs内核信息并上报服务器，服务器返回结果决定使用哪个内核。
		QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {
			@Override
			public void onViewInitFinished(boolean arg0) {
				// TODO Auto-generated method stub
				//x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
				LogUtil.e("X5", " onViewInitFinished is " + arg0);
			}

			@Override
			public void onCoreInitFinished() {
				LogUtil.e("X5", " onCoreInitFinished   @@@@@@@@@@" );
				// TODO Auto-generated method stub
			}
		};
		//x5内核初始化接口
		QbSdk.initX5Environment(getApplicationContext(),  cb);
	}

}
