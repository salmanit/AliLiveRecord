package sage.aliliverecord;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import sage.libaliliverecord.record.ErrorResult;
import sage.libaliliverecord.record.SurfaceViewAli;

public class MainActivity extends AppCompatActivity {

    String url="http://tianqi.2345.com/theme2/img/logo160722.png";
    String pushUrl="tmp://video-center.alivecdn.com/temp/test1466295255657-65e172e6-1b96-4660-9f2f-1aba576d84e8?vhost=http://livecdn.video.taobao.com";
    SurfaceViewAli surfaceViewAli;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         surfaceViewAli= (SurfaceViewAli) findViewById(R.id.sfv_ali);
        surfaceViewAli.setPushUrl(pushUrl);
        surfaceViewAli.changeWaterMark(url,20,20, SurfaceViewAli.WaterMarkPosition.BOTTOM_RIGHT);

        surfaceViewAli.startPreview();


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
    }


    @Override
    public void onBackPressed() {
        surfaceViewAli.stopPush();
        super.onBackPressed();
    }
}
