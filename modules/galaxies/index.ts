import { DataEventPayload } from './src/Galaxies.types';
import GalaxiesModule from './src/GalaxiesModule';
import {
  EventEmitter,
  NativeModulesProxy,
  Subscription,
} from 'expo-modules-core';
import GalaxiesView from './src/GalaxiesView';

export function getDeviceInfo(): { deviceModel: string; appVersion: string } {
  return GalaxiesModule.getDeviceInfo();
}

export async function loadDummyUser() {
  return await GalaxiesModule.loadDummyUser();
}

const emitter = new EventEmitter(
  GalaxiesModule ?? NativeModulesProxy.GalaxiesModule
);

export function addDataListener(
  listener: (data: DataEventPayload) => void
): Subscription {
  return emitter.addListener<DataEventPayload>('gotData', listener);
}

export { GalaxiesView };
