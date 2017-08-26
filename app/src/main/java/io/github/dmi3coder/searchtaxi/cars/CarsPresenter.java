package io.github.dmi3coder.searchtaxi.cars;

import io.github.dmi3coder.searchtaxi.R;
import io.github.dmi3coder.searchtaxi.cars.CarsContract.Presenter;
import io.github.dmi3coder.searchtaxi.cars.CarsContract.View;
import io.github.dmi3coder.searchtaxi.data.Taxi;
import io.github.dmi3coder.searchtaxi.data.source.TaxiRepository;

/**
 * Created by dim3coder on 8/26/17.
 */
public class CarsPresenter implements Presenter {

  private View view;

  public CarsPresenter(View view) {
    this.view = view;
    view.setPresenter(this);
  }

  @Override
  public void start() {
    new Thread(() -> {
      view.setLoading(true);
      TaxiRepository.getInstance().getTaxis().subscribe(
          view::showCars,
          error -> {
            view.setError(R.string.app_name);
            view.setLoading(false);
          },
          () -> view.setLoading(false)
      );
    }).start();
  }

  @Override
  public void requestReload() {

  }

  @Override
  public void requestDetailedInfo(Taxi taxi) {

  }
}
