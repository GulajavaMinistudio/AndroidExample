package id.jl.android.exampleappcompat23_2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Button showPeekButton, hidePeekButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Call bottom sheet
        View bottomSheet = findViewById(R.id.bottom_sheet);
        final BottomSheetBehavior behavior = BottomSheetBehavior.from(bottomSheet);
        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                // React to state change
                Log.e("onStateChanged", "onStateChanged:" + newState);
                if (newState == BottomSheetBehavior.STATE_EXPANDED) {

                    // Example fab gone
                    //fab.setVisibility(View.GONE);
                } else {

                    // Example fab visible
                    //fab.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                // React to dragging events
                Log.e("onSlide", "onSlide");
            }
        });
        //behavior.setPeekHeight(1000);

        showPeekButton = (Button) findViewById(R.id.show_peak_button);
        hidePeekButton = (Button) findViewById(R.id.hide_peak_button);
        final View peakView = findViewById(R.id.peakview);

        showPeekButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Let's peek it, programmatically
                behavior.setPeekHeight(peakView.getHeight());
                peakView.requestLayout();
            }
        });

        hidePeekButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                behavior.setPeekHeight(0);
                if (behavior.getState() != BottomSheetBehavior.STATE_HIDDEN)
                    behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_save:
                return true;
            case R.id.menu_delete:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
