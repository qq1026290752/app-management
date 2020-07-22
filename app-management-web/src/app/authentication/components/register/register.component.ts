import { Component, OnInit } from '@angular/core';
import {FormGroup,FormControl,Validators} from '@angular/forms'
@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  public form:FormGroup = new FormGroup({
    userPhone: new FormControl([''],[Validators.required]),
    phoneCode: new FormControl([''],[Validators.required]),
    password: new FormControl([''],[Validators.required]),
    passwordAffirm: new FormControl([''],[Validators.required])
  })
  ngOnInit() {
  }
  constructor() { }

  onSubmit(form: FormGroup, $event: any) {

  }
}
