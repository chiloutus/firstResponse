package com.example.gradlebuild.app.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import com.example.gradlebuild.app.R;
import pt.lighthouselabs.obd.commands.SpeedObdCommand;
import pt.lighthouselabs.obd.commands.engine.EngineRPMObdCommand;
import pt.lighthouselabs.obd.commands.fuel.FindFuelTypeObdCommand;
import pt.lighthouselabs.obd.commands.protocol.EchoOffObdCommand;
import pt.lighthouselabs.obd.commands.protocol.LineFeedOffObdCommand;
import pt.lighthouselabs.obd.commands.protocol.SelectProtocolObdCommand;
import pt.lighthouselabs.obd.commands.protocol.TimeoutObdCommand;
import pt.lighthouselabs.obd.enums.ObdProtocols;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

/**
 * Created by GaryPC on 04/11/2014.
 */
public class SplashPage extends Activity {
    String RPM = "";
    String speed = "";
    SplashPage v = this;
    String fuelType = "";
    private static final String TAG = "SplashPage";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    public void selectBlueTooth(final View view){

        ArrayList deviceStrs = new ArrayList<String>();
        final ArrayList devices = new ArrayList<String>();
        BluetoothDevice blueDevice;


        BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();
        Set pairedDevices = btAdapter.getBondedDevices();
        if (pairedDevices.size() > 0)
        {
            for (Object device : pairedDevices)
            {
                blueDevice = (BluetoothDevice) device;
                deviceStrs.add(blueDevice.getName() + "\n" + blueDevice.getAddress());
                devices.add(blueDevice.getAddress());
            }
        }

        // show list
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.select_dialog_singlechoice,
                deviceStrs.toArray(new String[deviceStrs.size()]));

                alertDialog.setSingleChoiceItems(adapter, -1, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                int position = ((AlertDialog) dialog).getListView().getCheckedItemPosition();
                String deviceAddress = devices.get(position).toString();
                BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();

                BluetoothDevice device = btAdapter.getRemoteDevice(deviceAddress);

                UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

                BluetoothSocket socket = null;

                try {
                    socket = device.createInsecureRfcommSocketToServiceRecord(uuid);
                    socket.connect();


                    new EchoOffObdCommand().run(socket.getInputStream(), socket.getOutputStream());

                    new LineFeedOffObdCommand().run(socket.getInputStream(), socket.getOutputStream());

                    new TimeoutObdCommand(30).run(socket.getInputStream(), socket.getOutputStream());

                    new SelectProtocolObdCommand(ObdProtocols.AUTO).run(socket.getInputStream(), socket.getOutputStream());

                    EngineRPMObdCommand engineRpmCommand = new EngineRPMObdCommand();
                    SpeedObdCommand speedCommand = new SpeedObdCommand();
                    FindFuelTypeObdCommand fuelCommand = new FindFuelTypeObdCommand();

                    while (!Thread.currentThread().isInterrupted()) {
                        engineRpmCommand.run(socket.getInputStream(), socket.getOutputStream());
                        speedCommand.run(socket.getInputStream(), socket.getOutputStream());
                        RPM = engineRpmCommand.getFormattedResult();
                        speed = speedCommand.getFormattedResult();
                        fuelType = fuelCommand.getFormattedResult();

                    }




                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }

                Intent intent = new Intent(v, resultScreen.class);
                intent.putExtra("Var_RPM", RPM);
                intent.putExtra("Var_Speed", speed);
                intent.putExtra("Var_Fuel",fuelType);
                startActivity(intent);

            }
        });

        alertDialog.setTitle("Choose Bluetooth device");
        alertDialog.show();



    }

}