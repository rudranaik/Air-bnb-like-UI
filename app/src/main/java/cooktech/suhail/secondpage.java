package cooktech.suhail;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;


public class secondpage extends ActionBarActivity implements Constants {
    ImageView im;
    HashMap<String,Object> hmap;
    int[] bImageArray;
    RatingBar ratingBar;
    TextView textView;
    TextView textView2;
    ImageView imageView;
    Bitmap bmp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secondpage);
        im = (ImageView) findViewById(R.id.browseragain);
        im.setImageResource(getIntent().getIntExtra("IMG",R.mipmap.five));
        hmap = (HashMap<String,Object>) getIntent().getSerializableExtra(MAP);
        animate(im);

        bImageArray = (int[]) hmap.get(BROWSER);
    }

    public void animate(final View view){
        int f = getIntent().getIntExtra("POS", 1500);
        System.out.println("Received position: "+getIntent().getIntExtra("POS",1500));
        TranslateAnimation ta = new TranslateAnimation(0,0,f,0);
        ta.setDuration(1000);
        ta.setFillAfter(true);
        //ta.setFillEnabled(true);
        view.setAnimation(ta);

        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                System.out.println("Current position of the image: "+view.getY());
                int index = findIndexOf(getIntent().getIntExtra("IMG",R.mipmap.five),(int[])hmap.get(BROWSER));
                renderRest(index);
                /*view.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "this is my Toast message!!! =)", Toast.LENGTH_LONG).show();*/
            }
        },300);
    }
    public void renderRest(int index){


        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        ratingBar.setRating(((float[])hmap.get(RATING))[index]);
        ratingBar.setClickable(false);
        ratingBar.setAnimation(gettAnimation(ratingBar));

        textView = (TextView) findViewById(R.id.textView);
        textView.setText(((String[]) hmap.get(PROJNAME))[index]);
        textView.setAnimation(gettAnimation(textView));

        textView2 = (TextView) findViewById(R.id.textView2);
        textView2.setText(((String[]) hmap.get(UT))[index] + " | By: "
                + (((String[]) hmap.get(PERNAME))[index]) + " | "
                + (((int[]) hmap.get(EXP))[index]) + " yrs Exp | 22 Projects");
        textView2.setAnimation(gettAnimation(textView2));

        imageView = (ImageView) findViewById(R.id.imageView);
        bmp = BitmapFactory.decodeResource(this.getResources(), ((int[]) hmap.get(DP))[index]);
        imageView.setImageBitmap(circularize(bmp)); bmp.recycle(); bmp = null;
        imageView.setAnimation(gettAnimation(imageView));
    }
    public int findIndexOf(int res,int[] resourceArray){
        int result = 0;
        int i = 0;
        for ( i = 0; i < resourceArray.length; i++){
            if (resourceArray[i] == res){
                break;}
        }
        return i;
    }
    public AlphaAnimation gettAnimation(final View view){
        AlphaAnimation ab = new AlphaAnimation(0.0f,1.0f);
        ab.setInterpolator(new AccelerateInterpolator());
        ab.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                view.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        ab.setDuration(500);
        ab.setFillAfter(true);
        ab.setFillEnabled(true);
        return ab;
    }

    public Bitmap circularize(Bitmap bitmap){
        Bitmap circleBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getWidth(), Bitmap.Config.ARGB_8888);
        BitmapShader shader = new BitmapShader (bitmap,  Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        Paint paint = new Paint();
        paint.setShader(shader);
        Canvas c = new Canvas(circleBitmap);
        c.drawCircle(circleBitmap.getWidth()/2, circleBitmap.getHeight()/2, circleBitmap.getWidth()/2, paint);
        return circleBitmap;
    }

}
