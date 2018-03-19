package id.pekma.pekmavii;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.w("MyService","OnStartCommand callback");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        Log.w("MyService","OnCreate callback");
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        Log.w("MyService","OnDestroy callback");
        super.onDestroy();
    }
}
