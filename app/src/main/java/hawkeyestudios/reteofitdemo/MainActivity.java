package hawkeyestudios.reteofitdemo;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ProgressDialog dialog;
    private final String BASE_URL="https://api.github.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dialog = new ProgressDialog(this);
        dialog.setMessage("Fetching repos. Please wait...");
        dialog.setCancelable(false);
        dialog.setTitle("In Progress");
        recyclerView = (RecyclerView) findViewById(R.id.recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        Api api = retrofit.create(Api.class);
        Call<List<GithubRepo>> call = api.reposForUser("Aman2612");
        dialog.show();
        call.enqueue(new Callback<List<GithubRepo>>() {
            @Override
            public void onResponse(Call<List<GithubRepo>> call, Response<List<GithubRepo>> response) {
                List<GithubRepo> list = response.body();
                RVAdapter adapter = new RVAdapter(list);
                recyclerView.setAdapter(adapter);
                dialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<GithubRepo>> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(MainActivity.this, "OOPS, something went wrong :(", Toast.LENGTH_SHORT).show();
            }
        });




    }
}
