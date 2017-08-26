package io.github.dmi3coder.searchtaxi.cars;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import io.github.dmi3coder.searchtaxi.R;
import io.github.dmi3coder.searchtaxi.cars.CarsAdapter.CarHolder;
import io.github.dmi3coder.searchtaxi.data.Taxi;
import java.util.List;

/**
 * Created by dim3coder on 8/26/17.
 */
public class CarsAdapter extends RecyclerView.Adapter<CarHolder> {

  private List<Taxi> cars;

  public CarsAdapter(List<Taxi> cars) {
    this.cars = cars;
  }

  @Override
  public CarHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new CarHolder(
        LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_car, parent, false)
    );
  }

  @Override
  public void onBindViewHolder(CarHolder holder, int position) {

  }

  @Override
  public int getItemCount() {
    return cars.size();
  }

  public class CarHolder extends RecyclerView.ViewHolder {

    public CarHolder(View itemView) {
      super(itemView);
    }
  }
}
