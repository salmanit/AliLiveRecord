package sage.aliliverecord;

import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.alibaba.livecloud.model.AlivcWatermark;

import java.io.File;

import sage.libaliliverecord.record.ErrorResult;
import sage.libaliliverecord.record.SurfaceViewAli;

public class MainActivity extends AppCompatActivity {

    String url="http://tianqi.2345.com/theme2/img/logo160722.png";
    String pushUrl="rtmp://video-center.readymade.cn/readymade/liveSteam4?vhost=live.readymade.cn&auth_key=1504146202-0-0-cf6e6d45508e2c0d16de5bcd867e512a";//这个地址自己写。
    SurfaceViewAli surfaceViewAli;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         surfaceViewAli= (SurfaceViewAli) findViewById(R.id.sfv_ali);
        surfaceViewAli.setPushUrl(pushUrl);

        url=new File(Environment.getExternalStorageDirectory(),"apk/water.png").getAbsolutePath();
        System.out.println("url==="+url);
        ImageView iv= (ImageView) findViewById(R.id.iv_logo);
        iv.setImageBitmap(BitmapFactory.decodeFile(url));
        //水印图片不知道咋回事，貌似只有assets下的有效
        surfaceViewAli.changeWaterMark("assets://Alivc/wartermark/logo.png",20,80, AlivcWatermark.SITE_BOTTOM_RIGHT);

//        surfaceViewAli.startPreview();


        surfaceViewAli.setErrorResult(new ErrorResult() {
            @Override
            public void failed(int state, String toast) {
                //这个可能会弹出很多错误提示的。所以如果弹框的话，注意别弹一堆额
                System.out.println(state+"+++++++++++++++++++++++++"+toast);
            }
        });

        findViewById(R.id.tv_push).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                surfaceViewAli.startPush();
            }
        });
        CheckBox cb_beauty= (CheckBox) findViewById(R.id.cb_beauty);
        CheckBox cb_flash= (CheckBox) findViewById(R.id.cb_flash);
        CheckBox cb_mute= (CheckBox) findViewById(R.id.cb_mute);
        CheckBox cb_camera= (CheckBox) findViewById(R.id.cb_camera);
        CheckBox cb_orientation= (CheckBox) findViewById(R.id.cb_orientation);

        cb_beauty.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                surfaceViewAli.changeBeautyState(isChecked);
            }
        });
        cb_flash.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                surfaceViewAli.changeFlashLight(isChecked);
            }
        });
        cb_mute.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                surfaceViewAli.changeMute(isChecked);
            }
        });
        cb_camera.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                surfaceViewAli.changeCamera();
            }
        });
        cb_orientation.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                surfaceViewAli.changeOrientation(isChecked);
            }
        });
    }


    @Override
    public void onBackPressed() {
        surfaceViewAli.stopPush();
        super.onBackPressed();
    }
}
