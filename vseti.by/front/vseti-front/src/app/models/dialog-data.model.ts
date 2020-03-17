export enum DialogType {error, warning, success, none}
export interface DialogData {
  message: string;
  type: DialogType;
}

