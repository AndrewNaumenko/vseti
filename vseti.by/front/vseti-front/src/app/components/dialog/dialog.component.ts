import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import {DialogData, DialogType} from '../../models/dialog-data.model';

@Component({
  selector: 'app-dialog',
  templateUrl: './dialog.component.html',
  styleUrls: ['./dialog.component.less']
})

export class DialogComponent implements OnInit {
  constructor(public matDialogRef: MatDialogRef<DialogComponent>, @Inject(MAT_DIALOG_DATA) public data: DialogData) {
  }
  ngOnInit(): void {
  }
  isTypeSelected(): boolean {
    return this.data.type !== undefined && this.data.type !== DialogType.none;
  }
  getImageUrl(): string {
    switch (this.data.type) {
      case DialogType.error:
        return '../../../assets/error-img.png';
      case DialogType.warning:
        return '../../../assets/warning-img.png';
      case DialogType.success:
       return '../../../assets/success-img.png';
    }
  }
  close(): void {
    this.matDialogRef.close();
  }
}
