package io.github.dmi3coder.searchtaxi;

/**
 * Created by dim3coder on 8/26/17.
 */
public interface BaseContract {

  interface BaseView<T extends BasePresenter> {

    void setPresenter(T presenter);
  }

  interface BasePresenter {

    void start();
  }
}
