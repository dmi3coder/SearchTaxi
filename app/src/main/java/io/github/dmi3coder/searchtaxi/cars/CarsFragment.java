package io.github.dmi3coder.searchtaxi.cars;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import io.github.dmi3coder.searchtaxi.R;
import io.github.dmi3coder.searchtaxi.Utils;
import io.github.dmi3coder.searchtaxi.cars.CarsContract.Presenter;
import io.github.dmi3coder.searchtaxi.data.Taxi;
import java.util.List;

/**
 * Created by dim3coder on 8/26/17.
 */
public class CarsFragment extends Fragment implements CarsContract.View, OnClickListener {

  private FloatingActionButton searchButton;
  private BottomSheetBehavior<RecyclerView> bottomSheetBehavior;
  private RecyclerView mainList;
  private Presenter presenter;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_cars, container, false);
    searchButton = findById(view, R.id.cars_search_fab);
    mainList = findById(view, R.id.cars_bottom_sheet);

    setupBottomSheet();
    searchButton.setOnClickListener(this);

    return view;
  }


  private void setupBottomSheet() {
    bottomSheetBehavior = BottomSheetBehavior.from(mainList);
    bottomSheetBehavior.setHideable(true);
    bottomSheetBehavior.setPeekHeight((int) Utils.convertDpToPixel(192, getContext()));
    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
  }

  @Override
  public void onClick(View view) {
    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
  }


  @Override
  public void setPresenter(Presenter presenter) {
    this.presenter = presenter;
  }

  @Override
  public void showCars(List<Taxi> taxis) {

  }

  @Override
  public void setLoading(boolean loading) {
    if (loading) {
      searchButton.setOnClickListener(null);
      searchButton.setAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.animation_blink));
    } else {
      searchButton.clearAnimation();
      searchButton.setOnClickListener(this);
    }

  }

  @Override
  public void showDetailedInfo(Taxi taxi) {

  }

  private <T extends View> T findById(View v, int id) {
    return v.findViewById(id);
  }
}
