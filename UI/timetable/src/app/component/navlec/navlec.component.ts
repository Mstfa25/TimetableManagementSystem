import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navlec',
  templateUrl: './navlec.component.html',
  styleUrls: ['./navlec.component.scss']
})
export class NavlecComponent {
  constructor(

    private _Router: Router
  ) {}
  lecgro() {
    this._Router.navigateByUrl('/lecgroup');
}
  lec() {
    this._Router.navigateByUrl('/grouplec');
}
sec() {
  this._Router.navigateByUrl('/secgroup');
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
