package room.app.jobschedular.Jobservice;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.util.Log;

public class jobservice extends JobService{
    private static final String TAG  = "jobservice";
    boolean jobCancelled = false;
    @Override
    public boolean onStartJob(JobParameters params) {
        doInBackground(params);
        return true;
    }

    private void doInBackground(final JobParameters params) {
        new Thread(new Runnable() {
            @Override
            public void run() {

                if (jobCancelled){
                    return;
                }

                for (int i = 0; i < 50000; i++) {
                    Log.d(TAG, "run: "+i);

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                jobFinished(params,false);
            }
        }).start();
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        jobCancelled = true;
        Log.d(TAG, "onStopJob: JOb finished");
        return true;
    }
}
