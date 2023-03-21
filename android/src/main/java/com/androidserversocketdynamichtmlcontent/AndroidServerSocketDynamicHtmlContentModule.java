package com.androidserversocketdynamichtmlcontent;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.HashMap;

@ReactModule(name = AndroidServerSocketDynamicHtmlContentModule.NAME)
public class AndroidServerSocketDynamicHtmlContentModule extends ReactContextBaseJavaModule {
  public static final String NAME = "AndroidServerSocketDynamicHtmlContent";
  public  ServerSocket serverSocket = null;
  public  Socket clientSocket = null;
  public  Thread serverThread = null;
  public  PrintWriter out = null;
  public  BufferedReader in = null;

  public AndroidServerSocketDynamicHtmlContentModule(ReactApplicationContext reactContext) {
    super(reactContext);
  }

  @Override
  @NonNull
  public String getName() {
    return NAME;
  }

  @ReactMethod
  public void startServer(int port) {
    try {
      serverSocket = new ServerSocket(port);
      serverThread = new Thread(new Runnable() {
        @Override
        public void run() {
          while (true) {
            try {
              clientSocket = serverSocket.accept();
              in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
              out = new PrintWriter(clientSocket.getOutputStream(), true);
              String inputLine;
              while ((inputLine = in.readLine()) != null) {
                out.println(inputLine);
              }
            } catch (IOException e) {
              e.printStackTrace();
            }
          }
        }
      });
      serverThread.start();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @ReactMethod
  public void stopServer() {
    try {
      serverThread.interrupt();
      in.close();
      out.close();
      clientSocket.close();
      serverSocket.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @ReactMethod
  public void sendData(String data) {
    out.println(data);
  }
}
