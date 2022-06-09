package group7.com.demofirebase;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;


import android.os.Bundle;

import android.text.TextUtils;

import android.util.Log;
import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.Button;

import android.widget.EditText;

import android.widget.ListView;
import android.widget.Toast;



import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;


import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;

import com.google.firebase.database.DatabaseError;

import com.google.firebase.database.DatabaseReference;

import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private EditText edtIdSan, edtIdChuSan,edtTenChuSan,edtGia,edtDiaChi,edtTenSan;

    private Button btnSend,btnChange;
    ListView lv;
    ArrayAdapter<San> arrayAdapter;
    ArrayList<EmployeeInfo> arlEmployee;
    ArrayList<San> arlSan=new ArrayList<>();
    ArrayList<ItemStadium> arlStadium=new ArrayList<>();

  public static ArrayList<San> stt_arlSan=new ArrayList<>();


    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        edtIdSan = findViewById(R.id.idSan);
        edtTenSan=findViewById(R.id.idTenSan);
        edtIdChuSan = findViewById(R.id.id_ChuSan);
        edtTenChuSan = findViewById(R.id.idTenChuSan);
        edtGia = findViewById(R.id.idGia);
        edtDiaChi = findViewById(R.id.idDiaChi);

        btnSend = findViewById(R.id.idBtnSendData);
        btnChange = findViewById(R.id.idChangeActivity);

        lv=findViewById(R.id.lvMain);
      //  arlSan=new ArrayList<>();
        arrayAdapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,arlSan);
        lv.setAdapter(arrayAdapter);

       btnSend.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               onClickPushData();
           }
       });
        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickChange();
            }
        });

    }


    private void onClickChange() {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef= database.getReference("listSan");
        FirebaseDatabase database1 = FirebaseDatabase.getInstance();


        Intent intent =new Intent(MainActivity.this,ListSan.class);
        Bundle bundle = new Bundle();


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot dt : snapshot.getChildren()) {
                    ItemStadium item=new ItemStadium();
                    San value= dt.getValue(San.class);
                    arlSan.add(value);

                    item.setTenSan(value.getTenSan());
                    item.setDiaChi(value.getDiaChi());
                    String idChuSanText=value.getId_ChuSan();


                    Log.e("path","listChuSan/"+ idChuSanText);
                    DatabaseReference myRef1= database1.getReference("listChuSan/"+idChuSanText);
                    myRef1.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            ChuSan cs= snapshot.getValue(ChuSan.class);
                            item.setTenChuSan(cs.getTenChuSan());
                            item.setSDT(cs.getSDT());
                            arlStadium.add(item);

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                   // arlStadium.add(item);
                }



                for(ItemStadium it:arlStadium)
                Log.e("itemCuaArl",it.toString());
                arrayAdapter.notifyDataSetChanged();
                for(San s : arlSan){
                    Log.e("RS:",s.toString());
                }

                Log.e("Sai",arlSan.size()+"");
                Log.e("Size arlStadium",arlStadium.size()+"");
                //bundle.putParcelableArrayList("ArrayListSan",arlSan);
                bundle.putSerializable("ArrayListItemStadium",arlStadium);
                intent.putExtras(bundle);
                startActivity(intent);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        /*
       String[] str ;
                myRef.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        San value= snapshot.getValue(San.class);
                        ItemStadium item=new ItemStadium();
                        arlSan.add(value);


                        item.setTenSan(value.getTenSan());
                        item.setDiaChi(value.getDiaChi());
                        String idChuSanText=value.getId_ChuSan();
                        DatabaseReference myRef1= database1.getReference("listChuSan/"+idChuSanText);
                        arlStadium.add(item);
                        for(ItemStadium it:arlStadium){
                            Log.e("kaka",it.toString());
                        }

                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {


                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
        arrayAdapter.notifyDataSetChanged();
        for(San s : arlSan){
            Log.e("RS:",s.toString());
        }

         */
    }

    private void onClickPushData() {
        arlSan.clear();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef= database.getReference("listSan/"+edtIdSan.getText().toString());

        San s=new San();
        s.setId_San(edtIdSan.getText().toString());
        s.setId_ChuSan(edtIdChuSan.getText().toString());
        s.setTenSan(edtTenSan.getText().toString());
        s.setGia(edtGia.getText().toString());
        s.setDiaChi(edtDiaChi.getText().toString());

        myRef.setValue(s, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                Toast.makeText(MainActivity.this,"Push Data Succes",Toast.LENGTH_LONG).show();
            }
        });
    }



    @Override
    protected void onResume() {
        arlStadium.clear();
        super.onResume();
        //  arrayAdapter.notifyDataSetChanged();
    }
}

/*
                    FirebaseDatabase database1 = FirebaseDatabase.getInstance();
                    DatabaseReference myRef1= database.getReference("listSan/"+value.getId_San()+"/ChuSan");
                    myRef1.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                                ChuSan cs= snapshot.getValue(ChuSan.class);
                                value.setChuSan(cs);
                              Log.e("Chu San",cs.getTenChuSan());

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

 */