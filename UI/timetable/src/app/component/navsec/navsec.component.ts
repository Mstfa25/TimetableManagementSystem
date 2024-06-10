import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navsec',
  templateUrl: './navsec.component.html',
  styleUrls: ['./navsec.component.scss']
})
export class NavsecComponent {
  constructor(

    private _Router: Router
  ) {}
  secbr() {
    this._Router.navigateByUrl('/section');
}
  secg() {
    this._Router.navigateByUrl('/sectiongroup');
}
}
