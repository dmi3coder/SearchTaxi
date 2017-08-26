package io.github.dmi3coder.searchtaxi.data.source.remote;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import io.github.dmi3coder.searchtaxi.data.Taxi;
import io.github.dmi3coder.searchtaxi.data.source.TaxiDataSource;
import io.reactivex.Observable;
import java.io.IOException;
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

  @Override
  public Observable<List<Taxi>> getTaxis() {
    return Observable.create(observer -> new Thread(() -> {
      OkHttpClient client = new OkHttpClient();
      Call call = client.newCall(
          new Request.Builder()
              .url("http://redirect.mytaxi.net/car2go/vehicles.json")
              .get()
              .build()
      );
      try {
        Response response = call.execute();
        Moshi moshi = new Moshi.Builder().build();
        Type taxiListType = Types.newParameterizedType(List.class, Taxi.class);
        JsonAdapter<List<Taxi>> jsonAdapter = moshi.adapter(taxiListType);
        List<Taxi> placemarks = jsonAdapter
            .fromJson(new JSONObject(response.body().string()).get("placemarks").toString());
      } catch (IOException | JSONException e) {

      }
    }).start());
  }

}
