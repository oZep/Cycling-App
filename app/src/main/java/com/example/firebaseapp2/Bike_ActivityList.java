package com.example.firebaseapp2;

        import android.app.Activity;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.TextView;

        import java.util.List;

public class Bike_ActivityList extends ArrayAdapter<Bike_Activity> {
    private Activity context;
    List<Bike_Activity> bike_activity;

    public Bike_ActivityList(Activity context, List<Bike_Activity> bike_activity) {
        super(context, R.layout.product_list, bike_activity);
        this.context = context;
        this.bike_activity = bike_activity;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.product_list, null, true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);
        TextView textViewPrice = (TextView) listViewItem.findViewById(R.id.textViewPrice);

        Bike_Activity product = bike_activity.get(position);
        textViewName.setText(product.getActivityName());
        textViewPrice.setText(String.valueOf(product.getLevel()));
        return listViewItem;
    }
}