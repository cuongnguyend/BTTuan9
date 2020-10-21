package com.example.bttuan9;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.MultiChoiceModeListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    Context context;
    ArrayList<food> food_data;
    CustomAdapter customAdapter;
    food foods;
    private ArrayList<food> Selection = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=this;
        setContentView(R.layout.activity_main);
        //getviews
        listView = findViewById(R.id.listView);
        food_data = new ArrayList<>();
        listView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE_MODAL);
        listView.setMultiChoiceModeListener(modeListener);
        //add food Data
        fooddata();
        customAdapter = new CustomAdapter(context,food_data);
        listView.setAdapter(customAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Toast.makeText(context,food_data.get(position).getName(),Toast.LENGTH_SHORT).show();
            }
        });
    }
    AbsListView.MultiChoiceModeListener modeListener = new AbsListView.MultiChoiceModeListener() {
        @Override
        public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
            if(Selection.contains(food_data.get(position))){
                Selection.remove(food_data.get(position));
            }
            else{
                Selection.add(food_data.get(position));
            }
            mode.setTitle(Selection.size()+" items selected");
        }

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.getMenuInflater().inflate(R.menu.options_menu,menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()){
                case R.id.delete:
                    customAdapter.removeItems(Selection);
                    mode.finish();
                    return true;
                default:
                    return false;
            }

        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            Selection.clear();
        }
    };

        private void fooddata(){
        //food1
        foods = new food();
        foods.setId(1);
        foods.setName("Bánh mì");
        foods.setImg(R.drawable.banhmi);
        foods.setAmount(99);
        foods.setPrice("30.000");
        food_data.add(foods);
        //food2
        foods = new food();
        foods.setId(2);
        foods.setName("Sandwich");
        foods.setImg(R.drawable.sandwich);
        foods.setAmount(100);
        foods.setPrice("35.000");
        food_data.add(foods);
        //food3
        foods = new food();
        foods.setId(3);
        foods.setName("Nước cam");
        foods.setImg(R.drawable.cam);
        foods.setAmount(990);
        foods.setPrice("20.000");
        food_data.add(foods);

        foods = new food();
        foods.setId(4);
        foods.setName("Nước ép");
        foods.setImg(R.drawable.nuocep);
        foods.setAmount(920);
        foods.setPrice("25.000");
        food_data.add(foods);

        foods = new food();
        foods.setId(5);
        foods.setName("Coffee");
        foods.setImg(R.drawable.cf);
        foods.setAmount(920);
        foods.setPrice("45.000");
        food_data.add(foods);



    }
}