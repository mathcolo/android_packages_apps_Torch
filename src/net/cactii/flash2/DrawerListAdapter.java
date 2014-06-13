package net.cactii.flash2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Switch;

public class DrawerListAdapter extends BaseAdapter {
	
	private static LayoutInflater inflater = null;
	private MainActivity act;
	private int mStrobePeriod;

	public DrawerListAdapter(Activity act) {
		this.act = (MainActivity) act;
		inflater = (LayoutInflater) act.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	@Override
	public int getCount() {
		if(act.getResources().getBoolean(R.bool.hasHighBrightness))return 3;
		return 2;
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(this.getCount() == 2)position++;
		
		if(position == 0) {
			View view = inflater.inflate(R.layout.drawerhighbrightness, null);
			
			CompoundButton box = (CompoundButton) view.findViewById(R.id.highBrightnessCheckbox);
			box.setChecked(act.mPrefs.getBoolean("bright", false));
			box.setOnCheckedChangeListener(new OnCheckedChangeListener() {

				@Override
				public void onCheckedChanged(CompoundButton arg0, boolean on) {
					// TODO Auto-generated method stub
					if(on)act.openBrightDialog((Switch) arg0);
					else act.mPrefs.edit().putBoolean("bright", false).commit();
					
				}
				
			});
			
			return view;
		}
		
		else if(position == 1)
		{
			View view = inflater.inflate(R.layout.drawerstrobe, null);
			SeekBar seekbar = (SeekBar) view.findViewById(R.id.slider);
			
			mStrobePeriod = 100;
	
			seekbar.setProgress(400 - mStrobePeriod);		

			seekbar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {		
			@Override		
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {		
				updateStrobePeriod(Math.max(20, 401 - progress));		

				Intent intent = new Intent("net.cactii.flash2.SET_STROBE");		
				intent.putExtra("period", mStrobePeriod);	
				act.sendBroadcast(intent);		
			}		

			@Override		
			public void onStartTrackingTouch(SeekBar seekBar) {		
			}		

			@Override		
			public void onStopTrackingTouch(SeekBar seekBar) {		
			}	            
			});
			
	        seekbar.setOnTouchListener(new ListView.OnTouchListener() 
	        {

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					int action = event.getAction();
			        switch (action) 
			        {
			        case MotionEvent.ACTION_DOWN:
			            v.getParent().requestDisallowInterceptTouchEvent(true);
			            break;

			        case MotionEvent.ACTION_UP:
			            v.getParent().requestDisallowInterceptTouchEvent(false);
			            break;
			        }

			        v.onTouchEvent(event);
			        return true;
				}
	        });
			return view;
		}
		
		else if(position == 2) {
			View view = inflater.inflate(R.layout.drawerabout, null);
			view.setTag("About");
			return view;
		}
		
		return null;
	}
	
	private void updateStrobePeriod(int period) { 
		//TODO
	}

}
