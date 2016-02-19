package id.jl.android.easypermissions;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {

    private static final String TAG = "Permissions";
    private static final int RC_LOCATION_PERM = 101;
    private static final int RC_CAMERA_PERM = 102;

    private Button btnLocationPermissions, btnCameraPermissions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Init Button
        btnLocationPermissions = (Button) findViewById(R.id.btnLocationPermissions);
        btnCameraPermissions = (Button) findViewById(R.id.btnCameraPermissions);

        // call method askLocationPermissions
        View.OnClickListener mBtnLocationListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Check android version -- if M or newer ask permissions
                if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    askLocationPermissions();
                } else {
                    Toast.makeText(MainActivity.this, "Your android version doesn't need to ask location permissions", Toast.LENGTH_LONG).show();
                }
            }
        };

        View.OnClickListener mBtnCameraListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Check android version -- if M or newer ask permissions
                if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    askCameraPermissions();
                } else {
                    Toast.makeText(MainActivity.this, "Your android version doesn't need to ask camera permissions", Toast.LENGTH_LONG).show();
                }
            }
        };

        // set listener to button
        btnLocationPermissions.setOnClickListener(mBtnLocationListener);
        btnCameraPermissions.setOnClickListener(mBtnCameraListener);
    }

    private void askLocationPermissions() {
        if (EasyPermissions.hasPermissions(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
            // Have permission, do the thing!
            Toast.makeText(this, "TODO: Location things", Toast.LENGTH_LONG).show();
        } else {
            // Request one permission
            EasyPermissions.requestPermissions(this, getString(R.string.need_access_location),
                    RC_LOCATION_PERM, Manifest.permission.ACCESS_FINE_LOCATION);
        }
    }

    private void askCameraPermissions() {
        if (EasyPermissions.hasPermissions(this, Manifest.permission.CAMERA)) {
            // Have permission, do the thing!
            Toast.makeText(this, "TODO: Camera things", Toast.LENGTH_LONG).show();
        } else {
            // Request one permission
            EasyPermissions.requestPermissions(this, getString(R.string.need_access_camera),
                    RC_CAMERA_PERM, Manifest.permission.CAMERA);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // EasyPermissions handles the request result.
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        Log.d(TAG, "onPermissionsGranted:" + requestCode + ":" + perms.size());
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        Log.d(TAG, "onPermissionsDenied:" + requestCode + ":" + perms.size());
    }
}