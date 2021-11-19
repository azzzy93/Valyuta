package kg.geektech.valyuta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import kg.geektech.valyuta.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private MainAdapter adapter;
    private List<MainModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        initAdapter();
        initThread();

    }

    private void initAdapter() {
        list = new ArrayList<>();
        adapter = new MainAdapter();
    }

    private void initThread() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                getWeb();
            }
        };
        Thread secThread = new Thread(runnable);
        secThread.start();
    }

    private void getWeb() {
        try {
            Document doc = Jsoup.connect("https://www.nbkr.kg/index.jsp?lang=RUS").get();
            Elements tables = doc.getElementsByTag("tbody");
            Element element_valyuta = tables.get(3);
            Elements our_table = element_valyuta.children();

            for (int i = 0; i < our_table.size(); i++) {
                list.add(new MainModel(our_table.get(i).child(0).text(), our_table.get(i).child(1).text()));
            }

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    adapter.setList(list);
                    binding.rvMain.setAdapter(adapter);
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}