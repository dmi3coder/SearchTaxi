package io.github.dmi3coder.searchtaxi.data.source;

import io.github.dmi3coder.searchtaxi.data.Taxi;
import io.reactivex.Observable;
import java.util.List;

/**
 * Created by dim3coder on 8/26/17.
 */
public interface TaxiDataSource {

  Observable<List<Taxi>> getTaxis();

}
