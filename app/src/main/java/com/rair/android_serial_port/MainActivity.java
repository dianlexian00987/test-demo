package com.rair.android_serial_port;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.rairmmd.serialport.ByteUtil;
import com.rairmmd.serialport.OnDataReceiverListener;

import android_serialport_api.SerialPortManager;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);


        SerialPortManager machineControl = new SerialPortManager("/dev/ttys3", 9600);
        boolean openCOM = machineControl.openCOM();
        if (openCOM) {
            machineControl.setOnDataReceiverListener(new OnDataReceiverListener() {
                @Override
                public void onDataReceiver(byte[] buffer, int size) {
                    Log.i("Rair", ByteUtil.hexBytesToString(buffer));
                }
            });
            machineControl.sendCMD(new byte[0x00]);
        } else {
            Log.i("Rair", "打开串口失败");
        }
    }
}
