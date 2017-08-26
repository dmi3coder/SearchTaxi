package io.github.dmi3coder.searchtaxi.cars;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import io.github.dmi3coder.searchtaxi.R;
import io.github.dmi3coder.searchtaxi.Utils;

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
    searchButton
        .setOnClickListener(v -> bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED));

    return view;
  }


  private void setupBottomSheet() {
    bottomSheetBehavior = BottomSheetBehavior.from(mainList);
    bottomSheetBehavior.setHideable(true);
    bottomSheetBehavior.setPeekHeight((int) Utils.convertDpToPixel(192, getContext()));
    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
  }

  private <T extends View> T findById(View v, int id) {
    return v.findViewById(id);
  }
}
