package com.makingware.helloandroid;

import java.io.File;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Í¼Æ¬²Ã¼ô
 * @author liux
 *
 */
public class CropImageActivity extends Activity {
	/** Called when the activity is first created. */
	private Button selectImageBtn;
	private ImageView imageView;
	
	private File sdcardTempFile;
	private AlertDialog dialog;
	private int crop = 180;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cropimage);

		selectImageBtn = (Button) findViewById(R.id.selectImageBtn);
		imageView = (ImageView) findViewById(R.id.cropimageView);

		selectImageBtn.setOnClickListener(selectImageClick);
		sdcardTempFile = new File("/mnt/sdcard/", "tmp_pic_" + SystemClock.currentThreadTimeMillis() + ".jpg");

	}

	private View.OnClickListener selectImageClick = new View.OnClickListener(){
		public void onClick(View v) {
			if (v == selectImageBtn) {
				if (dialog == null) {
					dialog = new AlertDialog.Builder(CropImageActivity.this).setItems(new String[] { "Ïà»ú", "Ïà²á" }, new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							if (which == 0) {
								Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
								intent.putExtra("output", Uri.fromFile(sdcardTempFile));
								intent.putExtra("crop", "true");
								intent.putExtra("aspectX", 1);// ²Ã¼ô¿ò±ÈÀý
								intent.putExtra("aspectY", 1);
								intent.putExtra("outputX", crop);// Êä³öÍ¼Æ¬´óÐ¡
								intent.putExtra("outputY", crop);
								startActivityForResult(intent, 101);
							} else {
								Intent intent = new Intent("android.intent.action.PICK");
								intent.setDataAndType(MediaStore.Images.Media.INTERNAL_CONTENT_URI, "image/*");
								intent.putExtra("output", Uri.fromFile(sdcardTempFile));
								intent.putExtra("crop", "true");
								intent.putExtra("aspectX", 1);// ²Ã¼ô¿ò±ÈÀý
								intent.putExtra("aspectY", 1);
								intent.putExtra("outputX", crop);// Êä³öÍ¼Æ¬´óÐ¡
								intent.putExtra("outputY", crop);
								startActivityForResult(intent, 100);
							}
						}
					}).create();
				}
				if (!dialog.isShowing()) {
					dialog.show();
				}
			}
		}
	};	

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
		if (resultCode == RESULT_OK) {
			Bitmap bmp = BitmapFactory.decodeFile(sdcardTempFile.getAbsolutePath());
			imageView.setImageBitmap(bmp);
		}
	}
}
