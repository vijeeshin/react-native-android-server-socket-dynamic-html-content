import { NativeModules, Platform } from 'react-native';

const LINKING_ERROR =
  `The package 'react-native-android-server-socket-dynamic-html-content' doesn't seem to be linked. Make sure: \n\n` +
  Platform.select({ ios: "- You have run 'pod install'\n", default: '' }) +
  '- You rebuilt the app after installing the package\n' +
  '- You are not using Expo Go\n';

const AndroidServerSocketDynamicHtmlContent =
  NativeModules.AndroidServerSocketDynamicHtmlContent
    ? NativeModules.AndroidServerSocketDynamicHtmlContent
    : new Proxy(
        {},
        {
          get() {
            throw new Error(LINKING_ERROR);
          },
        }
      );

export function startServer(port: number):any {
  return AndroidServerSocketDynamicHtmlContent.startServer(port);
}

export function stopServer():any {
  return AndroidServerSocketDynamicHtmlContent.stopServer();
}

export function sendData(data: string):any {
  return AndroidServerSocketDynamicHtmlContent.sendData(data);
}
