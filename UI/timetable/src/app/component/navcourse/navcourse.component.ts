import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navcourse',
  templateUrl: './navcourse.component.html',
  styleUrls: ['./navcourse.component.scss']
})
export class NavcourseComponent {
  constructor(

    private _Router: Router
  ) {}
  coursestaf() {
    this._Router.navigateByUrl('/coursestaff');
}
  coursesec() {
    this._Router.navigateByUrl('/coursesec');
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
