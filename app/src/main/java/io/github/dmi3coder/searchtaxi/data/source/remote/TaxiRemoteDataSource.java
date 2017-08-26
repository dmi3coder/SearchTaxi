package io.github.dmi3coder.searchtaxi.data.source.remote;

import android.accounts.NetworkErrorException;
import android.util.Log;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import io.github.dmi3coder.searchtaxi.data.Taxi;
import io.github.dmi3coder.searchtaxi.data.source.TaxiDataSource;
import io.reactivex.Emitter;
import io.reactivex.Observable;
import java.io.IOException;
import java.io.NotSerializableException;
import java.lang.reflect.Type;
import java.util.List;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by dim3coder on 8/26/17.
 */
public class TaxiRemoteDataSource implements TaxiDataSource {

  private static final String TAG = "TaxiRemoteDataSource";
  private OkHttpClient httpClient;
  private Moshi parser;

  public TaxiRemoteDataSource(OkHttpClient client, Moshi parser) {
    this.httpClient = client;
    this.parser = parser;
  }

  @Override
  public Observable<List<Taxi>> getTaxis() {
    return Observable.create(observer -> new Thread(() -> loadTaxiData(observer, 0)).start());
  }

  private void loadTaxiData(Emitter<List<Taxi>> emitter, final int retry) {
    Call call = httpClient.newCall(new Request.Builder()
        .url("http://redirect.mytaxi.net/car2go/vehicles.json")
        .get()
        .build());
    try {
      Response response = call.execute();
      List<Taxi> taxis = convertTaxis(response.body().string());
      emitter.onNext(taxis);
    } catch (IOException | NullPointerException e) {
      handleRetryException(emitter, retry);
    } catch (JSONException e) {
      emitter.onError(new NotSerializableException());
    }
  }

  private List<Taxi> convertTaxis(String json) throws JSONException, IOException {
    Type taxiListType = Types.newParameterizedType(List.class, Taxi.class);
    JsonAdapter<List<Taxi>> jsonAdapter = parser.adapter(taxiListType);
    return jsonAdapter
        .fromJson(new JSONObject(json).get("placemarks").toString());
  }

  private void handleRetryException(Emitter<List<Taxi>> emitter, final int retry) {
    if (retry >= 4) {
      emitter.onError(new NetworkErrorException());
      return;
    }
    Log.d(TAG, "loadTaxiData: something went wrong, retrying x" + retry);
    try {
      Thread.sleep(1000);
      loadTaxiData(emitter, retry + 1);
    } catch (InterruptedException ignore) {
    }
  }

}
