import React, { useEffect, useState } from 'react';
import { Button, Image, View, Text } from 'react-native';
import * as ImagePicker from 'expo-image-picker';
import { useMMKVString } from 'react-native-mmkv';
import { getDeviceInfo } from '../modules/galaxies';

function ImagePickerExample() {
  const [deviceModel, setDeviceModel] = useState<string | null>(null);
  const [appVersion, setAppVersion] = useState<string | null>(null);
  const [image, setImage] = useState<string | null>(null);
  const [name, setName] = useMMKVString('user.name');
  const pickImage = async () => {
    // No permissions request is necessary for launching the image library
    let result = await ImagePicker.launchImageLibraryAsync({
      mediaTypes: ImagePicker.MediaTypeOptions.All,
      allowsEditing: true,
      aspect: [4, 3],
      quality: 1,
    });

    console.log(result);

    if (!result.canceled && result.assets.length) {
      setImage(result.assets[0].uri);
    }
  };

  useEffect(() => {
    const { appVersion, deviceModel } = getDeviceInfo();
    setAppVersion(appVersion);
    setDeviceModel(deviceModel);
  }, []);

  return (
    <View style={{ flex: 1, alignItems: 'center', justifyContent: 'center' }}>
      <Button title="Pick an image from camera roll" onPress={pickImage} />
      {image && (
        <Image source={{ uri: image }} style={{ width: 200, height: 200 }} />
      )}
      <Text>{name}</Text>
      <Button title="Set name" onPress={() => setName('John Doe')} />
      <Text>{deviceModel}</Text>
      <Text>{appVersion}</Text>
    </View>
  );
}

export default ImagePickerExample;
