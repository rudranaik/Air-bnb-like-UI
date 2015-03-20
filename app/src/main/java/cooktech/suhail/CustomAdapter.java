/**
 * Created by rudra on 11-02-2015.
 */
package cooktech.suhail;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Map;

public class CustomAdapter extends ArrayAdapter implements Constants {

    private final Activity context;
    private final int layoutres;

/*
    public boolean[] favorited;
    public int[] rates;
    public int[] browserImages;
    public int[] displayPics;
    public String[] projectNames;
    public String[] undertakings;
    public String[] personName;
    public int[] experience;
   */
    Map<String,Object> dataMap;

    public Bitmap bmp;
    ViewHolder vH;


    public CustomAdapter(Activity context, int layoutres,Map<String,Object> map){
        super(context,layoutres,(String[]) map.get(PERNAME));
        this.context = context;
        this.layoutres = layoutres;
        dataMap = map;

    }

    private class ViewHolder{
        ImageView BI;
        TextView ProjN;
        TextView WD;
        TextView Rates;
        ImageView BMP;
        CheckBox CB;
    }

    @Override
    public View getView(int position, View rowView, ViewGroup parent){
        ViewHolder vH = null;

        if(rowView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            rowView = inflater.inflate(layoutres, parent, false);
            vH = new ViewHolder();
            vH.BI = ((ImageView) rowView.findViewById(R.id.browerImages));
            vH.BMP = (ImageView) rowView.findViewById(R.id.smallDP);
            vH.CB = (CheckBox) rowView.findViewById(R.id.favorited);
            vH.ProjN = (TextView) rowView.findViewById(R.id.projectName);
            vH.WD = (TextView) rowView.findViewById(R.id.workerDetails);
            vH.Rates = (TextView) rowView.findViewById(R.id.rate);
            rowView.setTag(vH);
        } else{
            vH = (ViewHolder) rowView.getTag();
        }
        int ress = ((int[]) dataMap.get(BROWSER))[position];
        vH.BI.setImageResource(ress);
        vH.BI.setTag(ress);
        bmp = BitmapFactory.decodeResource(context.getResources(), ((int[]) dataMap.get(DP))[position]);
        vH.BMP.setImageBitmap(circularize(bmp)); bmp.recycle(); bmp = null;
        vH.BMP.setTag(ress);
        vH.CB.setChecked((((boolean[]) dataMap.get(FAV))[position]));
        vH.ProjN.setText(((String[]) dataMap.get(PROJNAME))[position]);
        vH.ProjN.setTag(ress);
        vH.WD.setText(
                ((String[]) dataMap.get(UT))[position] + " | By: "
                        + (((String[]) dataMap.get(PERNAME))[position]) + " | "
                        + (((int[]) dataMap.get(EXP))[position]) + " yrs Exp | 22 Projects");
        vH.WD.setTag(ress);
        vH.Rates.setText("Rs."+Integer.toString(((int[]) dataMap.get(RATES))[position]));
        vH.Rates.setTag(ress);
        AlphaAnimation aa = new AlphaAnimation(0.0f,1.0f);
        aa.setDuration(800);
        rowView.setAnimation(aa);
        //setTags((ViewGroup)rowView,ress);
        return rowView;
    }
    public void setTags(ViewGroup view, int resId){

        int c = view.getChildCount();
        for (int i = 0 ; i < c; i++){
            view.getChildAt(i).setTag(resId);
        }
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