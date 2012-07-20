package com.makingware.helloandroid;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class TextHtml extends Activity {
	private TextView tv;
	private static Context ctx;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		tv = new TextView(this);
		ctx = this;

		String htmlLinkText =  "���ǳ�����"+ "<a href='lianjie'><font color='red'>�����ӵ���¼�</font></a>";
		// ���ֵ���ʽ��style�������ǣ����ܸı䡭��

		tv.setText(Html.fromHtml(htmlLinkText));
		tv.setMovementMethod(LinkMovementMethod.getInstance());
		CharSequence text = tv.getText();
		if (text instanceof Spannable) {
			int end = text.length();
			Spannable sp = (Spannable) tv.getText();
			URLSpan[] urls = sp.getSpans(0, end, URLSpan.class);
			SpannableStringBuilder style = new SpannableStringBuilder(text);
			style.clearSpans();// should clear old spans

			//ѭ�������ӷ���ȥ			
			for (URLSpan url : urls) {
				MyURLSpan myURLSpan = new MyURLSpan(url.getURL());
				style.setSpan(myURLSpan, sp.getSpanStart(url),
						sp.getSpanEnd(url), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
			}
			tv.setText(style);
		}
		setContentView(tv);

	}

	private static class MyURLSpan extends ClickableSpan {

		private String mUrl;

		MyURLSpan(String url) {
			mUrl = url;
		}

		@Override
		public void onClick(View widget) {
			if(mUrl.equals("lianjie")){
				Toast.makeText(ctx, mUrl, Toast.LENGTH_LONG).show();
				widget.setBackgroundColor(Color.parseColor("#FFFFFFFF"));

			}
		}
	}
}

