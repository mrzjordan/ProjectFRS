package app.mjordan.projectfrs;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity implements BottomNavBar.OnBottomNavListerner,ImageChoice.OnImageChoiceListerner {
    MKB_DB dbHelper;

    FragmentTransaction ft;
    String type,json,list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new MKB_DB(this);

        type=getIntent().getExtras().getString("Type","Guest");
        FragmentTransaction ft= getSupportFragmentManager().beginTransaction();
        json = getIntent().getExtras().getString("UserData",null);
        Profile profile=new Profile();
        Bundle bundle=new Bundle();
        list="List";
        bundle.putString("Type",type);
        bundle.putString("json",json);
        bundle.putString("ListType",list);
        profile.setArguments(bundle);
        ft.add(R.id.TabFragment,profile);
        ft.commit();
        //PersmissionUtils.checkAndRequestPermissions(MainActivity.this);

    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater=getMenuInflater();
//        inflater.inflate(R.menu.login_menu,menu);
//        MenuItem item1=menu.findItem(R.id.done);
//        MenuItem item2=menu.findItem(R.id.edit);
//        if(show!=null){
//            if(show){
//                item1.setVisible(show);
//                item2.setVisible(!show);
//            }else{
//                item1.setVisible(show);
//                item2.setVisible(!show);
//            }
//        }
//        return true;
//    }
//
//
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        FragmentTransaction ft= getSupportFragmentManager().beginTransaction();
//        Profile profile;
//        Bundle bundle;
//        switch (item.getItemId()){
//            case R.id.LogOut:
//
//                break;
//            case R.id.edit:
//                list="Edit";
//                profile=new Profile();
//                bundle=new Bundle();
//               bundle.putString("Type",type);
//                bundle.putString("json",json);
//                bundle.putString("ListType",list);
//                profile.setArguments(bundle);
//                ft.replace(R.id.TabFragment,profile);
//                show=true;
//                invalidateOptionsMenu();
//                ft.commit();
//                break;
//            case R.id.done:
//                list="List";
//                profile=new Profile();
//                bundle=new Bundle();
//                bundle.putString("Type",type);
//                bundle.putString("json",json);
//                bundle.putString("ListType",list);
//                profile.setArguments(bundle);
//                ft.replace(R.id.TabFragment,profile);
//                show=false;
//                invalidateOptionsMenu();
//                ft.commit();
//                break;
//        }
//        return true;
//    }

    @Override
    public void fragment(int n) {
        FragmentTransaction ft= getSupportFragmentManager().beginTransaction();
        switch(n){
            case 1:

                ft.replace(R.id.TabFragment,new Order());
                Log.d("zxc","CASE1");
                break;
            case 2:

                ft.replace(R.id.TabFragment,new Eat());
                Log.d("zxc","CASE2");
                break;
            case 3:
                Log.d("zxc","CASE3");
                Profile profile=new Profile();
                Bundle bundle=new Bundle();
                list="List";
                bundle.putString("Type",type);
                bundle.putString("json",json);
                bundle.putString("ListType",list);
                profile.setArguments(bundle);
                ft.replace(R.id.TabFragment,profile);
                show=false;
                invalidateOptionsMenu();
                break;
        }
        ft.commit();
    }
    @Override
    public void image_select( Uri uri) {
        Profile frag = (Profile)
                getSupportFragmentManager().findFragmentById(R.id.TabFragment);
        if(uri!=null){
            frag.setImage(uri);
        }
    }

}
