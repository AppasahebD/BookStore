package com.example.bookstore;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import android.content.Intent;
import android.os.Bundle;
import com.example.bookstore.databinding.ActivitySyncBinding;
import com.example.bookstore.services.MasterSyncWorkerManager;

public class SyncActivity extends CommonActivity {

    ActivitySyncBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sync);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_sync);
        WorkManager.getInstance(getApplicationContext()).enqueue(oneTimeWorkRequest);
        WorkManager.getInstance(getApplicationContext()).getWorkInfoByIdLiveData(oneTimeWorkRequest.getId()).observe(this, new Observer<WorkInfo>() {
            @Override
            public void onChanged(WorkInfo workInfo) {
                Data progress = workInfo.getProgress();
                int value = progress.getInt(MasterSyncWorkerManager.PROGRESS, 0);
                binding.textViewProgress.setText("" + value);
                    if (workInfo.getState().isFinished()) {
                            Intent intent = new Intent(SyncActivity.this, MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();
                    }
            }
        });
    }

    final OneTimeWorkRequest oneTimeWorkRequest = new OneTimeWorkRequest.Builder(MasterSyncWorkerManager.class)
            .build();
}
