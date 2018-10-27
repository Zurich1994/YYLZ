package whu.iss.sric.android;

import android.app.Activity;
import whu.iss.sric.view.GameView;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import android.content.Intent;

import android.os.Bundle;
import android.view.LayoutInflater;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;


public class MyDialog extends Dialog implements OnClickListener{

	private GameView gameview;
	private Context context;
	
	public MyDialog(Context context, GameView gameview, String msg, int time) {
		super(context,R.style.dialog);
		this.gameview = gameview;
		this.context = context;
		this.setContentView(R.layout.dialog_view);
		TextView text_msg = (TextView) findViewById(R.id.text_message);
		TextView text_time = (TextView) findViewById(R.id.text_time);
		ImageButton btn_menu = (ImageButton) findViewById(R.id.menu_imgbtn);
		ImageButton btn_next = (ImageButton) findViewById(R.id.next_imgbtn);
		ImageButton btn_replay = (ImageButton) findViewById(R.id.replay_imgbtn);
		
		text_msg.setText(msg);
		text_time.setText(R.string.time);
		btn_menu.setOnClickListener(this);
		btn_next.setOnClickListener(this);
		btn_replay.setOnClickListener(this);
		this.setCancelable(false);
	}

	@Override
	public void onClick(View v) {
		this.dismiss();
		if(v.getId()== R.id.menu_imgbtn){
			Dialog dialog = new AlertDialog.Builder(context)
            .setIcon(R.drawable.buttons_bg20)
            .setTitle(R.string.quit)
            .setMessage(R.string.sure_quit)
            .setPositiveButton(R.string.alert_dialog_ok, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                	((WelActivity)context).quit();
                }
            })
            .setNegativeButton(R.string.alert_dialog_cancel, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                }
            })
            .create();
			dialog.show();
		}else if(v.getId()==R.id.replay_imgbtn){
			gameview.startPlay();
		}else if(v.getId()==R.id.next_imgbtn){
			//gameview.startNextPlay();
			/*Intent in = new Intent();
			  // in.setClassName("whu.iss.sric.android", "whu.iss.sric.android.WelActivity");
			   in = new Intent(getActivity(),WelActivity.class);
			   getActivity().startActivity(in);*/
		}
		/*switch(v.getId()){
		case R.id.menu_imgbtn:
			Dialog dialog = new AlertDialog.Builder(context)
            .setIcon(R.drawable.buttons_bg20)
            .setTitle(R.string.quit)
            .setMessage(R.string.sure_quit)
            .setPositiveButton(R.string.alert_dialog_ok, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                	((WelActivity)context).quit();
                }
            })
            .setNegativeButton(R.string.alert_dialog_cancel, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                }
            })
            .create();
			dialog.show();
//			gameview.startPlay();
//			Toast.makeText(context, text, duration);
			break;
		case R.id.replay_imgbtn:
			gameview.startPlay();
			break;
		case R.id.next_imgbtn:
			gameview.startNextPlay();
			break;
		}*/
	}

	/*public void finish() {
		super.finish();
		overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
	}*/
}
