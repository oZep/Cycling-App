package com.example.firebaseapp2;

        import androidx.appcompat.app.AppCompatActivity;

        import android.os.Bundle;
        import android.app.AlertDialog;

        import android.text.TextUtils;
        import android.view.LayoutInflater;
        import android.view.View;

        import android.widget.AdapterView;
        import android.widget.Button;
        import android.widget.CheckBox;
        import android.widget.EditText;
        import android.widget.ListView;
        import android.widget.Toast;

        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;

        import java.util.ArrayList;
        import java.util.List;

public class AdminPage extends AppCompatActivity {

    EditText editTextLocation;
    EditText editTextIntensity;

    CheckBox TimeTrial;
    CheckBox HillClimb;
    CheckBox RoadRace;
    CheckBox GroupRides;
    Button buttonAddProduct;
    ListView listViewProducts;

    List<BikeActivity> bikeActivities;

    DatabaseReference databaseReference;
    FirebaseDatabase databaseFirebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_page);

        editTextLocation = (EditText) findViewById(R.id.editTextName2);
        editTextIntensity = (EditText) findViewById(R.id.editTextName3);
        listViewProducts = (ListView) findViewById(R.id.listViewProducts);
        buttonAddProduct = (Button) findViewById(R.id.addButton);
        TimeTrial = (CheckBox) findViewById(R.id.checkBox);
        HillClimb = (CheckBox) findViewById(R.id.checkBox2);
        RoadRace = (CheckBox) findViewById(R.id.checkBox4);
        GroupRides = (CheckBox) findViewById(R.id.checkBox5);

        bikeActivities = new ArrayList<>();

        //adding an onclicklistener to button
        buttonAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addProduct();
            }
        });

        listViewProducts.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                BikeActivity activity = bikeActivities.get(i);
                showUpdateDeleteDialog(activity.getId(), activity.getActivityName(), activity.getLocation(), activity.getLevel());
                return true;
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
    }


    private void showUpdateDeleteDialog(final String productId, String productName, String location, int intensity) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.update_dialog, null);
        dialogBuilder.setView(dialogView);

        //final EditText editTextLocation = (EditText) dialogView.findViewById(R.id.editTextName2);
        //final EditText editTextIntensity  = (EditText) dialogView.findViewById(R.id.editTextName3);
        final Button buttonUpdate = (Button) dialogView.findViewById(R.id.userSignUp);
        final Button buttonDelete = (Button) dialogView.findViewById(R.id.adminSignUp);

        dialogBuilder.setTitle(productName);
        final AlertDialog b = dialogBuilder.create();
        b.show();

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String location = editTextLocation.getText().toString().trim();
                int intensity = Integer.parseInt(String.valueOf(editTextIntensity.getText().toString()));
                if (!TextUtils.isEmpty(location)) {
                    //FIX
                    updateProduct(productId, productName, intensity, location);
                    b.dismiss();
                }
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteProduct(view);
                b.dismiss();
            }
        });
    }

    private void updateProduct(String id, String name, int intensity, String location) {
        BikeActivity p = new BikeActivity(id, name, intensity, location);
        MyDBHandler dbHandler = new MyDBHandler(this);
        dbHandler.addProduct(p);

        Toast.makeText(getApplicationContext(), "Event Updated", Toast.LENGTH_LONG).show();
    }

    private void deleteProduct(View view) {

        MyDBHandler dbHandler = new MyDBHandler(this);
        boolean result = dbHandler.deleteProduct(productBox.getText().toString());

        if (result) {
            idView.setText("Activity Deleted");
            productBox.setText("");
            skuBox.setText("");
        }
        else
            idView.setText("No Match Found");

    }


    private void addProduct() {
        TimeTrial = (CheckBox) findViewById(R.id.checkBox);
        HillClimb = (CheckBox) findViewById(R.id.checkBox2);
        RoadRace = (CheckBox) findViewById(R.id.checkBox4);
        GroupRides = (CheckBox) findViewById(R.id.checkBox5);
        String name;
        if (GroupRides.isChecked()) {
            name = "GroupRides";
        }
        else if ((RoadRace).isChecked()) {
            name = "RoadRace";
        }
        else if (HillClimb.isChecked()) {
            name = "HillClimb";
        }
        else if (TimeTrial.isChecked()) {
            name = "TimeTrial";
        }
        else {
            name = "ERROR activity not selected";
        }

        int level = Integer.parseInt(String.valueOf(editTextIntensity.getText().toString()));
        String location = String.valueOf(editTextLocation.getText().toString());

        if (!TextUtils.isEmpty(name)) {
            //FIX
            BikeActivity p = new BikeActivity(id, name, level, location);

            MyDBHandler dbHandler = new MyDBHandler(this);
            dbHandler.addProduct(p);

            editTextIntensity.setText("");
            editTextLocation.setText("");

            Toast.makeText(this, "Events added", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this, "Please enter a name", Toast.LENGTH_LONG).show();
        }


        Toast.makeText(this, "NOT IMPLEMENTED YET", Toast.LENGTH_LONG).show();
    }
}
