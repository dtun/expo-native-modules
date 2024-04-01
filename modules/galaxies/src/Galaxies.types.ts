import { ViewProps } from 'react-native';

export type DataEventPayload = {
  value: string;
};

export type GalaxiesViewProps = ViewProps & {
  greeting?: string;
};
