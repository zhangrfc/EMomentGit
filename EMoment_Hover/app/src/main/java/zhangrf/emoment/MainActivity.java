package zhangrf.emoment;

import java.util.*;

import android.support.v7.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.daimajia.androidviewhover.BlurLayout;

public class MainActivity extends AppCompatActivity {
    private Context mContext;
    //private BlurLayout mSampleLayout, mSampleLayout2, mSampleLayout3, mSampleLayout4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        View mainView = getLayoutInflater().inflate(R.layout.activity_main, null);
        BlurLayout.setGlobalDefaultDuration(450);


        /*
        mSampleLayout = (BlurLayout)findViewById(R.id.blur_layout);
        View hover = LayoutInflater.from(mContext).inflate(R.layout.hover_sample, null);
        hover.findViewById(R.id.heart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YoYo.with(Techniques.Tada)
                        .duration(550)
                        .playOn(v);
            }
        });
        hover.findViewById(R.id.share).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YoYo.with(Techniques.Swing)
                        .duration(550)
                        .playOn(v);
            }
        });
        mSampleLayout.setHoverView(hover);
        mSampleLayout.setBlurDuration(550);
        mSampleLayout.addChildAppearAnimator(hover, R.id.heart, Techniques.FlipInX, 550, 0);
        mSampleLayout.addChildAppearAnimator(hover, R.id.share, Techniques.FlipInX, 550, 250);
        mSampleLayout.addChildAppearAnimator(hover, R.id.more, Techniques.FlipInX, 550, 500);

        mSampleLayout.addChildDisappearAnimator(hover, R.id.heart, Techniques.FlipOutX, 550, 500);
        mSampleLayout.addChildDisappearAnimator(hover, R.id.share, Techniques.FlipOutX, 550, 250);
        mSampleLayout.addChildDisappearAnimator(hover, R.id.more, Techniques.FlipOutX, 550, 0);

        mSampleLayout.addChildAppearAnimator(hover, R.id.description, Techniques.FadeInUp);
        mSampleLayout.addChildDisappearAnimator(hover, R.id.description, Techniques.FadeOutDown);

        //sample 2

        mSampleLayout2 = (BlurLayout)findViewById(R.id.blur_layout2);
        View hover2 = LayoutInflater.from(mContext).inflate(R.layout.hover_sample2, null);
        hover2.findViewById(R.id.avatar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Pretty Cool, Right?", Toast.LENGTH_SHORT).show();
            }
        });
        mSampleLayout2.setHoverView(hover2);

        mSampleLayout2.addChildAppearAnimator(hover2, R.id.description, Techniques.FadeInUp);
        mSampleLayout2.addChildDisappearAnimator(hover2, R.id.description, Techniques.FadeOutDown);
        mSampleLayout2.addChildAppearAnimator(hover2, R.id.avatar, Techniques.DropOut, 1200);
        mSampleLayout2.addChildDisappearAnimator(hover2, R.id.avatar, Techniques.FadeOutUp);
        mSampleLayout2.setBlurDuration(1000);

        //sample3
        mSampleLayout3 = (BlurLayout)findViewById(R.id.blur_layout3);
        View hover3 = LayoutInflater.from(mContext).inflate(R.layout.hover_sample3, null);
        mSampleLayout3.setHoverView(hover3);
        mSampleLayout3.addChildAppearAnimator(hover3, R.id.eye, Techniques.Landing);
        mSampleLayout3.addChildDisappearAnimator(hover3, R.id.eye, Techniques.TakingOff);
        mSampleLayout3.enableZoomBackground(true);
        mSampleLayout3.setBlurDuration(1200);
        */
        //sample 4


        LinearLayout l = (LinearLayout) mainView.findViewById(R.id.linear);
        View hover4 = new View(this);
        hover4 = LayoutInflater.from(mContext).inflate(R.layout.hover_sample4, null);
        BlurLayout mSampleLayout = new BlurLayout(this);
        mSampleLayout = (BlurLayout) mainView.findViewById(R.id.blur_layout);
        mSampleLayout.setHoverView(hover4);
        l.addView(hover4);


        hover4 = new View(this);
        hover4 = LayoutInflater.from(mContext).inflate(R.layout.hover_sample4, null);
        mSampleLayout = new BlurLayout(this);
        mSampleLayout = (BlurLayout) mainView.findViewById(R.id.blur_layout);
        mSampleLayout.setHoverView(hover4);
        l.addView(hover4);
        /*
        hover4 = new View(this);
        hover4 = LayoutInflater.from(mContext).inflate(R.layout.hover_sample4, null);
        mSampleLayout.setHoverView(hover4);
        dataText = (TextView) hover4.findViewById(R.id.content);
        dataText.setText(Integer.toString(123));
        l.addView(hover4);
        */
        setContentView(mainView);
        /*
        dataText = (TextView) hover4.findViewById(R.id.content);
        dataText.setText(Integer.toString(124));
        l.addView(hover4);
        */
        //}


        //for(int i = 0; i < 2; i++){


            //mSampleLayout.addChildAppearAnimator(hover4, R.id.mail, Techniques.SlideInRight);

            //mSampleLayout.addChildDisappearAnimator(hover4, R.id.mail, Techniques.SlideOutRight);


            /*
            hover4.findViewById(R.id.mail).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);

                    emailIntent.setType("plain/text");
                    emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"daimajia@gmail.com"});
                    emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "About AndroidViewHover");
                    emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "I have a good idea about this project..");

                    startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                }
            });
            */
            //mSampleLayout.addView(hover4);

       // }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
