package ar.com.utreporter;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import ar.com.utreporter.utils.DataHelper;

public class MisTicketsActivity extends Activity {

	TableLayout ticketList;
	Button refesh;
	JSONArray items;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.mistickets);
		ticketList = (TableLayout) findViewById(R.id.table);
		refesh = (Button) findViewById(R.id.refresh);
		refesh.setOnClickListener(refreshListener);
		refesh.setEnabled(false);
		Thread t = new Thread() {
			public void run() {
				requestTickets();
			};
		};
		t.start();
	}

	final OnClickListener refreshListener = new OnClickListener() {
		public void onClick(View v) {
			refesh.setEnabled(false);
			Thread t = new Thread() {
				public void run() {
					requestTickets();
				};
			};
			t.start();
		}
	};

	Handler h = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 0) {
				ticketList.removeAllViews();
				TicketTableAdapter adapter = new TicketTableAdapter(
						MisTicketsActivity.this);
				for (int i = 0; i < items.length(); i++) {
					try {
						JSONObject item = items.getJSONObject(i);
						View row = adapter.getView(item.getString("id_ticket"),
								item.getString("descripcion"));
						ticketList.addView(row);
					} catch (Exception e) {
						System.out.println("EXPLOTO UN JSONObject");
					}
				}

			}
			refesh.setEnabled(true);
		}
	};

	private void requestTickets() {
		items = DataHelper.getInstance().getMyTicket(this);
		if (items != null) {
			h.sendEmptyMessage(0);
		} else {
			h.sendEmptyMessage(1);
		}
	}

	final OnLongClickListener longclick = new OnLongClickListener() {
		String id_ticket;

		public boolean onLongClick(View v) {
			id_ticket = (String) ((TextView) v.findViewById(R.id.id)).getText();

			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
					MisTicketsActivity.this);
			alertDialogBuilder
					.setMessage(
							"Desea marcar el ticket#: " + id_ticket
									+ " como entregado?")
					.setCancelable(false)
					.setPositiveButton("Si",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									DataHelper.getInstance()
											.setTicketEntregado(
													MisTicketsActivity.this,
													id_ticket);
									refesh.setEnabled(false);
									Thread t = new Thread() {
										public void run() {
											requestTickets();
										};
									};
									t.start();
								}
							});
			alertDialogBuilder.setNegativeButton("NO",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							dialog.cancel();
						}
					});
			AlertDialog alert = alertDialogBuilder.create();
			alert.show();

			return false;
		}
	};

	class TicketTableAdapter {
		Context ctx;

		public TicketTableAdapter(Context ctx) {
			this.ctx = ctx;
		}

		public View getView(String id, String desc) {
			LayoutInflater vi = (LayoutInflater) ctx
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			TableRow row = (TableRow) vi.inflate(R.layout.ticket_row, null);
			row.setLongClickable(true);
			row.setOnLongClickListener(longclick);
			TextView idText = (TextView) row.findViewById(R.id.id);
			idText.setText(id);
			TextView descText = (TextView) row.findViewById(R.id.desc);
			descText.setText(desc);
			return row;
		}

	}

}
