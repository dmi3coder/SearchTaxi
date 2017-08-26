package io.github.dmi3coder.searchtaxi.cars;

import io.github.dmi3coder.searchtaxi.BaseContract.BasePresenter;
import io.github.dmi3coder.searchtaxi.BaseContract.BaseView;
import io.github.dmi3coder.searchtaxi.data.Taxi;
import java.util.List;

/**
 * Created by dim3coder on 8/26/17.
 */
public interface CarsContract {

  interface View extends BaseView<Presenter> {
    void showCars(List<Taxi> taxis);
    void setLoading(boolean loading);
    void showDetailedInfo(Taxi taxi);
  }

  interface Presenter extends BasePresenter {
    void requestReload();
    void requestDetailedInfo(Taxi taxi);
  }

}
