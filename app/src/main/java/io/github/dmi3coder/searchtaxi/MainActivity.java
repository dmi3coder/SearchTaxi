package io.github.dmi3coder.searchtaxi;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import io.github.dmi3coder.searchtaxi.cars.CarsFragment;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    getSupportFragmentManager().beginTransaction()
        .add(R.id.main_frame, new CarsFragment(), CarsFragment.class.getSimpleName())
        .commit();
  }

  @Override
  public void onBackPressed() {
    Fragment fragment = getSupportFragmentManager()
        .findFragmentByTag(CarsFragment.class.getSimpleName());
    if (fragment == null || !((CarsFragment) fragment).handleBack()) {
      super.onBackPressed();
    }

  }
}
