package io.github.dmi3coder.searchtaxi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    getSupportFragmentManager().beginTransaction()
        .add(R.id.main_frame, new CarsFragment(), CarsFragment.class.getSimpleName())
        .commit();
  }
}
