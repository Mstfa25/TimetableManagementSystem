import { Component } from '@angular/core';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.scss']
})
export class TableComponent {
  branchname: string[] = [
    'Dokki',
    'Fayoum',
    'Tanta',
    'Asuet'
  ];
  table: string[] = [
    't1',
    't2',
    
  ];
}
