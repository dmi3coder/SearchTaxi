package io.github.dmi3coder.searchtaxi.cars;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import io.github.dmi3coder.searchtaxi.R;
import io.github.dmi3coder.searchtaxi.cars.CarsAdapter.CarHolder;
import io.github.dmi3coder.searchtaxi.data.Taxi;
import io.github.dmi3coder.searchtaxi.databinding.ItemCarBinding;
import java.util.List;

/**
 * Created by dim3coder on 8/26/17.
 */
public class CarsAdapter extends RecyclerView.Adapter<CarHolder> {

  private static final String TAG = "CarsAdapter";
  private List<Taxi> cars;
  private GoogleMap googleMap;

  public CarsAdapter(List<Taxi> cars, GoogleMap googleMap) {
    this.cars = cars;
    this.googleMap = googleMap;
  }

  @Override
  public CarHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new CarHolder(
        ItemCarBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
  }

  @Override
  public void onBindViewHolder(CarHolder holder, int position) {
    Taxi car = cars.get(position);
    ItemCarBinding binding = holder.binding;
    binding.clickableCard
        .setOnClickListener(view -> googleMap.animateCamera(CameraUpdateFactory
            .newLatLngZoom(new LatLng(car.getCoordinates()[1], car.getCoordinates()[0]), 18)));
    binding.name.setText(car.getName());
    binding.location.setText(car.getAddress());
    binding.fuelProgress.setProgress(car.getFuel());
    Context context = holder.itemView.getContext();
    binding.interiorTitle.setText(context
        .getString(
            R.string.interior_prefixed,
            context.getString(car.getInterior().getStringRes())
        )
    );
    binding.exteriorTitle.setText(context
        .getString(
            R.string.exterior_prefixed,
            context.getString(car.getExterior().getStringRes())
        )
    );
  }


  @Override
  public int getItemCount() {
    return cars.size();
  }

  public class CarHolder extends RecyclerView.ViewHolder {

    private ItemCarBinding binding;

    public CarHolder(ItemCarBinding binding) {
      super(binding.getRoot());
      this.binding = binding;
    }
  }
}
