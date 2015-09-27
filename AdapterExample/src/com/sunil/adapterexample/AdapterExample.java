package com.sunil.adapterexample;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class AdapterExample extends Activity implements OnItemClickListener 
{

	 public static final String[] titles = new String[] { "Strawberry",
         "Banana", "Orange", "Mixed" };

 public static final String[] descriptions = new String[] {
         "It is an aggregate accessory fruit",
         "It is the largest herbaceous flowering plant", "Citrus Fruit",
         "Mixed Fruits" };
 public static final Integer[] images = { R.drawable.straw,
     R.drawable.banana, R.drawable.orange, R.drawable.mixed };

ListView listView;
List<RowItem> rowItems;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adapter_example);
        rowItems = new ArrayList<RowItem>();
        for (int i = 0; i < titles.length; i++) 
        {
            RowItem item = new RowItem(images[i], titles[i], descriptions[i]);
            rowItems.add(item);
        }
 
        listView = (ListView) findViewById(R.id.listview1);
        CustomListAdapter adapter = new CustomListAdapter(this,
                R.layout.list_item, rowItems);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }
 
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
            long id) {
        Toast toast = Toast.makeText(getApplicationContext(),
            "Item " + (position + 1) + ": " + rowItems.get(position),
            Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
    }
    
    public class CustomListAdapter extends ArrayAdapter<RowItem>
    {
    	Context context;

		public CustomListAdapter(Context context, int resource,
				List<RowItem> items) {
			super(context, resource, items);
             this.context=context;
           }
		 private class ViewHolder {
		        ImageView imageView;
		        TextView txtTitle;
		        TextView txtDesc;
		    }
		     
		    public View getView(int position, View convertView, ViewGroup parent) {
		        ViewHolder holder = null;
		        RowItem rowItem = getItem(position);
		         
		        LayoutInflater mInflater = (LayoutInflater) context
		                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		        if (convertView == null) {
		            convertView = mInflater.inflate(R.layout.list_item, null);
		            holder = new ViewHolder();
		            holder.txtDesc = (TextView) convertView.findViewById(R.id.desc);
		            holder.txtTitle = (TextView) convertView.findViewById(R.id.title);
		            holder.imageView = (ImageView) convertView.findViewById(R.id.icon);
		            convertView.setTag(holder);
		        } else
		            holder = (ViewHolder) convertView.getTag();
		                 
		        holder.txtDesc.setText(rowItem.getDesc());
		        holder.txtTitle.setText(rowItem.getTitle());
		        holder.imageView.setImageResource(rowItem.getImageId());
		         
		        return convertView;
		    }
		}

}
