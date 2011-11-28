package ar.com.utreporter.utils;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import ar.com.utreporter.R;

public class DialogFactory {
	
	private static DialogFactory INSTANCE = null;
	
	private static final int COMMON_DIALOG=0;
	
	private DialogFactory(){
		
	}
	
	public static DialogFactory getFactory(){
		if(INSTANCE == null){
			INSTANCE = new DialogFactory();
		}
		return 	INSTANCE;
	}
	
	private Dialog makeDialog(Context ctx, String title, String text, int typeDialog){
		
		Dialog dialog = new Dialog(ctx);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.customdialog);
				((TextView)dialog.findViewById(R.id.titulo)).setText(title);
				((TextView)dialog.findViewById(R.id.texto)).setText(text);
				((Button)dialog.findViewById(R.id.cerrar)).setOnClickListener(new DialogClickListener(dialog));
				dialog.setCancelable(true);
		return dialog;
	}
	
	
	public Button getDialogCancel(Context ctx, String title, String text){
		Dialog dialog = makeDialog(ctx, title, text,COMMON_DIALOG);
		Button b = (Button) dialog.findViewById(R.id.cerrar);
		dialog.show();
		return b;
	}
	
	public void getDialogAcept(Context ctx, String title, String text){
		Dialog dialog = makeDialog(ctx, title, text,COMMON_DIALOG);
		Button b = (Button) dialog.findViewById(R.id.cerrar);
		b.setText("ACEPTAR");
		b.setBackgroundResource(R.drawable.boton_celeste_normal);
		dialog.show();
		
	}

	public void getDialogWithTimer(int milisec, Context ctx, String title,String text){
		Dialog d = makeDialog(ctx, title, text,COMMON_DIALOG);
		((Button) d.findViewById(R.id.cerrar)).setVisibility(View.GONE);
		Timer t = new Timer();
		t.schedule(new HideDialogTask(d), milisec);
		d.show();
	}
	
	private class DialogClickListener implements View.OnClickListener{
		Dialog dialog = null;
		
		public DialogClickListener(Dialog d) {
			this.dialog = d;
		}
		
		public void onClick(View v) {
			if(dialog != null){
				dialog.dismiss();
			}
		}
		
	}
	
	private class HideDialogTask extends TimerTask{
		Dialog dialog = null;
		public HideDialogTask(Dialog d) {
			this.dialog = d;
		}
		public void run() {
			if(dialog!=null){
				dialog.dismiss();
			}
		}
	}
}
