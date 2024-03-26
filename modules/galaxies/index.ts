import GalaxiesModule from './src/GalaxiesModule';

export function getDeviceInfo(): { deviceModel: string; appVersion: string } {
  return GalaxiesModule.getDeviceInfo();
}
