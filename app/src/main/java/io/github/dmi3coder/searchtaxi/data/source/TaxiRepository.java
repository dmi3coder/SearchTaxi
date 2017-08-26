package io.github.dmi3coder.searchtaxi.data.source;

import com.squareup.moshi.Moshi;
import io.github.dmi3coder.searchtaxi.data.Taxi;
import io.github.dmi3coder.searchtaxi.data.source.remote.TaxiRemoteDataSource;
import io.reactivex.Observable;
import java.util.List;
import okhttp3.OkHttpClient;

/**
 * Created by dim3coder on 8/26/17.
 */
public class TaxiRepository implements TaxiDataSource {

  private static TaxiRepository INSTANCE = null;

  private TaxiDataSource remoteDataSource;


  private TaxiRepository() {
    //No need to decoupling in this situation, but in enterprise applications, it's must-have
    remoteDataSource = new TaxiRemoteDataSource(new OkHttpClient(), new Moshi.Builder().build());
  }

  public static TaxiRepository getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new TaxiRepository();
    }
    return INSTANCE;
  }

  @Override
  public Observable<List<Taxi>> getTaxis() {
    return remoteDataSource.getTaxis();
  }
}
