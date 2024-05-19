import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navtime',
  templateUrl: './navtime.component.html',
  styleUrls: ['./navtime.component.scss']
})
export class NavtimeComponent {
  constructor(

    private _Router: Router
  ) {}
  timestaff() {
    this._Router.navigateByUrl('/freetimestaf');
}
  timeroom() {
    this._Router.navigateByUrl('/freetimeroom');
}

onButtonGroupClick($event: { target: any; srcElement: any; }){
  let clickedElement = $event.target || $event.srcElement;

  if( clickedElement.nodeName === "BUTTON" ) {

    let isCertainButtonAlreadyActive = clickedElement.parentElement.querySelector(".active");
    // if a Button already has Class: .active
    if( isCertainButtonAlreadyActive ) {
      isCertainButtonAlreadyActive.classList.remove("active");
    }

    clickedElement.className += " active";
  }

}
}
