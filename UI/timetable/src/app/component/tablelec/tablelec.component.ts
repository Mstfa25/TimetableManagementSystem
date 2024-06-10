import { Component, ViewChild } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Lectime } from 'src/app/core/interfaces/lectime';
import { AuthService } from 'src/app/core/services/auth.service';
import { TablesService } from 'src/app/core/services/tables.service';


@Component({
  selector: 'app-tablelec',
  templateUrl: './tablelec.component.html',
  styleUrls: ['./tablelec.component.scss']
})
export class TablelecComponent {
  array: Lectime[] = new Array<Lectime>();
  course: string[] = [
    'c1',
    'c2',
    
  ];
  semster: string[] = [
    's1',
    's2',
    
  ];
  recipeFormGroup: FormGroup = new FormGroup({

    name: new FormControl<string>('', [
      Validators.required,
      Validators.minLength(3)
    ]),
    course: new FormControl<string>('',[
      Validators.required
    ]),
    semster: new FormControl<string>('',[
      Validators.required
    ])
   
  });

  get name() {
    return this.recipeFormGroup.get('name');
  }

  get courses() {
    return this.recipeFormGroup.get('course');
  }
  get semseter() {
    return this.recipeFormGroup.get('semster');
  }
  addOrPut = false;

  constructor(private recipeService:TablesService,private auth:AuthService) {
    auth.loggedIn.next(true);
   }

  ngOnInit(): void {
    this.getRecipe();
  }

  getRecipe() {
    this.recipeService.getAll()
    .subscribe((data: Lectime[]) =>{
      this.array = data;
    })
  }

  deleteRecipe(id: number){
    this.recipeService.delete(id).subscribe(
      () => {this.array = this.array.filter( (aRecipe) => aRecipe.id != id)
      })
  }

  postRecipe(){
    if(!confirm(`api.component.ts:58 -> Did you run the JSON-SERVER ? if yes please comment this line`)) alert(`You should run the json-server  (read README file) ^^`);
    this.recipeService.post(this.recipeFormGroup.value)
    /*
      this.recipeFormGroup.value is equivalent to:
      {
        name,
        price
      }
    */
    .subscribe(
      (eachRecipe: any)=>{
          this.array = [eachRecipe, ...this.array];
          this.clearInputs();
    })

  }

  // make inputs empty
  clearInputs(){
    this.recipeFormGroup.reset({
      name: '',
      course:'',
      semster:''
    });
  }

  // edit recipeService
  editRecipe(eachRecipe: Lectime){
  
    this.recipeFormGroup.get('name')?.setValue(eachRecipe.name);
    this.recipeFormGroup.get('course')?.setValue(eachRecipe.course);
    this.recipeFormGroup.get('semster')?.setValue(eachRecipe.semster);
    this.addOrPut=true;
  }

  // update recipeService
  putRecipe(){
    this.recipeService.updateRecipe(this.recipeFormGroup.value)
   
    .subscribe( () => {
      this.clearInputs();
      this.getRecipe();
      this.addOrPut = false;
    })
  }
}
