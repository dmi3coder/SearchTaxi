package io.github.dmi3coder.searchtaxi.cars;

import android.support.annotation.StringRes;
import io.github.dmi3coder.searchtaxi.BaseContract.BasePresenter;
import io.github.dmi3coder.searchtaxi.BaseContract.BaseView;
import io.github.dmi3coder.searchtaxi.data.Taxi;
import io.reactivex.Single;
import java.util.List;

/**
 * Created by dim3coder on 8/26/17.
 */
public interface CarsContract {

  interface View extends BaseView<Presenter> {

    void showCars(List<Taxi> taxis);

    void setLoading(boolean loading);

    void setError(@StringRes int errorResId);
  }

  interface Presenter extends BasePresenter {

    void requestReload();

    Single<List<Taxi>> filterData(String query);

    Single<List<Taxi>> clearFilter();
  }

}
