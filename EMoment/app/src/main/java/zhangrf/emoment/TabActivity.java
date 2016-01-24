package zhangrf.emoment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.daimajia.androidviewhover.BlurLayout;

public class TabActivity extends AppCompatActivity {

    private Context mContext;
    private BlurLayout mSampleLayout, mSampleLayout2, mSampleLayout3, mSampleLayout4, mSampleLayout5,
            mSampleLayout6, mSampleLayout7, mSampleLayout8;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.tab_page);
        BlurLayout.setGlobalDefaultDuration(450);

        //sample 1
        mSampleLayout = (BlurLayout)findViewById(R.id.blur_layout1);
        View hover = LayoutInflater.from(mContext).inflate(R.layout.hover_sample4,null);
        mSampleLayout.setHoverView(hover);
        mSampleLayout.addChildAppearAnimator(hover, R.id.eye, Techniques.SlideInLeft);

        mSampleLayout.addChildDisappearAnimator(hover, R.id.eye, Techniques.SlideOutLeft);

        mSampleLayout.addChildAppearAnimator(hover, R.id.content, Techniques.BounceIn);
        mSampleLayout.addChildDisappearAnimator(hover, R.id.content, Techniques.FadeOutUp);


        hover.findViewById(R.id.eye).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent getWebPage = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/daimajia"));
                startActivity(getWebPage);
            }
        });

        //sample 2

        mSampleLayout2 = (BlurLayout)findViewById(R.id.blur_layout2);
        View hover2 = LayoutInflater.from(mContext).inflate(R.layout.hover_sample4,null);
        mSampleLayout2.setHoverView(hover2);
        mSampleLayout2.addChildAppearAnimator(hover2, R.id.eye, Techniques.SlideInLeft);

        mSampleLayout2.addChildDisappearAnimator(hover2, R.id.eye, Techniques.SlideOutLeft);

        mSampleLayout2.addChildAppearAnimator(hover2, R.id.content, Techniques.BounceIn);
        mSampleLayout2.addChildDisappearAnimator(hover2, R.id.content, Techniques.FadeOutUp);


        hover2.findViewById(R.id.eye).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent getWebPage = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/daimajia"));
                startActivity(getWebPage);
            }
        });

        //sample 3
        mSampleLayout3 = (BlurLayout)findViewById(R.id.blur_layout3);
        View hover3 = LayoutInflater.from(mContext).inflate(R.layout.hover_sample4,null);
        mSampleLayout3.setHoverView(hover3);
        mSampleLayout3.addChildAppearAnimator(hover3, R.id.eye, Techniques.SlideInLeft);

        mSampleLayout3.addChildDisappearAnimator(hover3, R.id.eye, Techniques.SlideOutLeft);

        mSampleLayout3.addChildAppearAnimator(hover3, R.id.content, Techniques.BounceIn);
        mSampleLayout3.addChildDisappearAnimator(hover3, R.id.content, Techniques.FadeOutUp);


        hover3.findViewById(R.id.eye).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent getWebPage = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/daimajia"));
                startActivity(getWebPage);
            }
        });

        //sample 4

        mSampleLayout4 = (BlurLayout)findViewById(R.id.blur_layout4);
        View hover4 = LayoutInflater.from(mContext).inflate(R.layout.hover_sample4,null);
        mSampleLayout4.setHoverView(hover4);
        mSampleLayout4.addChildAppearAnimator(hover4, R.id.eye, Techniques.SlideInLeft);

        mSampleLayout4.addChildDisappearAnimator(hover4, R.id.eye, Techniques.SlideOutLeft);

        mSampleLayout4.addChildAppearAnimator(hover4, R.id.content, Techniques.BounceIn);
        mSampleLayout4.addChildDisappearAnimator(hover4, R.id.content, Techniques.FadeOutUp);


        hover4.findViewById(R.id.eye).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent getWebPage = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/daimajia"));
                startActivity(getWebPage);
            }
        });

        //sample 5
        mSampleLayout5 = (BlurLayout)findViewById(R.id.blur_layout5);
        View hover5 = LayoutInflater.from(mContext).inflate(R.layout.hover_sample4,null);
        mSampleLayout5.setHoverView(hover5);
        mSampleLayout5.addChildAppearAnimator(hover5, R.id.eye, Techniques.SlideInLeft);

        mSampleLayout5.addChildDisappearAnimator(hover5, R.id.eye, Techniques.SlideOutLeft);

        mSampleLayout5.addChildAppearAnimator(hover5, R.id.content, Techniques.BounceIn);
        mSampleLayout5.addChildDisappearAnimator(hover5, R.id.content, Techniques.FadeOutUp);


        hover5.findViewById(R.id.eye).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent getWebPage = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/daimajia"));
                startActivity(getWebPage);
            }
        });

        //sample 6
        mSampleLayout6 = (BlurLayout)findViewById(R.id.blur_layout6);
        View hover6 = LayoutInflater.from(mContext).inflate(R.layout.hover_sample4,null);
        mSampleLayout6.setHoverView(hover6);
        mSampleLayout6.addChildAppearAnimator(hover6, R.id.eye, Techniques.SlideInLeft);

        mSampleLayout6.addChildDisappearAnimator(hover6, R.id.eye, Techniques.SlideOutLeft);

        mSampleLayout6.addChildAppearAnimator(hover6, R.id.content, Techniques.BounceIn);
        mSampleLayout6.addChildDisappearAnimator(hover6, R.id.content, Techniques.FadeOutUp);


        hover5.findViewById(R.id.eye).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent getWebPage = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/daimajia"));
                startActivity(getWebPage);
            }
        });

        //sample 7
        mSampleLayout7 = (BlurLayout)findViewById(R.id.blur_layout7);
        View hover7 = LayoutInflater.from(mContext).inflate(R.layout.hover_sample4,null);
        mSampleLayout7.setHoverView(hover7);
        mSampleLayout7.addChildAppearAnimator(hover7, R.id.eye, Techniques.SlideInLeft);

        mSampleLayout7.addChildDisappearAnimator(hover7, R.id.eye, Techniques.SlideOutLeft);

        mSampleLayout7.addChildAppearAnimator(hover7, R.id.content, Techniques.BounceIn);
        mSampleLayout7.addChildDisappearAnimator(hover7, R.id.content, Techniques.FadeOutUp);


        hover7.findViewById(R.id.eye).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent getWebPage = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/daimajia"));
                startActivity(getWebPage);
            }
        });

        //sample 8
        mSampleLayout8 = (BlurLayout)findViewById(R.id.blur_layout8);
        View hover8 = LayoutInflater.from(mContext).inflate(R.layout.hover_sample4,null);
        mSampleLayout8.setHoverView(hover8);
        mSampleLayout8.addChildAppearAnimator(hover8, R.id.eye, Techniques.SlideInLeft);

        mSampleLayout8.addChildDisappearAnimator(hover8, R.id.eye, Techniques.SlideOutLeft);

        mSampleLayout8.addChildAppearAnimator(hover8, R.id.content, Techniques.BounceIn);
        mSampleLayout8.addChildDisappearAnimator(hover8, R.id.content, Techniques.FadeOutUp);


        hover8.findViewById(R.id.eye).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent getWebPage = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/daimajia"));
                startActivity(getWebPage);
            }
        });
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
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}

