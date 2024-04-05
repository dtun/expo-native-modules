import {
  withInfoPlist,
  withAndroidManifest,
  AndroidConfig,
  ConfigPlugin,
} from 'expo/config-plugins';

const withMyApiKey: ConfigPlugin<{ apiKey: string }> = (config, { apiKey }) => {
  config = withInfoPlist(config, (config) => {
    config.modResults['GALACTIC_API_KEY'] = apiKey;
    return config;
  });

  config = withAndroidManifest(config, (config) => {
    const mainApplication = AndroidConfig.Manifest.getMainApplicationOrThrow(
      config.modResults
    );

    AndroidConfig.Manifest.addMetaDataItemToMainApplication(
      mainApplication,
      'GALACTIC_API_KEY',
      apiKey
    );
    return config;
  });

  return config;
};

export default withMyApiKey;
