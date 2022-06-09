package group7.com.demofirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListSan extends AppCompatActivity {

    ListView lv;

    ArrayAdapter<ItemStadium> adapter;
    MyArrayAdapterForShowAllStadium myArrayAdapterForShowAllStadium;
    ArrayList<ItemStadium> arl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_san);

        AnhXa();
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();


        arl= (ArrayList<ItemStadium>) bundle.getSerializable("ArrayListItemStadium");
        for(ItemStadium item:arl){
            Log.e("item:",item.toString());

        }
        adapter= new MyArrayAdapterForShowAllStadium(this,R.layout.my_custom_listview_show_all_stadium,arl);
        lv.setAdapter(adapter);
       // adapter.notifyDataSetChanged();
        Toast.makeText(ListSan.this,arl.size()+"",Toast.LENGTH_LONG).show();
       // adapter=new ArrayAdapter(this,R.layout.my_custom_listview_show_all_stadium,arl) ;
     //   lv.setAdapter(adapter);



    }

    private void AnhXa() {
        lv=findViewById(R.id.ls_listview);
        arl=new ArrayList<>();

    }

    @Override
    protected void onPause() {
        Toast.makeText(ListSan.this,arl.size()+"pause",Toast.LENGTH_LONG).show();
        arl.clear();
        super.onPause();
    }


}