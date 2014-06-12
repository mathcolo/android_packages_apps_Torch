package net.cactii.flash2;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SeekBar;

public class DrawerListAdapter extends BaseAdapter {
	
	private static LayoutInflater inflater = null;
	private Activity act;

	public DrawerListAdapter(Activity act) {
		this.act = act;
		inflater = (LayoutInflater) act.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	@Override
	public int getCount() {
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
		if(position == 0) {
			View view = inflater.inflate(R.layout.drawerhighbrightness, null);
			return view;
		}
		
		if(position == 1)
		{
			View view = inflater.inflate(R.layout.drawerstrobe, null);
			SeekBar seekbar = (SeekBar) view.findViewById(R.id.seekBar1);
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
		return null;
	}

}
