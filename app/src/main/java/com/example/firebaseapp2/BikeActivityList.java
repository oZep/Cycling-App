package com.example.firebaseapp2;

        import android.app.Activity;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.TextView;

        import java.util.List;

public class BikeActivityList extends ArrayAdapter<BikeActivity> {
    private Activity context;
    List<BikeActivity> bikeActivities;

    public BikeActivityList(Activity context, List<BikeActivity> bikeActivities) {
        super(context, R.layout.product_list, bikeActivities);
        this.context = context;
        this.bikeActivities = bikeActivities;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.product_list, null, true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);
        TextView textViewPrice = (TextView) listViewItem.findViewById(R.id.textViewPrice);

        BikeActivity product = bikeActivities.get(position);
        textViewName.setText(product.getActivityName());
        textViewPrice.setText(String.valueOf(product.getLevel()));
        return listViewItem;
    }
}
