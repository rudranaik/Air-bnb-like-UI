package cooktech.suhail;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends ActionBarActivity implements Constants{

    /* This data can be extracted Dynamically from a database or anyother source*/
    public boolean[] favorited ={true, false, true, false, true, false};
    public int[] rates = {123,234,345,456,543,432};
    public int[] browserImages = {R.mipmap.one,R.mipmap.two,R.mipmap.three,R.mipmap.four,R.mipmap.five,R.mipmap.six};
    public int[] displayPics = {R.mipmap.one1,R.mipmap.two2,R.mipmap.three3,R.mipmap.four4,R.mipmap.five5,R.mipmap.six6};
    public String[] projectNames = {"Drawing Room Design",
            "Garden Design","Library Design",
            "Kitchen Design","Bedroom Design","Storage Room Design"};
    public String[] undertakings = {"Living","Leisure","Involve","Explore","Relax","Connoiseur"};
    public String[] personName = {"George","Steve","Miranda","Craig","Steyn","Donald"};
    public int[] experience = {5,6,2,4,7,9};
    public float[] rating = {2,4.5f,3.5f,1,5,4};

    /*Map to hold all data*/
    Map<String,Object> Data = new HashMap<String,Object>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        putDataIntoMap();
        CustomAdapter customAdapter = new CustomAdapter(this,R.layout.browser_template,Data);
        ((ListView) findViewById(R.id.browser)).setAdapter(customAdapter);
    }

    public void putDataIntoMap(){
        Data.put(FAV,favorited);
        Data.put(RATES,rates);
        Data.put(BROWSER,browserImages);
        Data.put(DP,displayPics);
        Data.put(PROJNAME,projectNames);
        Data.put(UT,undertakings);
        Data.put(PERNAME,personName);
        Data.put(EXP,experience);
        Data.put(RATING,rating);
    }


    public void onClick(View view){
        View viewParent = (View) view.getParent();
        System.out.println( "View's parent: "+viewParent.getY());
        View parentparent = (View) viewParent.getParent();
        System.out.println( "Parent's parent: "+parentparent.getY());
        Intent intt = new Intent(this,secondpage.class);
        intt.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        intt.putExtra("IMG", (int) view.getTag());
        //int[] xy = new int[2];
        //viewParent.getLocationOnScreen(xy);
        //int f =  xy[1];
        int f = (int) viewParent.getY();
        System.out.println("Starting from point "+f);
                intt.putExtra("POS", f);
        intt.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        intt.putExtra(MAP, (java.io.Serializable) Data);
        startActivity(intt);
    }


}

