package io.github.dmi3coder.searchtaxi;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by dim3coder on 8/26/17.
 */
public class CarsFragment extends Fragment {

  private FloatingActionButton searchButton;
  private BottomSheetBehavior<RecyclerView> bottomSheetBehavior;
  private RecyclerView mainList;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_cars, container, false);
    searchButton = findById(view, R.id.cars_search_fab);
    mainList = findById(view, R.id.cars_bottom_sheet);

    setupBottomSheet();

    return view;
  }


  private void setupBottomSheet() {
    bottomSheetBehavior = BottomSheetBehavior.from(mainList);
    bottomSheetBehavior.setHideable(true);
    bottomSheetBehavior.setPeekHeight(200);
    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
  }

  public <T extends View> T findById(View v, int id) {
    return v.findViewById(id);
  }
}
