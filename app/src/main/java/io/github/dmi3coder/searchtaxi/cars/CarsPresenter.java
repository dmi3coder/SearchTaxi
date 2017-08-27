package io.github.dmi3coder.searchtaxi.cars;

import io.github.dmi3coder.searchtaxi.R;
import io.github.dmi3coder.searchtaxi.cars.CarsContract.Presenter;
import io.github.dmi3coder.searchtaxi.cars.CarsContract.View;
import io.github.dmi3coder.searchtaxi.data.Taxi;
import io.github.dmi3coder.searchtaxi.data.source.TaxiRepository;
import io.reactivex.Observable;
import io.reactivex.Single;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dim3coder on 8/26/17.
 */
public class CarsPresenter implements Presenter {

  private View view;
  private List<Taxi> cars;

  public CarsPresenter(View view) {
    this.view = view;
    view.setPresenter(this);
  }

  @Override
  public void start() {
    new Thread(() -> {
      view.setLoading(true);
      TaxiRepository.getInstance().getTaxis()
          .subscribe(cars -> {
            this.cars = cars;
            view.showCars(cars);
            view.setLoading(false);
          },
          error -> {
            view.setError(R.string.app_name);
          }
      );
    }).start();
  }

  @Override
  public Single<List<Taxi>> filterData(String query) {
    return Observable.fromIterable(cars)
        .filter(taxi -> taxi.getName().contains(query))
        .toList();
  }

  @Override
  public Single<List<Taxi>> clearFilter() {
    return Single.just(cars);
  }


  @Override
  public void requestReload() {
    start();
  }

  @Override
  public void requestDetailedInfo(Taxi taxi) {

  }
}
