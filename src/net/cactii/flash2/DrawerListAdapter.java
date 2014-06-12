package net.cactii.flash2;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class DrawerListAdapter extends BaseAdapter {
	
	private static LayoutInflater inflater = null;

	public DrawerListAdapter(Activity act) {
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
			return view;
		}
		return null;
	}

}
