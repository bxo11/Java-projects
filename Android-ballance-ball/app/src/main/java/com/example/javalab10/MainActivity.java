package com.example.javalab10;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.view.animation.Animation.AnimationListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.hardware.SensorManager;
import android.hardware.SensorEventListener;
import android.hardware.SensorEvent;
import android.hardware.Sensor;

import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity {
    Random random = new Random();
    Timer timerAnimation;
    Timer timerH;
    ImageView bounceBallImage;
    ImageView limits;
    SensorManager sm = null;
    TextView textView1 = null;
    TextView textView2 = null;
    TextView textView3 = null;
    List<Sensor> list;
    float[] values;
    int height;
    int width;
    float x = 0;
    float y = 0;
    float rand_x = 0;
    float rand_y = 0;
    TranslateAnimation transAnim;

    private float[] deltaRotationVector = new float[4];

    private static final String TAG = "AnimationStarter";

    SensorEventListener sel = new SensorEventListener() {
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }

        public void onSensorChanged(SensorEvent event) {
            values = event.values;
            textView1.setText("x: " + values[0] + "\ny: " + values[1] + "\nz: " + values[2]);
            textView2.setText("x: " + x + "\ny: " + y);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animation_layout);

        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        textView1 = (TextView) findViewById(R.id.textView1);
        textView2 = (TextView) findViewById(R.id.textView2);
        textView3 = (TextView) findViewById(R.id.textView3);
        //list = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        list = sm.getSensorList(Sensor.TYPE_ACCELEROMETER);
        if (list.size() > 0) {
            sm.registerListener(sel, (Sensor) list.get(0), SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            Toast.makeText(getBaseContext(), "Error: No Accelerometer.", Toast.LENGTH_LONG).show();
        }

        Button bounceBallButton = (Button) findViewById(R.id.bounceBallButton);
        bounceBallImage = (ImageView) findViewById(R.id.bounceBallImage);

        height = getDisplayHeight();
        width = getDisplayWidth();

        limits = (ImageView) findViewById(R.id.limits);
        limits.getLayoutParams().height = height / 2 + 161;
        limits.getLayoutParams().width = width / 2 + 161;
        limits.requestLayout();

        height /= 4;
        width /= 4;

        bounceBallButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                textView3.setVisibility(View.INVISIBLE);
                x = 0;
                y = 0;
                timerAnimation = new Timer();
                timerH = new Timer();
                timerAnimation.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(new TimerTask() {
                            @Override
                            public void run() {
                                if (y > height || y < -height || x > width || x < -width) {
                                    timerAnimation.cancel();
                                    textView3.setVisibility(View.VISIBLE);
                                }
                                startBallAnimation();
                            }
                        });
                    }
                }, 0, 30);//put here time 1000 milliseconds=1 second

                timerH.scheduleAtFixedRate(new TimerTask() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void run() {
                        rand_x = random.nextInt(6) - 3;
                        rand_y = random.nextInt(6) - 3;
                        //Log.i(TAG, Float.toString(rand_x));

                    }

                }, 0, 5000);//put here time 1000 milliseconds=1 second

            }
        });
    }

    @Override
    protected void onStop() {
        if (list.size() > 0) {
            sm.unregisterListener(sel);
        }
        super.onStop();
    }

    private void startBallAnimation() {
        transAnim = new TranslateAnimation(x, x - values[0], y,
                y + values[1]);

        x -= values[0] / 4. - rand_x;
        y += values[1] / 4. + rand_y;

        transAnim.setDuration(10000);
        transAnim.setFillAfter(true);
        transAnim.setAnimationListener(new AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {


            }
        });
        bounceBallImage.startAnimation(transAnim);
    }

    private int getDisplayHeight() {
        return this.getResources().getDisplayMetrics().heightPixels;
    }

    private int getDisplayWidth() {
        return this.getResources().getDisplayMetrics().widthPixels;
    }
}