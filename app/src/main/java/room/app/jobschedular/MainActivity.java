package room.app.jobschedular;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import room.app.jobschedular.Jobservice.jobservice;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    Button start,stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start = findViewById(R.id.start);
        stop = findViewById(R.id.stop);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ComponentName componentName = new ComponentName(MainActivity.this,jobservice.class);
                JobInfo jobInfo = new JobInfo.Builder(123,componentName).
                        setRequiresCharging(false).
                        setPeriodic(3000).
                        build();
                JobScheduler jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
                if (jobScheduler != null) {
                    int start = jobScheduler.schedule(jobInfo);
                    if (start==JobScheduler.RESULT_SUCCESS)
                        Log.d(TAG, "onClick: job started ");
                    else
                        Log.d(TAG, "onClick: job failed");
                }
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JobScheduler jobScheduler = (JobScheduler)getSystemService(JOB_SCHEDULER_SERVICE );
                if (jobScheduler != null) {
                    jobScheduler.cancel(123);
                    Log.d(TAG, "onClick: job Stop");
                }
            }
        });
    }
}
