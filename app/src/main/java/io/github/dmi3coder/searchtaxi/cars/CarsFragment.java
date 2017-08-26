package io.github.dmi3coder.searchtaxi.cars;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetBehavior.BottomSheetCallback;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

  private static final String TAG = "CarsFragment";
  private FloatingActionButton searchButton;
  private BottomSheetBehavior<RecyclerView> bottomSheetBehavior;
  private RecyclerView mainList;
  private Presenter presenter;

  public CarsFragment() {
    new CarsPresenter(this);
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_cars, container, false);
    searchButton = findById(view, R.id.cars_search_fab);
    mainList = findById(view, R.id.cars_bottom_sheet);

    setupBottomSheet();
    searchButton.setOnClickListener(this);

    presenter.start();
    return view;
  }

  private void setupBottomSheet() {
    bottomSheetBehavior = BottomSheetBehavior.from(mainList);
    bottomSheetBehavior.setHideable(true);
    bottomSheetBehavior.setPeekHeight((int) Utils.convertDpToPixel(192, getContext()));
    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
    bottomSheetBehavior.setBottomSheetCallback(new BottomSheetCallback() {
      @Override
      public void onStateChanged(@NonNull View bottomSheet, int newState) {
      }

      @Override
      public void onSlide(@NonNull View bottomSheet, float slideOffset) {
        Log.d(TAG, "onSlide: " + slideOffset);
        if(slideOffset<0) return;
        int alpha = (int) (200 * slideOffset);
        bottomSheet.setBackgroundColor(Color.argb(alpha, 255, 255, 255));
      }
    });
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
    getActivity().runOnUiThread(() -> {
      mainList.setLayoutManager(new LinearLayoutManager(getContext()));
      mainList.setAdapter(new CarsAdapter(taxis));
      Log.d(TAG, "showCars: here i am!");
    });
  }

  @Override
  public void setLoading(boolean loading) {
    getActivity().runOnUiThread(() -> {
      if (loading) {
        searchButton.setOnClickListener(null);
        searchButton
            .setAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.animation_blink));
      } else {
        searchButton.clearAnimation();
        searchButton.setOnClickListener(this);
      }
    });
  }

  @Override
  public void showDetailedInfo(Taxi taxi) {

  }

  public boolean handleBack() {
    if ((bottomSheetBehavior.getState()
        & (BottomSheetBehavior.STATE_EXPANDED | BottomSheetBehavior.STATE_COLLAPSED)) != 0) {
      bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
      mainList.scrollToPosition(0);
      return true;
    }
    return false;
  }

  @Override
  public void setError(int errorResId) {

  }


  private <T extends View> T findById(View v, int id) {
    return v.findViewById(id);
  }
}
