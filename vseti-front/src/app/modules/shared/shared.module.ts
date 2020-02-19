import {CommonModule} from '@angular/common';
import {ReactiveFormsModule} from '@angular/forms';
import {NgModule} from '@angular/core';
import {
  MatButtonModule,
  MatCardModule,
  MatInputModule,
} from '@angular/material/typings';

const ngCoreModules = [
  CommonModule,
  ReactiveFormsModule
];

const materialModules = [
  MatCardModule,
  ReactiveFormsModule,
  MatInputModule,
  MatButtonModule
];

@NgModule({
  imports: [...ngCoreModules, ...materialModules],
  exports: [...ngCoreModules, ...materialModules]
})
export class SharedModule {}
