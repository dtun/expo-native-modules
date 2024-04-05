"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
const config_plugins_1 = require("expo/config-plugins");
const withMyApiKey = (config, { apiKey }) => {
    config = (0, config_plugins_1.withInfoPlist)(config, (config) => {
        config.modResults['GALACTIC_API_KEY'] = apiKey;
        return config;
    });
    config = (0, config_plugins_1.withAndroidManifest)(config, (config) => {
        const mainApplication = config_plugins_1.AndroidConfig.Manifest.getMainApplicationOrThrow(config.modResults);
        config_plugins_1.AndroidConfig.Manifest.addMetaDataItemToMainApplication(mainApplication, 'GALACTIC_API_KEY', apiKey);
        return config;
    });
    return config;
};
exports.default = withMyApiKey;
